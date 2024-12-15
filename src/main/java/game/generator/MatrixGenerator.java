package game.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import game.config.GameConfig;
import game.config.symbol.Symbol;

public class MatrixGenerator {

    public String[][] generateMatrix(GameConfig config) {
        Integer rows = config.getRows();
        Integer columns = config.getColumns();

        //I add this field to limit amount of bonus symbols. Its optional
        Integer bonusLimit = config.getLimitedBonusSymbols();
        int bonusCounter = 0;

        Map<String, Integer> bonusSymbols = config.getProbabilities().getBonusSymbols() != null
                ? config.getProbabilities().getBonusSymbols().getSymbols()
                : new HashMap<>();
        String[][] matrix = new String[rows][columns];

        
        for (int row = 0; row < config.getRows(); row++) {
            for (int col = 0; col < config.getColumns(); col++) {
                final int currentRow = row;
                final int currentCol = col;

                try {
                    Map<String, Integer> listOfSymbolsIndex = config.getProbabilities()
                            .getStandardSymbols()
                            .stream()
                            .filter(e -> e.getRow() == currentRow && e.getColumn() == currentCol)
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException(
                                    "No symbols probabilities for row " + currentRow + " and col " + currentCol))
                            .getSymbols();

                    // merge bonus and standard symbols
                    Map<String, Integer> combinedSymbols = new HashMap<>(listOfSymbolsIndex);
                    if (!bonusSymbols.isEmpty() && bonusCounter < bonusLimit) {
                        for (Map.Entry<String, Integer> entry : bonusSymbols.entrySet()) {
                            combinedSymbols.merge(entry.getKey(), entry.getValue(), Integer::sum);
                        }
                    }

                    
                    int totalSum = combinedSymbols.values().stream()
                            .mapToInt(Integer::intValue)
                            .sum();

                    double r = Math.random();
                    double cumulative = 0.0;
                    //adding symbols depending on probabilities
                    for (Map.Entry<String, Integer> entry : combinedSymbols.entrySet()) {
                        cumulative += entry.getValue() / (double) totalSum;

                        if (r <= cumulative) {
                            String selectedSymbol = entry.getKey();
                            
                            if (bonusSymbols.containsKey(selectedSymbol)) {
                                if (bonusCounter < bonusLimit) {//updating bonusCounter
                                    bonusCounter++;
                                    matrix[row][col] = selectedSymbol;
                                } else {
                                    //get a random standard symbol
                                    matrix[row][col] = getStandardSymbol(listOfSymbolsIndex);
                                }
                            } else {
                                matrix[row][col] = selectedSymbol;
                            }

                            break;
                        }
                    }
                } catch (IllegalArgumentException e) {
                    // If there's no probabilites setted for the index i add any symbol
                    Map<String, Symbol> allSymbols = config.getSymbols();
                    matrix[row][col] = getAnySymbol(allSymbols);
                }
            }
        }
//matrix printed
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + "\t");
            }
            System.out.println();
        }

        return matrix;
    }

    private String getAnySymbol(Map<String, Symbol> allSymbols) {
        Random random = new Random();
        List<Map.Entry<String, Symbol>> entries = new ArrayList<>(allSymbols.entrySet());
        Map.Entry<String, Symbol> randomEntry = entries.get(random.nextInt(entries.size()));
        return randomEntry.getKey();
    }

    private String getStandardSymbol(Map<String, Integer> standardSymbols) {
        Random random = new Random();
        List<String> keys = new ArrayList<>(standardSymbols.keySet());
        return keys.get(random.nextInt(keys.size()));
    }
}

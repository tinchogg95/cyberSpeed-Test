package game.winCombination.checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.config.GameConfig;
import game.winCombination.WinCombination;

public class WinningCombinationChecker {
	public Map<String, List<String>> checkWinningCombinations(String[][] matrix, GameConfig config) {
		Map<String, WinCombination> winCombinations = config.getWin_combinations();
	
		Map<String, List<String>> symbolCounts = countSymbolsAndTrackWinningCombinations(matrix, winCombinations);

		Map<String, String> otherWinCombinations = checkWinCombinations(matrix, winCombinations);
		

		return mergeCombinations(symbolCounts, otherWinCombinations,config);

	}

	private Map<String, List<String>> mergeCombinations(Map<String, List<String>> symbolCounts, Map<String, String> otherWinCombinations, GameConfig config) {
	    Map<String, List<String>> appliedWinningCombinations = new HashMap<>();	    
	    // scrolling symbolCounts map (symbol and its occurrence count)
	    for (Map.Entry<String, List<String>> entry : symbolCounts.entrySet()) {
	        String symbol = entry.getKey();
	        //I take only standard symbols to check if its a winning combination 
	        if (!config.isBonusSymbol(symbol)) {
	        	  List<String> winningCombinations = entry.getValue();

	  	        if (otherWinCombinations.containsKey(symbol)) {
	  	        	
	  	            List<String> allWinningCombinations = new ArrayList<>(winningCombinations);

	  	            // adding other winning combination
	  	            String verticalCombination = otherWinCombinations.get(symbol);
	  	            
	  	            if (verticalCombination != null && !allWinningCombinations.contains(verticalCombination)) {
	  	                allWinningCombinations.add(verticalCombination);
	  	            }
	  	            //putting only every winning combinations
	  	            appliedWinningCombinations.put(symbol, allWinningCombinations);
	  	        } else {
	  	        	//putting only count winning combinations
	  	            appliedWinningCombinations.put(symbol, winningCombinations);
	  	        }
	  	    }

	        }
	      
	    return appliedWinningCombinations;
	}

	
	public static Map<String, List<String>> countSymbolsAndTrackWinningCombinations(
		    String[][] matrix,
		    Map<String, WinCombination> winCombinations
		) {
		    Map<String, Integer> symbolCounts = new HashMap<>();
		   
		    Map<String, List<String>> winningSymbolsWithCombinations = new HashMap<>();
		    
		    for (String[] row : matrix) {
		        for (String symbol : row) {
		            symbolCounts.put(symbol, symbolCounts.getOrDefault(symbol, 0) + 1);
		        }
		    }
		    //scrolling winning combinations and matching with de symbol counts
		    for (Map.Entry<String, WinCombination> entry : winCombinations.entrySet()) {
		        String combinationKey = entry.getKey();
		        WinCombination winCombination = entry.getValue();

		        
		        for (Map.Entry<String, Integer> symbolEntry : symbolCounts.entrySet()) {
		            String symbol = symbolEntry.getKey();
		            int count = symbolEntry.getValue();

		            if (count == winCombination.getCount()) { 
		                winningSymbolsWithCombinations
		                    .computeIfAbsent(symbol, k -> new ArrayList<>())
		                    .add(combinationKey);
		            }
		        }
		    }

		    return winningSymbolsWithCombinations;
		}
// win combinations covering areas
	public Map<String, String> checkWinCombinations(String[][] matrix, Map<String, WinCombination> winCombinations) {
	    Map<String, String> appliedCombinations = new HashMap<>();

	    for (Map.Entry<String, WinCombination> entry : winCombinations.entrySet()) {
	        String combinationKey = entry.getKey();
	        WinCombination winCombination = entry.getValue();

	        if (winCombination.getCovered_areas() != null && !winCombination.getCovered_areas().isEmpty()) {
	            for (List<String> area : winCombination.getCovered_areas()) {
	                boolean combinationApplies = true;
	                String firstSymbol = null;

	                for (String a : area) {
	                    String[] parts = a.split(":");
	                    int row = Integer.parseInt(parts[0]);
	                    int col = Integer.parseInt(parts[1]);

	                    // checking matrix limits
	                    if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
	                        combinationApplies = false;
	                        break;
	                    }

	                    // Comparar símbolo en la posición actual con el primero encontrado
	                    if (firstSymbol == null ) {
	                        firstSymbol = matrix[row][col];
	                    } else if (!matrix[row][col].equals(firstSymbol)) {
	                        combinationApplies = false;
	                        break;
	                    }
	                }

	                
	                if (combinationApplies && firstSymbol != null) {
	                    appliedCombinations.put(firstSymbol, combinationKey ); 
	                }
	            }
	        }
	    }

	    return appliedCombinations;
	}



}
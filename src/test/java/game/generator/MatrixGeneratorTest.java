package game.generator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import game.config.GameConfig;
import game.config.probabilities.Probabilities;
import game.config.probabilities.StandardProbabilities;
import game.config.probabilities.SymbolProbabilities;
import game.config.symbol.Symbol;
import game.config.symbol.SymbolType;

public class MatrixGeneratorTest {

    @Test
    public void testGenerateMatrix() {
        GameConfig config = new GameConfig();
        config.setRows(3);
        config.setColumns(3);
        config.setLimitedBonusSymbols(2);

        Probabilities probabilities = new Probabilities();

        List<StandardProbabilities> standardSymbols = new ArrayList<StandardProbabilities>();
        StandardProbabilities sp = new StandardProbabilities();
        Map<String, Integer> symbols = new HashMap();
        sp.setColumn(0);
        sp.setRow(0);
        symbols.put("A", 30);        
        sp.setSymbols(symbols);
        
        standardSymbols.add(sp);
        probabilities.setStandardSymbols(standardSymbols);
        SymbolProbabilities bp = new SymbolProbabilities();
        
        
        Map<String, Integer> bonusSymbols = new HashMap<>();
        bonusSymbols.put("+500", 1);
        bp.setSymbols(bonusSymbols);
        probabilities.setBonusSymbols(bp);

        config.setProbabilities(probabilities);

        Map<String, Symbol> configSymbols = new HashMap<>();
        configSymbols.put("A", new Symbol(5.0, SymbolType.STANDARD));
        configSymbols.put("B", new Symbol(3.0, SymbolType.STANDARD));
        configSymbols.put("C", new Symbol(2.5, SymbolType.STANDARD));
        configSymbols.put("x5", new Symbol(5.0,SymbolType.BONUS, null, "multiply_reward",null));
        config.setSymbols(configSymbols);

        MatrixGenerator generator = new MatrixGenerator();
        config.setLimitedBonusSymbols(0);
        String[][] matrix = generator.generateMatrix(config);

        assertNotNull(matrix);
        assertEquals(3, matrix.length);
        assertEquals(3, matrix[0].length);

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                assertNotNull(matrix[row][col]);
            }
        }
      
        assertEquals(matrix[0][0],"A");
    }
}

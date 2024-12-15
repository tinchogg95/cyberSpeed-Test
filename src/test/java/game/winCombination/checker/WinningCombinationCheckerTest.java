package game.winCombination.checker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import game.config.GameConfig;
import game.config.symbol.Symbol;
import game.config.symbol.SymbolType;
import game.winCombination.WinCombination;

public class WinningCombinationCheckerTest {

    private WinningCombinationChecker checker;
    private GameConfig config;

    @Before
    public void setUp() {
        checker = new WinningCombinationChecker();
        config = new GameConfig();

        WinCombination horizontalWin = new WinCombination();
        horizontalWin.setCovered_areas(Arrays.asList(
            Arrays.asList("0:0", "0:1", "0:2"),
            Arrays.asList("1:0", "1:1", "1:2")
        ));
        
        WinCombination verticalWin = new WinCombination();
        verticalWin.setCovered_areas(Arrays.asList(
            Arrays.asList("0:0", "1:0", "2:0")
        ));
        
        WinCombination countWin4 = new WinCombination();
        countWin4.setCount(4);
        WinCombination countWin5 = new WinCombination();
        countWin5.setCount(5);
        
        Map<String, WinCombination> wcmap = new HashMap<String, WinCombination>();
        wcmap.put("horizontalWin", horizontalWin);
        wcmap.put("verticalWin", verticalWin);
        wcmap.put("countWin4", countWin4);
        wcmap.put("countWin5", countWin5);
        config.setWin_combinations(wcmap);
        
        Map<String, Symbol> smap = new HashMap<String, Symbol> ();
        Symbol a = new Symbol(5.0, SymbolType.STANDARD);
        Symbol b = new Symbol(4.5, SymbolType.STANDARD);
        smap.put("A", a);
        smap.put("B", b);
        
        config.setSymbols(smap);
    }

    @Test
    public void testCheckWinningCombinations() {
        String[][] matrix = {
            {"A", "A", "A"},
            {"B", "B", "B"},
            {"A", "B", "B"}
        };

        Map<String, List<String>> results = checker.checkWinningCombinations(matrix, config);

        assertEquals(2, results.size());
        assertTrue(results.containsKey("A"));
        assertTrue(results.containsKey("B"));
    }

    @Test
    public void testCountSymbolsAndTrackWinningCombinations() {
        String[][] matrix = {
            {"A", "A", "A"},
            {"B", "B", "B"},
            {"A", "B", "B"}
        };

        Map<String, WinCombination> winCombinations = config.getWin_combinations();
        Map<String, List<String>> results = WinningCombinationChecker.countSymbolsAndTrackWinningCombinations(matrix, winCombinations);

        assertEquals(2, results.size());
        assertTrue(results.containsKey("A"));
        assertTrue(results.containsKey("B"));
    }

    @Test
    public void testCheckWinCombinations() {
        String[][] matrix = {
            {"A", "A", "A"},
            {"B", "B", "B"},
            {"A", "B", "B"}
        };

        Map<String, WinCombination> winCombinations = config.getWin_combinations();
        Map<String, String> results = checker.checkWinCombinations(matrix, winCombinations);

        assertEquals(2, results.size());
        assertTrue(results.containsKey("A"));
        assertTrue(results.containsKey("B"));
    }
}

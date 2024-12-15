package game.winCombination.checker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import game.config.GameConfig;
import game.config.symbol.Symbol;
import game.config.symbol.SymbolType;

public class BonusRewardsCheckerTest {

    private BonusRewardsChecker checker;
    private GameConfig config;

    @Before
    public void setUp() {
        checker = new BonusRewardsChecker();
        config = new GameConfig();
        Map<String, Symbol> symbols = new HashMap<String, Symbol>();
        
        
        Symbol bonusSymbol = new Symbol();
        bonusSymbol.setImpact("extra bonus");
        bonusSymbol.setExtra(100);
        bonusSymbol.setType(SymbolType.BONUS);
        symbols.put("B", bonusSymbol);
        
        Symbol standardSymbol = new Symbol(2.0, SymbolType.STANDARD);
        symbols.put("S", standardSymbol);
        config.setSymbols(symbols);
    }

    @Test
    public void testGetBonusRewards() {
        String[][] matrix = {
            {"B", "S", "B"},
            {"S", "B", "S"},
            {"S", "S", "B"}
        };

        List<Symbol> bonusRewards = checker.getBonusRewards(matrix, config);

        assertEquals(4, bonusRewards.size());
        bonusRewards.forEach(symbol -> assertTrue(symbol.getImpact() != null));
    }

    @Test
    public void testGetBonusRewardsResponse() {
        String[][] matrix = {
            {"B", "S", "B"},
            {"S", "B", "S"},
            {"S", "S", "B"}
        };

        List<String> bonusSymbols = checker.getBonusRewardsResponse(matrix, config);

        assertEquals(4, bonusSymbols.size());
        assertTrue(bonusSymbols.containsAll(Arrays.asList("B")));
    }
}

package game.calculator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.calculator.RewardCalculator;
import game.config.GameConfig;
import game.config.symbol.Symbol;
import game.config.symbol.SymbolType;
import game.winCombination.WinCombination;

import org.junit.Test;

public class RewardCalculatorTest {
	private static final String SYMBOL_A = "A";
	private static final String SYMBOL_B = "B";
	private static final String SYMBOL_5X = "5x";
	private static final String SYMBOL_500 = "+500";
    @Test
    public void testCalculateReward() {
        GameConfig config = new GameConfig();
        Map<String, Symbol> symbols = new HashMap<>();
        //bonus rewards
        List<Symbol> bonusRewards1 = new ArrayList<Symbol>();
        
        List<Symbol> bonusRewards2 = new ArrayList<Symbol>();
        
        
        
        
        //combinations obtained from the matrix
        Map<String, List<String>> combinations = new HashMap<>();
        
        //ading symbols to config
        Symbol symbolA = new Symbol((double) 5, SymbolType.STANDARD);
        
        Symbol symbolB = new Symbol((double) 3, SymbolType.STANDARD);
   
        symbols.put(SYMBOL_A, symbolA);
        symbols.put(SYMBOL_B, symbolB);
        
        config.setSymbols(symbols);
        
        //adding win combinations to config
        
        WinCombination wc = new WinCombination(); 
        String name = "same_symbol_3_times";
        wc.setCount(3);
        wc.setReward_multiplier(1);
        
        WinCombination wc2 = new WinCombination(); 
        String name2 = "same_symbol_5_times";
        wc2.setCount(5);
        wc2.setReward_multiplier(2);
        
        WinCombination wc3 = new WinCombination(); 
        String name3 = "same_symbol_6_times";
        wc3.setCount(6);
        wc3.setReward_multiplier(3);
        
        WinCombination wc4 = new WinCombination(); 
        String name4 = "same_symbols_vertically";
        wc4.setReward_multiplier(2);
        
        Map<String, WinCombination> winCombinations = new HashMap();
		winCombinations.put(name, wc);
		winCombinations.put(name2, wc2);
		winCombinations.put(name3, wc3);
		winCombinations.put(name4, wc4);
        
		config.setWin_combinations(winCombinations);
		
		
		
        config.setSymbols(symbols);
        config.setWin_combinations(winCombinations);
        
        //creating rewards with symbols and combinations
        List<String> combinationListA = new ArrayList<String>();
        combinationListA.add("same_symbol_3_times");
        combinationListA.add("same_symbol_5_times");
        
        List<String> combinationListB = new ArrayList<String>();
        combinationListB.add("same_symbol_6_times");
        combinationListB.add("same_symbols_vertically");
        
        combinations.put(SYMBOL_A, combinationListA);
        combinations.put(SYMBOL_B, combinationListB);
        
        
        //adding symbols to bonusRewards
        Symbol bonusA = new Symbol();
        bonusA.setRewardMultiplier(5.0);
        bonusA.setType(SymbolType.BONUS);
        bonusA.setImpact("multiply_reward");
        
        Symbol bonusB = new Symbol();
        bonusB.setExtra(500);
        bonusB.setType(SymbolType.BONUS);
        bonusB.setImpact("extra_bonus");
        
        bonusRewards1.add(bonusA);
        
        bonusRewards2.add(bonusB);
        
        RewardCalculator calculator = new RewardCalculator();
        
        
        Double betAmount = 100.0;
        
        Double reward = calculator.calculateReward(betAmount, combinations, config, bonusRewards1);
        assertEquals(14000.0, reward, 0.001); // (5*100*1*2) + (3*100*3*2) = 1000 + 1800 = 2800 *BONUS(5) = 14000   
        Double reward2 = calculator.calculateReward(betAmount, combinations, config, bonusRewards2);
        assertEquals(3300.0, reward2, 0.001); // (5*100*1*2) + (3*100*3*2) = 1000 + 1800 = 2800 + BONUS(500) = 3300
        combinations.clear();

        Double reward0 = calculator.calculateReward(betAmount, combinations, config, bonusRewards1); //0 
        
        assertNotNull(reward);
        
        assertTrue(reward0 == 0);
        
    }
}


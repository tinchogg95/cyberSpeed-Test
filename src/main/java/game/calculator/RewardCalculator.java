package game.calculator;

import java.util.List;
import java.util.Map;

import game.config.GameConfig;
import game.config.symbol.Symbol;

public class RewardCalculator {
	public Double calculateReward(Double betAmount, Map<String, List<String>> combinations, GameConfig config, List<Symbol> bonusRewards) {
		Double rewards = (double) 0;
		for (Map.Entry<String, List<String>> entry : combinations.entrySet()) { 
			//scrolling the map of combinations
			Double rewardsSymbol = (double) 0;
			String symbol = entry.getKey();
		
			List<String> listOfCombinations = entry.getValue();
			//adding up values for each symbol
			rewardsSymbol += betAmount * config.getSymbols().get(symbol).getRewardMultiplier(); 
			for(String c : listOfCombinations) {
				rewardsSymbol *= config.getWin_combinations().get(c).getReward_multiplier();
			}
			rewards += rewardsSymbol ;
		}
		//bonus rewards
		if (!bonusRewards.isEmpty() ) {
			for(Symbol bonus : bonusRewards) {
				if (bonus.getExtra() != null)
					rewards += bonus.getExtra();
				if (bonus.getRewardMultiplier() != null)
					rewards *= bonus.getRewardMultiplier();
			}
		}
		return rewards;
	}
}

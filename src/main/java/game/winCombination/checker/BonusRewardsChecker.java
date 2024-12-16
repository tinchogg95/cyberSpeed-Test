package game.winCombination.checker;

import java.util.ArrayList;
import java.util.List;

import game.config.GameConfig;
import game.config.symbol.Symbol;

public class BonusRewardsChecker {
	//returns rewards properties
	public List<Symbol> getBonusRewards(String[][] matrix, GameConfig config){
		List<Symbol> bonusSymbol = new ArrayList<Symbol>();
		for (int i = 0; i < matrix.length; i++) {
		    for (int j = 0; j < matrix[i].length; j++) {
		        Symbol symbol = config.getSymbols().get(matrix[i][j]);
		        if(symbol.getImpact()!= null) {
		        	bonusSymbol.add(symbol);
		        }
		    }
		}
		return bonusSymbol;
	}
	//returns only the symbols
	public List<String> getBonusRewardsResponse(String[][] matrix, GameConfig config){
		List<String> bonusSymbol = new ArrayList<String>();
		for (int i = 0; i < matrix.length; i++) {
		    for (int j = 0; j < matrix[i].length; j++) {
		        Symbol symbol = config.getSymbols().get(matrix[i][j]);
		        if(symbol.getImpact()!= null) {
		        	bonusSymbol.add(matrix[i][j]);
		        }
		    }
		}
		return bonusSymbol;
	}
}

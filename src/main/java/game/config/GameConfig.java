package game.config;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import game.config.probabilities.Probabilities;
import game.config.symbol.Symbol;
import game.winCombination.WinCombination;


public class GameConfig {
	
    @Override
	public String toString() {
		return "GameConfig [columns=" + columns + ", rows=" + rows + ", symbols=" + symbols + ", probabilities="
				+ probabilities + ", winCombinations=" + win_combinations + "]";
	}

    private Integer limitedBonusSymbols = 9999;
	private Integer columns;
    private Integer rows;
    @JsonProperty("symbols")
    private Map<String, Symbol> symbols;
    private Probabilities probabilities;
    private Map<String, WinCombination> win_combinations;

    public Boolean isBonusSymbol(String s) {
    	Symbol symbol = symbols.get(s);
    	return symbol.getImpact()!=null?Boolean.TRUE:Boolean.FALSE;
    }
    
    public Integer getColumns() {
        return columns;
    }

    public Integer getLimitedBonusSymbols() {
		return limitedBonusSymbols;
	}

	public void setLimitedBonusSymbols(Integer limitedBonusSymbols) {
		this.limitedBonusSymbols = limitedBonusSymbols;
	}

	public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Symbol> symbols) {
        this.symbols = symbols;
    }

    public Probabilities getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(Probabilities probabilities) {
        this.probabilities = probabilities;
    }

    public Map<String, WinCombination> getWin_combinations() {
        return win_combinations;
    }

    public void setWin_combinations(Map<String, WinCombination> win_combinations) {
        this.win_combinations = win_combinations;
    }


}


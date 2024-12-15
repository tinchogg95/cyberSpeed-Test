package game.config.probabilities;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Probabilities {
    @JsonProperty("standard_symbols")
    private List<StandardProbabilities> standardSymbols; 

    @JsonProperty("bonus_symbols")
    private SymbolProbabilities bonusSymbols;

    public List<StandardProbabilities> getStandardSymbols() {
        return standardSymbols;
    }
    
    public void setStandardSymbols(List<StandardProbabilities> standardSymbols) {
        this.standardSymbols = standardSymbols;
    }

	public SymbolProbabilities  getBonusSymbols() {
        return bonusSymbols;
    }

    public void setBonusSymbols(SymbolProbabilities bonusSymbols) {
        this.bonusSymbols = bonusSymbols;
    }
    
    @Override
 	public String toString() {
 		return "Probabilities [standardSymbols=" + standardSymbols + ", bonusSymbols=" + bonusSymbols
 				+ ", getStandardSymbols()=" + getStandardSymbols() + ", getBonusSymbols()=" + getBonusSymbols()
 				+ "]";
 	}
}
package game.config.symbol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import game.config.symbol.deserializer.SymbolDeserializer;

@JsonDeserialize(using = SymbolDeserializer.class) // Vinculamos el deserializador
public class Symbol {

    private Double rewardMultiplier;
    private SymbolType type; 
    private Integer extra;  
    private String impact;
    private Integer bonusValue;

    public Double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public Symbol() {
		super();
	}
    
    public Symbol(Double rewardMultiplier, SymbolType type) {
		super();
		this.rewardMultiplier = rewardMultiplier;
		this.type = type;
	}

	public Symbol(Double rewardMultiplier, SymbolType type, Integer extra, String impact, Integer bonusValue) {
		super();
		this.rewardMultiplier = rewardMultiplier;
		this.type = type;
		this.extra = extra;
		this.impact = impact;
		this.bonusValue = bonusValue;
	}

	public void setRewardMultiplier(Double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    @JsonProperty("type")
    public SymbolType getType() {
        return type;
    }

    public void setType(SymbolType type) {
        this.type = type;
    }

    public Integer getExtra() {
        return extra;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public Integer getBonusValue() {
        return bonusValue;
    }

    public void setBonusValue(Integer bonusValue) {
        this.bonusValue = bonusValue;
    }
}

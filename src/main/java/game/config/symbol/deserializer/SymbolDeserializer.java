package game.config.symbol.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import game.config.symbol.Symbol;
import game.config.symbol.SymbolType;
import game.util.Constants;

public class SymbolDeserializer extends StdDeserializer<Symbol> {

    public SymbolDeserializer() {
        super(Symbol.class);
    }

    @Override
    public Symbol deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        Symbol symbol = new Symbol();

        if (node.has(Constants.DES_REWARD_MULTIPLIER)) {
            symbol.setRewardMultiplier(node.get(Constants.DES_REWARD_MULTIPLIER).asDouble());
        }

        if (node.has(Constants.DES_TYPE)) {
            symbol.setType(SymbolType.valueOf(node.get(Constants.DES_TYPE).asText().toUpperCase()));
        }

        if (node.has(Constants.DES_EXTRA)) {
            symbol.setExtra(node.get(Constants.DES_EXTRA).asInt());
        }

        if (node.has(Constants.DES_IMPACT)) {
            symbol.setImpact(node.get(Constants.DES_IMPACT).asText());
        }

        if (node.has(Constants.DES_BONUS_VALUE)) {
            symbol.setBonusValue(node.get(Constants.DES_BONUS_VALUE).asInt());
        }

        return symbol;
    }
}

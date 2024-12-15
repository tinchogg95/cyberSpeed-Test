package game.config.symbol.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import game.config.symbol.Symbol;
import game.config.symbol.SymbolType;

public class SymbolDeserializer extends StdDeserializer<Symbol> {

    public SymbolDeserializer() {
        super(Symbol.class);
    }

    @Override
    public Symbol deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        Symbol symbol = new Symbol();

        if (node.has("reward_multiplier")) {
            symbol.setRewardMultiplier(node.get("reward_multiplier").asDouble());
        }

        if (node.has("type")) {
            symbol.setType(SymbolType.valueOf(node.get("type").asText().toUpperCase()));
        }

        if (node.has("extra")) {
            symbol.setExtra(node.get("extra").asInt());
        }

        if (node.has("impact")) {
            symbol.setImpact(node.get("impact").asText());
        }

        if (node.has("bonusValue")) {
            symbol.setBonusValue(node.get("bonusValue").asInt());
        }

        return symbol;
    }
}

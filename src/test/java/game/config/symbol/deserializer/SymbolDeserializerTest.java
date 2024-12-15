package game.config.symbol.deserializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import game.config.symbol.Symbol;
import game.config.symbol.SymbolType;
import game.config.symbol.deserializer.SymbolDeserializer;

public class SymbolDeserializerTest {

    @Test
    public void testDeserializeSymbol() throws JsonProcessingException, IOException {
        String json = "{"
                    + "\"reward_multiplier\": 2.5,"
                    + "\"type\": \"bonus\","
                    + "\"extra\": 100,"
                    + "\"impact\": \"extra_bonus\","
                    + "\"bonusValue\": 50"
                    + "}";

        // Configurar ObjectMapper con el deserializador
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Symbol.class, new SymbolDeserializer());
        mapper.registerModule(module);

        Symbol symbol = mapper.readValue(json, Symbol.class);

        assertNotNull(symbol);
        assertEquals(2.5, symbol.getRewardMultiplier(), 0.01);
        assertEquals(SymbolType.BONUS, symbol.getType());
        assertEquals(100, symbol.getExtra().intValue());
        assertEquals("extra_bonus", symbol.getImpact());
        assertEquals(50, symbol.getBonusValue().intValue());
    }
}

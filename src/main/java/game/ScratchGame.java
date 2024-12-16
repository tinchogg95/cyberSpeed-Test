package game;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import game.calculator.RewardCalculator;
import game.config.GameConfig;
import game.config.symbol.Symbol;
import game.config.symbol.deserializer.SymbolDeserializer;
import game.generator.MatrixGenerator;
import game.input.Parser;
import game.output.json.Response;
import game.util.Constants;
import game.winCombination.checker.BonusRewardsChecker;
import game.winCombination.checker.WinningCombinationChecker;

public class ScratchGame {
    public static void main(String[] args) {
    	CommandLine cmd = Parser.parseArguments(args);    	
    	if(cmd != null) {
    		String configFilePath = cmd.getOptionValue(Constants.CONFIG_FILE_PATH);
    		Double betAmount = Double.parseDouble(cmd.getOptionValue(Constants.BETTING_AMOUNT));
            ScratchGame game = new ScratchGame();
            game.playGame(configFilePath, betAmount);
    	}    	
    }

    public void playGame(String configFilePath, Double betAmount) {
        ObjectMapper objectMapper = new ObjectMapper();
        GameConfig config;

        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Symbol.class, new SymbolDeserializer());
            objectMapper.registerModule(module);

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFilePath);
            config = objectMapper.readValue(inputStream, GameConfig.class);

            MatrixGenerator generator = new MatrixGenerator();
            String[][] matrix = generator.generateMatrix(config);
            WinningCombinationChecker winningChecker = new WinningCombinationChecker();
            BonusRewardsChecker bonusChecker = new BonusRewardsChecker();
            Map<String, List<String>> combinations = winningChecker.checkWinningCombinations(matrix, config);
            List<Symbol> bonusRewards = new ArrayList<>();
            if (!combinations.isEmpty()) {
                bonusRewards = bonusChecker.getBonusRewards(matrix, config);
            }
            List<String> bonusRewardsResponse = new ArrayList<>();
            if (!combinations.isEmpty()) {
                bonusRewardsResponse = bonusChecker.getBonusRewardsResponse(matrix, config);
            }
            RewardCalculator calculator = new RewardCalculator();
            Double reward = calculator.calculateReward(betAmount, combinations, config, bonusRewards);

            Response response = new Response(matrix, reward, combinations, bonusRewardsResponse);
            System.out.println(response.toJson());

        } catch (Throwable e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

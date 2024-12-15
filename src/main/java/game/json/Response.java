package game.json;

import java.util.List;
import java.util.Map;

public class Response {
    private String[][] matrix;
    private Double reward;
    private Map<String, List<String>> applied_winning_combinations;
    private List<String> bonusRewards;

    public Response(String[][] matrix, Double reward, Map<String, List<String>> applied_winning_combinations, List<String> bonusRewards) {
        this.matrix = matrix;
        this.reward = reward;
        this.applied_winning_combinations = applied_winning_combinations;
        this.bonusRewards = bonusRewards;
    }

	public String[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }

    public Map<String, List<String>> getapplied_winning_combinations() {
        return applied_winning_combinations;
    }

    public void setCombinations(Map<String, List<String>> applied_winning_combinations) {
        this.applied_winning_combinations = applied_winning_combinations;
    }

    public List<String> getBonusRewards() {
        return bonusRewards;
    }

    public void setBonusRewards(List<String> bonusRewards) {
        this.bonusRewards = bonusRewards;
    }
}

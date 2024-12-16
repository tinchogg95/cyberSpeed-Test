package game.output.json;

import java.util.List;
import java.util.Map;

public class Response {
    private String[][] matrix;
    private Double reward;
    private Map<String, List<String>> applied_winning_combinations;
    private List<String> applied_bonus_symbol;

    public Response(String[][] matrix, Double reward, Map<String, List<String>> applied_winning_combinations, List<String> applied_bonus_symbol) {
        this.matrix = matrix;
        this.reward = reward;
        this.applied_winning_combinations = applied_winning_combinations;
        this.applied_bonus_symbol = applied_bonus_symbol;
    }

    public String toJson() {
        StringBuilder jsonResponse = new StringBuilder("{\n");
        jsonResponse.append("  \"matrix\": [\n");
        for (int i = 0; i < matrix.length; i++) {
            jsonResponse.append("    [");
            for (int j = 0; j < matrix[i].length; j++) {
                jsonResponse.append("\"").append(matrix[i][j]).append("\"");
                if (j < matrix[i].length - 1) jsonResponse.append(", ");
            }
            jsonResponse.append("]");
            if (i < matrix.length - 1) jsonResponse.append(",");
            jsonResponse.append("\n");
        }
        jsonResponse.append("  ],\n");
        jsonResponse.append("  \"reward\": ").append(reward).append(",\n");
        jsonResponse.append("  \"applied_winning_combinations\": ").append(toJson(applied_winning_combinations)).append(",\n");
        jsonResponse.append("  \"applied_bonus_symbol\": ").append(toJson(applied_bonus_symbol)).append("\n");
        jsonResponse.append("}\n");

        return jsonResponse.toString();
    }
    
    private static String toJson(Object obj) {
        StringBuilder json = new StringBuilder("[");
        if (obj instanceof List) {
            List<?> list = (List<?>) obj;
            for (int i = 0; i < list.size(); i++) {
                json.append("\"").append(list.get(i)).append("\"");
                if (i < list.size() - 1) json.append(", ");
            }
        } else if (obj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) obj;
            map.forEach((key, value) -> {
                json.append("\"").append(key).append("\": ").append(toJson(value)).append(", ");
            });
            if (json.length() > 1) {
                json.delete(json.length() - 2, json.length());
            }
        }
        json.append("]");
        return json.toString();
    }
}

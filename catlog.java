package jaavaa;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.*;
public class catlog {

	public static void main(String[] args) {
		String jsonString = "{\n" +
                "    \"keys\": {\n" +
                "        \"n\": 4,\n" +
                "        \"k\": 3\n" +
                "    },\n" +
                "    \"1\": {\n" +
                "        \"base\": \"10\",\n" +
                "        \"value\": \"4\"\n" +
                "    },\n" +
                "    \"2\": {\n" +
                "        \"base\": \"2\",\n" +
                "        \"value\": \"111\"\n" +
                "    },\n" +
                "    \"3\": {\n" +
                "        \"base\": \"10\",\n" +
                "        \"value\": \"12\"\n" +
                "    },\n" +
                "    \"6\": {\n" +
                "        \"base\": \"4\",\n" +
                "        \"value\": \"213\"\n" +
                "    }\n" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject keys = jsonObject.getJSONObject("keys");
        int n = keys.getInt("n");
        int k = keys.getInt("k");
        Map<Integer, Integer> points = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            if (!key.equals("keys")) {
                int x = Integer.parseInt(key);
                JSONObject point = jsonObject.getJSONObject(key);
                int base = Integer.parseInt(point.getString("base"));
                int y = Integer.parseInt(point.getString("value"), base);
                points.put(x, y);
            }
        }
        int constantTerm = findConstantTerm(points, k);
        System.out.println("Constant term (c) of the polynomial is: " + constantTerm);
    }
    private static int findConstantTerm(Map<Integer, Integer> points, int k) {
        int constantTerm = 0;
        for (Map.Entry<Integer, Integer> p : points.entrySet()) {
            int xi = p.getKey();
            int yi = p.getValue();

            double li = 1.0;
            for (Map.Entry<Integer, Integer> q : points.entrySet()) {
                int xj = q.getKey();
                if (xi != xj) {
                    li *= ((double) 0 - xj) / (xi - xj);
                }
            }
            constantTerm += yi * li;
        }
        return constantTerm;
    }
}

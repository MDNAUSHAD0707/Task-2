// File: WeatherApiClient.java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApiClient {

    // Method to fetch and display weather
    public static void fetchWeather() {
        try {
            // API URL for current weather in Delhi
            String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=28.61&longitude=77.23&current_weather=true";

            // Setup connection
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
            reader.close();

            // Parse JSON data
            JSONObject obj = new JSONObject(jsonData.toString());
            JSONObject weather = obj.getJSONObject("current_weather");

            // Display structured weather data
            System.out.println("📍 Weather Data (Delhi):");
            System.out.println("🌡 Temperature: " + weather.getDouble("temperature") + "°C");
            System.out.println("💨 Wind Speed: " + weather.getDouble("windspeed") + " km/h");
            System.out.println("⏱ Time: " + weather.getString("time"));

        } catch (Exception e) {
            System.out.println("❌ Error fetching weather: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        fetchWeather();
    }
}
import org.json.JSONObject;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApp {

    private static final String API_KEY = "9968ef746284846cfbaa0ff84dd42724"; // Replace with your actual API key

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a city to get the current weather:");
        String city = scanner.nextLine();
        fetchWeatherDetails(city);


    }

    private static void fetchWeatherDetails(String city) {
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + API_KEY;

        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            parseAndDisplayWeatherData(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseAndDisplayWeatherData(String jsonData) {
        JSONObject jsonObject = new JSONObject(jsonData);

        // Assuming we want to display temperature and weather description
        double temperature = jsonObject.getJSONObject("main").getDouble("temp");
        String weatherDescription = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

        System.out.println("Temperature in " + jsonObject.getString("name") + ": " + temperature + "°C");
        System.out.println("Weather: " + weatherDescription);
    }
}

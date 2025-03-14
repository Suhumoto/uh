import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiExample {
    public static void main(String[] args) {
        try {
            String apiUrl = "https://api.agify.io/?name=Dima";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();


                System.out.println("Отримані дані: " + response.toString());
            } else {
                System.out.println("Помилка! Код відповіді: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


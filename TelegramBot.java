//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONObject;

public class TelegramBot {
    private static final String BOT_TOKEN = "YOUR_BOT_TOKEN";
    private static final long CHANNEL_ID = -1001234567890L; // ID вашего канала
    private static final int SPAM_THRESHOLD = 5; // Количество сообщений за 10 секунд

    private static long lastUpdateId = 0; // ID последнего обновления
    private static final int CHECK_INTERVAL = 2000; // Интервал проверки (2 сек)

    public static void main(String[] args) {
        System.out.println("Бот запущен!");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkUpdates();
            }
        }, 0, CHECK_INTERVAL);
    }

    private static void checkUpdates() {
        try {
            String url = "https://api.telegram.org/bot" + BOT_TOKEN + "/getUpdates?offset=" + (lastUpdateId + 1);
            String response = sendHttpRequest(url, "GET", null);

            JSONObject jsonResponse = new JSONObject(response);
            JSONArray updates = jsonResponse.getJSONArray("result");

            for (int i = 0; i < updates.length(); i++) {
                JSONObject update = updates.getJSONObject(i);
                lastUpdateId = update.getLong("update_id");

                if (update.has("message")) {
                    JSONObject message = update.getJSONObject("message");
                    long chatId = message.getJSONObject("chat").getLong("id");

                    if (chatId == CHANNEL_ID) {
                        long userId = message.getJSONObject("from").getLong("id");
                        handleSpamDetection(userId, chatId);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleSpamDetection(long userId, long chatId) {
        // TODO: Логика спам-фильтра (например, хранить историю сообщений пользователей)
        System.out.println("Обнаружен подозрительный пользователь: " + userId);
        banUser(userId, chatId); // Забанить за спам
    }

    private static void banUser(long userId, long chatId) {
        try {
            String url = "https://api.telegram.org/bot" + BOT_TOKEN + "/banChatMember";
            String payload = "{\"chat_id\":" + chatId + ",\"user_id\":" + userId + "}";
            sendHttpRequest(url, "POST", payload);
            System.out.println("Пользователь " + userId + " забанен.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sendHttpRequest(String url, String method, String payload) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json");

        if (payload != null) {
            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                os.write(payload.getBytes());
                os.flush();
            }
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
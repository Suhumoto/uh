//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class TelegramMessenger extends JFrame {
    private JTextField messageField;
    private JButton sendButton;


    private static final String BOT_TOKEN = "8054578033:AAFrLAKDe9fr2xEk7DUw5_BHqHsVF8L7-Mo";

    private static final String CHAT_ID = "t.me/ilovepudgebot";

    public TelegramMessenger() {
        setTitle("Telegram Messenger");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("Введіть повідомлення:");
        label.setBounds(20, 20, 200, 30);
        add(label);

        messageField = new JTextField();
        messageField.setBounds(20, 60, 300, 30);
        add(messageField);

        sendButton = new JButton("Надіслати");
        sendButton.setBounds(250, 100, 120, 30);
        add(sendButton);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                sendMessageToTelegram(message);
            }
        });

        setVisible(true);
    }

    private void sendMessageToTelegram(String message) {
        try {
            String urlString = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";
            String jsonInputString = "{\"chat_id\":\"" + CHAT_ID + "\", \"text\":\"" + message + "\"}";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                JOptionPane.showMessageDialog(this, "Повідомлення надіслано!", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Помилка надсилання!", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Помилка: " + ex.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TelegramMessenger();
    }
}
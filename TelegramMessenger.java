//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class TelegramMessenger extends JFrame {
    private JTextField messageField;
    private JButton sendButton, historyButton;

    private static final String BOT_TOKEN = "your_bot_token"; // Замініть на свій токен
    private static final String CHAT_ID = "your_chat_id"; // Замініть на свій Chat ID
    private static final String FILE_NAME = "messages.json"; // Файл для збереження історії

    public TelegramMessenger() {
        setTitle("Telegram Messenger");
        setSize(400, 250);
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
        sendButton.addActionListener(new SendMessageListener());
        add(sendButton);

        historyButton = new JButton("Історія");
        historyButton.setBounds(20, 100, 120, 30);
        historyButton.addActionListener(new ShowHistoryListener());
        add(historyButton);

        setVisible(true);
    }

    private class SendMessageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                sendMessageToTelegram(message);
                saveMessageToJson(message);
            }
        }
    }

    private class ShowHistoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showHistory();
        }
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

    private void saveMessageToFile(String message) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Помилка запису у файл!", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void showHistory() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists() || file.length() == 0) {
                JOptionPane.showMessageDialog(this, "Історія порожня!", "Історія", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            List<String> messagesList = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
            String history = String.join("\n", messagesList);
            JOptionPane.showMessageDialog(this, history, "Історія повідомлень", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Помилка читання файлу!", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        new TelegramMessenger();
    }
}


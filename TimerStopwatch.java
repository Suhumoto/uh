import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerStopwatch {
    private static int elapsedTime = 0; // Час у мілісекундах
    private static boolean running = false; // Стан секундоміра
    private static Timer timer;

    public static void main(String[] args) {
        // Створення основного вікна
        JFrame frame = new JFrame("Секундомір");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        // Дисплей для часу
        JLabel timeDisplay = new JLabel(formatTime(elapsedTime), SwingConstants.CENTER);
        timeDisplay.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(timeDisplay, BorderLayout.CENTER);

        // Панель кнопок
        JPanel buttonPanel = new JPanel();

        // Кнопка "Пуск/Стоп"
        JButton startStopButton = new JButton("Старт");
        buttonPanel.add(startStopButton);

        // Кнопка "Скидання"
        JButton resetButton = new JButton("Скидання");
        buttonPanel.add(resetButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Таймер, який оновлює час
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 100;
                timeDisplay.setText(formatTime(elapsedTime));
            }
        });

        // Дія для кнопки "Пуск/Стоп"
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (running) {
                    timer.stop();
                    startStopButton.setText("Старт");
                } else {
                    timer.start();
                    startStopButton.setText("Стоп");
                }
                running = !running;
            }
        });

        // Дія для кнопки "Скидання"
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                running = false;
                elapsedTime = 0;
                timeDisplay.setText(formatTime(elapsedTime));
                startStopButton.setText("Старт");
            }
        });

        // Відображення вікна
        frame.setVisible(true);
    }

    // Форматування часу у форматі MM:SS:мс
    private static String formatTime(int time) {
        int minutes = (time / 60000);
        int seconds = (time / 1000) % 60;
        int milliseconds = (time % 1000) / 100;
        return String.format("%02d:%02d.%d", minutes, seconds, milliseconds);
    }
}





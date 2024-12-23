import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        // Створюємо головне вікно
        JFrame frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        // Панель для відображення результату
        JTextField display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        // Панель для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        // Масив кнопок
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        // Змінні для збереження стану
        final double[] num = {0};
        final String[] operator = {""};

        // Додаємо кнопки на панель
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();

                    if ("0123456789.".contains(command)) {
                        // Додавання цифр до дисплея
                        display.setText(display.getText() + command);
                    } else if ("/*-+".contains(command)) {
                        // Зберігаємо число та операцію
                        num[0] = Double.parseDouble(display.getText());
                        operator[0] = command;
                        display.setText("");
                    } else if ("=".equals(command)) {
                        // Виконуємо обчислення
                        double secondNum = Double.parseDouble(display.getText());
                        double result = 0;
                        switch (operator[0]) {
                            case "+": result = num[0] + secondNum; break;
                            case "-": result = num[0] - secondNum; break;
                            case "*": result = num[0] * secondNum; break;
                            case "/": result = num[0] / secondNum; break;
                        }
                        display.setText(String.valueOf(result));
                    }
                }
            });
            buttonPanel.add(button);
        }

        // Додаємо компоненти до головного вікна
        frame.setLayout(new BorderLayout());
        frame.add(display, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Відображаємо вікно
        frame.setVisible(true);
    }
}



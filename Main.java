import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private double memory = 0; // Для функції "Запам'ятати"
    private JFrame frame;
    private JTextField display;
    private JPanel buttonPanel;

    public Main() {
        frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));

        addButtons();

        frame.add(display, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addButtons() {
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "\u221A", "x^2", "M"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setForeground(Color.WHITE);

            // Установка кольорів для кнопок
            switch (label) {
                case "C":
                    button.setBackground(Color.RED);
                    break;
                case "=":
                    button.setBackground(Color.GREEN);
                    break;
                case "M":
                    button.setBackground(Color.ORANGE);
                    break;
                default:
                    button.setBackground(Color.BLUE);
            }

            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }
    }

    private class ButtonClickListener implements ActionListener {
        private String currentInput = "";
        private String operator = "";
        private double firstNumber = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "C":
                    currentInput = "";
                    operator = "";
                    firstNumber = 0;
                    display.setText("");
                    break;
                case "=":
                    calculate();
                    break;
                case "+": case "-": case "*": case "/":
                    operator = command;
                    firstNumber = Double.parseDouble(currentInput);
                    currentInput = "";
                    break;
                case "\u221A": // Квадратний корінь
                    double value = Double.parseDouble(currentInput);
                    display.setText(String.valueOf(Math.sqrt(value)));
                    currentInput = "";
                    break;
                case "x^2": // Квадрат числа
                    value = Double.parseDouble(currentInput);
                    display.setText(String.valueOf(Math.pow(value, 2)));
                    currentInput = "";
                    break;
                case "M": // Запам'ятати
                    memory = Double.parseDouble(currentInput);
                    display.setText("Збережено: " + memory);
                    currentInput = "";
                    break;
                default:
                    currentInput += command;
                    display.setText(currentInput);
            }
        }

        private void calculate() {
            double secondNumber = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    result = secondNumber != 0 ? firstNumber / secondNumber : 0;
                    break;
            }

            display.setText(String.valueOf(result));
            currentInput = "";
            operator = "";
            firstNumber = 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}




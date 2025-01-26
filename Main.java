//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Model
class Model {
    private String text;

    public Model(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

// View


class View extends JFrame {
    private JLabel label;
    private JButton button;

    public View() {
        // Налаштування вікна
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setLayout(new FlowLayout());

        // Створення компонентів
        label = new JLabel("Початковий текст");
        button = new JButton("Змінити текст");

        // Додавання компонентів до вікна
        this.add(label);
        this.add(button);
    }

    public JLabel getLabel() {
        return label;
    }

    public JButton getButton() {
        return button;
    }
}

// Controller


class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        // Додавання обробника подій до кнопки
        this.view.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setText("Текст змінено!");
                view.getLabel().setText(model.getText());
            }
        });
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        // Створення об'єктів MVC
        Model model = new Model("Початковий текст");
        View view = new View();
        Controller controller = new Controller(model, view);

        // Відображення вікна
        view.setVisible(true);
    }
}


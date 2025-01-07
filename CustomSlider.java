//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomSlider extends JComponent {
    private int value = 50; // Поточне значення
    private int minValue = 0; // Мінімальне значення
    private int maxValue = 100; // Максимальне значення

    public CustomSlider() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateValue(e.getX());
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateValue(e.getX());
            }
        });
    }

    private void updateValue(int mouseX) {
        int width = getWidth();
        value = (int) ((double) mouseX / width * (maxValue - minValue) + minValue);
        value = Math.max(minValue, Math.min(maxValue, value)); // Обмеження в межах min і max
        repaint();
    }

    public int getValue() {
        return value;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Малюємо фон
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0, height / 2 - 5, width, 10);

        // Малюємо заповнену частину
        int filledWidth = (int) ((double) value / maxValue * width);
        g2.setColor(Color.BLUE);
        g2.fillRect(0, height / 2 - 5, filledWidth, 10);

        // Малюємо повзунок
        g2.setColor(Color.RED);
        g2.fillOval(filledWidth - 5, height / 2 - 10, 20, 20);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Slider");
        CustomSlider slider = new CustomSlider();
        slider.setPreferredSize(new Dimension(300, 50));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(slider);
        frame.pack();
        frame.setVisible(true);
    }
}


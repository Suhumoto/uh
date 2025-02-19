//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.*;

public class SpriteAnimation extends JFrame {
    private int x = 50;
    private int speed = 5;
    private Timer timer;

    public SpriteAnimation() {
        setTitle("Анімація Спрайта");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        timer = new Timer(30, e -> {
            x += speed;
            if (x > getWidth() - 100 || x < 0) {
                speed = -speed;
            }
            repaint();
        });
        timer.start();

        openControlWindow();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillRect(x, 100, 50, 50);
    }

    private void openControlWindow() {
        JFrame controlFrame = new JFrame("Управління");
        controlFrame.setSize(200, 100);
        controlFrame.setLocationRelativeTo(null);

        JButton speedUp = new JButton("Прискорити");
        JButton slowDown = new JButton("Сповільнити");

        speedUp.addActionListener(e -> speed = Math.min(speed + 2, 20));
        slowDown.addActionListener(e -> speed = Math.max(speed - 2, 2));

        JPanel panel = new JPanel();
        panel.add(speedUp);
        panel.add(slowDown);

        controlFrame.add(panel);
        controlFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SpriteAnimation().setVisible(true));
    }
}


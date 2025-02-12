import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingSpriteAnimation extends JPanel implements ActionListener {
    private Image[] sprites;
    private int currentFrame = 0;
    private Timer timer;
    private int x = 50, y = 50; // Початкові координати
    private int dx = 2, dy = 2; // Швидкість руху

    public MovingSpriteAnimation() {
        // Завантаження спрайтів
        sprites = new Image[4]; // Кількість кадрів
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new ImageIcon("res/sprite" + (i + 1) + ".png").getImage();
        }

        // Налаштування таймера (швидкість зміни кадрів)
        timer = new Timer(100, this); // Зміна кадру кожні 100 мс
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(sprites[currentFrame], x, y, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Зміна кадру
        currentFrame++;
        if (currentFrame >= sprites.length) {
            currentFrame = 0;
        }

        // Оновлення координат
        x += dx;
        y += dy;

        // Відбивання від стінок
        if (x < 0 || x + sprites[currentFrame].getWidth(this) > getWidth()) {
            dx *= -1;
        }
        if (y < 0 || y + sprites[currentFrame].getHeight(this) > getHeight()) {
            dy *= -1;
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Рух анімованого спрайту");
        MovingSpriteAnimation animation = new MovingSpriteAnimation();
        frame.add(animation);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpriteAnimation extends JPanel implements ActionListener {
    private Image[] sprites;
    private int currentFrame = 0;
    private Timer timer;

    public SpriteAnimation() {

        sprites = new Image[4];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new ImageIcon("res/sprite" + (i + 1) + ".png").getImage();
        }


        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(sprites[currentFrame], 50, 50, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame++;
        if (currentFrame >= sprites.length) {
            currentFrame = 0;
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Анімація зі спрайтами");
        SpriteAnimation animation = new SpriteAnimation();
        frame.add(animation);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}



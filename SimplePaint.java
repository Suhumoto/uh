import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimplePaint {

    private JFrame frame;
    private JPanel canvas;
    private Color currentColor = Color.BLACK;
    private int brushSize = 5;

    public SimplePaint() {
        frame = new JFrame("Simple Paint");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        canvas = new JPanel() {
            private Image image;
            private Graphics2D g2;

            {
                setBackground(Color.WHITE);
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        draw(e.getX(), e.getY());
                    }
                });

                addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        draw(e.getX(), e.getY());
                    }
                });
            }

            private void draw(int x, int y) {
                if (image == null) {
                    image = createImage(getWidth(), getHeight());
                    g2 = (Graphics2D) image.getGraphics();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fillRect(0, 0, getWidth(), getHeight());
                }
                g2.setColor(currentColor);
                g2.fillOval(x - brushSize / 2, y - brushSize / 2, brushSize, brushSize);
                repaint();
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, 0, 0, null);
                }
            }

            public void clearCanvas() {
                if (g2 != null) {
                    g2.setColor(getBackground());
                    g2.fillRect(0, 0, getWidth(), getHeight());
                    repaint();
                }
            }
        };

        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            if (canvas instanceof JPanel) {
                ((JPanel) canvas).repaint(); // Очистка вызывается перерисовкой
            }
        });

        JButton colorButton = new JButton("Change Color");
        colorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(frame, "Choose a Color", currentColor);
            if (newColor != null) {
                currentColor = newColor;
            }
        });

        JLabel sizeLabel = new JLabel("Brush Size:");
        JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(brushSize, 1, 50, 1));
        sizeSpinner.addChangeListener(e -> brushSize = (int) sizeSpinner.getValue());

        controls.add(clearButton);
        controls.add(colorButton);
        controls.add(sizeLabel);
        controls.add(sizeSpinner);

        frame.add(canvas, BorderLayout.CENTER);
        frame.add(controls, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimplePaint::new);
    }
}





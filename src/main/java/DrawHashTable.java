import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DrawHashTable extends JPanel {
    private ArrayList<String>[] table;
    private int animatingIndex = -1;
    private int animationProgress = 0;
    private static final int ANIMATION_STEPS = 20;
    private static final int ANIMATION_DELAY = 20;
    private Timer animationTimer;

    public DrawHashTable(ArrayList<String>[] table) {
        this.table = table;
    }

    public void startAnimation(String key, int index) {
        animatingIndex = index;
        animationProgress = 0;

        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }

        animationTimer = new Timer(ANIMATION_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animationProgress < ANIMATION_STEPS) {
                    animationProgress++;
                } else {
                    animationTimer.stop();
                    animatingIndex = -1;
                }
                repaint();
            }
        });
        animationTimer.start();
    }

    public void updateTable() {
        this.table = table;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawHashTable(g2d, table);
    }

    private void drawHashTable(Graphics g, ArrayList<String>[] table) {
        int x = 20;
        int y = 20;
        int width = 100;
        int height = 30;
        int padding = 10;
        int arrowSize = 10;

        for (int i = 0; i < table.length; i++) {
            ArrayList<String> current = table[i];
            int innerX = x + width + padding;

            // Draw hash code box
            g.setColor(Color.BLACK);
            g.drawRect(x, y + i * (height + padding), width, height);
            g.drawString("HashCode: " + i, x + 10, y + i * (height + padding) + 20);

            // Draw animated node moving into the linked list
            if (current != null && i == animatingIndex) {
                int animationX = x + width + padding + animationProgress * 10;
                g.setColor(Color.BLUE);
                g.drawRect(animationX, y + i * (height + padding), width, height);
                g.drawString("Key: " + current.get(current.size() - 1), animationX + 10, y + i * (height + padding) + 20);
            }

            if (current != null && i != animatingIndex) {
                // Draw arrow from hash code box to the first node
                int x1 = x + width; // end of hash code box
                int y1 = y + i * (height + padding) + height / 2; // Center of hash code box
                int x2 = innerX; // start of arrow line (start of first node)
                int y2 = y1; // center of hash code box

                // Draw arrow line
                g.setColor(Color.RED);
                g.drawLine(x1+120, y1, x2-10, y2);

                // Draw arrowhead


                // Move innerX to start drawing nodes
                innerX += width + padding;
            }

            if (current != null) {
                for (int j = 0; j < current.size(); j++) {
                    String key = current.get(j);
                    // Draw node rectangle
                    g.setColor(Color.BLUE);
                    g.drawRect(innerX, y + i * (height + padding), width, height);
                    g.drawString("Key: " + key, innerX + 10, y + i * (height + padding) + 20);

                    // Draw arrows to next node
                    int x1 = innerX + width; // Start of arrow line
                    int y1 = y + i * (height + padding) + height / 2;
                    int x2 = innerX + width + padding; // End of arrow line
                    int y2 = y1;

                    // Draw arrow line
                    g.setColor(Color.RED);
                    g.drawLine(x1, y1, x2 - 10, y2);

                    // Draw arrowhead
                    double arrowAngle = Math.atan2(y2 - y1, x2 - x1);
                    int arrowDx = (int) (arrowSize * Math.cos(arrowAngle - Math.PI / 6));
                    int arrowDy = (int) (arrowSize * Math.sin(arrowAngle - Math.PI / 6));
                    g.drawLine(x2, y2, x2 - arrowDx, y2 - arrowDy);

                    arrowDx = (int) (arrowSize * Math.cos(arrowAngle + Math.PI / 6));
                    arrowDy = (int) (arrowSize * Math.sin(arrowAngle + Math.PI / 6));
                    g.drawLine(x2, y2, x2 - arrowDx, y2 - arrowDy);

                    // Draw three parallel segments if it's the last node in the chain
                    if (j == current.size() - 1) {
                        g.setColor(Color.RED);
                        int segmentLength = 20;
                        int segmentSpacing = 13;
                        g.drawLine(innerX + width + segmentLength, y + i * (height + padding) + 13, innerX + width + segmentLength, y + i * (height + padding));
                        g.drawLine(innerX + width + segmentLength, y + i * (height + padding) + 13, innerX + width + segmentLength, y + i * (height + padding) + 26);
                        g.drawLine(innerX + width, y + i * (height + padding) + 13, innerX + width + segmentLength, y + i * (height + padding) + 13);
                        for (int k = 0; k < 3; k++) {
                            int segmentY = y + i * (height + padding) + k * segmentSpacing;
                            g.drawLine(innerX + width + 20, segmentY, innerX + width + segmentLength + 20, segmentY - 10);
                        }
                    }

                    innerX += width + padding;
                }
            }
        }
    }

}

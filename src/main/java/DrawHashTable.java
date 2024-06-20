import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DrawHashTable extends JComponent {

    private final ArrayList<String>[] table;



    public DrawHashTable(ArrayList<String>[] hashTable) {
        this.table = hashTable;

    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Arial", Font.BOLD, 14)); // Set font for table content
        drawTable(g2d, table);

    }

    private void drawTable(Graphics g2d, ArrayList<String>[] table) {
        int width = 100;
        int height = 50;
        int padding = 10;
        for (int i = 0; i < table.length; i++) {
            int x = padding;
            int y = (i * height) + padding;
            g2d.drawRect(x, y, width, height);
            g2d.drawString("[" + i + "]", x + padding, y + height / 2 + padding);

            for (int j = 0; j < table[i].size(); j++) {
                int previousX = x;
                x += width + padding;
                g2d.drawRect(x, y, width, height);
                g2d.drawString(table[i].get(j), x + padding, y + height / 2 + padding);
                // Draw connecting line
                g2d.drawLine(previousX + width, y + height / 2, x, y + height / 2);
            }
            // Draw end-of-list symbol if the list is not empty
            if (!table[i].isEmpty()) {
                int previousX = x;
                x += width + padding;
                g2d.drawLine(previousX + width, y + height / 2, x, y + height / 2);
                // Draw vertical line
                g2d.drawLine(x, y + padding, x, y + height - padding);
                // Draw horizontal dashes that do not cross the vertical line
                int dashLength = 6;
                int dashSpacing = 4;
                for (int dashY = y + padding - 1; dashY < y + height - padding; dashY += dashSpacing) {
                    g2d.drawLine(x, dashY + dashLength, x + dashLength, dashY - dashLength);

                }

            }
            repaint();


        }




    }



    }











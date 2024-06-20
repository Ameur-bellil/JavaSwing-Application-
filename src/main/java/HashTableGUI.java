import javax.swing.*;
import java.awt.*;

public class HashTableGUI {

    private HashTable hashTable;
    private DrawHashTable drawHashTable;

    public void createAndShowGUI() {
        hashTable = new HashTable(10);
        drawHashTable = new DrawHashTable(hashTable.getTable());

        // Create the frame
        JFrame frame = new JFrame("HashTable Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 900);

        // Draw panel
        JPanel drawPanel = new JPanel(new BorderLayout());
        drawPanel.add(drawHashTable, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        Dimension buttonSize = new Dimension(150, 50);

        JButton btn1 = new JButton("+");
        btn1.setPreferredSize(buttonSize);
        buttonPanel.add(btn1);

        JButton btn2 = new JButton("-");
        btn2.setPreferredSize(buttonSize);
        buttonPanel.add(btn2);

        JButton btn3 = new JButton("?");
        btn3.setPreferredSize(buttonSize);
        buttonPanel.add(btn3);

        JButton btn4 = new JButton("...");
        btn4.setPreferredSize(buttonSize);
        buttonPanel.add(btn4);

        // TextField and Label panel
        JPanel textFieldPanel = new JPanel(new GridLayout(2, 1));
        JTextField textField = new JTextField(10);
        textFieldPanel.add(textField);

        JLabel label = new JLabel("Res:");
        textFieldPanel.add(label);

        // Position panels
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(buttonPanel, BorderLayout.EAST);
        southPanel.add(textFieldPanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        // Add draw panel
        frame.add(drawPanel, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);

        // Add action listeners
        btn1.addActionListener(e -> {
            String input = textField.getText();
            if (!input.isEmpty()) {
                hashTable.add(input);
                label.setText("Added " + input);
                drawHashTable.startAnimation(input, hashTable.hash(input));
            } else {
                label.setText("Enter a value to add");
            }
        });

        btn2.addActionListener(e -> {
            String input = textField.getText();
            if (!input.isEmpty()) {
                hashTable.remove(input);
                label.setText("Removed " + input);
                drawHashTable.updateTable(); // Update and repaint the table
            } else {
                label.setText("Enter a value to remove");
            }
        });

        btn3.addActionListener(e -> {
            String input = textField.getText();
            if (!input.isEmpty()) {
                boolean contains = hashTable.contains(input);
                if (contains) {
                    label.setText(input + " found");
                } else {
                    label.setText(input + " not found");
                }
            } else {
                label.setText("Enter a value to search");
            }
        });

        btn4.addActionListener(e -> {
            label.setText("Displaying...");
            hashTable.display();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HashTableGUI gui = new HashTableGUI();
            gui.createAndShowGUI();
        });
    }
}

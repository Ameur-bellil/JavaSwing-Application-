import javax.swing.*;
import java.awt.*;

public class HashTableGUI {

    HashTable hashTable = new HashTable(10);

    public void createAndShowGUI() {

        // Create the frame
        JFrame frame = new JFrame("HashTable Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 900);


        // Draw panel
        JPanel drawPanel = new JPanel();
        DrawHashTable drawHashTable = new DrawHashTable(hashTable.table);
        drawPanel.setLayout(new GridLayout());
        drawPanel.add(new DrawHashTable(hashTable.table) , BorderLayout.CENTER);

        // Button panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        Dimension buttonSize = new Dimension(150, 50);
        JButton btn1 = new JButton("+");
        btn1.setPreferredSize(buttonSize);
        panel.add(btn1);
        JButton btn2 = new JButton("-");
        btn2.setPreferredSize(buttonSize);
        panel.add(btn2);
        JButton btn3 = new JButton("?");
        btn3.setPreferredSize(buttonSize);
        panel.add(btn3);
        JButton btn4 = new JButton("...");
        btn4.setPreferredSize(buttonSize);
        panel.add(btn4);



        // TextField and Label panel
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1));

        JTextField textField = new JTextField(10);
        panel2.add(textField);

        JLabel label = new JLabel("Res:");
        panel2.add(label);

        // Position panels
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(panel, BorderLayout.EAST);
        southPanel.add(panel2, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        // add draw panel
        frame.add(drawPanel, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);

        //add action listeners
        btn1.addActionListener(e1 -> {
            String input = textField.getText();
            if (!input.isEmpty()) {
                hashTable.add(input);
                label.setText("Added " + input);
            }else {
                label.setText("Enter a value to add");
            }
        });


        btn2.addActionListener(e -> {
            String input = textField.getText();
            if (!input.isEmpty()) {
                hashTable.remove(input);
                label.setText("removed " + input);
            }else {
                label.setText("Enter a value to remove");
            }
        });

        btn3.addActionListener(e -> {
            String input = textField.getText();
            if (!input.isEmpty()) {
                hashTable.contains(input);
                if ((hashTable.contains(input)))  {
                    label.setText(input + " found");
                } else {
                    label.setText(input + " not found");
                }
            }else {
                label.setText("Enter a value to search");
            }
        });

        btn4.addActionListener(e -> {
                    label.setText("Displaying...");
                    hashTable.display();

            });

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            HashTableGUI gui = new HashTableGUI();
            gui.createAndShowGUI();
        });
    }
}

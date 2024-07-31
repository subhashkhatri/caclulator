package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {
    private final JTextField display;
    private double num1, num2, result;
    private char operator;
    private boolean isOperatorClicked = false;

    public CalculatorApp() {
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 18));
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttons = {
                "1", "2", "3", "+",
                "4", "5", "6", "-",
                "7", "8", "9", "*",
                "0", ".", "=", "/",
                "%", "C"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(this);
            panel.add(button);
        }

        JLabel footer = new JLabel("Created by Chandan Kumar", SwingConstants.CENTER);
        footer.setFont(new Font("Arial", Font.BOLD, 15));
        footer.setForeground(Color.BLACK);

        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        setTitle("Calculator");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            if (isOperatorClicked) {
                display.setText(display.getText() + " " + command);
                isOperatorClicked = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if (command.equals("C")) {
            display.setText("");
        } else if (command.equals("=")) {
            String[] parts = display.getText().split(" ");
            if (parts.length == 3) {
                num1 = Double.parseDouble(parts[0]);
                operator = parts[1].charAt(0);
                num2 = Double.parseDouble(parts[2]);

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                    case '%':
                        result = (num1 * num2) / 100;
                        break;
                }
                display.setText(String.valueOf(result));
                num1 = result;
            }
        } else {
            if (!display.getText().isEmpty() && !isOperatorClicked) {
                display.setText(display.getText() + " " + command);
                isOperatorClicked = true;
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }
}

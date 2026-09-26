import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    JTextField display;

    JButton[] numberButtons = new JButton[10];

    JButton addButton;
    JButton subtractButton;
    JButton multiplyButton;
    JButton divideButton;
    JButton percentButton;
    JButton decimalButton;
    JButton equalsButton;
    JButton clearButton;
    JButton backspaceButton;

    double firstNumber = 0;
    double secondNumber = 0;
    double result = 0;

    String operator = "";

    public Calculator() {

        setTitle ("Simple Calculator");

        setSize (400,550);

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        display = new JTextField();

        display.setFont(new Font("Arial", Font.PLAIN,30));

        display.setHorizontalAlignment(JTextField.RIGHT);

        display.setEditable(false);

        display.setPreferredSize (new Dimension (320,80));

        add(display, BorderLayout.NORTH);

        JPanel panel =new JPanel();

        panel.setLayout(new GridLayout(5,4,5,4));

        panel.setBorder(
                BorderFactory.createEmptyBorder(8,8,8,8)
        );

        for (int i = 0; i<10; i++) {

            numberButtons[i] = new JButton(String.valueOf(i));

            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));

            numberButtons[i].addActionListener(this);
        }

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        percentButton = new JButton("%");

        decimalButton = new JButton(".");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");
        backspaceButton = new JButton("⌫");

        addButton.setFont(new Font("Arial", Font.PLAIN, 18));
        subtractButton.setFont(new Font("Arial", Font.PLAIN, 18));
        multiplyButton.setFont(new Font("Arial", Font.PLAIN, 18));
        divideButton.setFont(new Font("Arial", Font.PLAIN, 18));
        percentButton.setFont(new Font("Arial", Font.PLAIN, 18));
        decimalButton.setFont(new Font("Arial", Font.PLAIN, 18));
        equalsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        clearButton.setFont(new Font("Arial", Font.PLAIN, 18 ));

        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
        percentButton.addActionListener(this);

        decimalButton.addActionListener(this);
        equalsButton.addActionListener(this);
        clearButton.addActionListener(this);
        backspaceButton.addActionListener(this);

        panel.add(clearButton);
        panel.add(backspaceButton);
        panel.add(percentButton);
        panel.add(divideButton);
        panel.add(equalsButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(multiplyButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subtractButton);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(numberButtons[0]);
        panel.add(decimalButton);
        panel.add(equalsButton);

        add(panel,BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String buttonText = e.getActionCommand();

        if(buttonText.matches("[0-9]")) {

            display.setText(display.getText() + buttonText);
        }

        else if (buttonText.equals(".")) {

            if (!display.getText().contains(".")) {

                display.setText(display.getText() + ".");
            }
        }

        else if (buttonText.equals("+")) {

            setFirstNumber("+");
        }

        else if (buttonText.equals("-")) {

            setFirstNumber("-");
        }

        else if (buttonText.equals("*")) {

            setFirstNumber("*");
        }

        else if (buttonText.equals("/")) {

            setFirstNumber("/");
        }

        else if (buttonText.equals("%")) {

            if(!display.getText().isEmpty()) {

                double number =
                        Double.parseDouble(display.getText());

                result = number / 100;

                display.setText(formatNumber(result));
            }
        }

        else if (buttonText.equals("=")) {
            calculateResult();
        }

        else if (buttonText.equals("C")) {
            clearCalculator();
        }

        else if (buttonText.equals("⌫")) {
            String text = display.getText();
            if(!text.isEmpty()) {
                display.setText(
                        text.substring(0, text.length()-1)
                );
            }
        }
    }

    public void setFirstNumber(String selectedOperator) {

        if(display.getText().isEmpty()) {
            return;
        }

        firstNumber=
                Double.parseDouble(display.getText());

        operator = selectedOperator;

        display.setText("");
    }

    public void calculateResult() {

        if(display.getText().isEmpty()) {
            return;
        }

        secondNumber=
                Double.parseDouble(display.getText());

        try{

            if (operator.equals("+")) {
                result = firstNumber + secondNumber;
            }

            else if (operator.equals("-")) {
                result = firstNumber - secondNumber;
            }

            else if (operator.equals("*")){
                result = firstNumber * secondNumber;
            }

            else if (operator.equals("/")) {
                if (secondNumber == 0) {

                    display.setText("Cannot divide by 0");

                    return;
                }

                result = firstNumber / secondNumber;
            }

            display.setText(formatNumber(result));

            firstNumber = result;

            operator = "";
        } catch (Exception e) {

            display.setText("Error");
        }
    }

    public void clearCalculator() {

        display.setText("");

        firstNumber = 0;
        secondNumber = 0;
        result = 0;

        operator = "";
    }

    public String formatNumber(double number) {

        if(number == (long) number) {

            return String.valueOf((long) number);
        }else {
            return String.valueOf(number);
        }
    }

    public static void main(String[] args) {

        new Calculator();
    }
}

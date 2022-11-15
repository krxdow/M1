package web.service.client.main.gui;

import web.service.client.Calculator;
import web.service.client.CalculatorSoap;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class GUI extends JFrame {
    public boolean setClear=true;

    String op;
    int result;
    final int FRAME_WIDTH = 400;
    final int FRAME_HEIGHT = 400;
    final int HEIGHT = 30, WIDTH = 50, H_SPACE = 10, V_SPACE = 10;
    final int TOPX = 50, TOPY = 50;
    //    public boolean setClear = true;
    String[] digitButtonText = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0"};
    //    String[] controlInputButtonsText = { "="};
    String[] operatorButtonText = { "\u21FD","C","\u00F7", "x", "-", "+", "=", };

    DigitButton [] digitButton = new DigitButton[digitButtonText.length];
    OperatorButton operatorButton[] = new OperatorButton[operatorButtonText.length];
    JTextField displayLabel = new JTextField();
    JLabel alert =new JLabel("");
    static URL url;

    static {
        try {
            url = new URL("http://www.dneonline.com/calculator.asmx?WSDL");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    static Calculator calculator = new Calculator(url);
    static CalculatorSoap proxy = calculator.getCalculatorSoap();


    // Constructor
    public GUI() throws MalformedURLException {
        super("SOAP Calculatrice");

        int tempX = TOPX, y = TOPY;
        displayLabel.setBounds(tempX, y, FRAME_WIDTH-2*tempX -3, HEIGHT);
        Border border = BorderFactory.createLineBorder(Color.black);
        alert.setBounds(tempX, TOPY+30, FRAME_WIDTH-2*tempX -3, HEIGHT);
        displayLabel.setBorder(border);
        displayLabel.setText("");
        displayLabel.setBackground(Color.WHITE);
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int posWith = (device.getDisplayMode().getWidth() / 2) - (FRAME_WIDTH/2);
        int posHeight = (device.getDisplayMode().getHeight()/ 2) - (FRAME_WIDTH/2);
        alert.setForeground(Color.RED);
        super.setLocation(posWith, posHeight);

        displayLabel.setHorizontalAlignment(4);
        add(displayLabel);
        add(alert);

        tempX = 2*(WIDTH+TOPX)-7;
        y = TOPY + 1 * (HEIGHT + V_SPACE);
        int digitX = TOPX + WIDTH + H_SPACE;
        int digitY = TOPY + 2 * (HEIGHT + V_SPACE);
        tempX = TOPX;
        y = digitY;
        for (int i = 0; i < digitButton.length; i++) {
            if (digitButtonText[i].equals("0")) {
                digitButton[i] = new DigitButton(tempX, y, 3*WIDTH + 2*H_SPACE, HEIGHT, digitButtonText[i], this);
                digitButton[i].setForeground(Color.BLUE);
                tempX += 2*WIDTH + 2*H_SPACE;
            }
            else {
                digitButton[i] = new DigitButton(tempX, y, WIDTH, HEIGHT, digitButtonText[i], this);
                digitButton[i].setForeground(Color.BLUE);
                tempX += WIDTH + H_SPACE;
            }


            if ((i + 1) % 3 == 0) {
                tempX = TOPX;
                y += HEIGHT + V_SPACE;
            }
        }

        int opsX = digitX + WIDTH + H_SPACE;
        int opsY = digitY;
        tempX = opsX;
        y = opsY;
        for (int i = 0; i < operatorButton.length; i++) {
            tempX += WIDTH + H_SPACE;
            if (operatorButtonText[i].equals("=")) {
                operatorButton[i] = new OperatorButton(tempX, y, 2*WIDTH + H_SPACE, HEIGHT, operatorButtonText[i], this);
                operatorButton[i].setForeground(Color.WHITE);
                operatorButton[i].setBackground(Color.BLUE);
            } else {
                operatorButton[i] = new OperatorButton(tempX, y, WIDTH, HEIGHT, operatorButtonText[i], this);
                operatorButton[i].setForeground(Color.RED);
            }

            if ((i + 1) % 2 == 0) {
                tempX = opsX;
                y += HEIGHT + V_SPACE;
            }
        }

        displayLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if ((e.getKeyChar() >= '0' && e.getKeyChar()<='9') || e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == '+' || e.getKeyChar() == '-' || e.getKeyChar() == '*' || e.getKeyChar() == '/') {
                    String display = displayLabel.getText();
                    if(e.getKeyChar() == '+') {
                        displayLabel.setEditable(false);
//                        setClear=true;
//                        displayLabel.setText("");
                        Arrays.stream(operatorButton).filter( operatorButton1 -> operatorButton1.getText().equals("+")).findFirst().get().doClick();
                    }
                    else if(e.getKeyChar() == '-') {
                        displayLabel.setEditable(false);
//                        displayLabel.setText("");
                        setClear=true;
                        Arrays.stream(operatorButton).filter( operatorButton1 -> operatorButton1.getText().equals("-")).findFirst().get().doClick();
                    }
                    else if(e.getKeyChar() == '*') {
                        displayLabel.setEditable(false);
                        setClear=true;
//                        displayLabel.setText("");
                        Arrays.stream(operatorButton).filter( operatorButton1 -> operatorButton1.getText().equals("x")).findFirst().get().doClick();
                    }
                    else if(e.getKeyChar() == '/') {
                        displayLabel.setEditable(false);
                        setClear=true;
//                        displayLabel.setText("");
                        Arrays.stream(operatorButton).filter( operatorButton1 -> operatorButton1.getText().equals("\u00F7")).findFirst().get().doClick();
                    }
                    else if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                        displayLabel.setEditable(false);
//                        displayLabel.setText("");
                        Arrays.stream(operatorButton).filter( operatorButton1 -> operatorButton1.getText().equals("\u21FD")).findFirst().get().doClick();
                    }
                    else if(e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == '=') {
                        displayLabel.setEditable(false);
                        setClear=true;
                        System.out.println(e.getKeyChar());
                        Arrays.stream(operatorButton).filter( operatorButton1 -> operatorButton1.getText().equals("=")).findFirst().get().doClick();
                    }
                    else {
                        if (setClear)
                        {
                            displayLabel.setText("");
                            setClear = false;
                        }
                        displayLabel.setEditable(false);
                        String digit = String.valueOf(e.getKeyChar());
                        Arrays.stream(digitButton).filter( digitButton1 -> digitButton1.getText().equals(digit)).findFirst().get().doClick();
                    }
                    alert.setText("");
                }
                else {
                    displayLabel.setEditable(false);
                    alert.setText("Entrez des chiffres uniquement");
                }
            }
        });
        setBackground(Color.BLACK);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GUI.super.dispose();
            }
        });
        setLayout(null);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setVisible(true);
    }


    static class DigitButton extends JButton implements ActionListener {
        GUI cl;

        DigitButton(int x, int y, int width, int height, String cap, GUI clc) {
            super(cap);
            setBounds(x, y, width, height);
            this.cl = clc;
            this.cl.add(this);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent ev) {
            String tempButtonText = ((DigitButton) ev.getSource()).getText();
            if (cl.setClear) {
                cl.displayLabel.setText(""+tempButtonText);
                cl.setClear = false;
            }
            else {
                String tempInput = cl.displayLabel.getText();
                System.out.println(tempButtonText);
                cl.displayLabel.setText(tempInput+tempButtonText);
            }

        }//actionPerformed
    }//class defination



    static class OperatorButton extends JButton implements ActionListener {
        GUI cl;

        OperatorButton(int x, int y, int width, int height, String cap, GUI clc) {
            super(cap);
            setBounds(x, y, width, height);
            this.cl = clc;
            this.cl.add(this);
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent ev) {
            String opText = ((OperatorButton) ev.getSource()).getText();
            System.out.println("Temp input: "+cl.displayLabel.getText());
            cl.setClear =true;
            int temp = 0;
            if (!cl.displayLabel.getText().isEmpty())
                temp=Integer.parseInt(cl.displayLabel.getText());


            if (opText.equals("C")) {

                cl.result=0;
                cl.op="";
                cl.displayLabel.setText("");
                System.out.println("CLS Pressed"+cl.result);
                return;
            }
            if (opText.equals("\u21FD")) {
                cl.setClear =false;
                String tempInputText = cl.displayLabel.getText();
                if (tempInputText.isEmpty()) {
                    return;
                } else {
                    System.out.println(tempInputText);
                    tempInputText=tempInputText.substring(0, tempInputText.length() - 1);
                    cl.displayLabel.setText(tempInputText);
                    System.out.println(tempInputText);
                    return;
                }

            }
            if (!opText.equals("=")) {
                cl.displayLabel.setText("");
                cl.result=temp;
                cl.op = opText;
                cl.displayLabel.setText(String.valueOf(cl.result));
//                cl.result=0;
                return;
            }
// process = button pressed
            switch (cl.op) {
                case "+":
                    temp = proxy.add(temp, cl.result);
                    break;
                case "-":
                    temp = proxy.subtract(temp, cl.result);
                    break;
                case "x":
                    temp = proxy.multiply(temp, cl.result);
                    break;
                case "\u00F7":
                    try {
                        temp = proxy.divide(temp, cl.result);
                        System.out.println("REsult: "+cl.result+"  temp: "+temp);
                    } catch (ArithmeticException excp) {
                        cl.alert.setText("Division par 0");
                        return;
                    }
                    break;
            }//switch
            cl.displayLabel.setText(String.valueOf(temp));
        }//actionPerformed
    }//class
}

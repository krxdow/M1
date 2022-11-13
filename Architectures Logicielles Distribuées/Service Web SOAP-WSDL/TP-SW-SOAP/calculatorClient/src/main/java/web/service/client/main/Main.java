/**
 * @author Fanus Ludovic
 * @author AMAH GNIMDOU RICHARD
 */
/** * @author AMAH GNIMDOU RICHARD
 */

package web.service.client.main;

import web.service.client.Calculator;
import web.service.client.CalculatorSoap;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;


public class Main extends JFrame {
    final int FRAME_WIDTH = 400;
    final int FRAME_HEIGHT = 400;
    final int HEIGHT = 30, WIDTH = 50, H_SPACE = 10, V_SPACE = 10;
    final int TOPX = 50, TOPY = 50;
    public boolean setClear = true;

    String[] digitButtonText = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "-/+", "."};
    String[] controlInputButtonsText = { "CLS", "(",")","<-"};
    String[] operatorButtonText = { "Bksp","CLS","\u00F7", "x", "-", "+", "(",")",};

    DigitButton [] digitButton = new DigitButton[digitButtonText.length];
    OperatorButton operatorButton[] = new OperatorButton[operatorButtonText.length];
    SpecialButton specialButton[] = new SpecialButton[controlInputButtonsText.length];
 JTextField displayLabel = new JTextField();
// Label memLabel = new Label(" ", Label.RIGHT);


    // Constructor
    Main() {
        super("SOAP Calculatrice");

        int tempX = TOPX, y = TOPY;
        displayLabel.setBounds(tempX, y, FRAME_WIDTH-2*tempX -3, HEIGHT);
        Border border = BorderFactory.createLineBorder(Color.black);
    displayLabel.setBorder(border);

        displayLabel.setBackground(Color.WHITE);
//        displayLabel.addKeyListener();
//        displayLabel.setForeground(Color.WHITE);
        add(displayLabel);

//        memLabel.setBounds(TOPX, TOPY + HEIGHT + V_SPACE, WIDTH, HEIGHT);
//        add(memLabel);

        tempX = TOPX; // + 1 * (WIDTH + H_SPACE);
        y = TOPY + 1 * (HEIGHT + V_SPACE);
        for (int i = 0; i < specialButton.length; i++) {
            specialButton[i] = new SpecialButton(tempX, y, WIDTH , HEIGHT, controlInputButtonsText[i], this);
            specialButton[i].setForeground(Color.RED);

            tempX = tempX +  WIDTH + H_SPACE;
        }


        int digitX = TOPX + WIDTH + H_SPACE;
        int digitY = TOPY + 2 * (HEIGHT + V_SPACE);
        tempX = TOPX;
        y = digitY;
        for (int i = 0; i < digitButton.length; i++) {
            digitButton[i] = new DigitButton(tempX, y, WIDTH, HEIGHT, digitButtonText[i], this);
            digitButton[i].setForeground(Color.BLUE);
            tempX += WIDTH + H_SPACE;
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
            operatorButton[i] = new OperatorButton(tempX, y, WIDTH, HEIGHT, operatorButtonText[i], this);
            operatorButton[i].setForeground(Color.RED);
            if ((i + 1) % 2 == 0) {
                tempX = opsX;
                y += HEIGHT + V_SPACE;
            }
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Main.super.dispose();
            }
        });

        setLayout(null);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
//        setResizable(false);
        setVisible(true);

    }


    public static void main(String[] args) throws MalformedURLException {
        new Main();
        try {
            URL url = new URL("http://www.dneonline.com/calculator.asmx?WSDL");
            Calculator calculator = new Calculator(url);

            /*<wsdl:port name="CalculatorSoap" binding="tns:CalculatorSoap">*/
            CalculatorSoap proxy = calculator.getCalculatorSoap();

            int a = 10;
            int b = 12;
            System.out.println(a + " + " + b + " = " + proxy.add(a, b));
            System.out.println(a + " - " + b + " = " + proxy.subtract(a, b));

        } catch (MalformedURLException exception) {
            exception.printStackTrace();
        }

    }

}


class DigitButton extends Button implements ActionListener {
    Main cl;

    //////////////////////////////////////////
    DigitButton(int x, int y, int width, int height, String cap, Main clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        this.cl.add(this);
        addActionListener(this);
    }

    ////////////////////////////////////////////////
    static boolean isInString(String s, char ch) {
        for (int i = 0; i < s.length(); i++) if (s.charAt(i) == ch) return true;
        return false;
    }

    /////////////////////////////////////////////////
    public void actionPerformed(ActionEvent ev) {
        String tempText = ((DigitButton) ev.getSource()).getLabel();

        if (tempText.equals(".")) {
            if (cl.setClear) {
                cl.displayLabel.setText("0.");
                cl.setClear = false;
            } else if (!isInString(cl.displayLabel.getText(), '.'))
                cl.displayLabel.setText(cl.displayLabel.getText() + ".");
            return;
        }

        int index = 0;
        try {
            index = Integer.parseInt(tempText);
        } catch (NumberFormatException e) {
            return;
        }

        if (index == 0 && cl.displayLabel.getText().equals("0")) return;

        if (cl.setClear) {
            cl.displayLabel.setText("" + index);
            cl.setClear = false;
        } else
            cl.displayLabel.setText(cl.displayLabel.getText() + index);
    }//actionPerformed
}//class defination

/********************************************/

class OperatorButton extends Button implements ActionListener {
    Main cl;

    OperatorButton(int x, int y, int width, int height, String cap, Main clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        this.cl.add(this);
        addActionListener(this);
    }

    ///////////////////////
    public void actionPerformed(ActionEvent ev) {
        String opText = ((OperatorButton) ev.getSource()).getLabel();

        cl.setClear = true;
        double temp = Double.parseDouble(cl.displayLabel.getText());

        if (opText.equals("1/x")) {
            try {
                double tempd = 1 / (double) temp;
//                cl.displayLabel.setText(Main.getFormattedText(tempd));
            } catch (ArithmeticException excp) {
                cl.displayLabel.setText("Divide by 0.");
            }
            return;
        }
        if (opText.equals("sqrt")) {
            try {
                double tempd = Math.sqrt(temp);
//                cl.displayLabel.setText(MyCalculator.getFormattedText(tempd));
            } catch (ArithmeticException excp) {
                cl.displayLabel.setText("Divide by 0.");
            }
            return;
        }
//        if (!opText.equals("=")) {
//            cl.number = temp;
//            cl.op = opText.charAt(0);
//            return;
//        }
//// process = button pressed
//        switch (cl.op) {
//            case '+':
//                temp += cl.number;
//                break;
//            case '-':
//                temp = cl.number - temp;
//                break;
//            case '*':
//                temp *= cl.number;
//                break;
//            case '%':
//                try {
//                    temp = cl.number % temp;
//                } catch (ArithmeticException excp) {
//                    cl.displayLabel.setText("Divide by 0.");
//                    return;
//                }
//                break;
//            case '/':
//                try {
//                    temp = cl.number / temp;
//                } catch (ArithmeticException excp) {
//                    cl.displayLabel.setText("Divide by 0.");
//                    return;
//                }
//                break;
//        }//switch
//
//        cl.displayLabel.setText(Main.getFormattedText(temp));
//cl.number=temp;
    }//actionPerformed
}//class

class SpecialButton extends Button implements ActionListener {
    Main cl;

    SpecialButton(int x, int y, int width, int height, String cap, Main clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        this.cl.add(this);
        addActionListener(this);
    }

    //////////////////////
    static String backSpace(String s) {
        String Res = "";
        for (int i = 0; i < s.length() - 1; i++) Res += s.charAt(i);
        return Res;
    }

    //////////////////////////////////////////////////////////
    public void actionPerformed(ActionEvent ev) {
        String opText = ((SpecialButton) ev.getSource()).getLabel();
//check for backspace button
        if (opText.equals("Backspc")) {
            String tempText = backSpace(cl.displayLabel.getText());
            if (tempText.equals(""))
                cl.displayLabel.setText("0");
            else
                cl.displayLabel.setText(tempText);
            return;
        }
//check for "C" button i.e. Reset
        if (opText.equals("C")) {
//            cl.number = 0.0;
//            cl.op = ' ';
//            cl.memValue = 0.0;
//            cl.memLabel.setText(" ");
        }

//it must be CE button pressed
//        cl.displayLabel.setText("0");
//        cl.setClear = true;
    }//actionPerformed
}

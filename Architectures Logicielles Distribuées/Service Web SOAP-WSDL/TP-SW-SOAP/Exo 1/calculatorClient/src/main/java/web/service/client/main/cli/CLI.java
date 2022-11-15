package web.service.client.main.cli;

import web.service.client.Calculator;
import web.service.client.CalculatorSoap;
import web.service.client.main.cli.calc.Calc;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CLI {

        URL url = new URL("http://www.dneonline.com/calculator.asmx?WSDL");
        Calculator calculator = new Calculator(url);
        /*<wsdl:port name="CalculatorSoap" binding="tns:CalculatorSoap">*/
        CalculatorSoap proxy = calculator.getCalculatorSoap();
private Scanner scanner;

    public CLI() throws MalformedURLException {
    }

    public void start() {



    }
}


// For later


//    private final Scanner scanner;
//    private final Calc calc;
//    private final Writer output;
//
//    public CLI(Reader input, Calc calculator, Writer output) {
//        this.scanner = new Scanner(input);
//        this.calc = calculator;
//        this.output = output;
//    }
//
//    public void start() throws IOException {
//        while (true) {
//            // Read
//            this.write("Calculate: ");
//            String line = this.scanner.nextLine();
//
//            // Evaluate
//            if (line.equalsIgnoreCase("exit") || line.isEmpty()) {
//                this.writeLine("\tGoodbye");
//                break;
//            }
//            Integer result = this.calc.calculate(line);
//
//            // Print
//            this.writeLine("\t" + result.toString());
//        }
//    }
//
//    private void write(String message) throws IOException {
//        this.output.write(message);
//        this.output.flush();
//    }
//
//    private void writeLine(String line) throws IOException {
//        this.write(line + "\n");
//    }
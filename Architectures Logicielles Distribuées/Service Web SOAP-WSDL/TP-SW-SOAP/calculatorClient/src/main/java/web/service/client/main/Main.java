/**
 * @author Fanus Ludovic
 */
/** * @author AMAH GNIMDOU RICHARD
 */

package web.service.client.main;
import web.service.client.Calculator;
import web.service.client.CalculatorSoap;

import java.net.MalformedURLException;
import java.net.URL;


public class Main {
 public static void main(String[] args) throws MalformedURLException {

  try {
   URL url = new URL("http://www.dneonline.com/calculator.asmx?WSDL");
   Calculator calculator = new Calculator(url);

   /*<wsdl:port name="CalculatorSoap" binding="tns:CalculatorSoap">*/
   CalculatorSoap proxy = calculator.getCalculatorSoap();

   int a = 10 ; int b = 12;
   System.out.println(a+" + "+b+" = "+proxy.add(a,b));
   System.out.println(a+" - "+b+" = "+proxy.subtract(a,b));

  }catch (MalformedURLException exception){
   exception.printStackTrace();
  }

 }

}

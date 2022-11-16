/**
 * @author Fanus Ludovic
 * @author AMAH GNIMDOU RICHARD
 */

package web.service.client.main;

import web.service.client.main.gui.GUI;
import java.net.MalformedURLException;
import java.util.Scanner;


public class Main  {

    public static void main(String[] args) throws MalformedURLException {

        Scanner scanner = new Scanner(System.in);
        new GUI();
    }
}


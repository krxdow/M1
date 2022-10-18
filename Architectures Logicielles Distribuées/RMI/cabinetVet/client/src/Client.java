/**
 * @author FANUS LUDOVIC@author AMAH GNIMDOU RICHARD
 */
/**@author AMAH GNIMDOU RICHARD*/

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import javax.swing.*;

public class Client {
    private Client() {
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            //same port as server, search registry local
            Registry registry = LocateRegistry.getRegistry(host);

            //question petit 4
            IAnimal stub = (IAnimal) registry.lookup("Animal");
            stub.setName("zack");
            Espece especeCopy = new Espece("canus lupus", 15);
            stub.setEspeseObj(especeCopy.getname(), especeCopy.getlifeExpectancy());

            System.out.println(stub.getAnimalObj());
            int exit = 0;
            do {

                System.out.println("1: Lancer en CLI");
                System.out.println("2: Lancer en GUI");
                System.out.println("0: Arret immediat");
                Scanner scanner = new Scanner(System.in);
                int entree = scanner.nextInt();
                int sortie = 0;
                switch (entree) {
                    case 0:
                        System.out.println("Arret du programme");
                        exit = 0;
                        break;
                    case 1:
                        exit = 1;
                        System.out.println("Passage en CLI");
                        // Consulter la liste des animaux
                        // ajouter des animaux
                        //
                        try {
                            int cli=0;
                            final String osName = System.getProperty("os.name");
                            boolean windows= osName.contains("Windows");

                            do {

                                Scanner scCli = new Scanner(System.in);
                                if (windows) Runtime.getRuntime().exec("cls");
                                else Runtime.getRuntime().exec("clear");
                                System.out.println("****************************************************************************************");
                                System.out.println("                                       ALERTE");
                                System.out.println("                                       SEUIL:");
                                System.out.println("****************************************************************************************");
                                System.out.println("1: Listez les patients");
                                System.out.println("2: Ajouter un patient");
                                System.out.println("3: rechercher un patient");
                                System.out.println("4: modifier un patient");
                                System.out.println("0: Terminez le programme");

                            } while (cli!=0);

                        } catch (final Exception exception) {
                            exception.printStackTrace();
                        }

                        break;
                    case 2:
                        exit = 2;
                        System.out.println("Chargement GUI");
                        break;
                }
            } while (exit != 0);
//


        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}


//String reponse = stub.test();
// System.out.println("response du serveur"+ reponse);

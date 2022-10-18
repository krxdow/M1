/**
 * @author FANUS LUDOVIC@author AMAH GNIMDOU RICHARD
 */
/**@author AMAH GNIMDOU RICHARD*/

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import javax.swing.*;

public class Client extends UnicastRemoteObject implements IDistributedClient {
    private Client() throws RemoteException{
//        super();
    }



    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            //same port as server, search registry local
            Registry registry = LocateRegistry.getRegistry(host);

            //question petit 4
//            IAnimal stub = (IAnimal) registry.lookup("Animal");
//            stub.setName("zack");
//            Espece especeCopy = new Espece("canus lupus", 15);

//            stub.setEspeseObj(especeCopy.getname(), especeCopy.getlifeExpectancy());

//            System.out.println(stub.getAnimalObj());
            ICabinetVeterinaire cabinet = (ICabinetVeterinaire) registry.lookup("CabinetVet");
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
                                int choix;
                                if (windows) Runtime.getRuntime().exec("cls");
                                else Runtime.getRuntime().exec("clear");


                               do {
                                   System.out.println("1: Listez les patients");
                                   System.out.println("2: Ajouter un patient");
                                   System.out.println("3: rechercher un patient");
                                   System.out.println("4: modifier un patient");
                                   System.out.println("0: Terminez le programme");

                                   choix = scCli.nextInt();
                               } while (choix >= 0 && choix <5);


                                switch (choix) {
                                    case 0:
                                        System.exit(0);
                                    case 1:

                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                        break;
                                    case 4:
                                        break;

                                }

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

    /**
     * @return 
     * @throws RemoteException
     */
    @Override
    public void checkAlert() throws RemoteException {

        System.out.println("\n\n");
        System.out.println("************************************************************************************");
        System.out.println("                                    ALERTE");
        System.out.println("                     LE NOUVEAU SEUIL EST DE "+""+"PATIENTS");
        System.out.println("************************************************************************************\n\n");

    }
}



//String reponse = stub.test();
// System.out.println("response du serveur"+ reponse);

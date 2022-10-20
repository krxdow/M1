/**
 * @author FANUS LUDOVIC
 * @author AMAH GNIMDOU RICHARD
 */

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

//import javax.swing.*;
//
public class Client extends UnicastRemoteObject implements IDistributedClient {
    public Client() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
    }


    public static void main(String[] args) throws RemoteException {
IDistributedClient boundClient = new Client();
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

            try {
                int cli = 0;
                final String osName = System.getProperty("os.name");
                boolean windows = osName.contains("Windows");

                do {

                    Scanner scCli = new Scanner(System.in);
                    int choix;
                    if (windows) Runtime.getRuntime().exec("cls");
                    else Runtime.getRuntime().exec("clear");


                    do {
                        System.out.println("1: Listez les patients");
                        System.out.println("2: Ajouter un patient");
                        System.out.println("3: rechercher un patient");
//                        System.out.println("4: modifier un patient");
                        System.out.println("0: Terminez le programme");

                        choix = scCli.nextInt();
                    } while (choix > 0 && choix < 3);


                    switch (choix) {
                        case 0:
                            System.exit(0);
                        case 1:
                            List<Animal> allAnimal = cabinet.searchAllAnimal();
                            System.out.println("*********************************************************************************************");
                            System.out.println("                            LISTE DES PATIENTS");
                            System.out.println("*********************************************************************************************");
                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                            System.out.println("|            Nom          |     Nom du maitre(esse)        |           Espece        |  Duree de vie |     Race      |");
                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                           for (Animal patient: allAnimal) {
                               System.out.printf("|%-25s|%-32s|%-25s|%15d|%-15s|", patient.getName().length()>25?patient.getName().substring(0,25):patient.getName()
                                       ,patient.getNameMaster().length()>25?patient.getNameMaster().substring(0,25):patient.getNameMaster()
                                       ,patient.getEspeseObj().getname().length() > 25 ? patient.getEspeseObj().getname().substring(0,25) : patient.getEspeseObj().getname()
                                       , patient.getEspeseObj().getlifeExpectancy()
                                       , patient.getRace().length() > 15 ? patient.getRace().substring(0,15) : patient.getRace()  );
                               System.out.println("+-------------------------+--------------------------------+------------------+---------------+----------------+");

                           }
                            break;
                        case 2:
                            Scanner info = new Scanner(System.in);
                            System.out.println("VOUS ETES EN TRAIN D'AJOUTER UN PATIENT\n\n");
                            System.out.println("Veuillez renseigner les informations de l'animal patient");
                            System.out.println("Nom du patient:");
                            String name = info.nextLine();
                            System.out.println("Nom du maitre ou maitresse du patient:");
                            String nameMaster = info.nextLine();
                            System.out.println("Espece du patient:");
                            String specieName = info.nextLine();
                            System.out.println("Duree de vie moyen selon l'espece:");
                            int speciesAverageLife = Integer.parseInt(info.nextLine());
                            System.out.println("Race du patient:");
                            String race = info.nextLine();
                            System.out.println("Dossier de Suivi du patient");
                            System.out.println("Une description de l'etat de suivi du patient: ");
                            String followUp = info.nextLine();
                            int insert = cabinet.addPatient(name, nameMaster, specieName, speciesAverageLife, race, followUp);
                            if (insert == -2) {
                                System.out.println("Nous avons deja un tel patient de nom " + name + " ayant pour maitre(sse) " + nameMaster);
                            } else if (insert == -1) {
                                System.out.println("Une erreur s'est produite lors de l'ajout de patient");
                            } else {
                                System.out.println("Patient Enregistre'");
                            }
                            break;
                        case 3:
                            List<Animal> searchResult = cabinet.searchAllAnimal();
                            System.out.println("*********************************************************************************************");
                            System.out.println("                            RESULTAT DE LA RECHERCHE");
                            if(searchResult.size()>1)
                                System.out.println("                            Patients potententiellement recherche's");
                            System.out.println("*********************************************************************************************");
                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                            System.out.println("|            Nom          |     Nom du maitre(esse)        |           Espece        |  Duree de vie |     Race      |");
                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                            for (Animal patient: searchResult) {
                                System.out.printf("|%-25s|%-32s|%-25s|%15d|%-15s|", patient.getName().length()>25?patient.getName().substring(0,25):patient.getName()
                                        ,patient.getNameMaster().length()>25?patient.getNameMaster().substring(0,25):patient.getNameMaster()
                                        ,patient.getEspeseObj().getname().length() > 25 ? patient.getEspeseObj().getname().substring(0,25) : patient.getEspeseObj().getname()
                                        , patient.getEspeseObj().getlifeExpectancy()
                                        , patient.getRace().length() > 15 ? patient.getRace().substring(0,15) : patient.getRace()  );
                                System.out.println("+-------------------------+--------------------------------+------------------+---------------+----------------+");

                            }
                            break;
//                        case :
//                            break;

                    }

                } while (cli != 0);

            } catch (final Exception exception) {
                exception.printStackTrace();
            }
//            int exit = 0;
//            do {
//
//                System.out.println("1: Lancer en CLI");
//                System.out.println("2: Lancer en GUI");
//                System.out.println("0: Arret immediat");
//                Scanner scanner = new Scanner(System.in);
//                int entree = scanner.nextInt();
//                int sortie = 0;
//                switch (entree) {
//                    case 0:
//                        System.out.println("Arret du programme");
//                        exit = 0;
//                        break;
//                    case 1:
//                        exit = 1;
//                        System.out.println("Passage en CLI");
//                        // Consulter la liste des animaux
//                        // ajouter des animaux
//                        //
//
//
//                        break;
//                    case 2:
//                        exit = 2;
//                        System.out.println("Chargement GUI");
//                        break;
//                }
//            } while (exit != 0);
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
    public void checkAlert(int threshold) throws RemoteException {

        System.out.println("\n\n");
        System.out.println("************************************************************************************");
        System.out.println("                             NOUVELLE ALERTE:");
        System.out.println("                     LE NOUVEAU SEUIL EST DE " + "" + "PATIENTS");
        System.out.println("************************************************************************************\n\n");

    }


}


//String reponse = stub.test();
// System.out.println("response du serveur"+ reponse);

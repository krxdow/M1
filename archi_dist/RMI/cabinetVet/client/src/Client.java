/**
 * @author FANUS LUDOVIC
 * @author AMAH GNIMDOU RICHARD
 */

package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
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
            cabinet.bindClientToCabinet(boundClient);
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
                        System.out.println("3: Rechercher un patient");
                        System.out.println("4: Supprimer un patient");
                        System.out.println("0: Terminez le programme");

                        choix = scCli.nextInt();
                    } while (choix > 0 && choix < 4);


                    switch (choix) {
                        case 0:
                            cabinet.unbindClientToCabinet(boundClient);
                            System.exit(0);
                        case 1:
                            List<Animal> allAnimal = cabinet.searchAllAnimal();
                            System.out.println("*********************************************************************************************");
                            System.out.println("                            LISTE DES PATIENTS");
                            System.out.println("*********************************************************************************************");
                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                            System.out.println("|            Nom          |     Nom du maitre(esse)        |           Espece        |  Duree de vie |     Race      |");
                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                            for (Animal patient : allAnimal) {
                                System.out.printf("|%-25s|%-32s|%-25s|%15d|%-15s|", patient.getName().length() > 25 ? patient.getName().substring(0, 25) : patient.getName()
                                        , patient.getNameMaster().length() > 25 ? patient.getNameMaster().substring(0, 25) : patient.getNameMaster()
                                        , patient.getEspeseObj().getname().length() > 25 ? patient.getEspeseObj().getname().substring(0, 25) : patient.getEspeseObj().getname()
                                        , patient.getEspeseObj().getlifeExpectancy()
                                        , patient.getRace().length() > 15 ? patient.getRace().substring(0, 15) : patient.getRace());
                                System.out.println("+-------------------------+--------------------------------+------------------+---------------+----------------+");

                            }
                            break;
                        case 2:
                            int opSuccess = 1;
                            String retry = "";
                            do {

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
                                    opSuccess = -2;
                                    System.out.println("Nous avons deja un tel patient de nom " + name + " ayant pour maitre(sse) " + nameMaster);


                                } else if (insert == -1) {
                                    opSuccess = -1;
                                    System.out.println("Une erreur s'est produite lors de l'ajout de patient");
                                    do {
                                        Scanner rep1 = new Scanner(System.in);
                                        System.out.println("Voulez-vous reesayer l'ajout? (O)ui/(N)on: ");
                                        retry = rep1.nextLine();
                                    } while (retry.trim() != "oui".toUpperCase() || retry.trim() != "non".toUpperCase() || retry.trim() != "n".toUpperCase() || retry.trim() != "o".toUpperCase());
                                } else {
                                    opSuccess = 0;
                                    System.out.println("Patient Enregistre'");
                                }
                            } while ((opSuccess == -1 || opSuccess == -2) && (retry.trim() != "oui".toUpperCase() || retry.trim() != "o".toUpperCase()));
                            break;
                        case 3:
                            List<Animal> searchResult = cabinet.searchAllAnimal();
                            System.out.println("*********************************************************************************************");
                            System.out.println("                            RESULTATS DE LA RECHERCHE");
                            if (searchResult.size() > 1)
                                System.out.println("                            Patients potententiellement recherche's");
                            System.out.println("*********************************************************************************************");
                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                            System.out.println("|            Nom          |     Nom du maitre(esse)        |           Espece        |  Duree de vie |     Race      |");
                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                            for (Animal patient : searchResult) {
                                System.out.printf("|%-25s|%-32s|%-25s|%15d|%-15s|", patient.getName().length() > 25 ? patient.getName().substring(0, 25) : patient.getName()
                                        , patient.getNameMaster().length() > 25 ? patient.getNameMaster().substring(0, 25) : patient.getNameMaster()
                                        , patient.getEspeseObj().getname().length() > 25 ? patient.getEspeseObj().getname().substring(0, 25) : patient.getEspeseObj().getname()
                                        , patient.getEspeseObj().getlifeExpectancy()
                                        , patient.getRace().length() > 15 ? patient.getRace().substring(0, 15) : patient.getRace());
                                System.out.println("+-------------------------+--------------------------------+------------------+---------------+----------------+");

                            }
                            break;
                        case 4:
                            String confirm;
                            String retryDel = null;
                            do {
                                List<Animal> animalTobeDeleted = cabinet.searchAllAnimal();
                                System.out.println("*********************************************************************************************");
                                System.out.println("                            LISTE DES PATIENTS");
                                System.out.println("*********************************************************************************************");
                                System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                                System.out.println("|            Nom          |     Nom du maitre(esse)        |           Espece        |  Duree de vie |     Race      |");
                                System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                                for (Animal patient : animalTobeDeleted) {
                                    System.out.printf("|%-25s|%-32s|%-25s|%15d|%-15s|", patient.getName().length() > 25 ? patient.getName().substring(0, 25) : patient.getName()
                                            , patient.getNameMaster().length() > 25 ? patient.getNameMaster().substring(0, 25) : patient.getNameMaster()
                                            , patient.getEspeseObj().getname().length() > 25 ? patient.getEspeseObj().getname().substring(0, 25) : patient.getEspeseObj().getname()
                                            , patient.getEspeseObj().getlifeExpectancy()
                                            , patient.getRace().length() > 15 ? patient.getRace().substring(0, 15) : patient.getRace());
                                    System.out.println("+-------------------------+--------------------------------+------------------+---------------+----------------+");

                                }
                                Scanner delete = new Scanner(System.in);
                                System.out.println("Quel patient voluez-vous supprimer?");
                                System.out.println("Saisissez le nom du patient:");
                                String nameDel = delete.nextLine();
                                System.out.println("Saisissez le nom du patient:");
                                String nameMasterDel = delete.nextLine();
                                do {
                                    System.out.println("Etes-vous sur de vouloir supprimer ce patient? (O)ui/(N)on :");
                                    confirm = delete.nextLine();
                                } while (confirm.trim() != "oui".toUpperCase() ||
                                        confirm.trim() != "non".toUpperCase() ||
                                        confirm.trim() != "n".toUpperCase() ||
                                        confirm.trim() != "o".toUpperCase());
                                if (confirm.trim() == "oui".toUpperCase() ||
                                        confirm.trim() == "o".toUpperCase())
                                {  int delSucces=cabinet.deletePatient(nameDel + " " + nameMasterDel);
                                    if(delSucces==-1)
                                    {
                                        System.out.println("Le patient spe'cifie' n'a pas e'te' retrouve'");

                                        do {
                                            Scanner rep1 = new Scanner(System.in);
                                            System.out.println("Voulez-vous reesayer la suppression de patient? (O)ui/(N)on: ");
                                            retryDel = rep1.nextLine();
                                        } while (retryDel.trim() != "oui".toUpperCase() || retryDel.trim() != "non".toUpperCase() || retryDel.trim() != "n".toUpperCase() || retryDel.trim() != "o".toUpperCase());

                                    }
                                    System.out.println("Patient supprime'");

                                }
                            } while (retryDel.trim() == "oui".toUpperCase() || retryDel.trim() == "o".toUpperCase());

                            break;

                    }

                } while (cli != 0);

            } catch (final Exception exception) {
                exception.printStackTrace();
            }
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


                        break;
                    case 2:
                        exit = 2;
                        System.out.println("Chargement GUI");
                        // Main Window Frame
                        JFrame mainFrame = new JFrame("Cabinet Veterinaire");
                        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        // App's Toolbar
                        JToolBar toolbar = new JToolBar();
                        toolbar.setRollover(true);
                        JButton list  = new JButton("Lister patient");
                        toolbar.add(lister);
                        JButton add  = new JButton("Ajouter");
                        toolbar.add(add);
                        JButton search  = new JButton("Rechercher");
                        toolbar.add(search);
                        JButton delete  = new JButton("Supprimer un patient");
                        toolbar.add(delete);

                        //
                        Container contentPane = mainFrame.getContentPane();
                        contentPane.add(toolbar);
                        break;
                }
            } while (exit != 0);



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

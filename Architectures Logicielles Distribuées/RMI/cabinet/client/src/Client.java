/**
 * @author FANUS LUDOVIC
 * @author AMAH GNIMDOU RICHARD
 */


//import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;


//
public class Client extends UnicastRemoteObject implements IDistributedClient {
    public Client() throws RemoteException {
    }

    public static void main(String[] args) throws RemoteException {
        IDistributedClient boundClient = new Client();
        String host = (args.length < 1) ? null : args[0];
        try {
            //same port as server, search registry local
            Registry registry = LocateRegistry.getRegistry(1099);

            //question petit 4
//            IAnimal stub = (IAnimal) registry.lookup("Animal");
//            stub.setName("zack");
//            Espece especeCopy = new Espece("canus lupus", 15);
//            stub.setEspeseObj(especeCopy.getname(), especeCopy.getlifeExpectancy());
//            System.out.println(stub.getAnimalObj());

            ICabinetVeterinaire cabinet = (ICabinetVeterinaire) registry.lookup("cabinet");
            cabinet.bindClientToCabinet(boundClient);

            String searchName = "";
            int exit = 99;
            do {
                int entree = 99;
                do {
                    System.out.println("1: Lancer en CLI");
//                    System.out.println("2: Lancer en GUI");
                    System.out.println("0: Arret immediat");
                    System.out.print(">  ");
                    Scanner scanner = new Scanner(System.in);
                    entree = scanner.nextInt();
                } while ((exit < 0 && exit > 2));

//                int sortie = 0;
                switch (entree) {
                    case 0:
                        System.out.println("Arret du programme en cours...");
                        cabinet.unbindClientToCabinet(boundClient);
                        exit = 0;
                        System.exit(0);
                        break;
                    case 1:
                        exit = 1;
                        System.out.println("Passage en CLI");
                        try {
                            int cli = 1;
                            final String osName = System.getProperty("os.name");
                            boolean windows = osName.contains("windows");

                            do {
                                Scanner scCli = new Scanner(System.in);
                                int choix = 0;
                                System.out.println("Var OS: " + windows);
                                System.out.println("Running OS: " + osName);
                                if (windows) Runtime.getRuntime().exec("cls");
                                else Runtime.getRuntime().exec("clear");
                                do {
                                    System.out.println("1: Listez les patients");
                                    System.out.println("2: Ajouter un patient");
                                    System.out.println("3: Rechercher un patient");
                                    System.out.println("4: Supprimer un patient");
                                    System.out.println("0: Sortir de l'interface CLI");
                                    System.out.print(">>  ");
                                    choix = scCli.nextInt();
                                    System.out.println();
                                    cli = choix;
                                } while (choix < 0 && choix > 4);


                                switch (choix) {
                                    case 0:
                                        cabinet.unbindClientToCabinet(boundClient);
                                        break;
                                    case 1:
                                        List<IAnimal> allAnimal = cabinet.searchAllAnimal();
                                        System.out.println("**********************************************************************************************************************");
                                        System.out.println("                            LISTE DES PATIENTS");
                                        System.out.println("**********************************************************************************************************************");
                                        String notSpecified = "NON SPECIFE";
                                        if (allAnimal.size() <= 0) {
                                            System.out.println("             LA LISTE DES PATIENTS EST VIDE");
                                        } else {
                                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                                            System.out.println("|            Nom          |     Nom du maitre(esse)        |           Espece        |  Duree de vie |     Race      |");
                                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                                            for (IAnimal patient : allAnimal) {
                                                String averageLif = patient.getEspeseObj().getlifeExpectancy() + " ans";
                                                System.out.printf("|%-25s|%-32s|%-25s|%15s|%-15s|\n", patient.getName().length() > 25 ? patient.getName().substring(0, 25) : patient.getName()
                                                        , patient.getNameMaster().length() > 25 ? patient.getNameMaster().substring(0, 25) : patient.getNameMaster()
                                                        , patient.getEspeseObj().getname().length() > 25 ? patient.getEspeseObj().getname().substring(0, 25) : patient.getEspeseObj().getname()
                                                        , patient.getEspeseObj().getlifeExpectancy() <0? notSpecified : averageLif
                                                        , patient.getRace().length() > 15 ? patient.getRace().substring(0, 15) : patient.getRace());
                                            }
                                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                                        }

                                        break;
                                    case 2:
                                        int opSuccess = 1;
                                        String retry = "";
                                        String name ="";
                                        String nameMaster ="";
                                        String specieName = "";
                                        String followUp ="";
                                        do {

                                            Scanner info = new Scanner(System.in);
                                            System.out.println("VOUS ETES EN TRAIN D'AJOUTER UN PATIENT\n\n");
                                            System.out.println("Veuillez renseigner les informations de l'animal patient: ");
                                            do {
                                                System.out.print("Nom du patient: ");
                                                name = info.nextLine();
                                                if (name == "") System.out.println("Le nom du patient ne peut pas être vide.");
                                            } while (name == "");

                                            do {
                                                System.out.print("Nom du Maître ou Maîtresse du patient: ");
                                                nameMaster = info.nextLine();
                                                if (nameMaster=="") System.out.println("Le nom du maître ne peut pas être vide.");
                                            } while (nameMaster =="" );

                                            do {
                                                System.out.print("Espèce du patient : ");
                                                specieName = info.nextLine();
                                                if (specieName == "") System.out.println("L'espece du patient ne peut pas être vide.");
                                            } while (specieName == "");


                                            System.out.print("Durée de vie moyenne selon l'espèce (en chiffre entier, en ANNEES): ");
                                            String specieAvgLi = info.nextLine();
                                            int speciesAverageLife = specieAvgLi == "" ? -99 : Integer.parseInt(specieAvgLi);
                                            System.out.print("Race du patient: ");
                                            String race = info.nextLine();
                                            do {
                                                System.out.println("\nDossier de Suivi du patient");
                                                System.out.println("Une description de l'état de suivi du patient: ");
                                                followUp = info.nextLine();
                                                if (followUp=="") System.out.println("Le dossier du patient ne peut pas être vide.");
                                            } while (followUp == "");


//                                            IAnimal newPatient = null;
//
//                                            // cree un objet espece
//                                            newPatient.setName(name);
//                                            newPatient.setNameMaster(nameMaster);
//                                            newPatient.setRace(race);
//                                            newPatient.setEspeseObj(specieName, speciesAverageLife);
//                                            newPatient.setSuivi(followUp);
//                                            int insert = cabinet.addPatient(newPatient);
                                            int insert = cabinet.addPatient(name, nameMaster, specieName, speciesAverageLife, race, followUp);
                                            if (insert == -2) {
                                                opSuccess = -2;
                                                System.out.println("Nous avons déjà un tel patient de nom " + name + " ayant pour maitre(sse) " + nameMaster);
                                                do {
                                                    Scanner rep1 = new Scanner(System.in);

                                                    System.out.println("Voulez-vous réesayer l'ajout? (O)ui/(N)on: ");
                                                    retry = rep1.nextLine();
//                                                    System.out.println("conditon retry1: "+(retry.trim().toUpperCase() != "oui".toUpperCase()));
//                                                    System.out.println("conditon retry2: "+(retry.trim().toUpperCase() != "o".toUpperCase()));
//                                                    System.out.println("conditon retry3: "+(retry.trim().toUpperCase() != "non".toUpperCase()));
//                                                    System.out.println("conditon retry4: "+(retry.trim().toUpperCase() != "n".toUpperCase()));
     //                                               System.out.println("conditon retry General: " + (retry.trim().toUpperCase() != "oui".toUpperCase() &&
                                                     //       retry.trim().toUpperCase() != "o".toUpperCase() &&
                                                       //     retry.trim().toUpperCase() != "non".toUpperCase() &&
                                                         //   retry.trim().toUpperCase() != "n".toUpperCase()));

                                                } while (!retry.trim().toUpperCase().equals("oui".toUpperCase()) &&
                                                        !retry.trim().toUpperCase().equals("o".toUpperCase()) &&
                                                        !retry.trim().toUpperCase().equals("non".toUpperCase()) &&
                                                        !retry.trim().toUpperCase().equals("n".toUpperCase())
                                                );


                                            } else if (insert == -1) {
                                                opSuccess = -1;
                                                System.out.println("Une erreur s'est produite lors de l'ajout de patient");
                                                do {
                                                    Scanner rep1 = new Scanner(System.in);
                                                    if (retry.trim().toUpperCase() != "non".toUpperCase() || retry.trim().toUpperCase() != "n".toUpperCase()) break;
                                                    System.out.println("Voulez-vous reesayer l'ajout? (O)ui/(N)on: ");
                                                    retry = rep1.nextLine();

                                                } while (retry.trim().toUpperCase().toUpperCase() != "oui".toUpperCase().toUpperCase() || retry.trim().toUpperCase() != "non".toUpperCase() || retry.trim().toUpperCase() != "n".toUpperCase() || retry.trim().toUpperCase() != "o".toUpperCase());
                                            } else {
                                                opSuccess = 0;
                                                System.out.println("Patient Enregistré");
                                            }
                                        } while ((opSuccess == -1 || opSuccess == -2) && (retry.trim().toUpperCase() != "oui".toUpperCase() || retry.trim().toUpperCase() != "o".toUpperCase()));
                                        break;
                                    case 3:
                                        System.out.println("RECHERCHE DE PATIENT");
                                        List<IAnimal> searchResult = cabinet.searchAllAnimal(searchName);
                                        Scanner search = new Scanner(System.in);
                                        System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                                        System.out.println("|            Nom          |     Nom du maitre(esse)        |           Espece        |  Duree de vie |     Race      |");
                                        System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                                        for (IAnimal patient : searchResult) {
                                            System.out.printf("|%-25s|%-32s|%-25s|%11d ans|%-15s|\n", patient.getName().length() > 25 ? patient.getName().substring(0, 25) : patient.getName()
                                                    , patient.getNameMaster().length() > 25 ? patient.getNameMaster().substring(0, 25) : patient.getNameMaster()
                                                    , patient.getEspeseObj().getname().length() > 25 ? patient.getEspeseObj().getname().substring(0, 25) : patient.getEspeseObj().getname()
                                                    , patient.getEspeseObj().getlifeExpectancy()
                                                    , patient.getRace().length() > 15 ? patient.getRace().substring(0, 15) : patient.getRace());
                                        }
                                        System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+\n");
                                        do {
                                            System.out.print("\nVeuillez entrer le nom du patient (entrez 'quitter' pour quitter la recherche, Pensez a ajouter le nom du maitre pour plus de precision, Ex: 'NomPatient NomMaitre): ");
                                            searchName = search.nextLine();
                                            searchResult = cabinet.searchAllAnimal(searchName);

                                            System.out.println("**********************************************************************************************************************");
                                            System.out.println("                            RESULTATS DE LA RECHERCHE");
                                            if ((searchResult.size() > 1) && !searchName.trim().toUpperCase().equals("quitter".toUpperCase())) {
                                                System.out.println("                            Patients potententiellement recherche's");
                                                System.out.println("**********************************************************************************************************************");
                                                System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                                                System.out.println("|            Nom          |     Nom du maitre(esse)        |           Espece        |  Duree de vie |     Race      |");
                                                System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                                                String notSpecifiedDetail = "NON SPECIFE";

                                                for (IAnimal patient : searchResult) {
                                                    String averageLif = patient.getEspeseObj().getlifeExpectancy() + " ans";
                                                    System.out.printf("|%-25s|%-32s|%-25s|%15s|%-15s|\n", patient.getName().length() > 25 ? patient.getName().substring(0, 25) : patient.getName()
                                                            , patient.getNameMaster().length() > 25 ? patient.getNameMaster().substring(0, 25) : patient.getNameMaster()
                                                            , patient.getEspeseObj().getname().length() > 25 ? patient.getEspeseObj().getname().substring(0, 25) : patient.getEspeseObj().getname()
                                                            , patient.getEspeseObj().getlifeExpectancy() >=0 ? averageLif: notSpecifiedDetail
                                                            , patient.getRace().length() > 15 ? patient.getRace().substring(0, 15) : patient.getRace());
                                                }
                                                System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
//
                                            } else if ((searchResult.size() == 1) && !searchName.trim().toUpperCase().equals("quitter".toUpperCase())) {
                                                System.out.println("                            PATIENT: " + searchResult.get(0).getName() + " " + searchResult.get(0).getNameMaster());
                                                String namePromt = "NOM DU PATIENT";
                                                String nameMasterPromt = "NOM DU Maitre(sse)";
                                                String nameRace = "RACE";
                                                String nameSpecie = "ESPECE";
                                                String averageLifeSpan = "DUREE DE VIE (ans)";
                                                String dossier = "SUIVI";
                                                String notSpecifiedField = "NON SPECIFE";
                                                System.out.println("**********************************************************************************************************************");
                                                System.out.println("+------------------------------+----------------------------------------------------------------------------------------------------+");
                                                System.out.printf("|%-30s|%-100s|\n", namePromt, searchResult.get(0).getName());
//                                                System.out.println("+------------------------------+----------------------------------------------------------------------------------------------------+");
                                                System.out.printf("|%-30s|%-100s|\n", nameMasterPromt, searchResult.get(0).getNameMaster());
//                                              System.out.println("+------------------------------+----------------------------------------------------------------------------------------------------+");
                                                System.out.printf("|%-30s|%-100s|\n", nameRace, searchResult.get(0).getRace());
//                                                System.out.println("+------------------------------+----------------------------------------------------------------------------------------------------+");
                                                System.out.printf("|%-30s|%-100s|\n", nameSpecie, searchResult.get(0).getEspeseObj().getname());
//                                                System.out.println("+------------------------------+----------------------------------------------------------------------------------------------------+");
                                                System.out.printf("|%-30s|%-100s|\n", averageLifeSpan, searchResult.get(0).getEspeseObj().getlifeExpectancy()<=0 ? notSpecifiedField : searchResult.get(0).getEspeseObj().getlifeExpectancy() );
//                                                System.out.println("+------------------------------+----------------------------------------------------------------------------------------------------+");
                                                System.out.printf("|%-30s|%-100s|\n", dossier, searchResult.get(0).getSuivi());
                                                System.out.println("+------------------------------+----------------------------------------------------------------------------------------------------+");
                                                searchName = "";
                                            } else {
                                                searchName = "quitter";
                                            }
                                        } while (!searchName.trim().toUpperCase().equals("quitter".toUpperCase()));
                                        break;
                                    case 4:
                                        String confirm = "";
                                        String retryDel = "";
                                        do {
                                            List<IAnimal> animalTobeDeleted = cabinet.searchAllAnimal();
                                            System.out.println("**********************************************************************************************************************");
                                            System.out.println("                            LISTE DES PATIENTS");
                                            System.out.println("**********************************************************************************************************************");
                                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                                            System.out.println("|            Nom          |     Nom du maitre(esse)        |           Espece        |  Duree de vie |     Race      |");
                                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                                            for (IAnimal patient : animalTobeDeleted) {
                                                String averageLif = patient.getEspeseObj().getlifeExpectancy() + " ans";
                                                String notSpecifiedField = "NON SPECIFE";
                                                System.out.printf("|%-25s|%-32s|%-25s|%15s|%-15s|\n", patient.getName().length() > 25 ? patient.getName().substring(0, 25) : patient.getName()
                                                        , patient.getNameMaster().length() > 25 ? patient.getNameMaster().substring(0, 25) : patient.getNameMaster()
                                                        , patient.getEspeseObj().getname().length() > 25 ? patient.getEspeseObj().getname().substring(0, 25) : patient.getEspeseObj().getname()
                                                        , patient.getEspeseObj().getlifeExpectancy() <0 ? notSpecifiedField: averageLif
                                                        , patient.getRace().length() > 15 ? patient.getRace().substring(0, 15) : patient.getRace());
                                            }
                                            System.out.println("+-------------------------+--------------------------------+-------------------------+---------------+---------------+");
                                            Scanner delete = new Scanner(System.in);
                                            System.out.println("Quel patient voluez-vous supprimer?");
                                            System.out.print("Saisissez le nom du patient: ");
                                            String nameDel = delete.nextLine();
                                            System.out.print("Saisissez le nom du maitre(esse) du patient: ");
                                            String nameMasterDel = delete.nextLine();
                                            final String fullName = nameDel + " " + nameMasterDel;
                                            int i = 0;
                                            do {
                                                Scanner scanConf = new Scanner(System.in);
                                                System.out.print("Etes-vous sur de vouloir supprimer le patient '" + fullName + "' ? (O)ui/(N)on : ");

//                                                i++;
//                                                System.out.println(i);
                                                confirm = scanConf.nextLine();
//                                                System.out.println("Confirm: " + confirm.trim().toUpperCase()+"*****");
//                                                System.out.println(" oui :" + "oui".toUpperCase()+"*****");
//                                                System.out.println("confirm condition "+(!confirm.trim().toUpperCase().equals("oui".toUpperCase())));
                                            } while (!confirm.trim().toUpperCase().equals("oui".toUpperCase()) &&
                                                    !confirm.trim().toUpperCase().equals("non".toUpperCase()) &&
                                                    !confirm.trim().toUpperCase().equals("n".toUpperCase()) &&
                                                    !confirm.trim().toUpperCase().equals("o".toUpperCase()));

                                            if (confirm.trim().toUpperCase().equals("oui".toUpperCase()) ||
                                                    confirm.trim().toUpperCase().equals("o".toUpperCase())) {

                                                int delSucces = cabinet.deletePatient(fullName);
                                                if (delSucces == -1) {
                                                    System.out.println("Le patient spe'cifie' n'a pas e'te' retrouve' ou plusieur patient ont '" + fullName.trim() + "' dans leur nom");
                                                    System.out.println("Ajouter peut etre le nom du maitre(sse) de l'animal");
                                                    do {
                                                        Scanner rep1 = new Scanner(System.in);
                                                        System.out.print("Voulez-vous reesayer la suppression de patient? (O)ui/(N)on: ");
                                                        retryDel = rep1.nextLine();
                                                    } while (!retryDel.trim().toUpperCase().equals("oui".toUpperCase()) &&
                                                            !retryDel.trim().toUpperCase().equals("non".toUpperCase().toUpperCase()) &&
                                                            !retryDel.trim().toUpperCase().equals("n".toUpperCase()) &&
                                                            !retryDel.trim().equals("o".toUpperCase()));

                                                } else System.out.println("Patient supprime'");

                                            }
                                            else {

                                            }
//                                            System.out.println("Test retry: "+(retryDel.trim().toUpperCase() == "non".toUpperCase()));
                                        } while (retryDel.trim().toUpperCase().equals("oui".toUpperCase())
                                                || retryDel.trim().toUpperCase().equals("o".toUpperCase()));
                                        break;
                                }

                            } while (cli != 0);

                        } catch (final Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 2:
                        exit = 2;
                        System.out.println("Chargement GUI");
//                         Main Window Frame
//new GUI
//                        break;
                    default:
                        System.out.println("Veuillez entrez une option valide s'il vous plait...");

                }
            } while (exit != 0);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }


    // Question 5


    @Override
    public void checkAlert(int threshold) throws RemoteException {

        System.err.println("\n\n");
        System.err.println("************************************************************************************");
        System.err.println("                             NOUVELLE ALERTE:");
        if (threshold == 1) {
            System.err.println("                     LE NOUVEAU SEUIL EST DE " + threshold + " PATIENTS");
        } else if (threshold == 5) {
            System.err.println("                     LE NOUVEAU SEUIL EST PASSE A " + threshold + " PATIENTS");
        } else
            System.err.println("                     FELICITATIONS! LE CABINET A ATTEINT " + threshold + " PATIENTS");
        System.err.println("************************************************************************************\n\n");

    }


}

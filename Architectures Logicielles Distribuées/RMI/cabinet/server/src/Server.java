/**
 * @author FANUS LUDOVIC
 * @author AMAH GNIMDOU RICHARD
 */


import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;





public class Server {
    public Server() {
    }

    public static void main(String[] args) {
        try {


            // permet de ne pas coder en dure le path du codebase
            URI commonDirectoryPath = Paths.get("../common/src").toUri().normalize();
            System.setProperty("java.rmi.server.codebase", commonDirectoryPath.toString());

            //Chemain absolu du fichier
            Path policyFilePathURI = Paths.get("server/src/security.policy").toAbsolutePath().normalize();
            System.setProperty("java.security.policy", policyFilePathURI.toString());

            //lancement du manager de sécurité
            try {
                if (System.getSecurityManager() == null) {
                    System.setSecurityManager(new SecurityManager());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            Registry registry;
            CabinnetVeterinaireImpl cabinet = new CabinnetVeterinaireImpl();

            try {
                registry = LocateRegistry.createRegistry(1099);

            } catch (Exception e) {
                System.err.println("registre exixtant");
                registry = LocateRegistry.getRegistry();
            }


            //registry.bind("Animal", obj);

            registry.rebind("cabinet", cabinet);

            System.out.println("L'Objet cabinet bien a ete ditribue' dans le registre");
            System.err.println("Server est pret");

            // registry.rebind("CabinetVet", oICabinetVeterinaire cabinetStub = (ICabinetVeterinaire) registry.lookup("cabinet");


            // Question 5
/*             while (true) {
                int threshold = cabinetStub.getCurrentPatientNumber();
                 switch (threshold) {
                    case 100:
                         cabinetStub.sendAlert(100);
                         System.out.println("Le seuil est a 100 patients");
                         break;
                     case 500:
                         cabinetStub.sendAlert(500);
                         System.out.println("Le seuil est passe' a 100 patients");
                         break;
                     case 1000:
                         cabinetStub.sendAlert(1000);
                         System.out.println("Le nouveau seuil est a 1000 patients");
                         break;
                     default:
                         System.out.println("Le nombre de patients actuellement est: "+threshold);
                 }
             }*/

        } catch (Exception e) {
            System.err.println("Une erreur est survenue dans le serveur a cause du registre:  " + e);
            e.printStackTrace();
        }

    }
}

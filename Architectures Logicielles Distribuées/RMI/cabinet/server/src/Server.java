/**
 * @author FANUS LUDOVIC
 * @author AMAH GNIMDOU RICHARD
 */



import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server {
    public Server() {
    }

    public static void main(String[] args) {

        // Permettant de ne pas coder en dure le path du codebase
        String pathCommon = Paths.get("cabinet/common/src").toAbsolutePath().toString();
        System.setProperty(	"java.rmi.server.codebase",	pathCommon);

        // Recupere le chemin absolue du ficher depuis le programme
        // Permettant de ne pas coder en dure le path
/*        String path = Paths.get("src/security.policy").toAbsolutePath().toString();
        System.out.println("Notre path final: "+path);
        System.setProperty("java.security.policy", path);
         try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {

            Registry registry ;
            CabinnetVeterinaireImpl cabinet = new CabinnetVeterinaireImpl();

            try {
                registry = LocateRegistry.createRegistry(1099);

            } catch (Exception e) {
                System.err.println("registre exixtant");
                registry = LocateRegistry.getRegistry();
            }


            //registry.bind("Animal", obj);

            registry.bind("cabinet", cabinet);

            System.out.println("L'Objet cabinet bien a ete ditribue' dans le registre");
            System.err.println("Server est pret");

            // registry.rebind("CabinetVet", obj);
             ICabinetVeterinaire cabinetStub = (ICabinetVeterinaire) registry.lookup("cabinet");


             // Question 5
//             while (true) {
//                int threshold = cabinetStub.getCurrentPatientNumber();
//                 switch (threshold) {
//                     case 100:
//                         cabinetStub.sendAlert(100);
//                         System.out.println("Le seuil est a 100 patients");
//                         break;
//                     case 500:
//                         cabinetStub.sendAlert(500);
//                         System.out.println("Le seuil est passe' a 100 patients");
//                         break;
//                     case 1000:
//                         cabinetStub.sendAlert(1000);
//                         System.out.println("Le nouveau seuil est a 1000 patients");
//                         break;
//                     default:
//                         System.out.println("Le nombre de patients actuellement est: "+threshold);
//                 }
//             }

        } catch (Exception e) {
            System.err.println("Une erreur est survenue dans le serveur a cause du registre:  " + e.toString());
            e.printStackTrace();
        }

    }
}

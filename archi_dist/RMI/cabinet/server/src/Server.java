/**
 * @author FANUS LUDOVIC
 * @author AMAH GNIMDOU RICHARD
 */



import java.nio.file.Paths;
//import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server {
    public Server() {
    }

    public static void main(String[] args) {

//        permission	java.net.SocketPermission "*:1024-65535","connect,accept";
//        permission	java.net.SocketPermission ":80","connect";
        // Recupere le chemin absolue du ficher depuis le programme
        // Permettant de ne pas coder en dure le path
        String path = Paths.get("src/security.policy").toAbsolutePath().toString();
//        System.out.println("Notre path final: "+path);
        System.setProperty("java.security.policy", path);
//        System.setProperty("java.rmi.server.hostname","localhost");
        System.setProperty(	"java.rmi.server.codebase",	"file:/home/richard/Desktop/common");
//        System.setSecurityManager(new SecurityManager());
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            CabinnetVeterinaireImpl cabinet = new CabinnetVeterinaireImpl();
//            CabinnetVeterinaireImpl remoteMethodeStub = (CabinnetVeterinaireImpl) UnicastRemoteObject.exportObject(cabinetVet, 0);
            //cherche un registre deja en execution
//            Registry registry = LocateRegistry.getRegistry();
//            if (registry == null) {
//                System.err.println("Registre RMI non trouve'");
//                System.err.println("Lancement d'un nouveau registre ...");
//                registry = LocateRegistry.createRegistry(1099);
//            } else {
//             //  registry.bind("Animal", obj)
//                registry.bind("CabinetVet", obj);
//                System.err.println("Server ready");
//            }
         Registry   registry = LocateRegistry.createRegistry(1099);

//            registry.bind("Animal", obj);
            registry.bind("cabinet", cabinet);
            System.out.println("L'Objet cabinet bien a ete ditribue' dans le registre");
            System.err.println("Server est pret");
//            registry.rebind("CabinetVet", obj);
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

// Animal obj = new Animal();
           /* obj.suiviAnimal.setSuivi("le suivi");
            obj.especeObj.setname("canus");
*/

//not need if extend UnicastRemoteObject
//Animal stub = (Animal) UnicastRemoteObject.exportObject(obj, 0);

//   System.setSecurityManager(new SecurityManager());

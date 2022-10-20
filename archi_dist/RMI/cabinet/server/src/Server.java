/**
 * @author FANUS LUDOVIC@author AMAH GNIMDOU RICHARD
 */
/**@author AMAH GNIMDOU RICHARD*/

import java.nio.file.Paths;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server {
    public Server() {
    }

    public static void main(String[] args) {


        // Recupere le chemin absolue du ficher depuis le programme
        // Permettant de ne pas coder en dure le path
        String path = Paths.get("server/src/security.policy").toAbsolutePath().toString();

        System.out.println(path);
//        try {
//            if (System.getSecurityManager() == null) {
//                System.setSecurityManager(new SecurityManager());
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.setProperty("java.security.policy", path);
        String prop = System.setProperty("java.rmi.server.codebase",	"file:///home/richard/Desktop/out");
        System.out.println("prop= "+prop);
        System.setSecurityManager(new SecurityManager());
//        System.setSecurityManager(new SecurityManager());

        try {

            CabinnetVeterinaireImpl obj = new CabinnetVeterinaireImpl();

            //cherche un registre deja en execution
            Registry registry = LocateRegistry.getRegistry();
            if (registry == null) {
                System.err.println("Registre RMI non trouve'");
                System.err.println("Lancement d'un nouveau registre ...");
                registry = LocateRegistry.createRegistry(1099);
            } else {
             //  registry.bind("Animal", obj)
                registry.bind("CabinetVet", obj);
                System.err.println("Server ready");
            }
            registry.rebind("CabinetVet", obj);
//             ICabinetVeterinaire cabinet = (ICabinetVeterinaire) registry.lookup("CabinetVet");

//             while (true) {
//                int threshold = cabinet.getCurrentPatientNumber();
//                 if(threshold == 100) cabinet.sendAlert(100);
//                 if(threshold == 500) cabinet.sendAlert(500);
//                 if(threshold == 1000) cabinet.sendAlert(1000);
//             }

        } catch (Exception e) {
            System.err.println("Une erreur est survenue dans le serveur a cause du registre:" + e.toString());
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

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
        System.setProperty("java.security.policy", path);
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            CabinnetVeterinaireImpl obj = new CabinnetVeterinaireImpl();

            //creation de registre par code lancé sur en même temps que sur le svr ; même machine virtuelle
            Registry registry = LocateRegistry.createRegistry(1105);

            //cherche un registre deja en execution
            //Registry registry = LocateRegistry.getRegistry();
            if (registry == null) {
                System.err.println("RmiRegistry not found");
            } else {
             //  registry.bind("Animal", obj);
                registry.bind("CabinetVet", obj);
                System.err.println("Server ready");
            }
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
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

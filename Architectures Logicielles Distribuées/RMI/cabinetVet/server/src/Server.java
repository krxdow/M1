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
        //Path.of("src/security.policy").toAbsolutePath();

        String path = Paths.get("security.policy").toAbsolutePath().toString();
        //String path = Paths.get("security.policy").toRealPath().toString();

        System.out.println(path);

        System.setProperty("java.security.policy", "/home/ludow/Documents/GitHub/M1/Architectures Logicielles Distribuées/RMI/cabinetVet/server/security.policy");
/*
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

        try {

            CabinnetVeterinaireImpl obj = new CabinnetVeterinaireImpl();
            // Animal obj = new Animal();
           /* obj.suiviAnimal.setSuivi("le suivi");
            obj.especeObj.setname("canus");
*/

            //not need if extend UnicastRemoteObject
            //Animal stub = (Animal) UnicastRemoteObject.exportObject(obj, 0);

            //   System.setSecurityManager(new SecurityManager());


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



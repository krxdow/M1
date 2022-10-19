/**
 * @author FANUS LUDOVIC@author AMAH GNIMDOU RICHARD
 * @author AMAH GNIMDOU RICHARD
 */
/**@author AMAH GNIMDOU RICHARD*/

import java.nio.file.Paths;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server {
    public Server() {
    }

    public static void main(String[] args) throws RemoteException {

        Integer host = (args.length < 1) ? null : Integer.parseInt(args[0]);
        CabinnetVeterinaireImpl obj = new CabinnetVeterinaireImpl();
        //Animal obj = new Animal();
        Registry registry;

        String path = Paths.get("server/src/security.policy").toAbsolutePath().toString();
        System.out.println("path of policy:\n" + path);
        System.setProperty("java.security.policy", path);
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());
            }

            try {
                registry = LocateRegistry.createRegistry(1099);
            } catch (RemoteException remoteException) {
                registry = LocateRegistry.getRegistry(1099);
            }

            registry.bind("CabinetVet", obj);


        } catch (Exception e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }


    }
}



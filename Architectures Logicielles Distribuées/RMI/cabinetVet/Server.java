import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public Server() {
    }

    public static void main(String[] args) {

        try {
            AnimalImpl obj = new AnimalImpl();

            //not need if extend UnicastRemoteObject
            //Animal stub = (Animal) UnicastRemoteObject.exportObject(obj, 0);


            //creation de registre par code lancé sur en même temps que sur le svr ; même machine virtuelle
            Registry registry = LocateRegistry.createRegistry(1105);

            //cherche un registre deja en execution
            //Registry registry = LocateRegistry.getRegistry();

            if (registry == null) {
                System.err.println("RmiRegistry not found");
            } else {
                registry.bind("Animal", obj);
                System.err.println("Server ready");
            }
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }

    }


}
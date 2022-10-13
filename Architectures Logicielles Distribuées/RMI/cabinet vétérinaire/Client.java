import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            //same port as server
            Registry registry = LocateRegistry.getRegistry(1105);

            //search in regisry
            Animal stub = (Animal) registry.lookup("Animal");

            //call remote methode
            //stub.printAnimal();

            stub.test();


        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            //same port as server, search registry local
            Registry registry = LocateRegistry.getRegistry(1105);

            //search in class in regisry
            Animal stub = (Animal) registry.lookup("Animal");

            //call remote methode
            //stub.printAnimal();

            String reponse = stub.test();
            System.out.println("response du serveur"+ reponse);



        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

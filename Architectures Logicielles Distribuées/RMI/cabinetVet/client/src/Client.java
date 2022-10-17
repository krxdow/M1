/**@author FANUS LUDOVIC*/
/**@author AMAH GNIMDOU RICHARD*/
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


            //question petit 4
            // stub de l'implementation de l'interface Animal, pas la liste des animaux
            IAnimal stub = (IAnimal) registry.lookup("Animal");
            stub.setName("zack");
            Espece especeCopy = new Espece("canus lupus",15);
            stub.setEspeseObj(especeCopy.getname(),especeCopy.getlifeExpectancy());

             System.out.println( stub.getAnimalObj());






          //String reponse = stub.test();
           // System.out.println("response du serveur"+ reponse);



        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

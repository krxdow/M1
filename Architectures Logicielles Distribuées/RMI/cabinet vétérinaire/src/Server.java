import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Server {
    public Server(){}
    public static void main(String[] args)  {

        //System.out.println("Hello world!");
    try {
        AnimalImpl obj= new AnimalImpl();
        //creation de registre par code lancé sur en même temps que sur le svr ; même machine virtuelle
        Registry registry = LocateRegistry.createRegistry(1105);

        //cherche un registre deja en execution
        //Registry registry = LocateRegistry.getRegistry();
        if (registry==null){
            System.err.println("RmiRegistry not found");
        }else{
            registry.bind("Animal", obj);
            System.err.println("Server ready");
        }


    }catch (Exception e) {
        System.err.println("Server exception: " + e.toString());
        e.printStackTrace();
    }

    }



}
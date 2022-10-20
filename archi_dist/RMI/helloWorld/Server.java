package helloWorld;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class Server  {

	public Server() {}


	public static void main(String args[]) {

		try {


			HelloImpl obj = new HelloImpl();

			//registre lancé sur en même temps que sur le svr ; même machine virtuelle
			 Registry registry = LocateRegistry.createRegistry(1106);
			//Registry registry = LocateRegistry.getRegistry();
			if (registry==null){
				System.err.println("RmiRegistry not found");
			}else{
				registry.bind("Hello", obj);
				System.err.println("Server ready");
			}
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
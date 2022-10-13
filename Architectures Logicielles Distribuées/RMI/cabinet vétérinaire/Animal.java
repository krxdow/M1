import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Animal extends Remote {

    public void printAnimal() throws RemoteException;
}

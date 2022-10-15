import java.rmi.Remote;
import java.rmi.RemoteException;


public interface SuiviAnimal extends Remote {

    String getSuivi() throws RemoteException;
    void setSuivi(String s)  throws RemoteException;

}

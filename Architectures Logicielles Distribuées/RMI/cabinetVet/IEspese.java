import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEspese extends Remote {

    String getNom() throws RemoteException;

   void setNom(String name) throws RemoteException;

    String getDurée_de_vie() throws RemoteException;

    void setDurée_de_vie(String durée) throws RemoteException;



}

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SuiviAnimalImpl extends UnicastRemoteObject implements SuiviAnimal {


    private  String suivi;

    public SuiviAnimalImpl() throws RemoteException{}

    public SuiviAnimalImpl(String suivi) throws RemoteException {
        this.suivi=suivi;
    }


    public String getSuivi() throws RemoteException {
        return this.suivi;
    }


    public void  setSuivi(String suivi) throws RemoteException {
        this.suivi=suivi;

    }



}

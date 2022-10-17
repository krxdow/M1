/**
 * @author FANUS LUDOVIC@author AMAH GNIMDOU RICHARD
 */
/**@author AMAH GNIMDOU RICHARD*/

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SuiviAnimal extends UnicastRemoteObject implements ISuiviAnimal {


    private String suivi;

    public SuiviAnimal() throws RemoteException {
    }

    public SuiviAnimal(String suivi) throws RemoteException {
        this.suivi = suivi;
    }


    public String getSuivi() throws RemoteException {
        return this.suivi;
    }


    public void setSuivi(String suivi) throws RemoteException {
        this.suivi = suivi;

    }


}

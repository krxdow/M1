/**@author FANUS LUDOVIC*/
/**@author AMAH GNIMDOU RICHARD*/

package common;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ISuiviAnimal extends Remote {

    String getSuivi() throws RemoteException;
    void setSuivi(String s)  throws RemoteException;

}

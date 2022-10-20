/**@author FANUS LUDOVIC*/
/**@author AMAH GNIMDOU RICHARD*/

package common;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;


public interface IEspece extends Serializable {

    String getname() throws RemoteException;
    void setname(String name) throws RemoteException;
    int getlifeExpectancy() throws RemoteException;
    void setlifeExpectancy(int durée) throws RemoteException;
}

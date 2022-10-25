 /**@author FANUS LUDOVIC*/
/**@author AMAH GNIMDOU RICHARD*/

import java.io.Serializable;
import java.rmi.RemoteException;


 public interface IEspece extends Serializable {

    String getname() throws RemoteException;
    void setname(String name) throws RemoteException;
    int getlifeExpectancy() throws RemoteException;
    void setlifeExpectancy(int durée) throws RemoteException;
}

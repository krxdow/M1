/**@author FANUS LUDOVIC*/
/**@author AMAH GNIMDOU RICHARD*/

package common;

import java.rmi.RemoteException;
import java.io.Serializable;

public class Espece implements IEspece {


    private static final long serialVersionUID = -8367550406897436450L;
    private int lifeExpectancy ;
    private String name = null;

    public Espece() {
    }

    public Espece(String name, int duree_de_vie) {
        this.name = name;
        this.lifeExpectancy = duree_de_vie;
    }


    public String getname() throws RemoteException {
        return this.name;
    }

    public void setname(String s) throws RemoteException {
        this.name=s;
    }

    public int getlifeExpectancy() throws RemoteException {
        return this.lifeExpectancy;
    }

    public void setlifeExpectancy(int durée) throws RemoteException {
        this.lifeExpectancy=durée;

    }

}

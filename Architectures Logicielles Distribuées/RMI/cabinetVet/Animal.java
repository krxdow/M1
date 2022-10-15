import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Animal extends Remote {

    public void printAnimal() throws RemoteException;

    public String test() throws RemoteException;

    public String getName() throws  RemoteException;

    public void setName(String name) throws  RemoteException;

    public String getNameMaster() throws  RemoteException;

    public void setNameMaster(String nameMaster) throws  RemoteException;

/*
    public String getEspece() throws  RemoteException;

    public void setEspece(String espece) throws  RemoteException;
*/

    public String getRace() throws  RemoteException;

    public void setRace(String race) throws  RemoteException;


}

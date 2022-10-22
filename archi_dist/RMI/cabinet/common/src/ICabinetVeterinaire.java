/**@author FANUS LUDOVIC*/
/**@author AMAH GNIMDOU RICHARD*/

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface ICabinetVeterinaire extends Remote{
    public List<IAnimal> searchAllAnimal(String name) throws RemoteException;
    List<IAnimal> searchAllAnimal() throws RemoteException;

//    int addPatient(IAnimal newPateint) throws RemoteException;
//    String name, String ownerName, Espece species, String race, String state

    int addPatient(String name, String ownerName, String speciesName, int speciesAverageLife, String race, String state) throws RemoteException;

    public void bindClientToCabinet(IDistributedClient client) throws RemoteException;
    public void unbindClientToCabinet(IDistributedClient client) throws RemoteException;



    //    int getPatientNumber() throws RemoteException;
 void sendAlert(int threshold) throws RemoteException;
 int getCurrentPatientNumber()throws RemoteException;
 int deletePatient(String fullName) throws RemoteException;
}

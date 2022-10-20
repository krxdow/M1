/**@author FANUS LUDOVIC*/
/**@author AMAH GNIMDOU RICHARD*/

package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface ICabinetVeterinaire extends Remote{
    public List<Animal> searchAllAnimal(String name) throws RemoteException;
    List<Animal> searchAllAnimal() throws RemoteException;

    int addPatient(String name, String ownerName, String speciesName, int speciesAverageLife, String race, String state) throws RemoteException;

    int addPatient(String name, String ownerName, Espece species, String race, String state) throws RemoteException;

    public void bindClientToCabinet(IDistributedClient client) throws RemoteException;
    public void unbindClientToCabinet(IDistributedClient client) throws RemoteException;

//    int getPatientNumber() throws RemoteException;
 void sendAlert(int threshold) throws RemoteException;
 int getCurrentPatientNumber()throws RemoteException;
 int deletePatient(String fullName) throws RemoteException;
}

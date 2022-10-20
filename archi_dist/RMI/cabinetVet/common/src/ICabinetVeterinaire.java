/**@author FANUS LUDOVIC*/
/**@author AMAH GNIMDOU RICHARD*/

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface ICabinetVeterinaire extends Remote{
    public List<Animal> searchAllAnimal(String name) throws RemoteException;
    List<Animal> searchAllAnimal() throws RemoteException;


    int addPatient(String name, String ownerName, String speciesName, int speciesAverageLife, String race, String state) throws RemoteException;



    int addPatient(String name, String ownerName, Espece species, String race, String state) throws RemoteException;

//    int getPatientNumber() throws RemoteException;
 int sendAlert(int threshold) throws RemoteException;
 int getCurrentPatientNumber()throws RemoteException;

}

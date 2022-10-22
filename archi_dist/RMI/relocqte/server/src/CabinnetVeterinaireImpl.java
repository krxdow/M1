/**
 * @author FANUS LUDOVIC
 * @author AMAH GNIMDOU RICHARD
 */


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class CabinnetVeterinaireImpl extends UnicastRemoteObject implements ICabinetVeterinaire {

    // Liste des patients (animaux)
    //

    private List<Animal> patients = new ArrayList<Animal>();
    private List<IDistributedClient> runningClients = new ArrayList<IDistributedClient>();
    public CabinnetVeterinaireImpl() throws RemoteException {
    }

    public CabinnetVeterinaireImpl(List<Animal> patients) throws RemoteException {
        this.patients = patients;
    }


    private List<Animal> searchAllAnimalOfName(String name) throws RemoteException {
        List<Animal> foundAnimals = new ArrayList<Animal>();
        for (Animal patient : patients) {
            if (patient.getName().indexOf(name) >= 0) {
                foundAnimals.add(patient);
            }
        }
        return foundAnimals;
    }

    private Animal searchAnimalByFullName(String fullName) throws RemoteException {
        for (Animal patient : patients) {
            if (fullName.contains(patient.getName()) && fullName.contains(patient.getNameMaster()))
                return patient;
        }
        return null;
    }

        @Override
    public List<Animal> searchAllAnimal(String name) throws RemoteException {
        List<Animal> foundAnimals = new ArrayList<Animal>();
        if (searchAnimalByFullName(name) != null) {
            foundAnimals.add(searchAnimalByFullName(name));
            return foundAnimals;
        } else {
            foundAnimals = searchAllAnimalOfName(name);
            return foundAnimals;
        }
    }

        @Override
    public List<Animal> searchAllAnimal() throws RemoteException {
     return patients;
    }


    public int addPatient(String name, String nameMaster, Espece specie, String race, String followUp) throws RemoteException {
        IAnimal newPateint = new Animal(name, nameMaster, specie, race, followUp);

               try {
                   if (searchAnimalByFullName(name+" "+nameMaster)!= null)
                       return -2;
                   patients.add((Animal) newPateint);
                   return 0;

               } catch (Exception e) {
                   System.out.println("Une erreur s'est produite lors de l'ajout du patient "+name+" "+nameMaster);
                   e.printStackTrace();
                   return -1;
               }
    }

    /**
     * @param client
     * @throws RemoteException
     */
    @Override
    public void bindClientToCabinet(IDistributedClient client) throws RemoteException {
this.runningClients.add(client);
        System.out.println("Un nouveau veterinaire a e'te' lie' au cabinet");
        System.out.println("Total des veterinaire actuellement:"+this.runningClients.size());
    }

    /**
     * @param client
     * delete a client from the list of bound client to Cabinet Veterinaire
     * @throws RemoteException
     */
    @Override
    public void unbindClientToCabinet(IDistributedClient client) throws RemoteException {
this.runningClients.remove(client);
        System.out.println("Un nouveau veterinaire est parti du cabinet");
        System.out.println("Total des veterinaire actuellement:"+this.runningClients.size());
    }


    @Override
    public int addPatient(String name, String nameMaster, String specieName, int lifeExpectancy, String race, String followUp) throws RemoteException {
        IAnimal newPateint = new Animal(name, nameMaster, specieName, lifeExpectancy, race, followUp);
        try {
            if (searchAnimalByFullName(name+" "+nameMaster)!= null)
                return -2;
            patients.add((Animal) newPateint);
            return 0;

        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de l'ajout du patient "+name+" "+nameMaster);
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * @param threshold
     * sends alert to all client bound to Cabinet veterinaire
     * @throws RemoteException
     */
    @Override
    public void sendAlert(int threshold) throws RemoteException {
        for (IDistributedClient client: runningClients){
    client.checkAlert(threshold);
        }
    }

    /**
     * @return the size of the patients in CabinetVeterinaire
     * @throws RemoteException
     */
    public int getCurrentPatientNumber() throws RemoteException {
        return patients.size();
    }

    /**
     * @param fullName 
     * @return 0 if deletion was successful
     * -1 if the the animal was not found
     * @throws RemoteException
     */
    @Override
    public int deletePatient(String fullName) throws RemoteException {
        Animal foundAnimal =  this.searchAnimalByFullName(fullName);
    if(foundAnimal==null){
        return -1;
    }
     patients.remove(foundAnimal);
        return 0;
    }


}

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public  class CabinnetVeterinaireImpl extends UnicastRemoteObject implements ICabinetVeterinaire {

    // Liste des patients (animaux)
    //
    private List<Animal> patients = new ArrayList<>();

    public CabinnetVeterinaireImpl() throws RemoteException {
    }

    public CabinnetVeterinaireImpl(List<Animal> patients) throws RemoteException {
        this.patients = patients;
    }


    private List<Animal> searchAllAnimalOfName(String name) throws RemoteException {
        List<Animal> foundAnimals = new ArrayList<>();
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

//    @Override
    public List<Animal> searchAllAnimal(String name) throws RemoteException {
        List<Animal> foundAnimals = new ArrayList<>();
        if (searchAnimalByFullName(name) != null) {
            foundAnimals.add(searchAnimalByFullName(name));
            return foundAnimals;
        } else {
            foundAnimals = searchAllAnimalOfName(name);
            return foundAnimals;
        }
    }


    public boolean addPatient(String name, String nameMaster, Espece specie, String race, String followUp) throws RemoteException {
        IAnimal newPateint = new Animal(name, nameMaster, specie, race, followUp);
        return patients.add((Animal) newPateint);
    }

    @Override
    public boolean addPatient(String name, String nameMaster, String specieName, int lifeExpectancy, String race, String followUp) throws RemoteException {
        IAnimal newPateint = new Animal(name, nameMaster, specieName, lifeExpectancy, race, followUp);
        return patients.add((Animal) newPateint);

    }
}

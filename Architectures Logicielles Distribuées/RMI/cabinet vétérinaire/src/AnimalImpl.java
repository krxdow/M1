import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
//import rmi.commons.workflow.IObjetDistant;


public class AnimalImpl extends UnicastRemoteObject implements Animal{

    private String name;
    private String masterOfName;
    private String espece;
    private String race;

/*
    //constructeur
*/
protected AnimalImpl() throws RemoteException {

    this.name = name;
        this.masterOfName = masterOfName;
        this.espece = espece;
        this.race = race;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMasterOfName() {
        return masterOfName;
    }

    public void setMasterOfName(String masterOfName) {
        this.masterOfName = masterOfName;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public void printAnimal() throws RemoteException {
    System.out.println(getName()+' '+getEspece()+' '+getMasterOfName()+' '+getRace());
    }




}

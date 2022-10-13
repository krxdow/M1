import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
//import rmi.commons.workflow.IObjetDistant;

 public class AnimalImpl extends UnicastRemoteObject implements Animal{

    private String name;
    private String nameMaster;
    private String espece;
    private String race;

/*
    //constructeur
*/
protected AnimalImpl() throws RemoteException {

    this.name = name;
        this.nameMaster = nameMaster;
        this.espece = espece;
        this.race = race;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameMaster() {
        return nameMaster;
    }

    public Void setNameMaster(String nameMaster){
        this.nameMaster=nameMaster;
        return null;
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
    System.out.println(getName()+' '+getEspece()+' '+getNameMaster()+' '+getRace());
    }




}

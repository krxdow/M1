import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
//import rmi.commons.workflow.IObjetDistant;

public class AnimalImpl extends UnicastRemoteObject implements Animal {

    String nameMaster;
    // String espece;
    String race;
    SuiviAnimalImpl suiviAnimal;
    String text = null;
    EspeseImpl epece;
    private
    String name;


    /*
        //constructeur
    */
    public AnimalImpl() throws RemoteException {

        String nomEspese="canus";
        String duréeVieEspese="20ans";

        this.name = name;
        this.nameMaster = nameMaster;
        this.race = race;
        this.suiviAnimal = new SuiviAnimalImpl();
        /*this.epece = new EspeseImpl();*/
        this.epece = new EspeseImpl(nomEspese,duréeVieEspese);

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameMaster() {
        return this.nameMaster;
    }

    public void setNameMaster(String nameMaster) {
        this.nameMaster = nameMaster;
    }

/*
    public String getEspece() {
        return this.espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }
*/

    public String getRace() {
        return this.race;
    }

    public void setRace(String race) {
        this.race = race;
    }


    //    public void test() {
    public String test() {
//    System.out.println("the remote object is well distributed");
        return "the remote object is well distributed";

    }

    //@Override
    public void printAnimal() throws RemoteException {
        System.out.println(this.getName() + ' ' + ' ' + this.getNameMaster() + ' ' + this.getRace());
    }


}

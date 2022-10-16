import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
//import rmi.commons.workflow.IObjetDistant;

public class Animal extends UnicastRemoteObject implements IAnimal {

    String nameMaster;
    // String espece;
    String race;
    SuiviAnimal suiviAnimal;
    String text = null;
    EspeseImpl especeObj;

    private
    String name;


    /*
        //constructeur
    */
    public Animal() throws RemoteException {

  
        this.name = name;
        this.nameMaster = nameMaster;
        this.race = race;
        this.suiviAnimal = new SuiviAnimal();

        /*this.epece = new EspeseImpl();*/
        this.especeObj = new EspeseImpl();

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


    public String getEspeseInfo() throws RemoteException {
        return this.especeObj.getNom()+this.especeObj.getDurée_de_vie();
    }


    public IEspese getEspeseObj() throws RemoteException {
        return this.especeObj;
    }


    public void setEspeseObj(String espeseName,String duréevie) throws RemoteException {
        this.especeObj.setNom(espeseName);
        this.especeObj.setDurée_de_vie(duréevie);
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

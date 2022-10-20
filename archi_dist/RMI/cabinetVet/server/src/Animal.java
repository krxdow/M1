/**
 * @author FANUS LUDOVIC@author AMAH GNIMDOU RICHARD
 */
/**@author AMAH GNIMDOU RICHARD*/

package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Animal extends UnicastRemoteObject implements IAnimal {

    private String nameMaster;
    private SuiviAnimal suiviAnimal;
    private String race;
    private Espece specie;
    private String name;

    //    private String text = null;
    //    private // String espece;

    /*
        //constructeur
    */
    public Animal(String name, String nameMaster, Espece specie, String race, String followUp) throws RemoteException {
        this.name = name;
        this.nameMaster = nameMaster;
        this.specie = specie;
        this.race = race;
        this.suiviAnimal = new SuiviAnimal(followUp);


    }

    public Animal(String name, String nameMaster, String specieName, int speciesAverageLife, String race, String followUp) throws RemoteException {


        this.name = name;
        this.nameMaster = nameMaster;
        this.specie = new Espece(specieName, speciesAverageLife);
        this.race = race;
        this.suiviAnimal = new SuiviAnimal(followUp);


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
        return this.specie.getname() + this.specie.getlifeExpectancy();
    }

    public Espece getEspeseObj() throws RemoteException {
        return (Espece) this.specie;
    }

    public void setEspeseObj(String espeseName, int duréevie) throws RemoteException {
        this.specie.setname(espeseName);
        this.specie.setlifeExpectancy(duréevie);
    }

    @Override
    public String getSuivi() throws RemoteException {
        return this.suiviAnimal.getSuivi();
    }

    @Override
    public void setSuivi(String suivi) throws RemoteException {
        this.suiviAnimal.setSuivi(suivi);

    }

    @Override
    public Animal getAnimalObj() throws RemoteException {
        return this;
    }


    //    public void test() {
    public String test() {
    System.out.println("the remote object is well distributed");
        return "the remote object is well distributed";

    }

    //@Override
    public void printAnimal() throws RemoteException {
        System.out.println(this.getName() + ' ' + ' ' + this.getNameMaster() + ' ' + this.getRace());
    }


}

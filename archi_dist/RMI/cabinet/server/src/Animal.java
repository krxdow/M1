/**
 * @author FANUS LUDOVIC@author AMAH GNIMDOU RICHARD
 */
/**@author AMAH GNIMDOU RICHARD*/

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Animal extends UnicastRemoteObject implements IAnimal {

    private String nameMaster;
    private SuiviAnimal suiviAnimal;
    private String race;
    private Espece specie;
    private String name;

    public Animal(String name, String nameMaster, Espece specie, String race, String followUp) throws RemoteException {
        this.name = name;
        this.nameMaster = nameMaster;
        this.specie = specie;
        this.race = race;
        this.suiviAnimal = new SuiviAnimal();

    }

    public Animal(String name, String nameMaster, String specieName, int speciesAverageLife, String race, String followUp) throws RemoteException {

        this.name = name;
        this.nameMaster = nameMaster;
        this.specie = new Espece(specieName, speciesAverageLife);
        this.race = race;
        this.suiviAnimal = new SuiviAnimal();
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


    public String getSuivi() throws RemoteException {
        return this.suiviAnimal.getSuivi();
    }


    public void setSuivi(String suivi) throws RemoteException {
        this.suiviAnimal.setSuivi(suivi);

    }

    public Animal getAnimalObj() throws RemoteException {
        return this;
    }

    public String test() {
//    System.out.println("the remote object is well distributed");
        return "the remote object is well distributed";

    }

    public void printAnimal() throws RemoteException {
        System.out.println(this.getName() + ' ' + ' ' + this.getNameMaster() + ' ' + this.getRace());
    }


}

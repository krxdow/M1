import java.rmi.RemoteException;

public class Espese implements IEspese {

    private
    String durée_de_vie = null;
    String nom = null;

    public Espese() {
    }

    public Espese(String nom, String date) {
        this.nom = nom;
        this.durée_de_vie = date;
    }


    public String getNom() throws RemoteException {
        return this.nom;
    }

    public void setNom(String s) throws RemoteException {
        this.nom=s;
    }


    public String getDurée_de_vie() throws RemoteException {
        return this.durée_de_vie;
    }


    public void setDurée_de_vie(String durée) throws RemoteException {
        this.durée_de_vie=durée;

    }


}

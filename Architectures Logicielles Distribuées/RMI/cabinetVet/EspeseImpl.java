import java.rmi.RemoteException;

public class EspeseImpl implements Espese {

    String durée_de_vie = null;
    private
//
    String nom = null;

    public EspeseImpl() {
    }

    public EspeseImpl(String nom, String date) {
        this.nom = nom;
        this.durée_de_vie = date;
    }


    //    @Override
    public String getNom() throws RemoteException {
        return this.nom;
    }



    public void setNom(String s) throws RemoteException {
        this.nom=s;
    }

    public String getDurée_de_vie() throws RemoteException {
        return this.durée_de_vie;
    }

}

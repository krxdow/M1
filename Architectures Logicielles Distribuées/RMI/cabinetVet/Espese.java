import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Espese extends Remote {

//    String regne = null;
//    String embranchement = null;
//    String classe = null;
//    String famille = null;
//    String genre = null;
//    String nom = null;
//    String durée_de_vie = null;


    String getNom() throws RemoteException;

//    void setNom() throws RemoteException;

    String getDurée_de_vie() throws RemoteException;

//    void setDurée_de_vie() throws RemoteException;

//    String getregne() throws RemoteException;
//
//    void setregne() throws RemoteException;
//
//    String getembranchement() throws RemoteException;
//
//    void setembranchement() throws RemoteException;
//
//    String getclasse() throws RemoteException;
//
//    void setclasse() throws RemoteException;

//    String getfamille() throws RemoteException;
//
//    void setfamille() throws RemoteException;
//
//    String getgenre() throws RemoteException;
//
//    void setgenre() throws RemoteException;

}

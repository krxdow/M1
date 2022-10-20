import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDistributedClient extends Remote {
void checkAlert(int threshold) throws RemoteException;
}

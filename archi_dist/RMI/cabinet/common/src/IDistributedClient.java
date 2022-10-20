import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDistributedClient extends Remote {
void checkAlert() throws RemoteException;
}

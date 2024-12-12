package UvBookRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatConfi extends Remote {
    String getServerStatus() throws RemoteException;
    void setMaxUsers(int maxUsers) throws RemoteException;
}

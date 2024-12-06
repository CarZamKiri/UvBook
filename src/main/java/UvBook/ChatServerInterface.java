package UvBook;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatServerInterface extends Remote {
    void sendMessage(String username, String message) throws RemoteException;
    List<String> getMessages() throws RemoteException;
}
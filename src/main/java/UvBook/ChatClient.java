package UvBook;

import UvBookRMI.ChatServerInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

public class ChatClient {
    private final ChatServerInterface chatServer;

    public ChatClient(String serverAddress) throws Exception {
        chatServer = (ChatServerInterface) Naming.lookup("rmi://" + serverAddress + "/ChatServer");
    }

    public void sendMessage(String username, String message) throws RemoteException {
        chatServer.sendMessage(username, message);
    }

    public List<String> getMessages() throws RemoteException {
        return chatServer.getMessages();
    }
}

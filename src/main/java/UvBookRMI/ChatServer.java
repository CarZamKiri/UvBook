package UvBookRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements ChatServerInterface {
    private final List<String> messages;
    protected ChatServer() throws RemoteException {
        messages = new ArrayList<>();
    }

    @Override
    public synchronized void sendMessage(String username, String message) throws RemoteException {
        messages.add(username + ": " + message);
    }

    @Override
    public synchronized List<String> getMessages() throws RemoteException {
        return new ArrayList<>(messages);
    }

    public static void main(String[] args) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            ChatServerInterface chatServer = new ChatServer();
            java.rmi.Naming.rebind("rmi://localhost/ChatServer", chatServer);
            System.out.println("Servidor RMI del chat iniciado...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

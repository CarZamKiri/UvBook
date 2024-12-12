package UvBookRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplemChatConfi extends UnicastRemoteObject implements ChatConfi {
    private int maxUsers;
    private int currentUsers;

    protected ImplemChatConfi() throws RemoteException {
        super();
        this.maxUsers = 100; // Valor predeterminado
        this.currentUsers = 0;
    }

    @Override
    public String getServerStatus() throws RemoteException {
        return "Servidor en línea. Usuarios conectados: " + currentUsers + "/" + maxUsers;
    }

    @Override
    public void setMaxUsers(int maxUsers) throws RemoteException {
        this.maxUsers = maxUsers;
    }

    public synchronized void userConnected() {
        currentUsers++;
    }

    public synchronized void userDisconnected() {
        currentUsers--;
    }
}
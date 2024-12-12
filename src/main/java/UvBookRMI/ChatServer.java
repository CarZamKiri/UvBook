package UvBookRMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ChatServer {
    public static void main(String[] args) {
        try {
            // Inicia el registro RMI en el puerto 1099
            LocateRegistry.createRegistry(1099);

            ImplemChatConfi config = new ImplemChatConfi();
            Naming.rebind("//localhost/ChatConfi", config);

            System.out.println("Servidor RMI listo.");
        } catch (Exception e) {
            System.err.println("Error al iniciar el servidor RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
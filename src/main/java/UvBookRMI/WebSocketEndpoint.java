package UvBookRMI;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/chatEndpoint")
public class WebSocketEndpoint {
    private static CopyOnWriteArraySet<Session> connectedSessions = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        connectedSessions.add(session);
        System.out.println("Nueva conexión: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session senderSession) throws IOException {
        // Broadcast del mensaje a todos los clientes conectados
        for (Session session : connectedSessions) {
            if (session.isOpen() && !session.equals(senderSession)) {
                session.getBasicRemote().sendText(message);
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        connectedSessions.remove(session);
        System.out.println("Conexión cerrada: " + session.getId());
    }
}
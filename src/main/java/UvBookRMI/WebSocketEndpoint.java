package UvBookRMI;

import com.google.gson.Gson;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/chatEndpoint")
public class WebSocketEndpoint {
    private static Map<String, Session> userSessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        String user = session.getRequestParameterMap().get("user").get(0);
        userSessions.put(user, session);
        System.out.println("Conexión abierta por el usuario: " + user);
    }

    @OnMessage
    public void onMessage(String message, Session senderSession) throws IOException {
        // Crear instancia de Gson
        Gson gson = new Gson();

        // Deserializar mensaje JSON
        Message msg = gson.fromJson(message, Message.class);

        // Procesar el mensaje (enviar al receptor)
        Session receiverSession = userSessions.get(msg.getReceiver());
        if (receiverSession != null && receiverSession.isOpen()) {
            receiverSession.getBasicRemote().sendText(message);
        } else {
            System.out.println("Usuario no conectado: " + msg.getReceiver());
        }
    }

    @OnClose
    public void onClose(Session session) {
        // Remover al usuario desconectado del mapa
        userSessions.values().remove(session);
        System.out.println("Conexión cerrada: " + session.getId());
    }
}
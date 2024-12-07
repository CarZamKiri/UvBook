package UvBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
    private ChatClient chatClient;

    @Override
    public void init() throws ServletException {
        try {
            String serverAddress = "localhost"; // o el IP del servidor, no se como lo vamos a correr
            chatClient = new ChatClient(serverAddress);
        } catch (Exception e) {
            throw new ServletException("Error conectando al servidor RMI", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String message = req.getParameter("message");

        try {
            chatClient.sendMessage(username, message);
        } catch (RemoteException e) {
            throw new ServletException("Error enviando mensaje", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<String> messages = chatClient.getMessages();
            req.setAttribute("messages", messages);
            req.getRequestDispatcher("chat.jsp").forward(req, resp);
        } catch (RemoteException e) {
            throw new ServletException("Error obteniendo mensajes", e);
        }
    }
}

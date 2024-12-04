package UvBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private Neo4jConnection neo4jConnection;
    private Neo4jUserManager userManager;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // Inicializa la conexión con Neo4j
            String uri = "neo4j+s://cc5ca0ee.databases.neo4j.io";
            String user = "neo4j";
            String password = "ONBGDyFOd__VimALjNLZJ8GV7kshemkphWfVcUgsf7g";
            neo4jConnection = new Neo4jConnection(uri, user, password);
            userManager = new Neo4jUserManager(neo4jConnection);
        } catch (Exception e) {
            throw new ServletException("Error al inicializar la conexión con Neo4j", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtén los parámetros del formulario
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            // Registra al usuario en la base de datos
            userManager.registerUser(username, password);
            resp.getWriter().write("Usuario registrado exitosamente.");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error al registrar el usuario: " + e.getMessage());
        }
    }

    @Override
    public void destroy() {
        try {
            if (neo4jConnection != null) {
                neo4jConnection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.destroy();
    }
}

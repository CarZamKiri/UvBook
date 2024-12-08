import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

public class Neo4jServlet extends HttpServlet {
    private Neo4jDAO neo4jDAO;

    @Override
    public void init() throws ServletException {
        neo4jDAO = new Neo4jDAO();
    }

    // Método doGet para mostrar el saludo y los datos de la persona
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("crearEstudiante".equals(action)) {
            // Crear una nueva persona
            String nombre = request.getParameter("nombre");
            String ap_paterno = request.getParameter("ap_paterno");
            String ap_materno = request.getParameter("ap_materno");
            String matricula = request.getParameter("matricula");
            String email = request.getParameter("email");
            String contrasenia = request.getParameter("contrasenia");


            if (nombre != null && ap_paterno != null && ap_materno != null && matricula != null && email != null && contrasenia != null) {
                try {
                    neo4jDAO.crearEstudiante(nombre, ap_paterno, ap_materno, matricula, email, contrasenia);
                    response.getWriter().write("Alumno creado: " + nombre + " " + ap_paterno + " " + ap_materno + "matricula:" + matricula + "email:" + email + "contrasenia:" + contrasenia);
                } catch (IOException e) {
                    response.getWriter().write("Error");
                }
            } else {
                response.getWriter().write("Por favor, proporciona todos los campos");
            }
        } else {
            // Mostrar el saludo de Neo4j
            String greeting = neo4jDAO.getGreeting();
            response.getWriter().write(greeting);
        }
    }

    @Override
    public void destroy() {
        if (neo4jDAO != null) {
            neo4jDAO.close();
        }
    }
}

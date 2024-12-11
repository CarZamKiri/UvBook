import UvBook.Estudiante;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChatServletUsers extends HttpServlet {
    private Neo4jDAO neo4jDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        neo4jDAO = new Neo4jDAO(); // Inicializa la conexión a la base de datos
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Estudiante> estudiantes = neo4jDAO.obtenerEstudiantes(); // Obtén la lista de estudiantes desde la base de datos
        request.setAttribute("estudiantes", estudiantes); // Asigna la lista como atributo para el JSP
        request.getRequestDispatcher("chat.jsp").forward(request, response); // Redirige al JSP
    }

    @Override
    public void destroy() {
        super.destroy();
        neo4jDAO.close(); // Cierra la conexión con la base de datos
    }
}
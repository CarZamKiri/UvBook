import UvBook.Pregunta;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class DashboardServlet extends HttpServlet {
    private Neo4jDAO neo4jDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        neo4jDAO = new Neo4jDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pregunta> preguntas = neo4jDAO.obtenerPreguntas(); // Obtener preguntas desde la base de datos
        request.setAttribute("preguntas", preguntas); // Pasar la lista al JSP
        request.getRequestDispatcher("dashboard_student.jsp").forward(request, response); // Redirigir al JSP
    }

    @Override
    public void destroy() {
        super.destroy();
        neo4jDAO.close();
    }
}

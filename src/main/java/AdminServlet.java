import UvBook.Pregunta;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {
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
        request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response); // Redirigir al JSP
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String texto = request.getParameter("texto");
            String fecha = request.getParameter("fecha");

            if (texto != null && !texto.isEmpty() && fecha != null && !fecha.isEmpty()) {
                neo4jDAO.eliminarPregunta(texto, fecha); // Llamar al DAO con texto y fecha
            }
        }

        // Redirigir nuevamente al dashboard para actualizar la lista de preguntas
        response.sendRedirect("AdminServlet");
    }

    @Override
    public void destroy() {
        super.destroy();
        neo4jDAO.close();
    }
}
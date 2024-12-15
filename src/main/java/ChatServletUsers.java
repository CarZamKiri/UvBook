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
        request.getRequestDispatcher("usuarios.jsp").forward(request, response); // Redirige al JSP
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String correo = request.getParameter("correo");
            if (correo != null && !correo.isEmpty()) {
                try {
                    // Llamar al método para eliminar el usuario en la base de datos
                    neo4jDAO.eliminarUsuario(correo);

                    // Redirigir con un mensaje de éxito
                    request.setAttribute("message", "Usuario eliminado exitosamente");
                } catch (Exception e) {
                    // Manejar errores
                    request.setAttribute("error", "Error al eliminar el usuario: " + e.getMessage());
                }
            } else {
                request.setAttribute("error", "No se proporcionó un correo válido");
            }
        }

        // Volver a cargar la lista de usuarios y redirigir a la página JSP
        doGet(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        neo4jDAO.close(); // Cierra la conexión con la base de datos
    }
}
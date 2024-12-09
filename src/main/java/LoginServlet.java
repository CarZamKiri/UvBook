
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private Neo4jDAO dao;

    @Override
    public void init() throws ServletException {
        // Inicializar la conexión a Neo4j
        dao = new Neo4jDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String emailLogin = request.getParameter("emailLogin");
        String contraseniaLogin = request.getParameter("contraseniaLogin");

        if (emailLogin == null || contraseniaLogin == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Los parámetros email y contraseña son obligatorios.");
            return;
        }

        // Validar credenciales
        boolean isValid = dao.validarInicioSesion(emailLogin, contraseniaLogin);

        if (isValid) {
            request.getSession().setAttribute("correo", emailLogin);
            // Redirigir al dashboard en caso de éxito
            response.sendRedirect("dashboard_student.jsp");
        } else {
            // Volver al formulario de inicio de sesión con mensaje de error
            request.setAttribute("errorMessage", "Credenciales incorrectas.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cerrar la conexión a Neo4j
        if (dao != null) {
            dao.close();
        }
    }
}

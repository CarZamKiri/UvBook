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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("crearRespuesta".equals(action)) {
            //obtiene los parametros de la vista de estudiantes
            String texto = request.getParameter("answer");
            String pregunta = (String) request.getParameter("pregunta");//obtener las preguntas para crear la relacion
            String correo = (String) request.getSession().getAttribute("correo");//obtiene el correo del incio de sesion para crear la relacion

            if (texto != null && pregunta != null) {
                try {
                    //llama a las funciones correspondientes para hacer los create de nodos y relaciones
                    neo4jDAO.crearRespuesta(texto);
                    neo4jDAO.relacionAlumnoRespuesta(correo, texto);
                    neo4jDAO.relacionPreguntaRespuesta(texto, pregunta);

                    response.getWriter().write("UvBook.Respuesta creada: " + texto );
                } catch (IOException e) {
                    response.getWriter().write("Error al crear respuesta");
                }
            } else {
                response.getWriter().write("No has insertado tu respuesta");
            }
        } else {
            String greeting = neo4jDAO.getGreeting();
            response.getWriter().write(greeting);
        }
    }



    @Override
    public void destroy() {
        super.destroy();
        neo4jDAO.close();
    }
}

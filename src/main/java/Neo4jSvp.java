import UvBook.Pregunta;
import UvBook.Respuestas;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Neo4jSvp extends HttpServlet {
    private Neo4jDAO neo4jDAO;

    @Override
    public void init() throws ServletException {
        neo4jDAO = new Neo4jDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("crearPregunta".equals(action)) {
            //obtiene los parametros de la vista de estudiantes
            String texto = request.getParameter("question");

            if (texto != null) {
                try {
                    //llama a ala funcion de crear pregunta pasandole el parametro
                    neo4jDAO.crearPregunta(texto);
                    //obtiene el correo del incio de sesion para crear la relacion
                    String correo = (String) request.getSession().getAttribute("correo");
                    neo4jDAO.relacionAlumnoPregunta(correo, texto);
                    response.getWriter().write("UvBook.Pregunta creada: " + texto );
                } catch (IOException e) {
                    response.getWriter().write("Error");
                }
            } else {
                response.getWriter().write("No has insertado tu pregunta");
            }
        } else {
            String greeting = neo4jDAO.getGreeting();
            response.getWriter().write(greeting);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pregunta> preguntas = neo4jDAO.obtenerPreguntasnr(); // Obtener preguntas desde la base de datos
        request.setAttribute("preguntanr", preguntas); // Pasar la lista al JSP
        request.getRequestDispatcher("dashboard_teacher.jsp").forward(request, response); // Redirigir al JSP
    }

    @Override
    public void destroy() {
        if (neo4jDAO != null) {
            neo4jDAO.close();
        }
    }
}

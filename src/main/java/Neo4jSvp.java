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
            String texto = request.getParameter("question");

            if (texto != null ) {
                try {
                    neo4jDAO.crearPregunta(texto);
                    response.getWriter().write("Pregunta creada: " + texto );
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Neo4jDAO dao = new Neo4jDAO();
        List<String> preguntas = dao.mostrarPreguntas();
        req.setAttribute("preguntas", preguntas);
        req.getRequestDispatcher("dashboard_student.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        if (neo4jDAO != null) {
            neo4jDAO.close();
        }
    }
}

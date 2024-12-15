import UvBook.Perfil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PerfilServlet extends HttpServlet {
    private Neo4jDAO neo4jDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        neo4jDAO = new Neo4jDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = (String) request.getSession().getAttribute("correo");

        if (correo != null) {
            Perfil perfil = neo4jDAO.obtenerperfil(correo);

            if (perfil != null) {
                request.setAttribute("perfil", perfil);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("perfil.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy(){
        neo4jDAO.close();
    }

}

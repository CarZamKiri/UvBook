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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("actualizar".equals(action)) {
            // Obtener valores actuales
            String nombre = request.getParameter("nombre");
            String apellidop = request.getParameter("apellidop");
            String apellidom = request.getParameter("apellidom");
            String matricula = request.getParameter("matricula");
            String correo = request.getParameter("correo");

            // Obtener nuevos valores (si se proporcionan)
            String nnombre = request.getParameter("nnombre");
            String napellidop = request.getParameter("napellidop");
            String napellidom = request.getParameter("napellidom");
            String nmatricula = request.getParameter("nmatricula");
            String ncorreo = request.getParameter("ncorreo");

            // Usar nuevos valores si se proporcionan, de lo contrario usar los actuales
            nnombre = (nnombre != null && !nnombre.trim().isEmpty()) ? nnombre : nombre;
            napellidop = (napellidop != null && !napellidop.trim().isEmpty()) ? napellidop : apellidop;
            napellidom = (napellidom != null && !napellidom.trim().isEmpty()) ? napellidom : apellidom;
            nmatricula = (nmatricula != null && !nmatricula.trim().isEmpty()) ? nmatricula : matricula;
            ncorreo = (ncorreo != null && !ncorreo.trim().isEmpty()) ? ncorreo : correo;

            try {
                // Llamar al método de actualización
                neo4jDAO.modificarperfil(nombre, apellidop, apellidom, matricula, correo,
                        nnombre, napellidop, napellidom, nmatricula, ncorreo);

                // Actualizar sesión si el correo cambió
                request.getSession().setAttribute("correo", ncorreo);

                // Redirigir con mensaje de éxito
                request.setAttribute("message", "Perfil actualizado exitosamente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("perfil.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                // Manejar errores
                request.setAttribute("error", "Error al actualizar el perfil: " + e.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("perfil.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Manejar caso no esperado
            request.setAttribute("error", "Acción no válida");
            RequestDispatcher dispatcher = request.getRequestDispatcher("perfil.jsp");
            dispatcher.forward(request, response);
        }
    }
    public void destroy(){
        neo4jDAO.close();
    }

}

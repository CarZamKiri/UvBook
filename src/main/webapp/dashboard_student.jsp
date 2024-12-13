<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="UvBook.Pregunta" %>

<!DOCTYPE html>
<html>
<head>
    <title>Alumno - Dashboard</title>
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<header>
    <div class="profile">
        <img src="https://www.uv.mx/veracruz/nutricion/files/2021/04/Flor_con_uv_sin_fondo.png" alt="Alumno">
        <h2>Bienvenido, Alumno</h2>
    </div>
    <nav>
        <ul>
            <li><a href="DashboardServlet">Inicio</a></li>
            <li><a href="TeacherdServlet">Inicio</a></li>
            <li><a href="ChatServletUsers">Chat</a></li>
            <li><a href="index.jsp">Cerrar Sesión</a></li>
        </ul>
    </nav>
</header>
<main>
    <div class="post-question">
        <h3>Publicar una UvBook.Pregunta</h3>
        <form action="Neo4jSvp" method="post">
            <textarea name="question" placeholder="Escribe tu pregunta aquí..." maxlength="250" required></textarea>
            <input type="hidden" name="action" value="crearPregunta">
            <button type="submit">Publicar</button>
        </form>
    </div>
    <div class="questions-list">
        <h3>Preguntas Recientes</h3>
        <ul>
            <%
                List<Pregunta> preguntas = (List<Pregunta>) request.getAttribute("preguntas");
                if (preguntas != null) {
                    for (Pregunta pregunta : preguntas) {
            %>
            <li>
                <strong><%= pregunta.getTexto() %></strong>
                <br>Fecha: <%= pregunta.getFecha() %>
                <% if (pregunta.getAdjunto() != null && !pregunta.getAdjunto().isEmpty()) { %>
                <br><a href="<%= pregunta.getAdjunto() %>" target="_blank">Ver Adjunto</a>
                <% } %>
                <div class="post-question">
                    <form action="DashboardServlet" method="post">
                        <textarea name="answer" placeholder="Escribe tu respuesta..." maxlength="250" required></textarea>
                        <input type="hidden" name="action" value="crearRespuesta">
                        <input type="hidden" name="pregunta" value="<%= pregunta.getTexto() %>">
                        <button type="submit">Publicar</button>
                    </form>
                </div>
            </li>
            <%
                    }
                }
            %>
        </ul>


    </div>

</main>
<footer>
    <p>UVBook - Red Social Académica</p>
</footer>
</body>
</html>

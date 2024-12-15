<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="UvBook.Pregunta" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin - Dashboard</title>
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<header>
    <div class="profile">
        <img src="https://www.uv.mx/veracruz/nutricion/files/2021/04/Flor_con_uv_sin_fondo.png" alt="Alumno">
        <h2>Bienvenido, admin</h2>
    </div>
    <nav>
        <ul>
            <li><a href="usuarios.jsp">Usuarios</a></li>
            <li><a href="index.jsp">Cerrar Sesión</a></li>
        </ul>
    </nav>
</header>
<main>
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
                <!-- Formulario para eliminar pregunta -->
                <form action="AdminServlet" method="post" style="margin-top: 10px;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="texto" value="<%= pregunta.getTexto() %>">
                    <input type="hidden" name="fecha" value="<%= pregunta.getFecha() %>">
                    <button type="submit">Eliminar</button>
                </form>
            </li>
            <%
                }
            } else {
            %>
            <li>No hay preguntas disponibles.</li>
            <%
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
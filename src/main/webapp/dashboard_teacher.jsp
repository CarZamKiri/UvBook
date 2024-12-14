<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="UvBook.Respuestas" %>
<%@ page import="UvBook.Pregunta" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Profesor - Dashboard</title>
        <link rel="stylesheet" href="css/dashboard.css">
    </head>
    <body>
        <header>
            <div class="profile">
                <img src="https://www.uv.mx/veracruz/nutricion/files/2021/04/Flor_con_uv_sin_fondo.png" alt="Profesor">
                <h2>Bienvenido, Profesor</h2>
            </div>
            <nav>
                <ul>
                    <li><a href="DashboardServlet">Inicio</a></li>
                    <li><a href="chat.jsp">Chat</a></li>
                    <li><a href="index.jsp">Cerrar Sesión</a></li>
                </ul>
            </nav>
        </header>
        <main>
            <div class="questions-list">
                <h3>Preguntas Pendientes</h3>
                <ul>
                    <%
                        List<Pregunta> preguntas = (List<Pregunta>) request.getAttribute("preguntanr");
                        if (preguntas != null) {
                            for (Pregunta pregunta : preguntas) {
                    %>
                    <li>
                        <strong><%= pregunta.getTexto() %></strong>
                        <br>Fecha: <%= pregunta.getFecha() %>
                        <% if (pregunta.getAdjunto() != null && !pregunta.getAdjunto().isEmpty()) { %>
                        <br><a href="<%= pregunta.getAdjunto() %>" target="_blank">Ver Adjunto</a>
                        <% } %>
                    </li>
                    <%
                            }
                        }
                    %>
                </ul>
            </div>
            <div class="questions-list">
                <h3>Preguntas Resueltas</h3>
                <ul>
                    <%
                        List<Respuestas> respuestas = (List<Respuestas>) request.getAttribute("preguntasr");
                        if (respuestas != null) {
                            for (Respuestas respuesta : respuestas) {
                                Pregunta preguntarelacionada = respuesta.getPregunta().isEmpty() ? null: respuesta.getPregunta().get(0);
                    %>
                    <li>
                        <% if (preguntarelacionada != null) { %>
                        <strong>Pregunta: <br><%= preguntarelacionada.getTexto() %></strong>
                        <%= preguntarelacionada.getFecha() %>
                        <% if (preguntarelacionada.getAdjunto() != null && !preguntarelacionada.getAdjunto().isEmpty()) { %>
                        <br><a href="<%= preguntarelacionada.getAdjunto() %>" target="_blank">Ver Adjunto</a><br>
                            <% } %>
                        <% } %>
                        <strong><br>Respuesta: <br><%= respuesta.getTexto() %></strong>
                        <%= respuesta.getFecha() %>
                        <% if (respuesta.getAdjunto() != null && !respuesta.getAdjunto().isEmpty()) { %>
                        <br><a href="<%= respuesta.getAdjunto() %>" target="_blank">Ver Adjunto</a>
                        <% } %>
                    </li>
                    <%
                            }
                        }
                    %>
                </ul>
            </div>

            </div>
        </main>
        <footer>
            <p>UVBook - Red Social Académica</p>
        </footer>
    </body>
</html>
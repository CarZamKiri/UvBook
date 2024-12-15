<%@ page import="UvBook.Estudiante" %>
<%@ page import="java.util.List" %>
<%@ page import="UvBook.Perfil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Usuarios</title>
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<main id="usuarios">
<aside class="questions-list">
    <h3>Usuarios Disponibles</h3>
    <ul>
        <%
            List<Estudiante> estudiantes = (List<Estudiante>) request.getAttribute("estudiantes");
            if (estudiantes != null) {
                for (Estudiante estudiante : estudiantes) {
        %>
        <a><%= estudiante.getMatricula() %></a>
        <li><a href="#" onclick="selectUser('<%= estudiante.getNombre() %>')"><%= estudiante.getNombre() %></a></li>
        <%
            }
        } else {
        %>
        <li>No hay usuarios disponibles.</li>
        <%
            }
        %>
    </ul>
</aside>
    <section class="info-user">
        <div id="user-header">
            <h3>Información de <span id="selected-user">[Selecciona un estudiante]</span></h3>
        </div>
        <div id="info-area"></div>
        <form id="info-form" onsubmit="">
            <input type="hidden" id="username" value="<%= request.getSession().getAttribute("correo") %>">
            <button type="submit">Eliminar</button>
        </form>
    </section>
</main>
<footer>
    <p>UVBook - Red Social Académica</p>
</footer>
<script>
    function selectUser(user) {
        selectedUser = user;
        document.getElementById("selected-user").innerText = user;
        document.getElementById("info-area").innerHTML = "";
    }
</script>
</body>
</html>

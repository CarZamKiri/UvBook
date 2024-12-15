<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="UvBook.Perfil" %>

<!Doctype html>
<html>
<head>
  <title>Perfil</title>
  <link rel="stylesheet" href="css/perfil.css">
</head>
<body>
<header>
  <div class="profile">
    <a href="https://www.uv.mx/">
      <img src="https://www.uv.mx/veracruz/nutricion/files/2021/04/Flor_con_uv_sin_fondo.png" alt="Alumno"></a>
    <div class="menu">
      <%
        String correo = (String) request.getSession().getAttribute("correo");
      %>
    </div>
    <h2>Bienvenido, <%= correo%></h2>
  </div>
  <nav>
    <ul>
      <li><a href="DashboardServlet">Inicio</a></li>
      <li><a href="TeacherdServlet">Profesores</a></li>
      <li><a href="PerfilServlet">Mi perfil</a></li>
      <li><a href="ChatServletUsers">Chat</a></li>
      <li><a href="index.jsp">Cerrar Sesión</a></li>
    </ul>
  </nav>
</header>
<main>
  <div class="campo-post">
    <form action="PerfilServlet" method="post">
      <h1>Perfil</h1>
      <%
        Perfil perfil = (Perfil) request.getAttribute("perfil");
        if (perfil != null) {
      %>
      <div class="campo">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nnombre" value="<%= perfil.getNombre() %>">
      </div>

      <div class="campo">
        <label for="apellidop">Apellido paterno:</label>
        <input type="text" id="apellidop" name="napellidop" value="<%= perfil.getApellidop() %>">
      </div>

      <div class="campo">
        <label for="apellidom">Apellido materno:</label>
        <input type="text" id="apellidom" name="napellidom" value="<%= perfil.getApellidom() %>">
      </div>

      <div class="campo">
        <label for="correo">Correo electrónico:</label>
        <input type="email" id="correo" name="ncorreo" value="<%= perfil.getCorreo() %>">
      </div>

      <div class="campo">
        <label for="matricula">Matrícula:</label>
        <input type="text" id="matricula" name="nmatricula" value="<%= perfil.getMatricula() %>">
      </div>

      <!-- Campos con los valores originales   -->
      <input type="hidden" name="nombre" value="<%= perfil.getNombre() %>">
      <input type="hidden" name="apellidop" value="<%= perfil.getApellidop() %>">
      <input type="hidden" name="apellidom" value="<%= perfil.getApellidom() %>">
      <input type="hidden" name="matricula" value="<%= perfil.getMatricula() %>">
      <input type="hidden" name="correo" value="<%= perfil.getCorreo() %>">

      <input type="hidden" name="action" value="actualizar">
      <button type="submit" class="boton">Guardar cambios</button>
      <button type="reset" class="boton">Cancelar</button>
      <% } %>
    </form>
  </div>
</main>
<footer>
  <p>UvBook - Red Social Académica</p>
</footer>
</body>
</html>
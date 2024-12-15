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
      <img src="https://www.uv.mx/veracruz/nutricion/files/2021/04/Flor_con_uv_sin_fondo.png" alt="Alumno">
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
        <li><a href="perfil.jsp">Mi perfil</a></li>
        <li><a href="ChatServletUsers">Chat</a></li>
        <li><a href="index.jsp">Cerrar Sesión</a></li>
      </ul>
    </nav>
  </header>
  <main>
    <div class="campo-post">
      <form class="perfil">
        <h1>Perfil</h1>
        <%
          Perfil perfil = (Perfil) request.getAttribute("perfil");
          if (perfil != null) {
        %>
        <div class="campo">
          <label for="nombre">Nombre:</label>
          <input type="text" id="nombre" name="nombre" value="<%= perfil.getNombre() %>" required>
        </div>

        <div class="campo">
          <label for="apellidop">Apellido paterno:</label>
          <input type="text" id="apellidop" name="apellidop" value="<%= perfil.getApellidop() %>" required>
        </div>

        <div class="campo">
          <label for="apellidom">Apellido materno:</label>
          <input type="text" id="apellidom" name="apellidom" value="<%= perfil.getApellidom() %>" required>
        </div>

        <div class="campo">
          <label for="correo">Correo electrónico:</label>
          <input type="text" id="correo" name="correo" value="<%= perfil.getCorreo() %>" required>
        </div>

        <div class="campo">
          <label for="matricula">Matrícula:</label>
          <input type="text" id="matricula" name="matricula" value="<%= perfil.getMatricula() %>" required>
        </div>
        <% } %>
        <button type="submit" class="boton">Guardar cambios</button>
        <button type="reset" class="boton">Cancelar</button>
      </form>
    </div>
  </main>
  <footer>
    <p>UvBook - Red Social Académica</p>
  </footer>
  </body>
</html>

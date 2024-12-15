<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <li><a href="TeacherdServlet">Mi perfil</a></li>
        <li><a href="ChatServletUsers">Chat</a></li>
        <li><a href="index.jsp">Cerrar Sesión</a></li>
      </ul>
    </nav>
  </header>
  <main>
      <div class="campo-post">
        <form class="perfil">
          <h1>Perfil</h1>
          <div class="campo">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>
          </div>

          <div class="campo">
            <label for="apellidop">Apellido paterno:</label>
            <input type="text" id="apellidop" name="apellidop" required>
          </div>

          <div class="campo">
            <label for="apellidom">Apellido materno:</label>
            <input type="text" id="apellidom" name="apellidom" required>
          </div>

          <div class="campo">
            <label for="correo">Correo electronico:</label>
            <input type="text" id="correo" name="correo" required>
          </div>

          <div class="campo">
            <label for="matricula">Matricula:</label>
            <input type="text" id="matricula" name="matricula" required>
          </div>

          <div class="campo">
            <label for="f_nac">Fecha de nacimiento:</label>
            <input type="text" id="f_nac" name="f_nac" required>
          </div>
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

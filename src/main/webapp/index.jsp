<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="css/style.css" />
    <title>Inicio de Sesion</title>
</head>

<body>
<a href="dashboard_student.jsp"></a>
<div class="container" id="container">
    <div class="form-container sign-up">
        <form action="Neo4jServlet" method="get">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required><br><br>

            <label for="ap_paterno">Apellido paterno:</label>
            <input type="text" id="ap_paterno" name="ap_paterno" required><br><br>

            <label for="ap_materno">Apellido materno:</label>
            <input type="text" id="ap_materno" name="ap_materno" required><br><br>

            <label for="matricula">Matricula:</label>
            <input type="text" id="matricula" name="matricula" required><br><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br><br>

            <label for="contrasenia">Contraseña:</label>
            <input type="password" id="contrasenia" name="contrasenia" required><br><br>

            <input type="hidden" name="action" value="crearEstudiante">
            <input type="submit" value="Crear Estudiante">
        </form>
    </div>
    <div class="form-container sign-in">
        <form>
            <h1>Inicia Sesion</h1>

            <span>UvBook</span>
            <input type="email" placeholder="Correo" />
            <input type="password" placeholder="Contraseña" />
            <a href="#">Olvidaste tu password?</a>
            <button>Iniciar Sesion</button>
        </form>
    </div>
    <div class="toggle-container">
        <div class="toggle">
            <div class="toggle-panel toggle-left">
                <h1>¿Ya tienes una cuenta?</h1>
                <p>Inicia sesion y empieza a navegar</p>
                <button class="hidden" id="login">Iniciar sesion</button>
            </div>
            <div class="toggle-panel toggle-right">
                <h1>Bienvenido!</h1>
                <p>
                    Si aun no tienes una cuenta, registrate para utilizar el sitio.
                </p>
                <button class="hidden" id="register">Registrar</button>
            </div>
        </div>
    </div>
</div>

<script src="js/script.js"></script>
</body>
</html>

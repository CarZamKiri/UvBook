<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="style.css" />
    <title>Inicio de Sesion</title>
</head>

<body>
<div class="container" id="container">
    <div class="form-container sign-up">
        <form>
            <h1>Crear cuenta</h1>
            <span>Usa tu Email para registrarte</span>
            <input type="text" placeholder="Nombre" />
            <input type="email" placeholder="Correo" />
            <input type="password" placeholder="Contraseña" />
            <button>Registrar</button>
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

<script src="script.js"></script>
</body>
</html>

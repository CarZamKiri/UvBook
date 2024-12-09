<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                    <li><a href="dashboard_teacher.jsp">Inicio</a></li>
                    <li><a href="chat.jsp">Chat</a></li>
                    <li><a href="index.jsp">Cerrar Sesión</a></li>
                </ul>
            </nav>
        </header>
        <main>
            <div class="questions-list">
                <h3>Preguntas Pendientes</h3>
                <ul>
                    <li>
                        <strong>Juan Pérez</strong>
                        ¿Alguien puede explicarme el concepto de herencia en Java?
                        <br><a href="answer-question.jsp?id=1">Responder</a>
                    </li>
                    <li>
                        <strong>María López</strong>
                        ¿Qué libros recomiendan para aprender Estructura de Datos?
                        <br><a href="answer-question.jsp?id=2">Responder</a>
                    </li>
                </ul>
            </div>
            <div class="questions-list">
                <h3>Preguntas Resueltas</h3>
                <ul>
                    <li>
                        <strong>Alberto Caballero</strong>
                        ¿Cómo crear un archivo WAR?
                        <br><span>Respuesta enviada</span>
                    </li>
                </ul>
            </div>
        </main>
        <footer>
            <p>UVBook - Red Social Académica</p>
        </footer>
    </body>
</html>
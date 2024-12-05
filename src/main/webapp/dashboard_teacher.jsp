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
                <img src="images/teacher-avatar.png" alt="Profesor">
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
                        <strong>Pregunta 1</strong>
                        ¿Cómo implementar un método en Java?
                        <br><a href="answer-question.jsp?id=1">Responder</a>
                    </li>
                    <li>
                        <strong>Pregunta 2</strong>
                        ¿Qué es RMI en Java?
                        <br><a href="answer-question.jsp?id=2">Responder</a>
                    </li>
                </ul>
            </div>
            <div class="questions-list">
                <h3>Preguntas Resueltas</h3>
                <ul>
                    <li>
                        <strong>Pregunta 3</strong>
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
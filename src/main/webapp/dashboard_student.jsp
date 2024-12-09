<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Alumno - Dashboard</title>
        <link rel="stylesheet" href="css/dashboard.css">
    </head>
    <body>
        <header>
            <div class="profile">
                <img src="images/student-avatar.png" alt="Alumno">
                <h2>Bienvenido, Alumno</h2>
            </div>
            <nav>
                <ul>
                    <li><a href="dashboard_student.jsp">Inicio</a></li>
                    <li><a href="chat.jsp">Chat</a></li>
                    <li><a href="index.jsp">Cerrar Sesión</a></li>
                </ul>
            </nav>
        </header>
        <main>
            <div class="post-question">
                <h3>Publicar una Pregunta</h3>
                <form action="Neo4jSvp" method="POST">
                    <textarea name="question" placeholder="Escribe tu pregunta aquí..." required></textarea>
                    <input type="hidden" name="action" value="crearPregunta">
                    <button type="submit">Publicar</button>
                </form>
            </div>
            <div class="questions-list">
                <h3>Preguntas Recientes</h3>
                <ul>
                    <li>
                        <strong>Juan Pérez</strong>
                        ¿Alguien puede explicarme el concepto de herencia en Java?
                        <br><a href="view-question.jsp?id=1">Responder</a>
                    </li>
                    <li>
                        <strong>María Lopez</strong>
                        ¿Qué libros recomiendan para aprender Estructura de Datos?
                        <br><a href="view-question.jsp?id=2">Responder</a>
                    </li>
                    <c:forEach var="pregunta" items="${preguntas}">
                        <li>${pregunta}</li>
                    </c:forEach>
                </ul>
            </div>
        </main>
        <footer>
            <p>UVBook - Red Social Académica</p>
        </footer>
    </body>
</html>
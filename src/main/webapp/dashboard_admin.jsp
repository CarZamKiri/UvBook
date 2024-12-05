<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Administrador - Dashboard</title>
        <link rel="stylesheet" href="css/dashboard.css">
    </head>
    <body>
        <header>
            <div class="profile">
                <img src="images/admin-avatar.png" alt="Administrador">
                <h2>Bienvenido, Administrador</h2>
            </div>
            <nav>
                <ul>
                    <li><a href="dashboard_admin.jsp">Inicio</a></li>
                    <li><a href="users.jsp">Gestionar Usuarios</a></li>
                    <li><a href="index.jsp">Cerrar Sesión</a></li>
                </ul>
            </nav>
        </header>
        <main>
            <div class="questions-list">
                <h3>Preguntas Publicadas</h3>
                <ul>
                    <li>
                        <strong>Juan Pérez</strong>
                        ¿Alguien puede explicarme el concepto de herencia en Java?
                        <br><button onclick="deleteQuestion(1)">Eliminar</button>
                    </li>
                    <li>
                        <strong>María López</strong>
                        ¿Qué libros recomiendan para aprender Estructura de Datos?
                        <br><button onclick="deleteQuestion(2)">Eliminar</button>
                    </li>
                </ul>
            </div>
        </main>
        <footer>
            <p>UVBook - Red Social Académica</p>
        </footer>
        <script>
            function deleteQuestion(id) {
                if (confirm("¿Estás seguro de que deseas eliminar esta pregunta?")) {
                    window.location.href = `deleteQuestion.jsp?id=${id}`;
                }
            }
        </script>
    </body>
</html>
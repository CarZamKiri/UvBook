<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="UvBook.Estudiante" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chat - UVBook</title>
    <link rel="stylesheet" href="css/chat.css">
    <script>
        let socket;
        function initSocket() {
            socket = new WebSocket("ws://localhost:8080/chatEndpoint");
            socket.onmessage = (event) => {
                const chatWindow = document.getElementById("chat-messages");
                chatWindow.innerHTML += `<div>${event.data}</div>`;
            };
            socket.onclose = () => {
                console.log("Conexión cerrada.");
            };
        }
        function sendMessage() {
            const message = document.getElementById("messageInput").value;
            const userName = document.getElementById("userName").value;
            if (message.trim() !== "") {
                socket.send(userName + ": " + message);
                document.getElementById("messageInput").value = "";
            }
        }
    </script>

</head>
<body onload="initSocket()">
<header>
    <div class="profile">
        <img src="images/chat-avatar.png" alt="Chat">
        <h2>Bienvenido al Chat</h2>
    </div>
    <nav>
        <ul>
            <li><a href="DashboardServlet">Inicio</a></li>
            <li><a href="index.jsp">Cerrar Sesión</a></li>
        </ul>
    </nav>
</header>
<main class="chat-container">
    <aside class="user-list">
        <h3>Usuarios Disponibles</h3>
        <ul>
            <%
                List<Estudiante> estudiantes = (List<Estudiante>) request.getAttribute("estudiantes");
                if (estudiantes != null) {
                    for (Estudiante estudiante : estudiantes) {
            %>
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
    <section class="chat-section">
        <div id="chat-messages" class="messages"></div>
        <div class="chat-input">
            <input type="text" id="messageInput" placeholder="Escribe tu mensaje aquí..." />
            <button onclick="sendMessage()">Enviar</button>
        </div>
    </section>
</main>
<footer>
    <p>UVBook - Red Social Académica</p>
</footer>
</body>
</html>
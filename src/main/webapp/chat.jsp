<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="UvBook.Estudiante" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chat - UVBook</title>
    <link rel="stylesheet" type="text/css" href="css/chat.css">
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
            <% } %>
        </ul>
    </aside>
    <section class="chat-area">
        <div id="chat-header">
            <h3>Chat con <span id="selected-user">[Selecciona un estudiante]</span></h3>
        </div>
        <div id="chat-messages"></div>
        <form id="chat-form" onsubmit="sendMessage(event)">
            <input type="hidden" id="username" value="<%= request.getSession().getAttribute("correo") %>">
            <input type="text" id="message-input" placeholder="Escribe tu mensaje..." required>
            <button type="submit">Enviar</button>
        </form>
    </section>
</main>
<footer>
    <p>UVBook - Red Social Académica</p>
</footer>

<script>
    let websocket;
    let selectedUser = null;

    function initSocket() {
        websocket = new WebSocket(`ws://localhost:8080/chatEndpoint?user=${encodeURIComponent(username)}`);

        websocket.onopen = () => {
            console.log("Conectado al servidor WebSocket");
        };

        websocket.onmessage = (event) => {
            const data = JSON.parse(event.data);
            const chatMessages = document.getElementById("chat-messages");
            const newMessage = document.createElement("div");
            newMessage.className = data.sender === document.getElementById("username").value ? "sent-message" : "received-message";
            newMessage.innerText = `${data.sender}: ${data.message}`;
            chatMessages.appendChild(newMessage);
        };

        websocket.onclose = () => {
            console.log("Conexión WebSocket cerrada");
        };
    }

    function selectUser(user) {
        selectedUser = user;
        document.getElementById("selected-user").innerText = user;
        document.getElementById("chat-messages").innerHTML = "";
    }

    async function sendMessage(event) {
        event.preventDefault();
        if (!selectedUser) {
            alert("Por favor selecciona un estudiante para chatear.");
            return;
        }

        const username = document.getElementById("username").value;
        const message = document.getElementById("message-input").value.trim();

        if (message !== "") {
            const formattedMessage = JSON.stringify({ sender: username, receiver: selectedUser, message });
            websocket.send(formattedMessage);

            const chatMessages = document.getElementById("chat-messages");
            const newMessage = document.createElement("div");
            newMessage.className = "sent-message";
            newMessage.innerText = `Tú: ${message}`;
            chatMessages.appendChild(newMessage);

            document.getElementById("message-input").value = "";
        }
    }
</script>
</body>
</html>

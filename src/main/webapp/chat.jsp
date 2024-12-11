<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="UvBook.Estudiante" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Chat - UVBook</title>
        <link rel="stylesheet" href="css/chat.css">
    </head>
    <body>
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
            <div class="user-list">
            <h3>Usuarios Disponibles</h3>
                <ul>
                    <%
                        List<Estudiante> estudiantes = (List<Estudiante>) request.getAttribute("estudiantes");
                        if (estudiantes != null && !estudiantes.isEmpty()) {
                            for (Estudiante estudiante : estudiantes) {
                    %>
                    <li><%= estudiante.getMatricula() %></li>
                    <li><strong><%= estudiante.getNombre() %></strong></li>
                    <%
                        }
                    } else {
                    %>
                    <li>No hay usuarios disponibles.</li>
                    <%
                        }
                    %>

                </ul>
            </div>


            <!-- Chat Principal -->
            <section class="chat-box">
                <div id="chat-header">
                    <h3>Chat con <span id="selected-user">[Selecciona un estudiante]</span></h3>
                </div>
                <div id="chat-messages">
                    <c:forEach var="message" items="${messages}">
                        <div>${message}</div>
                    </c:forEach>
                </div>
                <form id="chat-form" action="chat" method="post">
                    <input type="hidden" name="username" value="${sessionScope.username}">
                    <input type="text" name="message" placeholder="Escribe tu mensaje..." required>
                    <button type="submit">Enviar</button>
                </form>
            </section>
        </main>
        <footer>
            <p>UVBook - Red Social Académica</p>
        </footer>

        <script>
            let selectedUser = null;

            function selectUser(user) {
                selectedUser = user;
                document.getElementById("selected-user").innerText = user;
                document.getElementById("chat-messages").innerHTML = "";
            }

            function sendMessage(event) {
                event.preventDefault();
                if (!selectedUser) {
                    alert("Por favor selecciona un estudiante para chatear.");
                    return;
                }

                const input = document.getElementById("chat-input");
                const message = input.value.trim();

                if (message !== "") {
                    const chatMessages = document.getElementById("chat-messages");
                    const newMessage = document.createElement("div");
                    newMessage.className = "sent-message";
                    newMessage.innerText = `Tú: ${message}`;
                    chatMessages.appendChild(newMessage);
                    chatMessages.scrollTop = chatMessages.scrollHeight; // Baja al final

                    input.value = "";
                }
            }
        </script>
    </body>
</html>
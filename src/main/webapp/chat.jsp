<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                    <li><a href="dashboard_studen.jsp">Inicio</a></li>
                    <li><a href="index.jsp">Cerrar Sesión</a></li>
                </ul>
            </nav>
        </header>
        <main class="chat-container">
            <aside class="user-list">
                <h3>Usuarios Conectados</h3>
                <ul>
                    <li><a href="#" onclick="selectUser('Juan Pérez')">Juan Pérez</a></li>
                    <li><a href="#" onclick="selectUser('María López')">María López</a></li>
                    <li><a href="#" onclick="selectUser('Profesor González')">Profesor González</a></li>
                    <li><a href="#" onclick="selectUser('Ana Torres')">Ana Torres</a></li>
                </ul>
            </aside>

            <!-- Chat Principal -->
            <section class="chat-box">
                <div id="chat-header">
                    <h3>Chat con <span id="selected-user">[Selecciona un usuario]</span></h3>
                </div>
                <div id="chat-messages">
                </div>
                <form id="chat-form" onsubmit="sendMessage(event)">
                    <input type="text" id="chat-input" placeholder="Escribe tu mensaje..." required>
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
                    alert("Por favor selecciona un usuario para chatear.");
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
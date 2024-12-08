package UvBook;

import org.neo4j.driver.*;

public class Neo4jUserManager { /*
    private final Neo4jDAO connection;

    // Constructor que recibe una conexión existente
    public Neo4jUserManager(Neo4jDAO connection) {
        this.connection = connection;
    }

    public void registerUser(String username, String password) {
        String hashedPassword = PasswordUtils.hashPassword(password); // Encriptar contraseña
        String query = String.format(
                "CREATE (u:User {username: '%s', password: '%s'})",
                username, hashedPassword
        );

        connection.executeQuery(query); // Usar la conexión para ejecutar la consulta
        System.out.println("User registered successfully.");
    }

    public boolean loginUser(String username, String password) {
        String query = String.format(
                "MATCH (u:User {username: '%s'}) RETURN u.password AS password",
                username
        );

        try (Session session = connection.getDriver().session()) {
            Result result = session.run(query);

            if (result.hasNext()) {
                // Usar el nombre completamente calificado para evitar conflicto
                org.neo4j.driver.Record record = result.next();
                String hashedPassword = record.get("password").asString();
                return PasswordUtils.verifyPassword(password, hashedPassword);
            } else {
                System.out.println("User not found.");
                return false;
            }
        }
    } */
}

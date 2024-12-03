package UvBook;

import org.neo4j.driver.*; //Gestiona la conexion con la bd

public class Neo4jConnection implements AutoCloseable {
    private final Driver driver;

    public Neo4jConnection(String uri, String user, String password) {
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    public void executeQuery(String query) {
        try (Session session = driver.session()) {
            session.run(query);
            System.out.println("Query executed successfully: " + query);
        }
    }

    public Driver getDriver() {
        return driver;
    }


    public static void main(String[] args) {
        // Carga de credenciales de acceso a AuraDB
        String uri = "neo4j+s://cc5ca0ee.databases.neo4j.io";
        String user = "neo4j";
        String password = "ONBGDyFOd__VimALjNLZJ8GV7kshemkphWfVcUgsf7g";

        try (Neo4jConnection connection = new Neo4jConnection(uri, user, password)) {
            Neo4jUserManager userManager = new Neo4jUserManager(connection);

            // Registrar un usuario
            userManager.registerUser("john_doe", "securePassword123");

            // Intentar iniciar sesión con la contraseña correcta
            boolean loginSuccess = userManager.loginUser("john_doe", "securePassword123");
            System.out.println("Login successful: " + loginSuccess);

            // Intentar iniciar sesión con una contraseña incorrecta
            boolean loginFail = userManager.loginUser("john_doe", "wrongPassword");
            System.out.println("Login successful: " + loginFail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

import org.neo4j.driver.*;

import static org.neo4j.driver.Values.parameters;

public class Neo4jDAO {
    private static final String URI = "neo4j+s://cc5ca0ee.databases.neo4j.io";
    private static final String USER = "neo4j";
    private static final String PASSWORD = "ONBGDyFOd__VimALjNLZJ8GV7kshemkphWfVcUgsf7g";
    private Driver driver;

    public Neo4jDAO() {
        driver = GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
    }

    public String getGreeting() {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                Result result = tx.run("RETURN 'Hello, Neo4j!'");
                return result.single().get(0).asString();
            });
        }
    }

    public void crearEstudiante(String nombre, String ap_paterno, String ap_materno, String matricula, String correo, String contrasenia) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("CREATE (n:estudiante {nombre: $nombre, ap_paterno: $ap_paterno, ap_materno: $ap_materno, matricula: $matricula, correo: $correo, contraseña: $contraseña})",
                        parameters("nombre", nombre, "ap_paterno", ap_paterno, "ap_materno", ap_materno, "matricula", matricula, "correo", correo, "contraseña", contrasenia));
                return null;
            });
        }
    }

    public void close() {
        if (driver != null) {
            driver.close();
        }
    }
}
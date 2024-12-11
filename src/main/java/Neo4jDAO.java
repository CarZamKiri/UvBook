import UvBook.Pregunta;
import UvBook.Estudiante;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class Neo4jDAO {
    private static final String URI = "neo4j+s://cc5ca0ee.databases.neo4j.io";
    private static final String USER = "neo4j";
    private static final String PASSWORD = "ONBGDyFOd__VimALjNLZJ8GV7kshemkphWfVcUgsf7g";
    private final Driver driver;
    private final BCryptPasswordEncoder passwordEncoder;

    public Neo4jDAO() {
        driver = GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
        passwordEncoder = new BCryptPasswordEncoder();
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
        // Encriptar la contraseña antes de guardarla
        String hashedPassword = passwordEncoder.encode(contrasenia);

        try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("CREATE (n:estudiante {nombre: $nombre, ap_paterno: $ap_paterno, ap_materno: $ap_materno, matricula: $matricula, correo: $correo, contraseña: $contraseña})",
                        parameters("nombre", nombre, "ap_paterno", ap_paterno, "ap_materno", ap_materno, "matricula", matricula, "correo", correo, "contraseña", hashedPassword));
                return null;
            });
        }
    }

    public boolean validarInicioSesion(String correo, String contraseniaIngresada) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                Result result = tx.run("MATCH (n:estudiante {correo: $correo}) RETURN n.contraseña AS contraseña",
                        parameters("correo", correo));
                if (result.hasNext()) {
                    String hashedPassword = result.next().get("contraseña").asString();
                    // Comparar la contraseña ingresada con la encriptada
                    return passwordEncoder.matches(contraseniaIngresada, hashedPassword);
                }
                return false; // Estudiante no encontrado
            });
        }
    }

    public void crearPregunta(String texto){
        try(Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("CREATE(p:pregunta{texto: $texto, fecha: date()})", parameters("texto", texto));
                return null;
            });
        }
    }

    public void relacionAlumnoPregunta(String correo, String texto){
        try(Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("MATCH (e:estudiante {correo: $correo}), (p:pregunta {texto: $texto}) " +
                                "CREATE (e)-[:HACE]->(p)", parameters("correo", correo, "texto", texto));
                return null;
            });
        }
    }

    public List<String> mostrarPreguntas(){
        List<String>preguntas = new ArrayList<>();
        try(Session session = driver.session()) {
            session.readTransaction(tx -> {
                Result result = tx.run("MATCH(p:pregunta) RETURN p.texto AS texto");
                while (result.hasNext()) {
                    preguntas.add(result.next().get("texto").asString());
                }
                return  null;
            });
        }
        return preguntas;
    }

    public void crearRespuesta(String textor){
        try(Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("CREATE(r:respuesta {texto: $texto, fecha: date()})", parameters("texto", textor));
                return null;
            });
        }
    }

    public void relacionPreguntaRespuesta(String texto, String textor){
        try(Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("MATCH (p:pregunta {texto: $texto}), (r:respuesta {texto: $texto}),  " +
                        "CREATE (p)-[:PERTENECE]->(r)", parameters("texto", texto, "textor", textor));
                return null;
            });
        }
    }

    public List<String> mostrarPreguntasRespondidas(){
        List<String>preguntas = new ArrayList<>();
        try(Session session = driver.session()) {
            session.readTransaction(tx -> {
                Result result = tx.run("MATCH n = (p:pregunta) - [a:PERTENECE] -> (r:respuesta) RETURN n");
                while (result.hasNext()) {
                    preguntas.add(result.next().get("texto").asString());
                }
                return  null;
            });
        }
        return preguntas;
    }

    public List<Pregunta> obtenerPreguntas() {
        List<Pregunta> preguntas = new ArrayList<>();
        try (Session session = driver.session()) {
            session.readTransaction(tx -> {
                Result result = tx.run("MATCH (p:pregunta) RETURN p.texto AS texto, p.fecha AS fecha, p.adjunto AS adjunto");

                while (result.hasNext()) {
                    Record record = result.next();
                    String texto = record.get("texto").asString();
                    String fecha;

                    // Intentar convertir la fecha
                    try {
                        fecha = record.get("fecha").asZonedDateTime().toLocalDateTime().toString(); // Si es tipo ZonedDateTime
                    } catch (Exception e) {
                        fecha = record.get("fecha").asString(); // Si ya es tipo String
                    }

                    String adjunto = record.containsKey("adjunto") ? record.get("adjunto").asString() : null;

                    Pregunta pregunta = new Pregunta(texto, fecha, adjunto);
                    preguntas.add(pregunta);
                }
                System.out.println(preguntas);

                return null;
            });
        }
        return preguntas;
    }

    public List<Estudiante> obtenerEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (Session session = driver.session()) {
            session.readTransaction(tx -> {
                Result result = tx.run("MATCH (e:estudiante) RETURN e.matricula AS matricula, e.nombre AS nombre");
                while (result.hasNext()) {
                    Record record = result.next();
                    String matricula = record.get("matricula").asString();
                    String nombre = record.get("nombre").asString();

                    Estudiante estudiante = new Estudiante(matricula, nombre);
                    estudiantes.add(estudiante);
                }
                System.out.println(estudiantes);
                return null;
            });
        }
        return estudiantes;
    }

    public void close() {
        if (driver != null) {
            driver.close();
        }
    }
}

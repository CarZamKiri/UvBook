import UvBook.Estudiante;
import UvBook.Pregunta;

import java.util.List;

public class Neo4jMainTest {
    public static void main(String[] args) {
        Neo4jDAO neo4jDAO = new Neo4jDAO();

        try {
            System.out.println("Conectando a Neo4j...");

            // Probar obtener estudiantes
            List<Pregunta> estudiantes = neo4jDAO.obtenerPreguntas();

            if (estudiantes.isEmpty()) {
                System.out.println("No se encontraron estudiantes en la base de datos.");
            } else {
                System.out.println("Estudiantes encontrados:");
                for (Pregunta estudiante : estudiantes) {
                    System.out.println("Matrícula: " + estudiante.getFecha() + ", Nombre: " + estudiante.getTexto());
                }
            }
        } catch (Exception e) {
            System.err.println("Ocurrió un error al interactuar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar la conexión
            neo4jDAO.close();
            System.out.println("Conexión cerrada.");
        }
    }
}
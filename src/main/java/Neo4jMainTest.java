import UvBook.Estudiante;
import java.util.List;

public class Neo4jMainTest {
    public static void main(String[] args) {
        Neo4jDAO neo4jDAO = new Neo4jDAO();

        try {
            System.out.println("Conectando a Neo4j...");

            // Probar obtener estudiantes
            List<Estudiante> estudiantes = neo4jDAO.obtenerEstudiantes();

            if (estudiantes.isEmpty()) {
                System.out.println("No se encontraron estudiantes en la base de datos.");
            } else {
                System.out.println("Estudiantes encontrados:");
                for (Estudiante estudiante : estudiantes) {
                    System.out.println("Matrícula: " + estudiante.getMatricula() + ", Nombre: " + estudiante.getNombre());
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
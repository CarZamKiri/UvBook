package UvBook;

public class Neo4jSubject {
    private final Neo4jConnection connection;

    public Neo4jSubject(Neo4jConnection connection) {
        this.connection = connection;
    }

    public void creation(String subject) {
        String Subject = subject.replace("'", "\\'"); //Evita que se rompa la consulta en caso de que se ingresen ''
        String query = String.format("CREATE (S:Subject {name: '%s'})", Subject);

        connection.executeQuery(query); // Usamos el metodo sin parámetros
        //System.out.println("Subject created: " + subject);
    }
}

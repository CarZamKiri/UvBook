package UvBook;

public class Neo4jUserdata {
    private final Neo4jConnection connection;
    String name;
    String firs_lastName;
    String second_lastName;
    String email;
    String matricula;
    String password;

    public Neo4jUserdata(Neo4jConnection connection, String name, String first_lastName, String second_lastName, String email, String matricula, String password) {
        this.connection = connection;
        this.name = name;
        this.firs_lastName = first_lastName;
        this.second_lastName = second_lastName;
        this.email = email;
        this.matricula = matricula;
        this.password = password;
    }

    public void create(){
        String query = String.format("CREATE (U:User {name: '%s', first_lastname: '%s', second_lastname: '%s', email: '%s', matricula: '%s', password: '%s'})",
                name, firs_lastName, second_lastName, email, matricula, password);
        connection.executeQuery(query);
    }

}

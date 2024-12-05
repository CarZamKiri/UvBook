package UvBook;

import org.neo4j.driver.Value;
import org.neo4j.driver.Values;

public class Neo4jQuestion {
    private final Neo4jConnection connection;
    String question;

    public Neo4jQuestion(Neo4jConnection connection, String question) {
        this.connection = connection;
        this.question = question;
    }

    public void create(String question){
        String Question = question.replace("'", "\\'"); //Evita que se rompa la consulta en caso de que se ingresen ''
        String query = String.format("CREATE (Q:Question {name: '$name'})", question);

        connection.executeQuery(query);
    }

    public void Update(String oldname, String newname) {
        String query = String.format(
                "MATCH (Q:%s {name: $oldname}) SET Q.name = $newname Return Q", oldname, newname);
        connection.executeQuery(query);

    }


}

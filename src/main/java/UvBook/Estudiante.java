package UvBook;

public class Estudiante {
    private String matricula;
    private String nombre;

    public Estudiante(String id, String nombre) {
        this.matricula = id;
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "UvBook.Estudiante{" +
                "matricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
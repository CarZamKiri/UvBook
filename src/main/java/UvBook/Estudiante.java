package UvBook;

public class Estudiante {
    private int matricula;
    private String nombre;

    public Estudiante(int id, String nombre) {
        this.matricula = id;
        this.nombre = nombre;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
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
package UvBook;

public class Perfil{
    private String nombre;
    private String apellidop;
    private String apellidom;
    private String matricula;
    private String correo;

    public Perfil(String nombre, String apellidop, String apellidom, String matricula, String correo) {
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.matricula = matricula;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String toString() {
        return "UvBook.Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", apellidop='" + apellidop + '\'' +
                ", apellidom='" + apellidom + '\'' +
                ", matricula='" + matricula + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }

}

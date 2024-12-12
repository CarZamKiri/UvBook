package UvBook;

import java.util.ArrayList;
import java.util.List;

//Clase para agregar materias
public class Respuestas {
    private String texto;
    private String fecha;
    private String adjunto;
    private List<Pregunta> pregunta; //Relacion con las preguntas
    // Constructor
    public Respuestas(String texto, String fecha, String adjunto) {
        this.texto = texto;
        this.fecha = fecha;
        this.adjunto = adjunto;
        this.pregunta = new ArrayList<>();
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }

    public List<Pregunta> getPregunta() {
        return pregunta;
    }
    public void agregarPregunta(Pregunta preguntas) {
        this.pregunta.add(preguntas);
    }

    // Sobrescribir toString
    @Override
    public String toString() {
        return "UvBook.Respuestas{" +
                " Pregunta='" + pregunta + '\'' +
                ", texto='" + texto + '\'' +
                ", fecha='" + fecha + '\'' +
                ", adjunto='" + adjunto + '\'' +
                '}';
    }
}

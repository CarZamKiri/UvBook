package UvBook;

public class Pregunta {
    private String texto;
    private String fecha;
    private String adjunto;

    // Constructor
    public Pregunta(String texto, String fecha, String adjunto) {
        this.texto = texto;
        this.fecha = fecha;
        this.adjunto = adjunto;
    }

    // Getters y Setters
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


    // Sobrescribir toString
    @Override
    public String toString() {
        return "UvBook.Pregunta{" +
                "texto='" + texto + '\'' +
                ", fecha='" + fecha + '\'' +
                ", adjunto='" + adjunto + '\'' +
                '}';
    }
}

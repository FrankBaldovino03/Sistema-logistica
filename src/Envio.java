import java.time.LocalDate;
import java.util.UUID;

public abstract class Envio {

    protected String id;
    protected String origen;
    protected String destino;
    protected double peso; // en kilogramos
    protected String prioridad; // Ejemplo: "ALTA", "MEDIA", "BAJA"
    protected LocalDate fechaEnvio;
    protected String estado; // Ejemplo: "PENDIENTE", "EN TRÁNSITO", "ENTREGADO"
    protected String tipoEnvio; // Ejemplo: "NACIONAL", "INTERNACIONAL"

    /**
     * Constructor base para inicializar un envío.
     */
    public Envio(String origen, String destino, double peso, String prioridad, String tipoEnvio) {
        if (origen == null || origen.isBlank()) throw new IllegalArgumentException("El origen no puede ser vacío.");
        if (destino == null || destino.isBlank()) throw new IllegalArgumentException("El destino no puede ser vacío.");
        if (peso <= 0) throw new IllegalArgumentException("El peso debe ser mayor a 0.");

        this.id = generarNumeroSeguimiento();
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.prioridad = prioridad;
        this.estado = "PENDIENTE"; // valor por defecto
        this.fechaEnvio = LocalDate.now();
        this.tipoEnvio = tipoEnvio;
    }

    // ======== MÉTODOS ABSTRACTOS ========

    /** Calcula el costo total del envío. */
    public abstract double calcularCosto();

    /** Calcula el tiempo estimado de entrega (en días). */
    public abstract int calcularTiempoEntrega();

    /** Devuelve detalles específicos del tipo de envío. */
    public abstract String obtenerDetallesEspecificos();

    // ======== MÉTODOS CONCRETOS ========

    /** Genera un número de seguimiento único basado en UUID. */
    public String generarNumeroSeguimiento() {
        return tipoEnvio.charAt(0) + "-" + UUID.randomUUID().toString().substring(0, 8);
    }

    /** Actualiza el estado actual del envío. */
    public void actualizarEstado(String nuevoEstado) {
        if (nuevoEstado == null || nuevoEstado.isBlank())
            throw new IllegalArgumentException("El estado no puede ser vacío.");
        this.estado = nuevoEstado;
    }

    // ======== GETTERS Y SETTERS ========

    public String getId() { return id; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public double getPeso() { return peso; }
    public String getPrioridad() { return prioridad; }
    public String getEstado() { return estado; }
    public LocalDate getFechaEnvio() { return fechaEnvio; }
    public String getTipoEnvio() { return tipoEnvio; }

    public void setPeso(double peso) {
        if (peso <= 0) throw new IllegalArgumentException("El peso debe ser mayor a 0.");
        this.peso = peso;
    }

    // ======== MÉTODO toString() ========

    @Override
    public String toString() {
        return "Envio{" +
                "id='" + id + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", peso=" + peso +
                ", prioridad='" + prioridad + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", tipoEnvio='" + tipoEnvio + '\'' +
                ", detalles=" + obtenerDetallesEspecificos() +
                '}';
    }
}

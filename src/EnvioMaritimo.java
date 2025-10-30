public class EnvioMaritimo extends Envio {
    private String puertoDestino;
    private static final double COSTO_POR_KG = 2000.0;
    private static final double RECARGO_CONTENEDOR = 150000.0; // si peso > 1000 kg
    private static final int TIEMPO_PROMEDIO = 30;

   
    
    public EnvioMaritimo(String origen, String destino, double peso, String prioridad, String tipoEnvio,
            String puertoDestino) {
        super(origen, destino, peso, prioridad, tipoEnvio);
          if (puertoDestino == null || puertoDestino.isBlank()) throw new IllegalArgumentException("Puerto destino requerido");
        this.puertoDestino = puertoDestino;
    }

    @Override
    public double calcularCosto() {
        double costo = COSTO_POR_KG * peso;
        if (peso > 1000.0) costo += RECARGO_CONTENEDOR;
        return Math.round(costo);
    }

    @Override
    public int calcularTiempoEntrega() {
        // Retornamos promedio 30 días para facilidad
        return TIEMPO_PROMEDIO;
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return "Maritimo{puertoDestino=" + puertoDestino + ", tiempoEstimadoDias=" + TIEMPO_PROMEDIO + "}";
    }

    public String getPuertoDestino() { return puertoDestino; }
}
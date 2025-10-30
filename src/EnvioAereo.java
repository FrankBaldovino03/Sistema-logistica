public class EnvioAereo extends Envio {

    private boolean esInternacional;
    private static final double COSTO_POR_KG = 15000.0;
    private static final double RECARGO_INTERNACIONAL = 50000.0;
    private static final double RECARGO_EXPRESS = 0.8; // 80%
    private static final double PESO_MAXIMO = 1000.0;

    public EnvioAereo(String origen, String destino, double peso, String prioridad, String tipoEnvio,
            boolean esInternacional) {
        super(origen, destino, peso, prioridad, tipoEnvio);
        this.esInternacional = esInternacional;
    }

    @Override
    public double calcularCosto() {
        double costo = COSTO_POR_KG * peso;
        if (esInternacional)
            costo += RECARGO_INTERNACIONAL;
        if (prioridad.equalsIgnoreCase("EXPRESS")) {
            costo += costo * RECARGO_EXPRESS;
        }
        return Math.round(costo);
    }

    @Override
    public int calcularTiempoEntrega() {
        if (esInternacional) {
            return 3 + (int) (Math.random() * 5); // 3-7 días (aleatorio)
        } else {
            return 1 + (int) (Math.random() * 3); // 1-3 días
        }
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return "Aereo{esInternacional=" + esInternacional + ", tiempoEstimadoDias=" + calcularTiempoEntrega() + "}";
    }

    public boolean isEsInternacional() {
        return esInternacional;
    }
}


  public class EnvioTerrestre extends Envio {

    private double distanciaKm;

    // Constantes
    private static final double COSTO_POR_KG = 5000.0;
    private static final double COSTO_POR_100KM = 500.0;
    private static final double RECARGO_EXPRESS = 0.5; // 50%
    private static final double PESO_MAXIMO = 5000.0;



    public EnvioTerrestre(String origen, String destino, double peso, String prioridad, String tipoEnvio,
            double distanciaKm) {
        super(origen, destino, peso, prioridad, tipoEnvio);
        this.distanciaKm = distanciaKm;
        if (distanciaKm <= 0)
            throw new IllegalArgumentException("La distancia debe ser mayor que 0.");
        if (peso <= 0 || peso > PESO_MAXIMO)
            throw new IllegalArgumentException("Peso inválido para envío terrestre (de 0 a 5000 kg).");
    }

    @Override
    public double calcularCosto() {
        // 🔧 Ajuste: 'peso' y 'prioridad' deben ser accesibles (usa getters o protégelos)
        double costo = COSTO_POR_KG * getPeso();

        double unidades100km = Math.ceil(distanciaKm / 100.0);
        costo += unidades100km * COSTO_POR_100KM;

        // ⚠️ Error corregido: no se compara Strings con '=='
        // Usa equalsIgnoreCase para comparar el valor de prioridad
        if ("EXPRESS".equalsIgnoreCase(getPrioridad())) {
            costo += costo * RECARGO_EXPRESS;
        }

        return Math.round(costo); // Redondea el resultado
    }

    @Override
    public int calcularTiempoEntrega() {
        // Aproximadamente 1 día por cada 500 km, mínimo 1 día
        return (int) Math.max(1, Math.ceil(distanciaKm / 500.0));
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return "Terrestre { distanciaKm = " + distanciaKm +
               ", tiempoEstimadoDias = " + calcularTiempoEntrega() + " }";
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }
}




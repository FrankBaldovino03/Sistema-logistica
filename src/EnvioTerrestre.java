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
        if (distanciaKm <= 0)
            throw new IllegalArgumentException("La distancia debe ser mayor que 0.");
        if (peso <= 0 || peso > PESO_MAXIMO)
            throw new IllegalArgumentException("Peso inválido para envío terrestre (de 0 a 5000 kg).");
        this.distanciaKm = distanciaKm;
    }


   @Override
public double calcularCosto() {
    double costo = COSTO_POR_KG * getPeso();

    double unidades100km = Math.ceil(distanciaKm / 100.0);
    costo += unidades100km * COSTO_POR_100KM;

    // ✅ Comparación correcta de Strings
    if ("EXPRESS".equalsIgnoreCase(getPrioridad())) {
        costo += costo * RECARGO_EXPRESS;
    }

    return Math.round(costo); // ✅ Redondea el resultado
}

@Override
public int calcularTiempoEntrega() {
    // ✅ Corrección: “a:1” no existe → era un error de sintaxis
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

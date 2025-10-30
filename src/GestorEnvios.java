
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class GestorEnvios {

    private final ArrayList<Envio> listaEnvios;

    public GestorEnvios() {
        this.listaEnvios = new ArrayList<>();
    }

    // Agregar envío
    public void agregarEnvio(Envio envio) {
        if (envio == null) {
            throw new IllegalArgumentException("El envío no puede ser null.");
        }
        this.listaEnvios.add(envio);
    }

    // Listar todos los envíos
    public void listarTodosLosEnvios() {
        if (listaEnvios.isEmpty()) {
            System.out.println("No hay envíos registrados.");
            return;
        }
        listaEnvios.forEach(envio -> {
            System.out.println(envio);
            System.out.println("-----------------------------------------");
        });
    }

    // Buscar envío por ID
    public Envio buscarEnvioPorId(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        return listaEnvios.stream()
                .filter(e -> id.equals(e.getId()))
                .findFirst()
                .orElse(null);
    }

    // Calcular costo total
    public double calcularCostoTotal() {
        return listaEnvios.stream()
                .mapToDouble(Envio::calcularCosto)
                .sum();
    }

    // Filtrar por tipo de envío (String)
    public ArrayList<Envio> filtrarPorTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            return new ArrayList<>();
        }
        List<Envio> filtrados = listaEnvios.stream()
                .filter(e -> tipo.equalsIgnoreCase(e.getTipoEnvio()))
                .collect(Collectors.toList());
        return new ArrayList<>(filtrados);
    }

    // Filtrar por estado (String)
    public ArrayList<Envio> filtrarPorEstado(String estado) {
        if (estado == null || estado.isBlank()) {
            return new ArrayList<>();
        }
        List<Envio> filtrados = listaEnvios.stream()
                .filter(e -> estado.equalsIgnoreCase(e.getEstado()))
                .collect(Collectors.toList());
        return new ArrayList<>(filtrados);
    }

    // Generar reporte
    public void generarReporte() {
        DecimalFormat df = new DecimalFormat("#,##0.00");

        int total = listaEnvios.size();
        double costoTotal = calcularCostoTotal();
        double costoPromedio = total == 0 ? 0.0 : costoTotal / total;

        System.out.println("===== REPORTE GESTOR DE ENVIOS =====");
        System.out.println("Total de envíos: " + total);
        System.out.println("Costo total (COP): " + df.format(costoTotal));
        System.out.println("Costo promedio (COP): " + df.format(costoPromedio));
        System.out.println();

        // Conteo por tipo de envío
        Map<String, Long> porTipo = listaEnvios.stream()
                .collect(Collectors.groupingBy(Envio::getTipoEnvio, Collectors.counting()));

        System.out.println("Cantidad por Tipo de Envío:");
        porTipo.forEach((tipo, cantidad) ->
                System.out.printf(" - %s : %d%n", tipo, cantidad)
        );
        System.out.println();

        // Conteo por estado
        Map<String, Long> porEstado = listaEnvios.stream()
                .collect(Collectors.groupingBy(Envio::getEstado, Collectors.counting()));

        System.out.println("Cantidad por Estado de Envío:");
        porEstado.forEach((estado, cantidad) ->
                System.out.printf(" - %s : %d%n", estado, cantidad)
        );

        System.out.println("====================================");
    }

    // Obtener lista de envíos
    public ArrayList<Envio> obtenerListaEnvios() {
        return new ArrayList<>(this.listaEnvios);
    }
}

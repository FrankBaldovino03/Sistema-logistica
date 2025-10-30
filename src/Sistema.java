
import java.util.Scanner;

/**
 * Sistema principal de gestión de envíos Logiexpress.
 * Permite registrar, listar, buscar, filtrar y generar reportes.
 */
public class Sistema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorEnvios gestor = new GestorEnvios();
        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE GESTIÓN DE ENVIOS =====");
            System.out.println("1. Registrar nuevo envío");
            System.out.println("2. Listar todos los envíos");
            System.out.println("3. Buscar envío por ID");
            System.out.println("4. Filtrar envíos por tipo");
            System.out.println("5. Filtrar envíos por estado");
            System.out.println("6. Mostrar reporte general");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> registrarEnvio(gestor, sc);
                case 2 -> gestor.listarTodosLosEnvios();
                case 3 -> buscarPorId(gestor, sc);
                case 4 -> filtrarPorTipo(gestor, sc);
                case 5 -> filtrarPorEstado(gestor, sc);
                case 6 -> gestor.generarReporte();
                case 0 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("⚠️ Opción inválida, intente nuevamente.");
            }

        } while (opcion != 0);

        sc.close();
    }

    // =====================================================
    // MÉTODOS DE MENÚ
    // =====================================================

    private static void registrarEnvio(GestorEnvios gestor, Scanner sc) {
        System.out.println("\n=== REGISTRAR NUEVO ENVÍO ===");
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        System.out.print("Peso (kg): ");
        double peso = sc.nextDouble();
        sc.nextLine(); // limpiar buffer
        System.out.print("Prioridad (ALTA, MEDIA, BAJA, EXPRESS): ");
        String prioridad = sc.nextLine().toUpperCase();
        System.out.print("Tipo de envío (TERRESTRE / MARITIMO): ");
        String tipo = sc.nextLine().toUpperCase();

        Envio envio;

        if (tipo.equals("TERRESTRE")) {
            System.out.print("Ingrese la distancia en km: ");
            double distancia = sc.nextDouble();
            sc.nextLine(); // limpiar buffer
            envio = new EnvioTerrestre(origen, destino, peso, prioridad, "NACIONAL", distancia);

        } else if (tipo.equals("MARITIMO")) {
            System.out.print("Ingrese el puerto de destino: ");
            String puertoDestino = sc.nextLine();
            envio = new EnvioMaritimo(origen, destino, peso, prioridad, "INTERNACIONAL", puertoDestino);

        } else {
            System.out.println("⚠️ Tipo no válido. Envío no registrado.");
            return;
        }

        gestor.agregarEnvio(envio);
        System.out.println("✅ Envío registrado con éxito. ID: " + envio.getId());
    }

    private static void buscarPorId(GestorEnvios gestor, Scanner sc) {
        System.out.print("\nIngrese el ID del envío: ");
        String id = sc.nextLine();
        Envio encontrado = gestor.buscarEnvioPorId(id);
        if (encontrado != null) {
            System.out.println("\n=== ENVÍO ENCONTRADO ===");
            System.out.println(encontrado);
        } else {
            System.out.println("⚠️ No se encontró ningún envío con ese ID.");
        }
    }

    private static void filtrarPorTipo(GestorEnvios gestor, Scanner sc) {
        System.out.print("\nIngrese el tipo de envío a filtrar (TERRESTRE / MARITIMO): ");
        String tipo = sc.nextLine().toUpperCase();
        var lista = gestor.filtrarPorTipo(tipo);
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay envíos de tipo " + tipo);
        } else {
            System.out.println("\n=== ENVÍOS DE TIPO " + tipo + " ===");
            lista.forEach(System.out::println);
        }
    }

    private static void filtrarPorEstado(GestorEnvios gestor, Scanner sc) {
        System.out.print("\nIngrese el estado a filtrar (PENDIENTE, EN TRÁNSITO, ENTREGADO, CANCELADO): ");
        String estado = sc.nextLine().toUpperCase();
        var lista = gestor.filtrarPorEstado(estado);
        if (lista.isEmpty()) {
            System.out.println("⚠️ No hay envíos con estado " + estado);
        } else {
            System.out.println("\n=== ENVÍOS EN ESTADO " + estado + " ===");
            lista.forEach(System.out::println);
        }
    }
}

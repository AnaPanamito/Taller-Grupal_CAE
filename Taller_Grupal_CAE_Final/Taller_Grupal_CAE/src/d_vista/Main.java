package d_vista;



import java.util.Scanner;
import c_logica.TicketServicio;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketServicio servicio = new TicketServicio();

        System.out.print("¿Cuántos tickets desea crear? ");
        int n = Integer.parseInt(sc.nextLine());
        servicio.crearTickets(n);

        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Atender siguiente ticket");
            System.out.println("2. Salir");
            System.out.print("Seleccione: ");
            opcion = Integer.parseInt(sc.nextLine());

            if (opcion == 1) servicio.atenderSiguiente();
        } while (opcion != 2);

        System.out.println("Programa finalizado");
    }
}

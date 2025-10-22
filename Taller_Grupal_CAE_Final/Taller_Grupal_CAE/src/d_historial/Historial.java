package d_historial;

import a_modelo.Ticket;
import java.util.ArrayList;
import java.util.List;

public class Historial {

    private List<Ticket> historialTickets = new ArrayList<>();

    // agregar un ticket al historial
    public void agregarHistorial(Ticket t) {
        historialTickets.add(t);
    }

    // mostrar todos los tickets finalizados
    public void mostrarHistorial() {
        if (historialTickets.isEmpty()) {
            System.out.println("(no hay tickets en el historial)");
        } else {
            for (Ticket t : historialTickets) {
                t.mostrar();
            }
        }
    }

    // verifica si hay tickets en el historial
    public boolean tieneHistorial() {
        return !historialTickets.isEmpty();
    }
}

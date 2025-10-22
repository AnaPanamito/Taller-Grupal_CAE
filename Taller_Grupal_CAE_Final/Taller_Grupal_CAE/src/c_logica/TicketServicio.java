package c_logica;

import b_estructuras.ColaTickets;
import b_estructuras.PilaAcciones;
import a_modelo.EstadoTicket;
import a_modelo.Ticket;
import d_historial.Historial;

import java.util.ArrayList;
import java.util.Scanner;

// clase ticket servicio que gestiona la creacion atencion y seguimiento de tickets
public class TicketServicio {

    private ColaTickets cola = new ColaTickets();
    private PilaAcciones undo = new PilaAcciones();
    private PilaAcciones redo = new PilaAcciones();
    private Ticket ticketActual = null;
    private Scanner sc = new Scanner(System.in);
    private Historial historial = new Historial();





    // metodo para crear n tickets solicitando el nombre del estudiante con validacion
    public void crearTickets(int n) {
        for (int i = 1; i <= n; i++) {
            String nombre;
            do {
                System.out.print("Ingrese nombre del estudiante para ticket " + i + ": ");
                nombre = sc.nextLine();
                if (!Validaciones.esNombreValido(nombre)) {
                    System.out.println("nombre invalido debe tener al menos 2 caracteres");
                }
            } while (!Validaciones.esNombreValido(nombre));
            cola.encolar(new Ticket(i, nombre));
        }
    }

    // metodo para atender el siguiente ticket en la cola
    public void atenderSiguiente() {
        if (cola.estaVacia()) {
            System.out.println("no hay tickets pendientes");
            // mostrar historial general
            if (historial.tieneHistorial()) {
                System.out.println("\n--- HISTORIAL GENERAL DE TICKETS FINALIZADOS ---");
                historial.mostrarHistorial();
            } else {
                System.out.println("(no hay tickets en el historial)");
            }
            System.out.println("fin del programa");
            System.exit(0); // termina automaticamente
            return;
        }

        ticketActual = cola.desencolar();
        ticketActual.estado = EstadoTicket.EN_ATENCION;
        System.out.println("\natendiendo ticket #" + ticketActual.id + " (" + ticketActual.estudiante + ")");
        menuTicket();
    }


    // metodo privado que muestra el menu de gestion de un ticket en atencion con validacion
    private void menuTicket() {
        int opcion;
        do {
            System.out.println("\n--- Gestion Ticket #" + ticketActual.id + " ---");
            System.out.println("1. Agregar nota");
            System.out.println("2. Eliminar nota");
            System.out.println("3. Mostrar notas");
            System.out.println("4. Deshacer ultima accion");
            System.out.println("5. Rehacer accion deshecha");
            System.out.println("6. Ver historial");
            System.out.println("7. Finalizar atencion");

            String entrada;
            do {
                System.out.print("Seleccione opcion: ");
                entrada = sc.nextLine();
                if (!Validaciones.esNumeroEntero(entrada) || !Validaciones.esOpcionValida(Integer.parseInt(entrada), 1, 7)) {
                    System.out.println("Opcion invalida. Ingrese un numero del 1 al 7.");
                    entrada = null;
                }
            } while (entrada == null);

            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1 -> agregarNota();
                case 2 -> eliminarNota();
                case 3 -> ticketActual.mostrar();
                case 4 -> deshacer();
                case 5 -> rehacer();
                case 6 -> verHistorial();  // 👈 muestra historial y regresa directo al menú
                case 7 -> finalizar();
            }
        } while (opcion != 7);  // 👈 sigue en bucle hasta finalizar
    }



    // metodo para agregar una nota al ticket actual con validacion
    private void agregarNota() {
        String texto;
        do {
            System.out.print("ingrese texto de la nota: ");
            texto = sc.nextLine();
            if (!Validaciones.esNotaValida(texto)) {
                System.out.println("texto invalido no puede estar vacio");
            }
        } while (!Validaciones.esNotaValida(texto));

        ticketActual.notas.agregarNota(texto);
        undo.push(new Accion("agregarNota", ticketActual, texto));
        redo = new PilaAcciones(); // limpiar pila de redo
        System.out.println("nota agregada correctamente");
    }

    // metodo para eliminar una nota del ticket actual con validacion
    private void eliminarNota() {
        String texto;
        do {
            System.out.print("ingrese texto exacto de la nota a eliminar: ");
            texto = sc.nextLine();
            if (!Validaciones.esNotaValida(texto)) {
                System.out.println("texto invalido no puede estar vacio");
                texto = null;
            }
        } while (texto == null);

        boolean eliminada = ticketActual.notas.eliminarNota(texto);
        if (eliminada) {
            undo.push(new Accion("eliminarNota", ticketActual, texto));
            redo = new PilaAcciones();
            System.out.println("nota eliminada correctamente");
        } else {
            System.out.println("no se encontro la nota");
        }
    }

    // metodo para deshacer la ultima accion realizada sobre el ticket actual
    private void deshacer() {
        if (undo.estaVacia()) {
            System.out.println("no hay acciones para deshacer");
            return;
        }

        Accion a = (Accion) undo.pop();
        switch (a.tipo) {
            case "agregarNota" -> {
                a.ticket.notas.eliminarNota(a.texto);
                redo.push(a);
                System.out.println("deshecho se elimino la nota \"" + a.texto + "\"");
            }
            case "eliminarNota" -> {
                a.ticket.notas.agregarNota(a.texto);
                redo.push(a);
                System.out.println("deshecho se restauro la nota \"" + a.texto + "\"");
            }
        }
    }

    // metodo para rehacer la ultima accion deshecha sobre el ticket actual
    private void rehacer() {
        if (redo.estaVacia()) {
            System.out.println("no hay acciones para rehacer");
            return;
        }

        Accion a = (Accion) redo.pop();
        switch (a.tipo) {
            case "agregarNota" -> {
                a.ticket.notas.agregarNota(a.texto);
                undo.push(a);
                System.out.println("rehecho se volvio a agregar la nota \"" + a.texto + "\"");
            }
            case "eliminarNota" -> {
                a.ticket.notas.eliminarNota(a.texto);
                undo.push(a);
                System.out.println("rehecho se volvio a eliminar la nota \"" + a.texto + "\"");
            }
        }
    }

    private void finalizar() {
        ticketActual.estado = EstadoTicket.COMPLETADO;
        historial.agregarHistorial(ticketActual);
        ticketActual = null;
    }


    private void verHistorial() {
        System.out.println("\n--- HISTORIAL DEL TICKET EN CURSO ---");
        if (ticketActual != null) {
            ticketActual.mostrar(); // muestra notas y estado del ticket actual
        } else {
            System.out.println("(no hay ticket en curso)");
        }
    }



}

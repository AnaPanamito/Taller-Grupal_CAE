package a_modelo;

import b_estructuras.ListaNotas;

// clase ticket que representa un ticket de atencion de un estudiante
public class Ticket {
    public int id; // identificador unico del ticket
    public String estudiante; // nombre del estudiante asociado al ticket
    public EstadoTicket estado; // estado actual del ticket que puede ser en cola en atencion o completado

    // lista de notas asociadas al ticket
    public ListaNotas notas = new ListaNotas();

    // constructor
    public Ticket(int id, String estudiante) {
        this.id = id;
        this.estudiante = estudiante;
        this.estado = EstadoTicket.EN_COLA;
    }

    // metodo para mostrar la informacion del ticket
    public void mostrar() {
        System.out.println("Ticket #" + id + " - " + estudiante + " [" + estado + "]");
        notas.listarNotas();
    }
}

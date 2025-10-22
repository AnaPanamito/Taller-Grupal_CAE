package c_logica;

import a_modelo.Ticket;

// clase accion que representa una accion realizada sobre un ticket como agregar o eliminar nota
public class Accion {


    String tipo; // tipo de accion puede ser agregarNota o eliminarNota
    Ticket ticket; // ticket sobre el cual se realiza la accion
    String texto; // texto de la nota involucrada en la accion

    // constructor
    public Accion(String tipo, Ticket ticket, String texto) {
        this.tipo = tipo;
        this.ticket = ticket;
        this.texto = texto;
    }
}

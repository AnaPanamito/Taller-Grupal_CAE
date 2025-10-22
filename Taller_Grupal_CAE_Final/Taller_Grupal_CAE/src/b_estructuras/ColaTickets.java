package b_estructuras;

import a_modelo.Nodo;
import a_modelo.Ticket;

// clase cola tickets que representa una cola de tickets para atencion
public class ColaTickets {


    private Nodo frente; // nodo que apunta al frente de la cola
    private Nodo fin; // nodo que apunta al final de la cola

    // metodo para agregar un ticket al final de la cola
    public void encolar(Ticket t) {
        Nodo nuevo = new Nodo(t);
        if (fin == null) frente = fin = nuevo;
        else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    // metodo para extraer y retornar el ticket del frente de la cola
    public Ticket desencolar() {
        if (frente == null) return null;
        Ticket t = (Ticket) frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return t;
    }

    // metodo que indica si la cola esta vacia
    public boolean estaVacia() {
        return frente == null;
    }
}

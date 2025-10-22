package b_estructuras;

import a_modelo.Nodo;

// clase pila acciones que representa una pila para almacenar acciones realizadas en los tickets
public class PilaAcciones {


    private Nodo cima;  // nodo que apunta al tope de la pila

    // metodo para agregar una accion al tope de la pila
    public void push(Object accion) {
        Nodo nuevo = new Nodo(accion);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    // metodo para extraer y retornar la accion del tope de la pila retorna null si esta vacia
    public Object pop() {
        if (cima == null) return null;
        Object dato = cima.dato;
        cima = cima.siguiente;
        return dato;
    }

    // metodo que indica si la pila esta vacia
    public boolean estaVacia() {
        return cima == null;
    }
}

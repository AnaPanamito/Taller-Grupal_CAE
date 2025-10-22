package a_modelo;

// clase nodo que representa un elemento de una estructura enlazada
public class Nodo {

    public Object dato; // dato que almacena cualquier objeto
    public Nodo siguiente; // referencia al siguiente nodo en la estructura

    // constructor
    public Nodo(Object dato) {
        this.dato = dato;
    }
}

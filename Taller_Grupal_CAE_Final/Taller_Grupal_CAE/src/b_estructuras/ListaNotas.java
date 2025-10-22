package b_estructuras;

import a_modelo.Nota;

// clase lista notas que representa una lista enlazada de notas de un ticket
public class ListaNotas {


    private Nota inicio; // referencia al inicio de la lista de notas

    // metodo para agregar una nota al inicio de la lista
    public void agregarNota(String texto) {
        Nota nueva = new Nota(texto);
        nueva.siguiente = inicio;
        inicio = nueva;
    }

    // metodo para eliminar una nota que coincida con el texto dado retorna true si se elimino
    public boolean eliminarNota(String texto) {
        Nota actual = inicio, anterior = null;
        while (actual != null) {
            if (actual.texto.equalsIgnoreCase(texto)) {
                if (anterior == null) inicio = actual.siguiente;
                else anterior.siguiente = actual.siguiente;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    // metodo para listar todas las notas en la consola
    public void listarNotas() {
        Nota actual = inicio;
        if (actual == null) {
            System.out.println("  (sin notas)");
            return;
        }
        while (actual != null) {
            System.out.println("  - " + actual.texto);
            actual = actual.siguiente;
        }
    }
}

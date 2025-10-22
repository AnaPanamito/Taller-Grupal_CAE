package a_modelo;

// clase nota que representa una nota de un ticket
public class Nota {


    public String texto;// texto que almacena el contenido de la nota
    public Nota siguiente; // referencia a la siguiente nota en la lista enlazada

    // constructor que recibe el texto de la nota y lo asigna al atributo
    public Nota(String texto) {
        this.texto = texto;
    }
}

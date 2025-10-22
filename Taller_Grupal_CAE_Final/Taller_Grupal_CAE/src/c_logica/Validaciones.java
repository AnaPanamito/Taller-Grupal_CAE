package c_logica;

// clase validaciones que contiene metodos utilitarios para validar datos del sistema
public class Validaciones {

    // metodo que verifica si un texto puede convertirse a un numero entero
    // validar que la opcion ingresada en el menu del ticket sea un numero
    public static boolean esNumeroEntero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // metodo que verifica si un texto es valido no nulo ni vacio
    //validar que el nombre del estudiante o la nota no sea vacio ni nulo
    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
    // metodo que valida que el id de un ticket sea mayor que cero
    // asegurar que el id del ticket sea valido al crearlo
    public static boolean esIdValido(int id) {
        return id > 0;
    }
    // metodo que valida el nombre del estudiante (no nulo, no vacio y al menos 2 caracteres)

    public static boolean esNombreValido(String nombre) {
        return esTextoValido(nombre) && nombre.trim().length() >= 2;
    }

    // metodo que valida que el texto de una nota tenga al menos 1 caracter
    public static boolean esNotaValida(String nota) {
        return esTextoValido(nota);
    }

    // metodo que valida que una opcion de menu este dentro de un rango permitido
    public static boolean esOpcionValida(int opcion, int min, int max) {
        return opcion >= min && opcion <= max;
    }
}

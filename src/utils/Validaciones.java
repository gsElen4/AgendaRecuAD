package utils;
import java.util.regex.Pattern; //sirve para trabajar con expresiones regulares

public class Validaciones {


    public static boolean validarNombre(String nombre) {
        return nombre != null && nombre.matches("[A-Za-záéíóúÁÉÍÓÚ ]{2,}");
    }

    public static boolean validarEmail(String email) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email);
    }

    public static boolean validarISBN(String isbn) {
        return isbn != null && isbn.matches("[0-9]{10}|[0-9]{13}");
    }

    public static boolean validarTitulo(String titulo) {
        return titulo != null && titulo.length() >= 2;
    }
}

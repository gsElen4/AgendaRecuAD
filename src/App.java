
import DAO.AutorDAO;
import java.util.Scanner;

public class App {

public static void MenuPrincipal(){
    System.out.println("\n--- Menú ---");
        System.out.println("1. Menú Autores");
        System.out.println("2. Menú Libros");
        System.out.println("3. Menú Editoriales");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
}

private static final Scanner sc = new Scanner(System.in);
    private static final AutorDAO autorDAO = new AutorDAO();


    //Autores
public static void MenuAutores(){
    System.out.println("\n--- Menú de Autores ---");
        System.out.println("1. Crear autor");
        System.out.println("2. Consultar autor (ID)");
        System.out.println("3. Listar autores (por ID)");
        System.out.println("4. Modificar autor");
        System.out.println("5. Eliminar autor");
        System.out.println("7. Salir al Menú Principal"); 
        System.out.print("Elige una opción: ");
}
public static void main(String[]args){
        int opcion = 0;
        while(opcion !=6){
            MenuAutores();

            try {
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1 -> CrearAutor();
                    case 2 -> ConsultarAutor(); 
                    case 3 -> ListarAutores(); 
                    case 4 -> ModificarAutor(); 
                    case 5 -> EliminarAutor(); 
                    case 6 -> System.out.println("Saliendo del menú de autores..."); 
                    default -> System.out.println("Opción no válida. Por favor, elige un número del 1 al 7.");
                }
            }catch (Exception e) {
            }
        }
}

private static void CrearAutor(){

}

private static void ConsultarAutor(){

}

private static void ListarAutores(){

}

private static void ModificarAutor(){

}

private static void EliminarAutor(){

}

}

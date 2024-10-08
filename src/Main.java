/**
 * Clase principal del programa
 */
public class Main {
    /**
     * Método principal de la aplicación
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Crear un objeto de la clase Menu
        Menu menu = new Menu();

        // Crear un objeto de la clase View
        View view = new View();

        // Crear un objeto de la clase Controller
        Controller controller = new Controller(menu, view);

        // Mostrar la vista
        view.setVisible(true);
    }
}
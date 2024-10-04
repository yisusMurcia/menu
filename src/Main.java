public class Main {
    public static void main(String[] args) {
        // Crear un objeto de la clase Menu
        Menu menu = new Menu();

        // Crear un objeto de la clase MenuView
        View view = new View();

        // Crear un objeto de la clase MenuController
        Controller controller = new Controller(menu, view);

        // Mostrar la vista
        view.setVisible(true);
    }
}
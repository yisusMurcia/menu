import java.util.ArrayList;

/**
 * Clase que representa el controlador de la aplicación.
 *
 * @author Jesús Antonio Murcia
 */
public class Controller {
    /**
     * Menú de la aplicación.
     */
    private Menu menu;

    /**
     * Vista de la aplicación.
     */
    private View view;

    /**
     * Constructor que inicializa el controlador con un menú y una vista.
     *
     * @param menu Menú de la aplicación.
     * @param view Vista de la aplicación.
     */
    public Controller(Menu menu, View view) {
        this.menu = menu;
        this.view = view;
    }

    /**
     * Agrega un plato al menú.
     *
     * @param plato Plato a agregar.
     */
    public void agregarPlato(Plato plato) {
        if (menu.agregarPlato(plato)) {
            view.mostrarMensaje("Plato agregado con éxito");
            view.mostrarMenu(menu.mostrarMenu());
        } else {
            view.mostrarMensaje("El plato ya existe en el menú");
        }
    }

    /**
     * Elimina un plato del menú.
     *
     * @param plato Plato a eliminar.
     */
    public void eliminarPlato(Plato plato) {
        if (menu.eliminarPlato(plato)) {
            view.mostrarMensaje("Plato eliminado con éxito");
            view.mostrarMenu(menu.mostrarMenu());
        } else {
            view.mostrarMensaje("El plato no existe en el menú");
        }
    }

    /**
     * Modifica un plato del menú.
     *
     * @param platoAntiguo Plato a modificar.
     * @param platoNuevo   Nuevo plato.
     */
    public void modificarPlato(Plato platoAntiguo, Plato platoNuevo) {
        menu.modificarPlato(platoAntiguo, platoNuevo);
        view.mostrarMensaje("Plato modificado con éxito");
        view.mostrarMenu(menu.mostrarMenu());
    }

    /**
     * Muestra el menú de la aplicación.
     */
    public void mostrarMenu() {
        ArrayList<Plato> platos = menu.mostrarMenu();
        view.mostrarMenu(platos);
    }
}
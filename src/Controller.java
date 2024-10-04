import java.util.ArrayList;

public class Controller {
    private Menu menu;
    private View view;

    public Controller(Menu menu, View view) {
        this.menu = menu;
        this.view = view;
    }

    public void agregarPlato(Plato plato) {
        if (menu.agregarPlato(plato)) {
            view.mostrarMensaje("Plato agregado con éxito");
            view.mostrarMenu(menu.mostrarMenu());
        } else {
            view.mostrarMensaje("El plato ya existe en el menú");
        }
    }

    public void eliminarPlato(Plato plato) {
        if (menu.eliminarPlato(plato)) {
            view.mostrarMensaje("Plato eliminado con éxito");
            view.mostrarMenu(menu.mostrarMenu());
        } else {
            view.mostrarMensaje("El plato no existe en el menú");
        }
    }

    public void modificarPlato(Plato platoAntiguo, Plato platoNuevo) {
        menu.modificarPlato(platoAntiguo, platoNuevo);
        view.mostrarMensaje("Plato modificado con éxito");
        view.mostrarMenu(menu.mostrarMenu());
    }

    public void mostrarMenu() {
        ArrayList<Plato> platos = menu.mostrarMenu();
        view.mostrarMenu(platos);
    }
}
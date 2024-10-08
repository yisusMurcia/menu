import java.util.ArrayList;

/**
 * Clase que representa un menú de platos.
 *
 * El menú se compone de una lista de platos y proporciona métodos para agregar, eliminar,
 * modificar y buscar platos.
 *
 * @author [Jesús Antonio Murcia]
 */
public class Menu {

    /**
     * Lista de platos que componen el menú.
     */
    private ArrayList<Plato> platos;

    /**
     * Número de menús creados.
     */
    private static int menuNum = 0;

    /**
     * Constructor que inicializa el menú con una lista vacía de platos.
     */
    public Menu() {
        platos = new ArrayList<>();
        menuNum++;
    }

    /**
     * Agrega un plato al menú.
     *
     * Si el plato ya existe en el menú, no se agrega y se devuelve false.
     *
     * @param plato el plato a agregar
     * @return true si el plato se agregó con éxito, false si ya existe en el menú
     */
    public boolean agregarPlato(Plato plato) {
        for (Plato platoEnMenu : platos) {
            if (platoEnMenu.getName().equalsIgnoreCase(plato.getName())) {
                return false;
            }
        }
        platos.add(plato);
        return true; // El plato se agregó con éxito
    }

    /**
     * Elimina un plato del menú.
     *
     * Si el plato no existe en el menú, se devuelve false.
     *
     * @param plato el plato a eliminar
     * @return true si el plato se eliminó con éxito, false si no existe en el menú
     */
    public boolean eliminarPlato(Plato plato) {
        return platos.remove(plato);
    }

    /**
     * Modifica un plato existente en el menú.
     *
     * Si el plato no existe en el menú, no se modifica.
     *
     * @param platoAntiguo el plato a modificar
     * @param platoNuevo el plato modificado
     */
    public void modificarPlato(Plato platoAntiguo, Plato platoNuevo) {
        int indice = platos.indexOf(platoAntiguo);
        if (indice != -1) {
            platos.set(indice, platoNuevo);
        }
    }

    /**
     * Busca platos por categoría.
     *
     * Se devuelve una lista de platos que coinciden con la categoría.
     *
     * @param categoria la categoría a buscar
     * @return una lista de platos que coinciden con la categoría
     */
    public ArrayList<Plato> buscarPlatosPorCategoria(String categoria) {
        ArrayList<Plato> resultados = new ArrayList<>();
        for (Plato plato : platos) {
            for (String category : plato.getCategory()) {
                if (category.equalsIgnoreCase(categoria)) {
                    resultados.add(plato);
                    break;
                }
            }
        }
        return resultados;
    }

    /**
     * Muestra el menú completo.
     *
     * Se devuelve una lista de platos disponibles.
     *
     * @return una lista de platos disponibles
     */
    public ArrayList<Plato> mostrarMenu() {
        return platos;
    }

    /**
     * Obtiene el número de menús creados.
     *
     * @return el número de menús creados
     */
    public static int getMenuNum() {
        return menuNum;
    }
}
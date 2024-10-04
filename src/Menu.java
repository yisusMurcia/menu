import java.util.ArrayList;

public class Menu {
    private ArrayList<Plato> platos;

    public Menu() {
        platos = new ArrayList<>();
    }

    /**
     * Agrega un plato al menú
     * @param plato el plato a agregar
     * @return true si el plato se agregó con éxito, false si ya existe en el menú
     */
    public boolean agregarPlato(Plato plato) {
        if (platos.contains(plato)) {
            return false; // El plato ya existe en el menú
        }
        platos.add(plato);
        return true; // El plato se agregó con éxito
    }

    /**
     * Elimina un plato del menú
     * @param plato el plato a eliminar
     * @return true si el plato se eliminó con éxito, false si no existe en el menú
     */
    public boolean eliminarPlato(Plato plato) {
        return platos.remove(plato);
    }

    /**
     * Modifica un plato existente en el menú
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
     * Busca platos por categoría
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
     * Muestra el menú completo
     * @return una lista de platos disponibles
     */
    public ArrayList<Plato> mostrarMenu() {
        return platos;
    }
}
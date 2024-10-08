import java.util.ArrayList;

/**
 * Clase que representa un plato de comida.
 *
 * @author Jesús Antonio Murcia
 */
public class Plato {
    /**
     * Nombre del plato.
     */
    private String name;

    /**
     * Descripción del plato.
     */
    private String description;

    /**
     * Categorías a las que pertenece el plato.
     */
    private ArrayList<String> category = new ArrayList<>();

    /**
     * Precio del plato.
     */
    private int price;

    /**
     * Constructor que inicializa un plato con nombre, descripción, categoría y precio.
     *
     * @param name        Nombre del plato.
     * @param description Descripción del plato.
     * @param category    Categoría a la que pertenece el plato.
     * @param precio      Precio del plato.
     */
    public Plato(String name, String description, String category, int precio) {
        this.name = name;
        this.description = description;
        this.price = precio;
        this.category.add(category);
    }

    /**
     * Constructor que inicializa un plato con nombre, precio y categoría. La descripción se establece en "No description added".
     *
     * @param name     Nombre del plato.
     * @param precio   Precio del plato.
     * @param category Categoría a la que pertenece el plato.
     */
    public Plato(String name, int precio, String category) {
        this.name = name;
        this.description = "No description added";
        this.price = precio;
        this.category.add(category);
    }

    /**
     * Obtiene el nombre del plato.
     *
     * @return Nombre del plato.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del plato.
     *
     * @param name Nuevo nombre del plato.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la descripción del plato.
     *
     * @return Descripción del plato.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del plato.
     *
     * @param description Nueva descripción del plato.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene las categorías a las que pertenece el plato.
     *
     * @return Categorías del plato.
     */
    public ArrayList<String> getCategory() {
        return category;
    }

    /**
     * Agrega una categoría al plato. Si la categoría ya existe, no se agrega y se devuelve false.
     *
     * @param category Categoría a agregar.
     * @return true si la categoría se agregó con éxito, false en caso contrario.
     */
    public boolean addCategory(String category) {
        for (String foodCategory : this.category) {
            if (category.equalsIgnoreCase(foodCategory)) {
                return false;
            }
        }
        this.category.add(category);
        return true;
    }

    /**
     * Elimina una categoría del plato.
     *
     * @param category Categoría a eliminar.
     * @return true si la categoría se eliminó con éxito, false en caso contrario.
     */
    public boolean deleteCategory(String category) {
        return this.category.remove(category);
    }

    /**
     * Obtiene el precio del plato.
     *
     * @return Precio del plato.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Establece el precio del plato.
     *
     * @param price Nuevo precio del plato.
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
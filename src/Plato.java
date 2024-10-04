import java.util.ArrayList;

public class Plato {
    private String name;
    private String description;
    private ArrayList<String> category = new ArrayList<>();
    private int price;


    public Plato(String name, String description, String category, int precio){
        this.name= name;
        this.description = description;
        this.price = precio;
        this.category.add(category);
    }
    public Plato(String name, int precio, String category){
        this.name= name;
        this.description = "No description added";
        this.price = precio;
        this.category.add(category);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public boolean addCategory(String category) {//Return a boolean indicating the success of the operation
        for(String foodCategory: this.category){
            if(category.equalsIgnoreCase(foodCategory)){
                return false;
            }
        }
        this.category.add(category);
        return true;
    }

    public boolean deleteCategory(String category){
        return this.category.remove(category);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
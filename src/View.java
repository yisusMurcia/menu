import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Clase que representa la vista de la aplicación.
 *
 * @author Jesús Antonio Murcia
 */
public class View extends JFrame {
    /**
     * Tabla que muestra los platos del menú.
     */
    private JTable table;

    /**
     * Modelo de la tabla.
     */
    private DefaultTableModel tableModel;

    /**
     * Botón para agregar un plato al menú.
     */
    private JButton addButton;

    /**
     * Botón para eliminar un plato del menú.
     */
    private JButton deleteButton;

    /**
     * Botón para modificar un plato del menú.
     */
    private JButton modifyButton;

    /**
     * Botón para mostrar el menú predeterminado.
     */
    private JButton showMenuButton;

    /**
     * Campo de texto para ingresar el nombre del plato.
     */
    private JTextField nameField;

    /**
     * Campo de texto para ingresar la descripción del plato.
     */
    private JTextArea descriptionField;

    /**
     * Campo de texto para ingresar la categoría del plato.
     */
    private JTextField categoryField;

    /**
     * Campo de texto para ingresar el precio del plato.
     */
    private JTextField priceField;

    /**
     * Controlador de la aplicación.
     */
    private Controller controller;

    /**
     * Botón para crear otro menú.
     */
    private JButton createAnotherMenuButton;

    /**
     * Constructor que inicializa la vista.
     */
    public View() {
        // Configura la ventana principal
        setTitle("Restaurant Menu Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crea un panel para la tabla
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        // Crea un modelo de tabla y una tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Descripción");
        tableModel.addColumn("Categoría");
        tableModel.addColumn("Precio");
        table = new JTable(tableModel);

        // Agrega la tabla al panel
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Crea un panel para los campos de texto
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        // Crea los campos de texto
        nameField = new JTextField();
        descriptionField = new JTextArea(5, 20);
        categoryField = new JTextField();
        priceField = new JTextField();

        // Agrega los campos de texto al panel
        inputPanel.add(new JLabel("Nombre:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Descripción:"));
        inputPanel.add(new JScrollPane(descriptionField));
        inputPanel.add(new JLabel("Categoría:"));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Precio:"));
        inputPanel.add(priceField);

        // Crea un panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Crea los botones
        addButton = new JButton("Agregar");
        deleteButton = new JButton("Eliminar");
        modifyButton = new JButton("Modificar");
        showMenuButton = new JButton("Generar Menú predeterminado");
        createAnotherMenuButton = new JButton("Crear otro menú");

        // Agrega los botones al panel
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(showMenuButton);
        buttonPanel.add(createAnotherMenuButton);

        // Agrega los paneles a la ventana principal
        add(tablePanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Crea un objeto Controlador
        Menu menu = new Menu();
        controller = new Controller(menu, this);

        // Agrega un oyente de eventos al botón "Crear otro menú"
        createAnotherMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crea una nueva ventana
                View anotherView = new View();
                anotherView.setVisible(true);
            }
        });


// Implementa los oyentes de eventos para los botones
addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Verifica si el campo de texto del precio es válido
            if (checkPriceField()) {
                // Obtiene los valores de los campos de texto
                String name = nameField.getText();
                String description = descriptionField.getText();
                String category = categoryField.getText();
                int price = Integer.parseInt(priceField.getText());
                // Crea un objeto Plato con los valores obtenidos
                Plato plato = new Plato(name, description, category, price);
                // Agrega el plato al menú mediante el controlador
                controller.agregarPlato(plato);
            }
        }
    });

deleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtiene la fila seleccionada en la tabla
            int row = table.getSelectedRow();
            if (row != -1) {
                // Crea un objeto Plato con los valores de la fila seleccionada
                Plato plato = new Plato((String) tableModel.getValueAt(row, 0), (String) tableModel.getValueAt(row, 1), (String) tableModel.getValueAt(row, 2), Integer.parseInt((String) tableModel.getValueAt(row, 3)));
                // Elimina el plato del menú mediante el controlador
                controller.eliminarPlato(plato);
                // Elimina la fila de la tabla
                tableModel.removeRow(row);
            }
        }
    });

modifyButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtiene la fila seleccionada en la tabla
            int row = table.getSelectedRow();
            if (row != -1) {
                // Obtiene los valores de los campos de texto
                String name = nameField.getText();
                String description = descriptionField.getText();
                String category = categoryField.getText();
                int price = Integer.parseInt(priceField.getText());
                // Crea un objeto Plato con los valores obtenidos
                Plato platoAntiguo = new Plato((String) tableModel.getValueAt(row, 0), (String) tableModel.getValueAt(row, 1), (String) tableModel.getValueAt(row, 2), Integer.parseInt((String) tableModel.getValueAt(row, 3)));
                Plato platoNuevo = new Plato(name, description, category, price);
                // Modifica el plato en el menú mediante el controlador
                controller.modificarPlato(platoAntiguo, platoNuevo);
                // Actualiza la tabla con el plato modificado
                tableModel.setValueAt(platoNuevo.getName(), row, 0);
                tableModel.setValueAt(platoNuevo.getDescription(), row, 1);
                tableModel.setValueAt(platoNuevo.getCategory().get(0), row, 2);
                tableModel.setValueAt(String.valueOf(platoNuevo.getPrice()), row, 3);
            }
        }
    });

        showMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Plato> platos = new ArrayList<>();
                int menuNum = Menu.getMenuNum();
                switch (menuNum) {
                    case 2:
                        // Agregar platos de comida china al menú
                        platos.add(new Plato("Wonton", "Wonton frito con salsa de soja", "Comida china", 8));
                        platos.add(new Plato("Dumpling", "Dumpling al vapor con salsa de soja", "Comida china", 9));
                        platos.add(new Plato("Kung Pao", "Pollo con verduras y salsa de soja", "Comida china", 12));
                        platos.add(new Plato("Lo Mein", "Fideos chinos con verduras y salsa de soja", "Comida china", 10));
                        platos.add(new Plato("Egg Foo Young", "Tortilla de huevo con verduras y salsa de soja", "Comida china", 11));
                        break;
                    case 3:
                        // Agregar platos de comida italiana al menú
                        platos.add(new Plato("Spaghetti Bolognese", "Spaghetti con carne de ternera y salsa de tomate", "Comida italiana", 12));
                        platos.add(new Plato("Pizza Margherita", "Pizza con tomate, mozzarella y albahaca", "Comida italiana", 10));
                        platos.add(new Plato("Risotto alla Milanese", "Risotto con azafrán y ossobuco", "Comida italiana", 15));
                        platos.add(new Plato("Pollo alla Cacciatora", "Pollo con champiñones y salsa de tomate", "Comida italiana", 14));
                        platos.add(new Plato("Tiramisú", "Postre de café y mascarpone", "Comida italiana", 8));
                        break;
                    case 4:
                        // Agregar platos de comida mexicana al menú
                        platos.add(new Plato("Tacos al pastor", "Tacos con carne de cerdo y salsa de aguacate", "Comida mexicana", 11));
                        platos.add(new Plato("Enchiladas Rojas", "Tortillas con carne de pollo y salsa de tomate", "Comida mexicana", 12));
                        platos.add(new Plato("Quesadillas", "Tortillas con queso y salsa de aguacate", "Comida mexicana", 10));
                        platos.add(new Plato("Chiles Rellenos", "Chiles poblano con queso y salsa de tomate", "Comida mexicana", 14));
                        platos.add(new Plato("Churros con Cajeta", "Churros con cajeta y canela", "Comida mexicana", 9));
                        break;
                    case 5:
                        // Agregar platos de comida colombiana al menú
                        platos.add(new Plato("Bandeja Paisa", "Plato típico con carne, chorizo, frijoles, arroz y arepa", "Comida colombiana", 18));
                        platos.add(new Plato("Ajiaco", "Sopa de pollo con papas, maíz y crema", "Comida colombiana", 12));
                        platos.add(new Plato("Empanadas", "Empanadas de carne o queso con salsa de aji", "Comida colombiana", 10));
                        platos.add(new Plato("Lechona", "Cerdo asado con arroz, frijoles y arepa", "Comida colombiana", 16));
                        platos.add(new Plato("Tamales", "Tamales de maíz con carne o queso y salsa de aji", "Comida colombiana", 14));
                        break;
                    default:
                        //Comida rápida
                        platos.add(new Plato("Hamburguesa", "Hamburguesa de carne con queso y verduras", "Comida rápida", 10));
                        platos.add(new Plato("Pizza", "Pizza de queso y pepperoni", "Comida rápida", 12));
                        platos.add(new Plato("Ensalada", "Ensalada de lechuga, tomate y zanahoria", "Ensaladas", 8));
                        platos.add(new Plato("Tacos", "Tacos de carne con salsa y guacamole", "Comida rápida", 11));
                        platos.add(new Plato("Sopa", "Sopa de verduras con pan", "Sopas", 9));
                }
                // Guarda los platos en el menú
                for (Plato plato : platos) {
                    controller.agregarPlato(plato);
                }
            }
        });
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarMenu(ArrayList<Plato> platos) {
        tableModel.setRowCount(0);
        for (Plato plato : platos) {
            tableModel.addRow(new Object[]{plato.getName(), plato.getDescription(), plato.getCategory().get(0), String.valueOf(plato.getPrice())});
        }
    }


    //Validar que se ingrese un precio en el campo
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    //Verificar que el precio sea numérico
    private boolean checkPriceField() {
        String price = priceField.getText();
        if (!isNumeric(price)) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un valor numérico");
            priceField.setText("");
        }

        return isNumeric(price);
    }
}
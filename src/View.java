import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// View.java
public class View extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton showMenuButton;
    private JTextField nameField;
    private JTextArea descriptionField;
    private JTextField categoryField;
    private JTextField priceField;
    private Controller controller;

    public View() {
        // Set up the frame
        setTitle("Restaurant Menu Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel for the table
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        // Create a table model and table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Descripción");
        tableModel.addColumn("Categoría");
        tableModel.addColumn("Precio");
        table = new JTable(tableModel);

        // Add the table to the panel
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Create a panel for the input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        // Create input fields
        nameField = new JTextField();
        descriptionField = new JTextArea(5, 20);
        categoryField = new JTextField();
        priceField = new JTextField();

        // Add input fields to the panel
        inputPanel.add(new JLabel("Nombre:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Descripción:"));
        inputPanel.add(new JScrollPane(descriptionField));
        inputPanel.add(new JLabel("Categoría:"));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Precio:"));
        inputPanel.add(priceField);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create buttons
        addButton = new JButton("Agregar");
        deleteButton = new JButton("Eliminar");
        modifyButton = new JButton("Modificar");
        showMenuButton = new JButton("Generar Menú predeterminado");

        // Add buttons to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(showMenuButton);

        // Add panels to the frame
        add(tablePanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Create a Controller object
        Menu menu = new Menu();
        controller = new Controller(menu, this);

        // Implement event listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkPriceField()) {
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    String category = categoryField.getText();
                    int price = Integer.parseInt(priceField.getText());
                    Plato plato = new Plato(name, description, category, price);
                    controller.agregarPlato(plato);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Plato plato = new Plato((String) tableModel.getValueAt(row, 0), (String) tableModel.getValueAt(row, 1), (String) tableModel.getValueAt(row, 2),  Integer.parseInt((String)tableModel.getValueAt(row, 3)));
                    controller.eliminarPlato(plato);
                    tableModel.removeRow(row);
                }
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    String category = categoryField.getText();
                    int price = Integer.parseInt(priceField.getText());
                    Plato platoAntiguo = new Plato((String) tableModel.getValueAt(row, 0), (String) tableModel.getValueAt(row, 1), (String) tableModel.getValueAt(row, 2), Integer.parseInt((String) tableModel.getValueAt(row, 3)));
                    Plato platoNuevo = new Plato(name, description, category, price);
                    controller.modificarPlato(platoAntiguo, platoNuevo);
                    // Actualiza la tabla con el plato modificado
                    tableModel.setValueAt(platoNuevo.getName(), row, 0);
                    tableModel.setValueAt(platoNuevo.getDescription(), row, 1);
                    tableModel.setValueAt(platoNuevo.getCategory().getFirst(), row, 2);
                    tableModel.setValueAt(String.valueOf(platoNuevo.getPrice()), row, 3);
                }
            }
        });

        showMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Plato> platos = new ArrayList<>();
                platos.add(new Plato("Hamburguesa", "Hamburguesa de carne con queso y verduras", "Comida rápida", 10));
                platos.add(new Plato("Pizza", "Pizza de queso y pepperoni", "Comida rápida", 12));
                platos.add(new Plato("Ensalada", "Ensalada de lechuga, tomate y zanahoria", "Ensaladas", 8));
                platos.add(new Plato("Tacos", "Tacos de carne con salsa y guacamole", "Comida rápida", 11));
                platos.add(new Plato("Sopa", "Sopa de verduras con pan", "Sopas", 9));
                mostrarMenu(platos);
            }
        });
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarMenu(ArrayList<Plato> platos) {
        tableModel.setRowCount(0);
        for (Plato plato : platos) {
            tableModel.addRow(new Object[]{plato.getName(), plato.getDescription(), plato.getCategory().getFirst(), String.valueOf(plato.getPrice())});
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

    private boolean checkPriceField() {
        String price = priceField.getText();
        if (!isNumeric(price)) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un valor numérico");
            priceField.setText("");
        }

        return isNumeric(price);
    }

}
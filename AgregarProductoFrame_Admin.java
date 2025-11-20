import javax.swing.*;
import java.awt.*;

public class AgregarProductoFrame_Admin extends JFrame {

    private Sistema sistema;

    public AgregarProductoFrame_Admin(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Agregar Producto");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtNombre = new JTextField();
        JTextField txtPrecio = new JTextField();
        JTextField txtStock = new JTextField();

        JButton btnAgregar = new JButton("Agregar");

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Precio:"));
        panel.add(txtPrecio);
        panel.add(new JLabel("Stock inicial:"));
        panel.add(txtStock);
        panel.add(btnAgregar);

        btnAgregar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                int stock = Integer.parseInt(txtStock.getText().trim());

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nombre inválido");
                    return;
                }

                sistema.añadirProducto(new Producto(nombre, precio, stock));
                JOptionPane.showMessageDialog(this, "Producto agregado");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos");
            }
        });

        add(panel);
    }
}

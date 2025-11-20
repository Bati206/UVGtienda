import javax.swing.*;
import java.awt.*;

public class AgregarProductoFrame extends JFrame {

    private Sistema sistema;

    public AgregarProductoFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Agregar al Carrito");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtNombre = new JTextField();
        JTextField txtCantidad = new JTextField();

        JButton btnAgregar = new JButton("Agregar");
        JButton btnCancelar = new JButton("Cancelar");

        panel.add(new JLabel("Nombre exacto del producto o kit:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Cantidad:"));
        panel.add(txtCantidad);
        panel.add(btnAgregar);
        panel.add(btnCancelar);

        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            int cantidad;

            try {
                cantidad = Integer.parseInt(txtCantidad.getText().trim());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida");
                return;
            }

            if (sistema.agregarAlCarrito(nombre, cantidad)) {
                JOptionPane.showMessageDialog(this, "Agregado al carrito");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar. Verifique nombre y stock.");
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        add(panel);
    }
}

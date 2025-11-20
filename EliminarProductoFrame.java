import javax.swing.*;
import java.awt.*;

public class EliminarProductoFrame extends JFrame {

    private Sistema sistema;

    public EliminarProductoFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Eliminar Producto");
        setSize(350, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtNombre = new JTextField();
        JButton btnEliminar = new JButton("Eliminar");

        panel.add(txtNombre);
        panel.add(btnEliminar);

        btnEliminar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            if (sistema.quitarProducto(nombre)) {
                JOptionPane.showMessageDialog(this, "Producto eliminado");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado");
            }
        });

        add(panel);
    }
}

import javax.swing.*;
import java.awt.*;

public class EliminarProductoDeKitFrame extends JFrame {

    private Sistema sistema;

    public EliminarProductoDeKitFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Eliminar Producto de Kit");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtKit = new JTextField();
        JTextField txtProd = new JTextField();
        JButton btnEliminar = new JButton("Eliminar");

        panel.add(new JLabel("Nombre del Kit:"));
        panel.add(txtKit);

        panel.add(new JLabel("Producto en el kit:"));
        panel.add(txtProd);

        panel.add(btnEliminar);

        btnEliminar.addActionListener(e -> {
            Kit k = sistema.buscarKitPorNombre(txtKit.getText().trim());
            Producto p = sistema.buscarProductoPorNombre(txtProd.getText().trim());

            if (k == null || p == null) {
                JOptionPane.showMessageDialog(this, "Nombre incorrecto");
                return;
            }

            k.eliminarProducto(p);

            JOptionPane.showMessageDialog(this, "Producto eliminado del kit");
            dispose();
        });

        add(panel);
    }
}

import javax.swing.*;
import java.awt.*;

public class AgregarProductoAKitFrame extends JFrame {

    private Sistema sistema;

    public AgregarProductoAKitFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Agregar Producto a Kit");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtKit = new JTextField();
        JTextField txtProd = new JTextField();
        JButton btnAgregar = new JButton("Agregar");

        panel.add(new JLabel("Nombre del Kit:"));
        panel.add(txtKit);

        panel.add(new JLabel("Producto a añadir:"));
        panel.add(txtProd);

        panel.add(btnAgregar);

        btnAgregar.addActionListener(e -> {
            Kit k = sistema.buscarKitPorNombre(txtKit.getText().trim());
            Producto p = sistema.buscarProductoPorNombre(txtProd.getText().trim());

            if (k == null || p == null) {
                JOptionPane.showMessageDialog(this, "Nombre incorrecto");
                return;
            }

            k.agregarProducto(p);

            JOptionPane.showMessageDialog(this, "Producto agregado al kit");
            dispose();
        });

        add(panel);
    }
}

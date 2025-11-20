import javax.swing.*;
import java.awt.*;

public class CrearKitFrame extends JFrame {

    private Sistema sistema;

    public CrearKitFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Crear Kit");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtNombre = new JTextField();
        JTextField txtDescripcion = new JTextField();
        JTextField txtPrecio = new JTextField();

        JButton btnCrear = new JButton("Crear");

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Descripción:"));
        panel.add(txtDescripcion);
        panel.add(new JLabel("Precio Base:"));
        panel.add(txtPrecio);
        panel.add(btnCrear);

        btnCrear.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String desc = txtDescripcion.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());

            sistema.añadirKit(new Kit(nombre, precio, desc));

            JOptionPane.showMessageDialog(this, "Kit creado");
            dispose();
        });

        add(panel);
    }
}

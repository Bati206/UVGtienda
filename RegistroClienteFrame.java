import javax.swing.*;
import java.awt.*;

public class RegistroClienteFrame extends JFrame {

    private Sistema sistema;

    public RegistroClienteFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Registro de Cliente");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtID = new JTextField();
        JTextField txtNombre = new JTextField();

        String[] tipos = { "General", "Estudiante" };
        JComboBox<String> comboTipo = new JComboBox<>(tipos);

        JTextField txtEstudianteInfo = new JTextField();
        JTextField txtTarjeta = new JTextField();

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnCancelar = new JButton("Cancelar");

        panel.add(new JLabel("ID:"));
        panel.add(txtID);

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);

        panel.add(new JLabel("Tipo:"));
        panel.add(comboTipo);

        panel.add(new JLabel("Info estudiante:"));
        panel.add(txtEstudianteInfo);

        panel.add(new JLabel("Tarjeta crédito:"));
        panel.add(txtTarjeta);

        panel.add(btnRegistrar);
        panel.add(btnCancelar);

        btnRegistrar.addActionListener(e -> {
            String id = txtID.getText().trim();
            String nombre = txtNombre.getText().trim();
            String tipo = comboTipo.getSelectedItem().toString();
            String info = txtEstudianteInfo.getText().trim();
            String tarjeta = txtTarjeta.getText().trim();

            if (id.isEmpty() || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID y Nombre son obligatorios");
                return;
            }

            if (tarjeta.isEmpty())
                tarjeta = null;

            boolean ok = sistema.registrarCliente(id, nombre, tipo, info, tarjeta);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Cliente registrado");
                new HomeFrame(sistema).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "ID ya registrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> {
            new HomeFrame(sistema).setVisible(true);
            dispose();
        });

        add(panel);
    }
}

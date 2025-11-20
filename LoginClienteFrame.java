import javax.swing.*;
import java.awt.*;

public class LoginClienteFrame extends JFrame {

    private Sistema sistema;

    public LoginClienteFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Login Cliente");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lbl = new JLabel("Ingrese su ID de cliente:", SwingConstants.CENTER);
        JTextField txtID = new JTextField();
        JButton btnLogin = new JButton("Ingresar");

        btnLogin.addActionListener(e -> {
            String id = txtID.getText().trim();

            Cliente cliente = sistema.login(id);

            if (cliente != null) {
                JOptionPane.showMessageDialog(this,
                        "Bienvenido " + cliente.getNombre(),
                        "Login exitoso", JOptionPane.INFORMATION_MESSAGE);

                new ClienteMenuFrame(sistema).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "ID incorrecto",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(lbl);
        panel.add(txtID);
        panel.add(btnLogin);

        add(panel);
    }
}

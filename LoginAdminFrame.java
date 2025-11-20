import javax.swing.*;
import java.awt.*;

public class LoginAdminFrame extends JFrame {

    private Sistema sistema;

    public LoginAdminFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Login Administrador");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtUser = new JTextField();
        JPasswordField txtPass = new JPasswordField();

        JButton btnLogin = new JButton("Ingresar");
        JButton btnCancelar = new JButton("Cancelar");

        panel.add(new JLabel("Usuario:"));
        panel.add(txtUser);

        panel.add(new JLabel("Contraseña:"));
        panel.add(txtPass);

        panel.add(btnLogin);
        panel.add(btnCancelar);

        btnLogin.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword()).trim();

            if (sistema.esAdministrador(user, pass)) {
                JOptionPane.showMessageDialog(this, "Bienvenido Administrador");
                new AdminMenuFrame(sistema).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> {
            new HomeFrame(sistema).setVisible(true);
            dispose();
        });

        add(panel);
    }
}

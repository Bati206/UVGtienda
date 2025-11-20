import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

    private Sistema sistema;

    public HomeFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Tienda UVG - Inicio");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel titulo = new JLabel("Bienvenido a la Tienda UVG", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnLoginCliente = new JButton("Iniciar sesión (Cliente)");
        JButton btnRegistrar = new JButton("Registrarse como nuevo Cliente");
        JButton btnAdmin = new JButton("Acceder como Administrador");
        JButton btnSalir = new JButton("Salir");

        // Eventos de botones
        btnLoginCliente.addActionListener(e -> {
            new LoginClienteFrame(sistema).setVisible(true);
            dispose();
        });

        btnRegistrar.addActionListener(e -> {
            new RegistroClienteFrame(sistema).setVisible(true);
            dispose();
        });

        btnAdmin.addActionListener(e -> {
            new LoginAdminFrame(sistema).setVisible(true);
            dispose();
        });

        btnSalir.addActionListener(e -> System.exit(0));

        panel.add(titulo);
        panel.add(btnLoginCliente);
        panel.add(btnRegistrar);
        panel.add(btnAdmin);
        panel.add(btnSalir);

        add(panel);
    }

    // PRUEBA RÁPIDA
    public static void main(String[] args) {
        Sistema sistema = new Sistema("admin", "admin123");
        new HomeFrame(sistema).setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;

public class AdminMenuFrame extends JFrame {

    private Sistema sistema;

    public AdminMenuFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Panel del Administrador");
        setSize(550, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout());

        // TÍTULO
        JLabel titulo = new JLabel("Panel del Administrador", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titulo, BorderLayout.NORTH);

        // BOTONES
        JPanel panel = new JPanel(new GridLayout(6, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        JButton btnVerProductos = new JButton("Ver todos los productos");
        JButton btnAgregarProducto = new JButton("Agregar nuevo producto");
        JButton btnEliminarProducto = new JButton("Eliminar producto");

        JButton btnVerKits = new JButton("Ver todos los kits");
        JButton btnCrearKit = new JButton("Crear nuevo kit");
        JButton btnAgregarProdKit = new JButton("Agregar producto a un kit");
        JButton btnEliminarProdKit = new JButton("Eliminar producto de un kit");

        JButton btnVerOrdenes = new JButton("Ver todas las órdenes");
        JButton btnVerClientes = new JButton("Ver clientes");

        JButton btnSalir = new JButton("Cerrar sesión");

        // Tamaño uniforme
        Dimension tam = new Dimension(260, 45);
        JButton[] botones = {
                btnVerProductos, btnAgregarProducto, btnEliminarProducto,
                btnVerKits, btnCrearKit, btnAgregarProdKit, btnEliminarProdKit,
                btnVerOrdenes, btnVerClientes, btnSalir
        };

        for (JButton b : botones)
            b.setPreferredSize(tam);

        // acciones
        btnVerProductos.addActionListener(e -> new VerProductosFrame(sistema).setVisible(true));
        btnAgregarProducto.addActionListener(e -> new AgregarProductoFrame_Admin(sistema).setVisible(true));
        btnEliminarProducto.addActionListener(e -> new EliminarProductoFrame(sistema).setVisible(true));

        btnVerKits.addActionListener(e -> new VerKitsFrame(sistema).setVisible(true));
        btnCrearKit.addActionListener(e -> new CrearKitFrame(sistema).setVisible(true));
        btnAgregarProdKit.addActionListener(e -> new AgregarProductoAKitFrame(sistema).setVisible(true));
        btnEliminarProdKit.addActionListener(e -> new EliminarProductoDeKitFrame(sistema).setVisible(true));

        btnVerOrdenes.addActionListener(e -> new VerOrdenesFrame(sistema).setVisible(true));
        btnVerClientes.addActionListener(e -> new VerClientesFrame(sistema).setVisible(true));

        btnSalir.addActionListener(e -> {
            new HomeFrame(sistema).setVisible(true);
            dispose();
        });

        // Añadir botones
        panel.add(btnVerProductos);
        panel.add(btnAgregarProducto);
        panel.add(btnEliminarProducto);
        panel.add(btnVerKits);
        panel.add(btnCrearKit);
        panel.add(btnAgregarProdKit);
        panel.add(btnEliminarProdKit);
        panel.add(btnVerOrdenes);
        panel.add(btnVerClientes);

        panel.add(new JLabel());
        panel.add(btnSalir);

        add(panel, BorderLayout.CENTER);
    }
}

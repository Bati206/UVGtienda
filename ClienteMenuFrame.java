import javax.swing.*;
import java.awt.*;

public class ClienteMenuFrame extends JFrame {

    private Sistema sistema;

    public ClienteMenuFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Menú del Cliente");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Layout principal
        setLayout(new BorderLayout());

        // ─────────────────── TÍTULO ───────────────────
        JLabel titulo = new JLabel("Menú del Cliente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titulo, BorderLayout.NORTH);

        // ─────────────── BOTONES CENTRALES ───────────────
        JPanel panelBotones = new JPanel(new GridLayout(4, 2, 20, 20));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        // Crear botones
        JButton btnProductos = new JButton("Ver catálogo de productos");
        JButton btnKits = new JButton("Ver catálogo de kits");
        JButton btnAgregar = new JButton("Agregar producto/kit al carrito");
        JButton btnCarrito = new JButton("Ver carrito");
        JButton btnProcesar = new JButton("Procesar orden");
        JButton btnOrdenes = new JButton("Mis órdenes");
        JButton btnSalir = new JButton("Cerrar sesión");

        // Tamaño uniforme para evitar "..."
        Dimension tam = new Dimension(230, 45);
        btnProductos.setPreferredSize(tam);
        btnKits.setPreferredSize(tam);
        btnAgregar.setPreferredSize(tam);
        btnCarrito.setPreferredSize(tam);
        btnProcesar.setPreferredSize(tam);
        btnOrdenes.setPreferredSize(tam);
        btnSalir.setPreferredSize(tam);

        // Acciones de los botones
        btnProductos.addActionListener(e -> new CatalogoFrame(sistema, "productos").setVisible(true));
        btnKits.addActionListener(e -> new CatalogoFrame(sistema, "kits").setVisible(true));
        btnAgregar.addActionListener(e -> new AgregarProductoFrame(sistema).setVisible(true));
        btnCarrito.addActionListener(e -> new CarritoFrame(sistema).setVisible(true));
        btnProcesar.addActionListener(e -> new ProcesarOrdenFrame(sistema).setVisible(true));
        btnOrdenes.addActionListener(e -> new OrdenesClienteFrame(sistema).setVisible(true));

        btnSalir.addActionListener(e -> {
            sistema.cerrarSesion();
            new HomeFrame(sistema).setVisible(true);
            dispose();
        });

        // Agregar botones
        panelBotones.add(btnProductos);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnKits);
        panelBotones.add(btnCarrito);
        panelBotones.add(btnProcesar);
        panelBotones.add(btnOrdenes);
        panelBotones.add(new JLabel());
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);
    }
}

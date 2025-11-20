import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CarritoFrame extends JFrame {

    private Sistema sistema;

    public CarritoFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Carrito");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Carrito carrito = sistema.getCarritoActual();

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);

        modelo.addColumn("Producto");
        modelo.addColumn("Precio");

        List<Producto> items = carrito.getItems();
        for (Producto p : items) {
            modelo.addRow(new Object[] { p.getNombre(), p.getPrecio() });
        }

        JLabel total = new JLabel("Total: $" + carrito.getTotalAPagar(), SwingConstants.RIGHT);
        total.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCerrar = new JButton("Cerrar");

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                String nombre = modelo.getValueAt(fila, 0).toString();
                sistema.eliminarDelCarrito(nombre);
                modelo.removeRow(fila);
            }
        });

        btnCerrar.addActionListener(e -> dispose());

        JPanel abajo = new JPanel(new BorderLayout());
        abajo.add(total, BorderLayout.CENTER);
        abajo.add(btnEliminar, BorderLayout.WEST);
        abajo.add(btnCerrar, BorderLayout.EAST);

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(abajo, BorderLayout.SOUTH);
    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VerProductosFrame extends JFrame {

    private Sistema sistema;

    public VerProductosFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Lista de Productos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);

        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("Estado");

        List<Producto> productos = sistema.getProductos();
        for (Producto p : productos) {
            modelo.addRow(new Object[] {
                    p.getNombre(),
                    p.getPrecio(),
                    p.getUnidadesDisponibles(),
                    p.isDisponible() ? "Disponible" : "Agotado"
            });
        }

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}

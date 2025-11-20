import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CatalogoFrame extends JFrame {

    private Sistema sistema;

    public CatalogoFrame(Sistema sistema, String tipo) {
        this.sistema = sistema;

        setTitle("Catálogo de " + tipo);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);

        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");

        if (tipo.equals("productos")) {
            List<Producto> productos = sistema.getProductosDisponibles();
            for (Producto p : productos) {
                modelo.addRow(new Object[] {
                        p.getNombre(),
                        p.getPrecio(),
                        p.getUnidadesDisponibles()
                });
            }
        } else {
            List<Kit> kits = sistema.getKitsDisponibles();
            for (Kit k : kits) {
                modelo.addRow(new Object[] {
                        k.getNombre(),
                        k.getPrecio(),
                        k.getUnidadesDisponibles()
                });
            }
        }

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}

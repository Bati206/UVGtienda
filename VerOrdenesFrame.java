import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VerOrdenesFrame extends JFrame {

    private Sistema sistema;

    public VerOrdenesFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Órdenes Registradas");
        setSize(650, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);

        modelo.addColumn("ID Orden");
        modelo.addColumn("Cliente");
        modelo.addColumn("Estado");
        modelo.addColumn("Total");

        List<Orden> ordenes = sistema.getOrdenes();

        for (Orden o : ordenes) {
            modelo.addRow(new Object[] {
                    o.getOrdenID(),
                    o.getComprador().getNombre(),
                    o.getOrdenStatus(),
                    o.getCarritoUsuario().getTotalAPagar()
            });
        }

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}

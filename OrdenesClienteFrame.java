import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OrdenesClienteFrame extends JFrame {

    private Sistema sistema;

    public OrdenesClienteFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Mis Órdenes");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);

        modelo.addColumn("ID");
        modelo.addColumn("Estado");
        modelo.addColumn("Total");

        List<Orden> ordenes = sistema.getOrdenesDelCliente();
        for (Orden o : ordenes) {
            modelo.addRow(new Object[] {
                    o.getOrdenID(),
                    o.getOrdenStatus(),
                    o.getCarritoUsuario().getTotalAPagar()
            });
        }

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}

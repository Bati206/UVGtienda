import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VerClientesFrame extends JFrame {

    private Sistema sistema;

    public VerClientesFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Clientes Registrados");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Tipo");

        List<Cliente> clientes = sistema.getClientes();

        for (Cliente c : clientes) {
            modelo.addRow(new Object[] { c.getClienteID(), c.getNombre(), c.getTipo() });
        }

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VerKitsFrame extends JFrame {

    private Sistema sistema;

    public VerKitsFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Lista de Kits");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);

        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");

        List<Kit> kits = sistema.getKits();
        for (Kit k : kits) {
            modelo.addRow(new Object[] {
                    k.getNombre(),
                    k.getPrecio(),
                    k.getUnidadesDisponibles()
            });
        }

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}

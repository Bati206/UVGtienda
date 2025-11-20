import javax.swing.*;
import java.awt.*;

public class ProcesarOrdenFrame extends JFrame {

    private Sistema sistema;

    public ProcesarOrdenFrame(Sistema sistema) {
        this.sistema = sistema;

        setTitle("Procesar Orden");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] metodos = { "tarjeta", "transferencia", "efectivo" };
        JComboBox<String> combo = new JComboBox<>(metodos);

        JButton btnProcesar = new JButton("Procesar");
        JButton btnCancelar = new JButton("Cancelar");

        panel.add(new JLabel("Seleccione método de pago:"));
        panel.add(combo);
        panel.add(btnProcesar);
        panel.add(btnCancelar);

        btnProcesar.addActionListener(e -> {
            String metodo = combo.getSelectedItem().toString();
            String resultado = sistema.procesarOrden(metodo);

            JOptionPane.showMessageDialog(this, "Resultado: " + resultado);
        });

        btnCancelar.addActionListener(e -> dispose());

        add(panel);
    }
}

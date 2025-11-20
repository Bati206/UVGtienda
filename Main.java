import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Sistema sistema = new Sistema("admin", "admin123");

        javax.swing.SwingUtilities.invokeLater(() -> {
            new HomeFrame(sistema).setVisible(true);
        });
    }
}

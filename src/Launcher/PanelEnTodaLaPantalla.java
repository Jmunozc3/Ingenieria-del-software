package Launcher;

import javax.swing.*;
import java.awt.*;

public class PanelEnTodaLaPantalla {
    public static void main(String[] args) {
        // Crear un nuevo marco (frame)
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Crear un panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE); // Color de fondo del panel

        // Establecer el tamaño y la posición del panel
        panel.setSize(screenSize.width, screenSize.height);
        panel.setLocation(0, 0);

        // Agregar el panel al marco
        frame.add(panel);

        // Establecer el tamaño del marco al tamaño de la pantalla
        frame.setSize(screenSize.width, screenSize.height);

        // Hacer visible el marco
        frame.setVisible(true);
    }
}

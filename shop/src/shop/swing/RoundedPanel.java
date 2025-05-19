package shop.swing;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int cornerRadius;

    /**
     * Constructor que define solo el radio de las esquinas.
     * Usa el layout por defecto de JPanel (FlowLayout).
     * 
     * @param radius Radio de las esquinas.
     */
    public RoundedPanel(int radius) {
        this.cornerRadius = radius;
        setOpaque(false); // importante para permitir el dibujo personalizado
    }

    /**
     * Constructor que define el radio de las esquinas y el layout manager.
     * Permite pasar un BorderLayout u otro manager para evitar desorden de componentes.
     * 
     * @param radius Radio de las esquinas.
     * @param layout LayoutManager (por ejemplo, new BorderLayout()).
     */
    public RoundedPanel(int radius, LayoutManager layout) {
        super(layout);
        this.cornerRadius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibuja el fondo del panel con esquinas redondeadas
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.dispose();

        // Llama al método original para pintar el contenido del panel
        super.paintComponent(g);
    }
}

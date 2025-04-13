import javax.swing.*;
import java.awt.*;

public class ArbolPitagoras extends JPanel {
    private int niveles;

    public ArbolPitagoras(int niveles) {
        this.niveles = niveles;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);

        int baseX = getWidth() / 2;
        int baseY = getHeight() - 50;
        int baseTamaño = 100;

        trazaArbol(g2d, baseX, baseY, baseTamaño, -90, niveles);
    }

    private void trazaArbol(Graphics2D g2d, int x, int y, int size, double angle, int nivel) {
        if (nivel == 0)
            return;

        int x1 = x + (int) (size * Math.cos(Math.toRadians(angle)));
        int y1 = y + (int) (size * Math.sin(Math.toRadians(angle)));

        g2d.drawLine(x, y, x1, y1);

        int newSize = (int) (size * 0.707); // 1/√2

        int xIzq = x1 + (int) (newSize * Math.cos(Math.toRadians(angle - 45)));
        int yIzq = y1 + (int) (newSize * Math.sin(Math.toRadians(angle - 45)));

        int xDer = x1 + (int) (newSize * Math.cos(Math.toRadians(angle + 45)));
        int yDer = y1 + (int) (newSize * Math.sin(Math.toRadians(angle + 45)));

        trazaArbol(g2d, x1, y1, newSize, angle - 45, nivel - 1);
        trazaArbol(g2d, x1, y1, newSize, angle + 45, nivel - 1);
    }

    public static void main(String[] args) {
        int niveles = 6;

        try {
            String input = JOptionPane.showInputDialog("Ingrese el número de niveles (6, 8 o 10):");
            if (input != null) {
                niveles = Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Se usará 6 niveles por defecto.");
        }

        JFrame frame = new JFrame("Árbol de Pitágoras");
        ArbolPitagoras panel = new ArbolPitagoras(niveles);
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
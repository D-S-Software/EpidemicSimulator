import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel implements ActionListener
{
    Graphics2D g2D;

    public BoardPanel()
    {
        super();
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g2D = (Graphics2D)g;
    }

    public Graphics2D getGraphics()
    {
        return g2D;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

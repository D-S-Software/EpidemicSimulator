import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel
{
    Board board;

    public BoardPanel()
    {
        super();
    }

   public BoardPanel(GridLayout gl)
   {
       super(gl);
   }
}

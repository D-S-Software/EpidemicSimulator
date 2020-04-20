import javax.swing.*;

public class Frame extends JFrame {

    private Panel panel;

    public Frame(Board board)
    {
        panel = new Panel(board);
        this.setSize((int)(board.dimens.xLen *1.25),(int)(1.25* board.dimens.yLen));
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public Panel getPanel()
    {
        return panel;
    }
}

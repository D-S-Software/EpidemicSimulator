import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Frame extends JFrame {

    Panel panel;

    public Frame(Board board, ActionListener actionListener)
    {
        panel = new Panel(board);
        this.setSize(board.boardDimensions.xLen *2, board.boardDimensions.yLen*2);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

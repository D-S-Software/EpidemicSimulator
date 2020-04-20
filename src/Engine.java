import java.util.Timer;

public class Engine {

    private Board myBoard;

    public Engine()
    {
        myBoard = new Board();
    }

    public static void main(String[] args)
    {
        Engine myEngine = new Engine();
        Frame screen = new Frame(myEngine.myBoard);

        Timer timer = new Timer(10, );

    }
}

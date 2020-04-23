
public class Main {

    GUI gui;

    public Main(int baseXLen, int baseYLen)
    {
        gui = new GUI(baseXLen, baseYLen);
    }
    public static void main(String[] args)
    {
        Main test = new Main(1500, 1000);
    }
}
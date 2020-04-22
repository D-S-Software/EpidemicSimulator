public class Main {


    public Main(Disease disease, int numPeople, int baseXLen, int baseYLen)
    {
        GUI gui = new GUI(baseXLen, baseYLen);
        Engine run = new Engine(gui, disease, numPeople, baseXLen, baseYLen);
    }
    public static void main(String[] args)
    {
        Main test = new Main(new Disease1(), 400, 1000, 600);
    }
}


//TODO: Pause button (Start and stop the clock object)
//TODO: End Button
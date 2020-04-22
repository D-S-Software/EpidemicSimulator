public class Main {


    public Main(Disease disease, int numPeople, int baseXLen, int baseYLen)
    {
        GUI gui = new GUI(baseXLen, baseYLen);
        Engine run = new Engine(gui, disease, numPeople, baseXLen, baseYLen);
    }
    public static void main(String[] args)
    {
        Main test = new Main(new Disease1(), 200, 1000, 600);
    }
}

public class Main {

    GUI gui;

    public Main(int baseXLen, int baseYLen)
    {
        gui = new GUI(baseXLen, baseYLen);
    }

    public static void main(String[] args)
    {
        Main test = new Main(1750, 1000);
    }

    //TODO Clean up code (make packages, check parameters (age, mortality rate, windowSize, delays ect.)
    //TODO Make Sim Settings (walls, other changes, behavior ect.)
    //TODO Make opening screen (start with music)
    //TODO make a button to toggle music (ex. Black Ops Zombies, Plague Inc., Break Bad Intro) (Other sounds to implement?)
    //TODO Find Diseases and set parameters for Disease 1-4
}
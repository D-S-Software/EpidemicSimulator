public class Main {

    GUI gui;

    public Main(int baseXLen, int baseYLen)
    {
        gui = new GUI(baseXLen, baseYLen);
    }

    public static void main(String[] args)
    {
        Main test = new Main(1600, 900); //Should be 16.9 to scale nicely with most screens
    }

    //TODO Set Font size in Graphs

    //TODO Clean up code (make packages, check parameters ect.)
    //TODO Make Sim Settings (All parameters in Engine class) - Graphic settings (higher graphics = more chart updates);
    //TODO Make opening screen (start with music)
    //TODO Find Diseases and set parameters for Disease 1-4

    //Action Events:
    // First 10 deaths - move less --> just update step and direction in person
    // going to a central location

}
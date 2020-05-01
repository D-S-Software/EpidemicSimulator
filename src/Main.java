public class Main {

    GUI gui;

    /**Creates the simulation object when the program is launched
     *
     * @param baseXLen The starting length of the window for the main gui
     * @param baseYLen The starting height of the window for the main gui
     */
    public Main(int baseXLen, int baseYLen)
    {
        gui = new GUI(baseXLen, baseYLen);
    }

    public static void main(String[] args)
    {
        Main test = new Main(1600, 900); //Should be 16.9 to scale nicely with most screens
    }

    //TODO Set Font size in Graphs - V1

    //TODO Fix Bugs
    //1. Bouncing is not really there (dimens are off)

    //TODO Clean up code (make packages, check parameters ect.) V1

    //TODO Write data to text fil (such as graph data to compare) - V2
    //TODO Make opening screen (start with music) - V2
    //TODO Leaderboard and finding R0 - V2
    //TODO Central Market - V2
    //TODO add display of run speed ( .5x , 1x, 1.5x ...)

    //TODO ACTION EVENTS - V2
    // First 10 deaths - move less --> just update step and direction in person
    // going to a central location
    // manual trigger

}
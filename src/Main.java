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

    //TODO Clean up code (make packages, check parameters (age, mortality rate, windowSize, delays ect.)
    //TODO Make Sim Settings (walls, other changes, behavior ect.) - Graphic settings (higher graphics = more chart updates);
    //TODO Make opening screen (start with music)
    //TODO make a button to toggle music (ex. Black Ops Zombies, Plague Inc., Break Bad Intro) (Other sounds to implement?)
    //TODO Find Diseases and set parameters for Disease 1-4

    //SimControl:
    // Have control over the ages and conditions of people (just send to board class (already implemented)
    // Behavior Settings --> How people are allowed to move
    // "Action Events" --> make people do something on an event

    //Behavior Settings:
    //1. Random Motion
    //2. 4 Corners with some travelers
    //3. Social Distancing (stay put / small movement)

    //Action Events:
    // First 10 deaths - move less --> just update step and direction in person
}
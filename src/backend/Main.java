package backend;

import gui.GUI;

public class Main {

    GUI gui;

    /**Creates the simulation gui object when the program is launched
     */
    public Main()
    {
        gui = new GUI();
    }

    public static void main(String[] args)
    {
        new Main();
    }
}
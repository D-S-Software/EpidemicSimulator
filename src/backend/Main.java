package backend;

import gui.GUI;

public class Main {

    GUI gui;

    /**Creates the simulation object when the program is launched
     */
    public Main()
    {
        gui = new GUI();
    }

    public static void main(String[] args)
    {
        Main test = new Main();
    }


    //TODO 5 min fix
    /*
    - Contagious Range plays when negative num entered on first run
    - Use space bar to pause and play sim and arrows to speed up and slow down
    - export data button (otherwise each new sim overwrites previous data
     */

    //TODO Make opening screen (start with music) - V2
    //TODO Central Market - V2
    //TODO add display of run speed ( .5x , 1x, 1.5x ...)

    //TODO ACTION EVENTS - V2
    // First 10 deaths - move less --> just update step and direction in person
    // going to a central location
    // manual trigger
}
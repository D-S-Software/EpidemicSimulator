import java.awt.*;

/**
 * Requirements for a SimBoard object to be a quarantine board
 */
public interface Quarantinable {

    /**
     * Draws a line to mark of the quarantine zone
     * @param g2D graphics object used to draw the line
     */
    void drawQuarLine(Graphics2D g2D);

    /**
     * Checks if a sick person needs to go into quarantine if they participate. Then checks if they recover and returns them back to their section of the board
     */
    void quarantineCheck();


}

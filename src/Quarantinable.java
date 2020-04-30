import java.awt.*;

/**
 * Requirements for a SimBoard object to be a quarantine board
 */
public interface Quarantinable {

    void drawQuarLine(Graphics2D g2D);

    void quarantineCheck();


}

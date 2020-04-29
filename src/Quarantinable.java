import java.awt.*;

/**
 * Requirements for a SimBoard object to be a quarantine board
 */
public interface Quarantinable {

    public void drawQuarLine(Graphics2D g2D);

    public void quarantineCheck();


}

package Library;

import java.awt.*;
/** All names and corresponding rgb coordinates come from wikipedia*/
public class CustomColor extends Color{

    public CustomColor(int r, int g, int b)
    {
        super(r,b,g);
    }


    public static final Color REDWOOD = new Color(164, 90, 82);/** Red based */
    public static final Color DARK_RED = new Color(139, 0, 0);/** Red based */
    public static final Color CARMINE = new Color(150, 0, 24);/** Red based */
    public static final Color BLOOD_RED = new Color(102, 0, 0);/** Red based */

    //Orange
    public static final Color ALLOY_ORANGE = new Color(196, 98, 16);

    //Yellow

    //Green

    //Blue
    public static final Color SAVOY_BLUE = new Color(75, 97, 209);/** Blue based */

    //Violet

    //Gray
    public static final Color DIM_GRAY = new Color(105,105,105); /** Gray based */
    public static final Color SILVER = new Color(192,192,192);/** Gray based */
    public static final Color JET = new Color(52, 52, 52);/** Gray based */
    public static final Color SLATE_GRAY = new Color(112, 128, 144);
    public static final Color GUNMETAL = new Color(42, 52, 57);
    public static final Color CHARCOAL = new Color(54, 69, 79);
    public static final Color EERIE_BLACK = new Color(27, 27, 27);
    public static final Color DAVYS_GRAY = new Color(85, 85, 85);
    public static final Color OUTER_SPACE = new Color(65, 74, 76);
    public static final Color Onyx = new Color(53, 56, 57);



    //Epidemic Specific
    public static final Color HEALTHY  = CustomColor.SAVOY_BLUE;
    public static final Color SICK = CustomColor.CARMINE;
    public static final Color RECOVERED = CustomColor.SILVER;
    public static final Color DEAD = CustomColor.DIM_GRAY;



}

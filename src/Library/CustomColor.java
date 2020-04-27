package Library;

import java.awt.*;
/** All names and corresponding rgb coordinates come from wikipedia*/
public class CustomColor extends Color{

    public CustomColor(int r, int g, int b)
    {
        super(r,b,g);
    }

    //Red
    public static final Color REDWOOD = new Color(164, 90, 82);/** Red based */
    public static final Color DARK_RED = new Color(139, 0, 0);/** Red based */
    public static final Color CARMINE = new Color(150, 0, 24);/** Red based */
    public static final Color BLOOD_RED = new Color(102, 0, 0);/** Red based */
    public static final Color GARNET = new Color(115, 54, 53);

    //Orange
    public static final Color ALLOY_ORANGE = new Color(196, 98, 16);
    public static final Color BURNT_ORANGE = new Color(191, 87, 0);
    public static final Color BURNT_UMBER = new Color(138, 51, 36);
    public static final Color KOBICHA = new Color(107, 68, 35);
    public static final Color JASPER = new Color(208, 83, 64);
    public static final Color SARACUSE_ORANGE = new Color(212, 69, 0);
    public static final Color OCHRE = new Color(204, 119, 34);
    public static final Color CHESTNUT = new Color(149, 69, 53);
    public static final Color VERY_DEEP_GAMBOGE = new Color(51, 32, 0);

    //Yellow
    public static final Color GOLDENROD = new Color(218, 165, 32);
    public static final Color ROYAL_YELLOW = new Color(250, 218, 94);
    public static final Color SAFTEY_YELLOW = new Color(255, 211, 0);
    public static final Color DEEP_GOLD = new Color(102, 88, 0);
    public static final Color DARK_GOLD = new Color(127, 112, 25);
    public static final Color VERY_DEEP_GOLD = new Color(51, 44, 0);
    public static final Color CREAM = new Color(255, 255, 204);

    //Green
    public static final Color ARTICHOKE_GREEN = new Color(75, 111, 68);
    public static final Color DARK_MOSS_GREEN = new Color(74, 93, 35);
    public static final Color DARK_GREEN_BLUE = new Color(1, 50, 36);

    //Blue
    public static final Color SAVOY_BLUE = new Color(75, 97, 209);/** Blue based */
    public static final Color INDEPENDENCE = new Color(76, 81, 109);
    public static final Color SPACE_CADET = new Color(30, 41, 82);
    public static final Color SPACE_CADET_LIGHT = new Color( 40,51,82);
    public static final Color Sapphire = new Color(8, 37, 103);


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
    public static final Color ONYX = new Color(53, 56, 57);
    public static final Color CINEROUS = new Color(152, 129, 123);

    //Brown
    public static final Color ZINNWALDITE_BROWN = new Color(44, 22, 8);
    public static final Color SEAL_BROWN = new Color(89, 38, 11);


    //Epidemic Specific
    public static final Color HEALTHY  = CustomColor.SAVOY_BLUE;
    public static final Color SICK = CustomColor.CARMINE;
    public static final Color ASYMPTOMATIC = CustomColor.ALLOY_ORANGE;
    public static final Color RECOVERED = CustomColor.SILVER;
    public static final Color DEAD = CustomColor.DIM_GRAY;

    public static final Color BACKGROUND = CustomColor.EERIE_BLACK;
    public static final Color BUTTON = CustomColor.DAVYS_GRAY;
    public static final Color FIELD = CustomColor.DAVYS_GRAY;
    public static final Color ON_BLOOD_RED_LABEL = CustomColor.BLACK;
    public static final Color ON_BUTTON_LABEL = Color.BLACK; /** Also for Fields */
    public static final Color CHART_LABEL = Color.WHITE;
 //


}

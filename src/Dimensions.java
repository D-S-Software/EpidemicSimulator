import java.awt.*;

//TODO Replace this class with java Rectangle
/** Dimensions contains values that will be used in the creation of a Board object and are passed to a Person object */
public class Dimensions {

    private int xOrigin;
    private int yOrigin;
    private int xLen;
    private int yLen;

    public Dimensions(int xOrigin, int yOrigin, int xLen, int yLen)
    {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.xLen = xLen;
        this.yLen = yLen;
    }

    public Dimensions(int xOrigin, int yOrigin, Dimension dimen)
    {
        this(xOrigin, yOrigin, dimen.width, dimen.height);
    }

    public Dimensions(Dimensions dimensions)
    {
        this.xOrigin = dimensions.xOrigin;
        this.yOrigin = dimensions.yOrigin;
        this.xLen = dimensions.xLen;
        this.yLen = dimensions.yLen;
    }

    /** Default board dimensions */
    public Dimensions()
    {
        this(0,0,800,450);
        //TODO: Fix xOrigin, yOrigin when not equal to 0
    }

    public void setxOrigin(int num)
    {
        this.xOrigin = num;
    }

    public void setxLen(int xLen) {
        this.xLen = xLen;
    }

    public void setyOrigin(int yOrigin) {
        this.yOrigin = yOrigin;
    }

    public void setyLen(int yLen) {
        this.yLen = yLen;
    }

    public int getxOrigin() {
        return xOrigin;
    }

    public int getxLen() {
        return xLen;
    }

    public int getyOrigin() {
        return yOrigin;
    }

    public int getyLen() {
        return yLen;
    }
}

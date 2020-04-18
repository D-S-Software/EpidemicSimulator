
/** BoardDimensions contains values that will be used in the creation of a Board object and are passed to a Person object */
public class BoardDimensions {

    public final int xOrigin;
    public final int yOrigin;
    public final int xLen;
    public final int yLen;

    public BoardDimensions(int xOrigin, int yOrigin, int xLen, int yLen)
    {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.xLen = xLen;
        this.yLen = yLen;
    }

    /** Default board dimensions */
    public BoardDimensions()
    {
        this(0,0,1600,900);
    }

}

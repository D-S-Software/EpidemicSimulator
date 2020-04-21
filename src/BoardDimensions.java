
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

    public BoardDimensions(BoardDimensions dimensions)
    {
        this.xOrigin = dimensions.xOrigin;
        this.yOrigin = dimensions.yOrigin;
        this.xLen = dimensions.xLen;
        this.yLen = dimensions.yLen;
    }

    /** Default board dimensions */
    public BoardDimensions()
    {
        this(0,0,800,450);
        //TODO: Fix xOrigin, yOrigin when not equal to 0
    }

}

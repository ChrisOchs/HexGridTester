package hextest.tile;

/**
 *
 * @author Chris
 */
public abstract class TileConstants {

    public static final int TILE_SIZE = 32;
    public static final int HEX_WIDTH = 2 * TILE_SIZE;
    public static final int HEX_HEIGHT = (int) Math.ceil((Math.sqrt(3) / 2) * HEX_WIDTH);
    
    public static final RelativeGridLocation [] EVEN_TILE_NEIGHBORS = new RelativeGridLocation [] {
        new RelativeGridLocation(0, -1),
        new RelativeGridLocation(-1, 1),
        new RelativeGridLocation(1, 0), 
        new RelativeGridLocation(0, 1),
        new RelativeGridLocation(-1, 0),
        new RelativeGridLocation(-1, -1)
    };
    
    public static final RelativeGridLocation [] ODD_TILE_NEIGHBORS = new RelativeGridLocation [] {
        new RelativeGridLocation(0, -1),
        new RelativeGridLocation(1, 0),
        new RelativeGridLocation(1, 1), 
        new RelativeGridLocation(0, 1),
        new RelativeGridLocation(1, -1),
        new RelativeGridLocation(-1, 0)
    };
    
    public static final int DIRECTION_NORTH = 0;
    public static final int DIRECTION_NORTHEAST = 1;
    public static final int DIRECTION_SOUTHEAST = 2;
    public static final int DIRECTION_SOUTH = 3;
    public static final int DIRECTION_SOUTHWEST = 4;
    public static final int DIRECTION_NORTHWEST = 5;
    
        
}

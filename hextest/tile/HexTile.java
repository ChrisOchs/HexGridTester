package hextest.tile;

import java.awt.Point;
import java.awt.Polygon;

/**
 *
 * @author Chris
 */
public class HexTile {
    private Polygon bounds;
    private Point midPoint;
    
    private GridLocation gridLocation;
    
    private TileState state = TileState.Default;
       
    public HexTile(int rowIndex, int colIndex, Polygon bounds, Point midPoint) {
        this.bounds = bounds;
        this.midPoint = midPoint;
        
        this.gridLocation = new GridLocation(rowIndex, colIndex);
    }
    
    public Polygon getBounds() {
        return bounds;
    }
    
    public Point getMidPoint() {
        return midPoint;
    }
    
    public GridLocation getWorldIndex() {
        return gridLocation;
    }
    
    public boolean containsPoint(Point p) {
        return bounds.contains(p);
    }
    
    public void setState(TileState state) {
        this.state = state;
    }
    
    public TileState getState() {
        return state;
    }
        
}

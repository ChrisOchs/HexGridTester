/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hextest.world;

import hextest.tile.GridLocation;
import hextest.tile.HexTile;
import hextest.tile.RelativeGridLocation;
import hextest.tile.TileConstants;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class World {
    private HexTile [][] hexGrid = new HexTile[5][9];
    
    public World() {
        
        for (int r = 0; r < hexGrid.length; r++) {
            for (int c = 0; c < hexGrid[r].length; c++) {
                Polygon hex = new Polygon();

                int xOffset = -(TileConstants.HEX_WIDTH / 4);
                int yOffset = ((c % 2) == 0) ? 0 : (TileConstants.HEX_HEIGHT / 2);

                Point midPoint = new Point(
                        (c + 1) * (TileConstants.HEX_WIDTH + xOffset),
                        (r + 1) * TileConstants.HEX_HEIGHT + yOffset);

                for (int a = 0; a < 6; a++) {
                    double angle = 2 * (Math.PI / 6) * a;

                    double x = midPoint.x + TileConstants.TILE_SIZE * Math.cos(angle);
                    double y = midPoint.y + TileConstants.TILE_SIZE * Math.sin(angle);

                    hex.addPoint((int) x, (int) y);
                }

                hexGrid[r][c] = new HexTile(r, c, hex, midPoint);
            }
        }
    }
    
    public HexTile [][] getWorldTiles() {
        return hexGrid;
    }
    
    
    
    public ArrayList<HexTile> getAllNeighboringTiles(HexTile tile) {
        ArrayList<HexTile> neighboringTiles = new ArrayList<HexTile>();
        
        GridLocation tileWorldIndex = tile.getWorldIndex();
        
        final RelativeGridLocation [] neighbors;

        if(tileWorldIndex.col % 2 == 0) {
            neighbors = TileConstants.EVEN_TILE_NEIGHBORS;
        } else {
            neighbors = TileConstants.ODD_TILE_NEIGHBORS;
        }
        
        for(RelativeGridLocation neighbor : neighbors) {
            int rowIndex = tileWorldIndex.row + neighbor.row;
            int colIndex = tileWorldIndex.col + neighbor.col;
            
            if(rowIndex >= 0 && rowIndex < hexGrid.length && 
                    colIndex >= 0 && colIndex < hexGrid[0].length) {
                
                neighboringTiles.add(hexGrid[rowIndex][colIndex]);
            }
        }
        
        return neighboringTiles;
    }

    public HexTile getTileAtPoint(Point p) {
        for (int r = 0; r < hexGrid.length; r++) {
            for (int c = 0; c < hexGrid[r].length; c++) {
                if (hexGrid[r][c].containsPoint(p)) {
                    return hexGrid[r][c];
                }
            }
        }
        
        return null;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hextest.world;

import hextest.tile.HexTile;
import hextest.tile.TileState;
import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class WorldStateManager {
    private HexTile selectedTile = null;
    
    private World world;
    
    public WorldStateManager(World world) {
        this.world = world;
    }
    
    public void setSelectedTile(HexTile tile) {
        if(selectedTile != null) {
            deselectTile(selectedTile);
        }
        
        if(tile != null) {
            selectTile(tile);
            this.selectedTile = tile;
        }
    }
    
    public void clearSelectedTile() {
        if(selectedTile != null) {
            deselectTile(selectedTile);
            selectedTile = null;
        }
    }
    
    private void selectTile(HexTile tile) {
        tile.setState(TileState.Selected);
        
        ArrayList<HexTile> neighborTiles = world.getAllNeighboringTiles(tile);
        
        for(HexTile neighborTile : neighborTiles) {
            neighborTile.setState(TileState.HighlightedNeighbor);
        }
    }
    
    private void deselectTile(HexTile tile) {
        tile.setState(TileState.Default);
        
        ArrayList<HexTile> neighborTiles = world.getAllNeighboringTiles(tile);
        
        for(HexTile neighborTile : neighborTiles) {
            neighborTile.setState(TileState.Default);
        }
    }
}

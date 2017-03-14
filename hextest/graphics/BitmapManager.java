package hextest.graphics;

import hextest.tile.TileConstants;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author Chris
 */
public class BitmapManager {
    
    private BufferedImage unselectedHexImage = drawHexImage(Color.WHITE);
    private BufferedImage selectedHexImage = drawHexImage(Color.BLUE);
    private BufferedImage highlightedNeighborHexImage = drawHexImage(Color.CYAN);
    
    public BitmapManager() {

    }
    
    private BufferedImage drawHexImage(Color innerColor) {
        BufferedImage hexImage = new BufferedImage(
                    TileConstants.HEX_WIDTH,
                    TileConstants.HEX_HEIGHT, 
                    BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2d = hexImage.createGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Polygon hex = new Polygon();
        
        Polygon innerHex = new Polygon();

        Point midPoint = new Point(
                TileConstants.HEX_WIDTH / 2,
                TileConstants.HEX_HEIGHT / 2);
        
        final int CLIP_SIZE = (int)(0.75 * TileConstants.TILE_SIZE);

        for (int a = 0; a < 6; a++) {
            double angle = 2 * (Math.PI / 6) * a;

            double x = midPoint.x + TileConstants.TILE_SIZE * Math.cos(angle);
            double y = midPoint.y + TileConstants.TILE_SIZE * Math.sin(angle);
            
            double innerX = midPoint.x + CLIP_SIZE * Math.cos(angle);
            double innerY = midPoint.y + CLIP_SIZE * Math.sin(angle);
            
            hex.addPoint((int) Math.ceil(x), (int) Math.ceil(y));
            
            innerHex.addPoint((int) Math.ceil(innerX), (int)Math.ceil(innerY));
        }
        
        g2d.setColor(new Color(0,0,0,0));
        g2d.fillRect(0, 0, hexImage.getWidth(), hexImage.getHeight());
        
        
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillPolygon(hex);
        
        g2d.setColor(innerColor);
        g2d.fillPolygon(innerHex);
        
        g2d.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawPolygon(hex);
        
        
        return hexImage;
    }
    
    public BufferedImage getUnselectedHexImage() {
        return unselectedHexImage;
    }
    
    public BufferedImage getSelectedHexImage() {
        return selectedHexImage;
    }
    
    public BufferedImage getHighlightedNeighborHexImage() {
        return highlightedNeighborHexImage;
    }
}

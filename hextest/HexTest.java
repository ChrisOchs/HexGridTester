/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hextest;

import hextest.graphics.BitmapManager;
import hextest.tile.HexTile;
import hextest.tile.TileConstants;
import hextest.tile.TileState;
import hextest.world.World;
import hextest.world.WorldStateManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Chris
 */
public class HexTest extends JFrame {

    public static void main(String[] args) {
        HexTest test = new HexTest();
    }
    
    public HexTest() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(512, 512);
        
        this.add(new HexPanel());
        
        this.setTitle("Let's talk about hex, baby.");
        
        this.setVisible(true);
    }

    private class HexPanel extends JPanel {

        private World world = new World();
        private WorldStateManager worldStateManager = new WorldStateManager(world);
        
        private BitmapManager bitmapManager = new BitmapManager();
        
        public HexPanel() {
            
            this.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    Point clickedPoint = e.getPoint();
                    
                    HexTile selectedTile = world.getTileAtPoint(clickedPoint);
                    
                    worldStateManager.setSelectedTile(selectedTile);
                }
            });

            Thread redrawThread = new Thread(new Runnable() {
                public void run() {
                    
                    long lastDraw = System.currentTimeMillis();

                    while (true) {
                        
                        if(System.currentTimeMillis() - lastDraw > 50) {
                            HexPanel.this.repaint();
                            lastDraw = System.currentTimeMillis();
                        }
                    }
                }
            });

            redrawThread.start();
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            BufferedImage buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            
            Graphics2D bufferG = buffer.createGraphics();
            
            bufferG.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            bufferG.setColor(Color.BLACK);
            bufferG.fillRect(0, 0, getWidth(), getHeight());
            
            HexTile [][] hexGrid = world.getWorldTiles();
            
            for (int r = 0; r < hexGrid.length; r++) {
                for (int c = 0; c < hexGrid[r].length; c++) {
                    BufferedImage tileImage;
                    
                    if(hexGrid[r][c].getState() == TileState.Selected) {
                        tileImage = bitmapManager.getSelectedHexImage();
                    } else if(hexGrid[r][c].getState() == TileState.HighlightedNeighbor) {
                        tileImage = bitmapManager.getHighlightedNeighborHexImage();
                    } else {
                        tileImage = bitmapManager.getUnselectedHexImage();
                    }
                    
                    bufferG.drawImage(tileImage, 
                               hexGrid[r][c].getMidPoint().x - (TileConstants.HEX_WIDTH / 2), 
                               hexGrid[r][c].getMidPoint().y - (TileConstants.HEX_HEIGHT / 2), null);

                    
                    String coord = String.format("(%d, %d)", r, c);
                    
                    bufferG.drawString(coord, hexGrid[r][c].getMidPoint().x - 8, hexGrid[r][c].getMidPoint().y);
                    
                    
                }
            }

            g.drawImage(buffer, 0, 0, null);
        }
    }
}

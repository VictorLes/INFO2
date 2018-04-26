package View;

import Model.Directable;
import Model.Game;
import Model.GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Map extends JPanel {
    private ArrayList<GameObject> objects = null;

    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void paint(Graphics g) {
        int size = Game.getSize();
        int block_size = GameObject.getBlockSize();
        
		for (int i = 0; i < size; i++) { // Virer la valeur 20 et parametrer ca - DONE
            for (int j = 0; j < size; j++) {
                int x = i;
                int y = j;
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x * block_size, y * block_size, block_size-2, block_size-2);
                g.setColor(Color.BLACK);
                g.drawRect(x * block_size, y * block_size, block_size-2, block_size-2);
            }
        }

        for (GameObject object : this.objects) {
            int x = object.getPosX();
            int y = object.getPosY();
            int color = object.getColor();

            if (color == 0) {
                g.setColor(Color.DARK_GRAY);
            } else if (color == 1) {
                g.setColor(Color.GRAY);
            } else if (color == 2) {
                g.setColor(Color.BLUE);
            } else if (color == 3) {
                g.setColor(Color.GREEN);
            } else if (color == 4) {
                g.setColor(Color.RED);
            } else if (color == 5) {
                g.setColor(Color.ORANGE);
            }

            g.fillRect(x * block_size, y * block_size, block_size-2, block_size-2);
            g.setColor(Color.BLACK);
            g.drawRect(x * block_size, y * block_size, block_size-2, block_size-2);
            
            // Decouper en fontions
            if(object instanceof Directable) {
                int direction = ((Directable) object).getDirection();
                
                int deltaX = 0;
                int deltaY = 0;
                
                switch (direction) {
                case Directable.EAST:
                    deltaX = +(block_size-2)/2;
                    break;
                case Directable.NORTH:
                    deltaY = -(block_size-2)/2;
                    break;
                case Directable.WEST:
                    deltaX = -(block_size-2)/2;
                    break;
                case Directable.SOUTH:
                    deltaY = (block_size-2)/2;
                    break;
                }

                int xCenter = x * block_size + (block_size-2)/2;
                int yCenter = y * block_size + (block_size-2)/2;
                g.drawLine(xCenter, yCenter, xCenter + deltaX, yCenter + deltaY);
            }
        }
    }

    public void setObjects(ArrayList<GameObject> objects) {
        this.objects = objects;
    }

    public void redraw() {
        this.repaint();
    }
}

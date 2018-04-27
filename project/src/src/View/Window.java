package View;

import Model.GameObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Window {
	public static int height;
	
    private Map map = new Map();

    public Window() {
    	int width = getWinSize("W");
    	int height = getWinSize("H");
        JFrame window = new JFrame("Game");
        //window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, width, height);		//setBounds is a shortcut for setSize and setLocation, so setSize don't do anything
        window.getContentPane().setBackground(Color.gray);
        window.getContentPane().add(this.map);
        window.setVisible(true);
    }

    public void setGameObjects(ArrayList<GameObject> objects) {
        this.map.setObjects(objects);
        this.map.redraw();
    }

    public void update() {
        this.map.redraw();
    }

    public void setKeyListener(KeyListener keyboard) {
        this.map.addKeyListener(keyboard);
    }
    
    public static int getWinSize(String a) {
    	int dim = 0;
   	    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	if (a == "H") {
    		dim += (int)dimension.getHeight();
    	}
    	else if (a == "W") {
    		dim += (int)dimension.getWidth();
    	}
    	dim = (int) (dim*0.95);		// permet de légèrement réduire la taille de la fenêtre (barre windows ou quoi dans le bas)
    	return dim;
    }
}

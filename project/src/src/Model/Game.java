package Model;

import View.Window;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.omg.CosNaming.IstringHelper;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Game implements DeletableObserver {
    private static final Set<String> HashSet = null;

	private ArrayList<GameObject> objects = new ArrayList<GameObject>();

    private Window window;
    private static int size = 50;
    // private int bombTimer = 3000;
    private int numberOfBreakableBlocks = 100;
    // ATTENTION : numberOfBreakableBlocks < (size-2)^2 - 1 [= size^2 - 4size + 3] !!!

    public void menu() {
    	System.out.print("Menu");
    }
    
    public Game(Window window) {
        this.window = window;

        // Creating one Player at a random position
        Random rand = new Random();
        int px = rand.nextInt(size-2) + 1;
        int py = rand.nextInt(size-2) + 1;
        objects.add(new Player(px, py, 3, 5));
        String pos_player = Integer.toString(px) + "-" + Integer.toString(py);

        // Map building
        for (int i = 0; i < size; i++) {
            objects.add(new BlockUnbreakable(i, 0));
            objects.add(new BlockUnbreakable(0, i));
            objects.add(new BlockUnbreakable(i, size - 1));
            objects.add(new BlockUnbreakable(size - 1, i));
        }
        
        // Random rand = new Random();
        // je suppose qu'il n'y aura jamais plus de blocks que de place disponible
        Set<String> positions = new LinkedHashSet<>();
        positions.add(pos_player);
        //for (int i = 0; i < numberOfBreakableBlocks; i++) {
        while (positions.size() <= numberOfBreakableBlocks) {
            int x = rand.nextInt(size-2) + 1;
            int y = rand.nextInt(size-2) + 1;
            String pos = Integer.toString(x) + "-" + Integer.toString(y);
            if (positions.contains(pos)) {
            }
            else {
            	int lifepoints = rand.nextInt(5) + 1;
            	BlockBreakable block = new BlockBreakable(x, y, lifepoints);
            	block.attachDeletable(this);
            	objects.add(block);
            	positions.add(pos);
            }
        }

        window.setGameObjects(this.getGameObjects());
        notifyView();
    }


    public void movePlayer(int x, int y, int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
        int nextX = player.getPosX() + x;
        int nextY = player.getPosY() + y;

        boolean obstacle = false;
        for (GameObject object : objects) {
            if (object.isAtPosition(nextX, nextY)) {
                obstacle = object.isObstacle();
            }
            if (obstacle == true) {
                break;
            }
        }
        player.rotate(x, y);
        if (obstacle == false) {
            player.move(x, y);
        }
        notifyView();
    }

    public void action(int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
        Activable aimedObject = null;
		for(GameObject object : objects){
			if(object.isAtPosition(player.getFrontX(),player.getFrontY())){
			    if(object instanceof Activable){
			        aimedObject = (Activable) object;
			    }
			}
		}
		if(aimedObject != null){
		    aimedObject.activate();
            notifyView();
		}
        
    }

    private void notifyView() {
        window.update();
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.objects;
    }

    @Override
    synchronized public void delete(Deletable ps, ArrayList<GameObject> loot) {
        objects.remove(ps);
        if (loot != null) {
            objects.addAll(loot);
        }
        notifyView();
    }


    public void playerPos(int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
        System.out.println(player.getPosX() + ":" + player.getPosY());
        
    }
    
    public static int getSize() {
    	return size;
    }

}
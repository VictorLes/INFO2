package Model;

import java.awt.Dimension;

import View.Window;

public abstract class Block extends GameObject {
    public Block(int x, int y, int color, int block_size) {
        super(x, y, color, block_size);
    }

    // permet d'adapter la taille des blocks par rapport � la fen�tre, pseudo full-screen
    static int size = Game.getSize();
	static int block_size = (int)(Window.getWinSize("H")-100)/size;
	// ATTENTION : la taille de la fen�tre tient aussi compte de la bande au-dessus, il faut donc la d�duire !
	// Ce n'est pas difficile : il suffit de jouer avec le petit chiffre � c�t� de "height" line 14
	
}

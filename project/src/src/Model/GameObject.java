package Model;

public abstract class GameObject {
    protected int posX;
    protected int posY;
    protected int color;
	protected static int block_size;

    public GameObject(int X, int Y, int color, int block_size) {
        this.posX = X;
        this.posY = Y;
        this.color = color;
        this.block_size = block_size;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getColor() {
        return this.color;
    }
    
    public static int getBlockSize() {
    	return block_size;
    }

    public boolean isAtPosition(int x, int y) {
        return this.posX == x && this.posY == y;
    }

    public abstract boolean isObstacle();
}

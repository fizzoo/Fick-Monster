package inda13projekt;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * This class is the basis for every object that needs to be called in the
 * update and render methods in Game.java. This way all those objects can be
 * easily stored in one list.
 * 
 * @author Alex
 * 
 */
public abstract class GameObject {
	protected float x;
	protected float y;
	protected final float width = 32;
	protected final float height = 32;

	protected SpriteSheet spriteSheet;
	protected Image sprite;
	protected int spriteOffset;

	protected boolean isMoving;
	protected int direction; // W=0;S=1;A=2;D=3;
	protected Camera camera;

	protected int gridX;
	protected int gridY;
	protected float velX;
	protected float velY;
	protected Map currentMap;

	/**
	 * 
	 * @param gridX
	 * @param gridY
	 * @param velX
	 *            the velocity of the object when moving on the x-axis (in
	 *            pixels)
	 * @param velY
	 *            the velocity of the object when moving on the y-axis (in
	 *            pixels)
	 * @param currentMap
	 */
	public GameObject(int gridX, int gridY, int velX, int velY,
			int spriteOffset, Map currentMap) {
		this.gridX = gridX;
		this.gridY = gridY;
		this.currentMap = currentMap;
		camera = currentMap.getCamera();

		try {
			this.spriteSheet = new SpriteSheet("././res/Gubbe.png", 32, 32);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		direction = 0;
		sprite = spriteSheet.getSprite(direction + spriteOffset, 0);

		x = 32 * gridX;
		y = 32 * gridY;
		this.velX = velX;
		this.velY = velY;

		this.spriteOffset = spriteOffset;
	}

	/**
	 * Tries to move player up one grid, if possible.
	 */
	public void moveUp() {
		if (!isMoving) {
			direction = 0;
			sprite = spriteSheet.getSprite(direction, 0);

			if (currentMap.isBlocked(gridX, gridY - 1))
				return;

			isMoving = true;
			gridY--;
		}
	}

	/**
	 * Tries to move player down one grid, if possible.
	 */
	public void moveDown() {
		if (!isMoving) {
			direction = 1;
			sprite = spriteSheet.getSprite(direction, 0);

			if (currentMap.isBlocked(gridX, gridY + 1))
				return;

			isMoving = true;
			gridY++;
		}
	}

	/**
	 * Tries to move player left one grid, if possible.
	 */
	public void moveLeft() {
		if (!isMoving) {
			direction = 2;
			sprite = spriteSheet.getSprite(direction, 0);

			if (currentMap.isBlocked(gridX - 1, gridY))
				return;

			isMoving = true;
			gridX--;
		}
	}

	/**
	 * Tries to move player right one grid, if possible.
	 */
	public void moveRight() {
		if (!isMoving) {
			direction = 3;
			sprite = spriteSheet.getSprite(direction, 0);

			if (currentMap.isBlocked(gridX + 1, gridY))
				return;

			isMoving = true;
			gridX++;
		}
	}

	/**
	 * updates the objects position, ai etc.
	 */
	public void update() {
		if (y < (gridY * 32)) {
			y += velY;
		} else if (y > (gridY * 32)) {
			y -= velY;
		} else if (x < (gridX * 32)) { // else since you can't walk
										// diagonally anyway
			x += velX;
		} else if (x > (gridX * 32)) {
			x -= velX;
		} else { // finished walking
			isMoving = false;
		}
	}

	/**
	 * renders the objects sprite
	 * 
	 * @param g
	 *            Part of slick2d, used to render to the display
	 */
	public void render(Graphics g) {
		g.drawImage(sprite, x + camera.getXOffset(), y + camera.getYoffset());
	}
}

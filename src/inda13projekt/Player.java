package inda13projekt;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * A class used to represent the player. Handles the players movements.
 * 
 * @author Alex
 * 
 */
public class Player extends GameObject {
	private int gridX;
	private int gridY;
	private boolean isMoving;
	private Map currentMap;
	private SpriteSheet spriteSheet;
	private int direction; // W=0;S=1;A=2;D=3;
	private Camera camera;

	public Player(int gridX, int gridY, Map currentMap) {
		this.gridX = gridX;
		this.gridY = gridY;
		this.currentMap = currentMap;
		camera = currentMap.getCamera();
		init();
	}

	public Player(int gridX, int gridY, Map currentMap) {
		this.gridX = gridX;
		this.gridY = gridY;
		this.currentMap = currentMap;
		init();
	}

	/**
	 * Inits the player to specified coordinate.
	 */
	public void init() {
		direction = 0;

		try {
			this.spriteSheet = new SpriteSheet("././res/Gubbe.png", 32, 32);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.init(32 * gridX + 16, 32 * gridY + 16, 2, 2, 32, 32,
				spriteSheet.getSprite(direction, 0));
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
	 * @return map the player is in
	 */
	public Map getMap() {
		return currentMap;
	}

	/**
	 * @param x
	 *            coordinate to start on, next map
	 * @param y
	 *            coordinate to start on, next map
	 * @param ref
	 *            map name
	 */
	public void changeMap(int newGridX, int newGridY, String ref) {
		currentMap = new Map(ref);
<<<<<<< HEAD
=======
		camera = currentMap.getCamera();
>>>>>>> origin/Victor
		gridX = newGridX;
		gridY = newGridY;
		x = gridX * 32 + 16;
		y = gridY * 32 + 16;
		isMoving = false;
	}

	/**
	 * Updates the player location
	 */
	@Override
	public void update() {
		if (y < (gridY * 32 + height / 2)) {
			y += velY;
		} else if (y > (gridY * 32 + height / 2)) {
			y -= velY;
		} else if (x < (gridX * 32 + width / 2)) { // else since you can't walk
													// diagonally anyway
			x += velX;
		} else if (x > (gridX * 32 + width / 2)) {
			x -= velX;
		} else { // finished walking
			isMoving = false;
		}
		// super.update();// Uses dX/dY

		String nextMap = currentMap.getTeleported(gridX, gridY);
		if (nextMap != null)
			changeMap(2, 0, nextMap);
<<<<<<< HEAD
=======

		camera.setLocation((int) x, (int) y);
>>>>>>> origin/Victor
	}

	/**
	 * Renders the player
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, x - width / 2 + camera.getXOffset(), y - height / 2
				+ camera.getYoffset());
	}
}

package inda13projekt;

import org.newdawn.slick.Graphics;

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

	/**
	 * Inits the player to specified coordinate.
	 */
	public void init(int gridX, int gridY, Map currentMap) {
		this.gridX = gridX;
		this.gridY = gridY;
		super.init(32 * gridX + 16, 32 * gridY + 16, 2, 2, 32, 32, null);
		this.currentMap = currentMap;
	}

	/**
	 * Tries to move player up one grid, if possible.
	 */
	public void moveUp() {
		if (!isMoving) {
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
			if (currentMap.isBlocked(gridX + 1, gridY))
				return;

			isMoving = true;
			gridX++;
		}
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
	}

	/**
	 * Renders the player
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
	}
}

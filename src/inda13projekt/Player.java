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

	/**
	 * Inits the player to specified coordinate.
	 */
	public void init(int gridX, int gridY) {
		this.gridX = gridX;
		this.gridY = gridY;
		super.init(32 * gridX + 16, 32 * gridY + 16, 2, 2, 32, 32, null);
	}

	/**
	 * 
	 */
	public void moveUp() {
		if (!isMoving) {
			isMoving = true;
			gridY--;
		}
	}

	/**
	 * 
	 */
	public void moveDown() {
		if (!isMoving) {
			isMoving = true;
			gridY++;
		}
	}

	/**
	 * 
	 */
	public void moveLeft() {
		if (!isMoving) {
			isMoving = true;
			gridX--;
		}
	}

	/**
	 * 
	 */
	public void moveRight() {
		if (!isMoving) {
			isMoving = true;
			gridX++;
		}
	}

	/**
	 * updates the player
	 */
	@Override
	public void update() {
		if (y < (gridY * 32 + height / 2)) {
			y += velY;
		} else if (y > (gridY * 32 + height / 2)) {
			y -= velY;
		} else if (x < (gridX * 32 + width / 2)) { // else since you can't walk
													// diagonally
			x += velX;
		} else if (x > (gridX * 32 + width / 2)) {
			x -= velX;
		} else { // finished walking
			isMoving = false;
		}
		// super.update();// Uses dX/dY
	}

	/**
	 * renders the player
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
	}
}

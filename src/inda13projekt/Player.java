package inda13projekt;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

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
	 * inits the player
	 */
	@Override
	public void init(final float x, final float y, final float velX,
			final float velY, final int width, final int height,
			final Image sprite) {
		super.init(x, y, velX, velY, width, height, sprite);
		gridX = 0;
		gridY = 0;
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

package inda13projekt;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

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
	protected float velX;
	protected float velY;
	protected float width;
	protected float height;
	protected int dX;
	protected int dY;
	protected Image sprite;

	/**
	 * Inits the most basic variables of gameobject
	 * 
	 * @param x
	 *            the x position of the object
	 * @param y
	 *            the y position of the object
	 * @param velX
	 *            the velocity of the object when moving on the x-axis (in
	 *            pixels)
	 * @param velY
	 *            the velocity of the object when moving on the y-axis (in
	 *            pixels)
	 * @param width
	 *            the width of the object (in pixels)
	 * @param height
	 *            the height of the object (in pixels)
	 * @param sprite
	 *            the sprite used to render the object
	 */
	public void init(final float x, final float y, final float velX,
			final float velY, final int width, final int height,
			final Image sprite) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.width = width;
		this.height = height;
		this.sprite = sprite;

		this.dX = 0;
		this.dY = 0;
	}

	/**
	 * updates the objects position, ai etc.
	 */
	public void update() {
		x += dX * velX;
		y += dY * velY;
	}

	/**
	 * renders the objects sprite
	 * 
	 * @param g
	 *            Part of slick2d, used to render to the display
	 */
	public void render(Graphics g) {
		g.fillRect(x - width / 2, y - height / 2, width, height);
	}
}

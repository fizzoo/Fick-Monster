package inda13projekt;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class GameObject {
	protected float x;
	protected float y;
	protected float velX;
	protected float velY;
	protected float	width;
	protected float	height;
	protected int 	dX;
	protected int 	dY;
	protected Image image;
	
	public void init(final float x, final float y, final float velX, 
			final float velY, final int width, final int height, 
			final Image image) {
		this.x 		= x;
		this.y 		= y;
		this.velX 	= velX;
		this.velY 	= velY;
		this.width = width;
		this.height = height;
		this.image 	= image;
		
		this.dX		= 0;
		this.dY 	= 0;
	}
	
	public void update() {
		x += dX * velX;
		y += dY * velY;
	}
	
	public void render(Graphics g) {
		g.fillRect(x - width/2, y - height/2, width, height);
	}
}

package inda13projekt;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

/**
 * A class used to represent the player. Handles the players movements. 
 * @author Alex
 *
 */
public class Player extends GameObject {
	/**
	 * Handles the players movements. 
	 */
	public Input input;
	
	/**
	 * inits the input
	 * @param gc part of slick2d
	 */
	public Player(GameContainer gc) {
		input = gc.getInput();
	}
	
	/**
	 * inits the player
	 */
	@Override
	public void init (final float x, final float y, final float velX, 
			final float velY, final int width, final int height, 
			final Image sprite) {
		super.init(x, y, velX, velY, width, height, sprite);
		
	}

	/**
	 * updates the player
	 */
	@Override
	public void update() {
		/*if(input.isKeyDown(Input.KEY_W)) {
			dY = -1;
		}
		if(input.isKeyDown(Input.KEY_S)) {
			dY = 1;
		}
		if(!input.isKeyDown(Input.KEY_W) && 
				!input.isKeyDown(Input.KEY_S)) {
			dY = 0;
		}
		if(input.isKeyDown(Input.KEY_A)) {
			dX = -1;
		}
		if(input.isKeyDown(Input.KEY_D)) {
			dX = 1;
		}
		if (!input.isKeyDown(Input.KEY_A) &&
				!input.isKeyDown(Input.KEY_D)){
			dX = 0;
		}*/
		if(input.isKeyDown(Input.KEY_W)) {
			y -= velY;
		}
		if(input.isKeyDown(Input.KEY_S)) {
			y += velY;
		}
		if(input.isKeyDown(Input.KEY_A)) {
			x -= velX;
		}
		if(input.isKeyDown(Input.KEY_D)) {
			x += velX;
		}
		super.update();
		
		if(x < 0 + width / 2) {
			x = 0 + width / 2;
		}
		else if (x > 640 - width / 2){
			x = 640 - width / 2;
		}
		if(y < 0 + height / 2) {
			y = 0 + height / 2;
		}
		else if (y > 480 - height / 2){
			y = 480 - height / 2;
		}
	}
	
	/**
	 * renders the player
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
	}
}

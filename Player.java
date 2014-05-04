package inda13projekt;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Player extends GameObject {
	public Input input;
	
	public Player(GameContainer gc) {
		input = gc.getInput();
	}
	
	public void init (final float x, final float y, final float velX, 
			final float velY, final int width, final int height, 
			final Image image) {
		super.init(x, y, velX, velY, width, height, image);
		
	}

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
	}
	
	public void render(Graphics g) {
		super.render(g);
	}
}

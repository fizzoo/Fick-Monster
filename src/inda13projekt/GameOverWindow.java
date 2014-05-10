package inda13projekt;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/**
 * 
 */
public class GameOverWindow implements Window {
	private Input input;
	private Window nextWindow;

	private final TrueTypeFont ttf32 = new TrueTypeFont(new Font("Verdana",
			Font.PLAIN, 32), true);

	/**
	 * 
	 */
	public GameOverWindow(Input input) {
		this.input = input;
		this.input.clearKeyPressedRecord();
		nextWindow = null;
	}

	/**
	 * Checks for input to restart game.
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if (input.isKeyPressed(Input.KEY_X)
				|| input.isKeyPressed(Input.KEY_RCONTROL)) {
			nextWindow = new MenuWindow(input, new Player(7, 6, "DU", 80, 24,
					16, 10, 10, 0, 1, 2, 3, 0, new Map("map1")));
		}
	}

	/**
	 * Renders all the objects in the window
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(Color.black);
		g.fillRect(0, 0, 640, 480);
		ttf32.drawString(220, 190, "GAME OVER!");
		ttf32.drawString(80, 223, "Press X or CTRL to start over!");
	}

	/**
	 * Handles window transition
	 */
	@Override
	public Window getNextWindow() {
		// TODO Auto-generated method stub
		return nextWindow;
	}

}

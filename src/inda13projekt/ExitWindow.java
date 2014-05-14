package inda13projekt;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * a very basic window that is simply used to indicate that the game should exit
 * 
 */
public class ExitWindow implements Window {

	/**
	 * 
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
	}

	/**
	 * 
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
	}

	/**
	 * 
	 */
	@Override
	public Window getNextWindow() {
		return null;
	}

}

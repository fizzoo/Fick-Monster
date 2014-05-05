package inda13projekt;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Used to create a simple window
 * 
 * @author Alex
 * 
 */
public interface Window {
	/**
	 * updates all the objects in the window
	 * 
	 * @throws SlickException
	 */
	public void update(GameContainer gc, int delta) throws SlickException;

	/**
	 * renders all the objects in the window
	 * 
	 * @throws SlickException
	 */
	public void render(GameContainer gc, Graphics g, Camera camera)
			throws SlickException;
	
	/**
	 * handles window transition
	 * @return 
	 */
	public Window getNextWindow();
}

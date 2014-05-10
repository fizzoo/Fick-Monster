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
	 * Checks for input and updates all objects.
	 * 
	 * @throws SlickException
	 */
	public void update(GameContainer gc, int delta) throws SlickException;

	/**
	 * Renders all the objects in the window
	 * 
	 * @throws SlickException
	 */
	public void render(GameContainer gc, Graphics g) throws SlickException;

	/**
	 * Handles window transition
	 * 
	 * @return
	 */
	public Window getNextWindow();
}

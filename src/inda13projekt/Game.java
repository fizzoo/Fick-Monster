package inda13projekt;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Starts the game and handles it's loop
 * 
 * @author Alex Sundström
 * 
 */
public class Game extends BasicGame {

	private static Window currentWindow;

	/**
	 * Used to create the display
	 */
	public static AppGameContainer app;
	/**
	 * Used to handle player input
	 */
	public static Input input;

	/**
	 * Constructor, generates the display name
	 */
	public Game() {
		super("Fick Monster");
	}

	/**
	 * creates the display and starts the app
	 * 
	 * @param args
	 * @throws SlickException
	 */
	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		app = new AppGameContainer(new Game());
		app.setMouseGrabbed(true);
		app.setDisplayMode(640, 480, false);
		app.setVSync(true);
		app.start();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.BasicGame#init(org.newdawn.slick.GameContainer)
	 * 
	 *      initialize the game
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		input = gc.getInput();
<<<<<<< HEAD
		currentWindow = new MenuWindow(gc, input);
=======
		currentWindow = new OverWorldWindow(input);
>>>>>>> ba5e983a250b1e90a4eae4af11f1d4cc69c2c91e
		gc.setShowFPS(false);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.BasicGame#update(org.newdawn.slick.GameContainer,
	 *      int)
	 * 
	 *      updates the game, is called automatically
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		currentWindow.update(gc, delta);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Game#render(org.newdawn.slick.GameContainer,
	 *      org.newdawn.slick.Graphics)
	 * 
	 *      renders the game, is called automatically
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		currentWindow.render(gc, g, null);
	}
	
	/**
	 * 
	 */
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			app.exit();
		}
	}
}

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
 * @author Alex Sundstr�m
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
		app = new AppGameContainer(new Game());
		app.setMouseGrabbed(true);
		app.setDisplayMode(640, 480, false);
		app.setVSync(true);
		app.setTargetFrameRate(60);
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
		Player player = new Player(7, 6, "DU", 80, 5, 5, 10, 10, 0, 1, 2, 3, 0,
				new Map("map1"));
		player.setStatpoints(3);
		currentWindow = new OverWorldWindow(input, player);

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
		if (currentWindow.getNextWindow() != null) {
			currentWindow = currentWindow.getNextWindow();
			if (currentWindow instanceof ExitWindow) {
				app.exit();
			}
		}
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
		currentWindow.render(gc, g);
	}
}

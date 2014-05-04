package inda13projekt;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.*;
import org.lwjgl.*;

/**
 * Starts the game and handles it's loop
 * @author Alex 
 *
 */
public class Game extends BasicGame{
	/**
	 * Used to create the display
	 */
	public static AppGameContainer app;
	/**
	 * Used to handle player input
	 */
	public static Input input;
	/**
	 * Used to iterate over the game objects (the player, npc:s etc)
	 */
	public static LinkedList<GameObject> objects;
	
	/**
	 * Constructor, generates the display name
	 */
	public Game() {
		super("Fick Monster");
	}
	
	/**
	 * creates the display and starts the app
	 * @param args
	 * @throws SlickException
	 */
	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		app = new AppGameContainer(new Game());
		app.setDisplayMode(640, 480, false);
		app.setVSync(true);
		app.start();
	}
	
	/** (non-Javadoc)
	 * @see org.newdawn.slick.BasicGame#init(org.newdawn.slick.GameContainer)
	 * 
	 * initialize the game
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setShowFPS(false);
		input = gc.getInput();
		objects = new LinkedList<GameObject>();
		Player player = new Player(gc);
		player.init(100, 100, 1, 1, 20, 20, null);
		objects.add(player);
	}
	
	/** (non-Javadoc)
	 * @see org.newdawn.slick.BasicGame#update(org.newdawn.slick.GameContainer, int)
	 * 
	 * updates the game, is called automatically 
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Iterator<GameObject> it = objects.iterator();
		
		while(it.hasNext()) {
			it.next().update();;
		}
	}
	
	/** (non-Javadoc)
	 * @see org.newdawn.slick.Game#render(org.newdawn.slick.GameContainer, org.newdawn.slick.Graphics)
	 * 
	 * renders the game, is called automatically
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		Iterator<GameObject> it = objects.iterator();
		
		while(it.hasNext()) {
			it.next().render(g);;
		}
	}

}

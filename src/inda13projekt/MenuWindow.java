package inda13projekt;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * The menu, the first window the user is presented. Here the user can 
 * press buttons which will change the game state
 * @author Alex
 *
 */
public class MenuWindow implements Window {
	private Image background;
	private ArrayList<GameObject> objects;
	
	final String name = "Menu";
	/**
	 * @throws SlickException 
	 * 
	 */
	public MenuWindow() throws SlickException {
		background = new Image("././res/bgi_test.bmp");
		objects = new ArrayList<GameObject>();
		objects.add(new Button(320, 128, "Start Game"));
	}
	
	/**
	 * 
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		for(GameObject object : objects) {
			object.update();
		}
	}

	/**
	 * 
	 */
	@Override
	public void render(GameContainer gc, Graphics g, Camera camera) throws SlickException {
		// TODO Auto-generated method stub
		background.draw(0, 0);
		for(GameObject object : objects) {
			object.render(g);
		}
	}

}

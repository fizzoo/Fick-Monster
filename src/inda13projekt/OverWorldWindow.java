package inda13projekt;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Manages input, logic and drawing whilst in the overworld.
 * 
 * @author Fizzo
 * 
 */
public class OverWorldWindow implements Window {
	private Map map;
	private ArrayList<GameObject> objects;
	private Input input;
	private Player player;

	/**
	 * @param input
	 */
	public OverWorldWindow(Input input) {
		map = new Map("untitled");

		player = new Player();
		player.init(2, 5, map);

		objects = new ArrayList<>();
		objects.add(player);

		this.input = input;
	}

	/**
	 * 
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP))
			player.moveUp();
		if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN))
			player.moveDown();
		if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT))
			player.moveLeft();
		if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT))
			player.moveRight();

		Iterator<GameObject> it = objects.iterator();

		while (it.hasNext()) {
			it.next().update();
		}
	}

	/**
	 * 
	 */
	@Override
	public void render(GameContainer gc, Graphics g, Camera camera)
			throws SlickException {
		map.render(0, 0);

		Iterator<GameObject> it = objects.iterator();

		while (it.hasNext()) {
			it.next().render(g);
		}
	}

	/**
	 * 
	 */
	@Override
	public Window getNextWindow() {
		// TODO Auto-generated method stub
		return null;
	}

}

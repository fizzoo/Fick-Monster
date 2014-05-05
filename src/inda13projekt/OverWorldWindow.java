package inda13projekt;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Manages input, logic and drawing whilst in the overworld.
 * 
 * @author Fizzo
 * 
 */
public class OverWorldWindow implements Window {
	private TiledMap map;
	private ArrayList<GameObject> objects;
	private Input input;
	private Player player;

	public OverWorldWindow(Input input) {
		player = new Player();
		player.init(0, 0, 1, 1, 20, 20, null);

		objects = new ArrayList<>();
		objects.add(player);

		chooseMap("untitled");
		this.input = input;
	}

	private void chooseMap(String ref) {
		try {
			map = new TiledMap("././res/" + ref + ".tmx");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if (input.isKeyDown(Input.KEY_W))
			player.moveUp();
		if (input.isKeyDown(Input.KEY_S))
			player.moveDown();
		if (input.isKeyDown(Input.KEY_A))
			player.moveLeft();
		if (input.isKeyDown(Input.KEY_D))
			player.moveRight();

		Iterator<GameObject> it = objects.iterator();

		while (it.hasNext()) {
			it.next().update();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g, Camera camera)
			throws SlickException {
		map.render(0, 0);

		Iterator<GameObject> it = objects.iterator();

		while (it.hasNext()) {
			it.next().render(g);
		}
	}

}

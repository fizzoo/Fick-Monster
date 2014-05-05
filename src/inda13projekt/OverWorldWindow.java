package inda13projekt;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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

	public OverWorldWindow() {
		chooseMap("untitled");
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
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, Graphics g, Camera camera)
			throws SlickException {
		map.render(0, 0);
	}

}

package inda13projekt;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Class managing a map and its properties.
 * 
 * @author Fizzo
 * 
 */
public class Map {
	private TiledMap map;
	private int width;
	private int height;
	private int blockLayer;
	private ArrayList<GameObject> objects;

	/**
	 * Creates a new map, initializes collision.
	 * 
	 * @param ref
	 *            Name of the mapfile
	 */
	public Map(String ref) {
		objects = new ArrayList<>();

		try {
			map = new TiledMap("././res/" + ref + ".tmx");
		} catch (SlickException e) {
			e.printStackTrace(); // Nothing else to do, wrong map is fatal since
									// the user has no choice in it.
		}

		width = map.getWidth();
		height = map.getHeight();

		blockLayer = map.getLayerIndex("Collidable");
	}

	/**
	 * @param x
	 *            coordinate to check, in tiles
	 * @param y
	 *            coordinate to check, in tiles
	 * @return true if coordinate is blocked
	 */
	public boolean isBlocked(int x, int y) {
		if (x < 0)
			return true;
		if (x >= width)
			return true;
		if (y < 0)
			return true;
		if (y >= height)
			return true;

		return (map.getTileId(x, y, blockLayer) != 0);
	}

	/**
	 * @return
	 */
	public ArrayList<GameObject> getObjects() {
		return objects;
	}

	public String getTeleported(int x, int y) {
		int teleportLayer = map.getLayerIndex("Teleportable");
		int tileID = map.getTileId(x, y, teleportLayer);
		return map.getTileProperty(tileID, "nextMap", null);
	}

	/**
	 * @param x
	 *            x location to start rendering, in pixels
	 * @param y
	 *            y location to start rendering, in pixels
	 */
	public void render(int x, int y) {
		map.render(x, y);
	}
}

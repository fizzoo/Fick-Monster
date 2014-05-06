package inda13projekt;

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
	private boolean[][] blocked;
	private int width;
	private int height;

	/**
	 * Creates a new map, initializes collision.
	 * 
	 * @param ref
	 *            Name of the mapfile
	 */
	public Map(String ref) {
		try {
			map = new TiledMap("././res/" + ref + ".tmx");
		} catch (SlickException e) {
			e.printStackTrace(); // Nothing else to do, wrong map is fatal since
									// the user has no choice in it.
		}

		width = map.getWidth();
		height = map.getHeight();

		int blockLayer = map.getLayerIndex("Collidable");
		blocked = new boolean[width][height];
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				if (map.getTileId(i, j, blockLayer) != 0) {
					blocked[i][j] = true;
				}
			}
		}
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

		return blocked[x][y];
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

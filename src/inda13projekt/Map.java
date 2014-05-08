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
	private GameObject[][] objectGrid;

	private Camera camera;

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

		camera = new Camera(width, height);

		blockLayer = map.getLayerIndex("Collidable");

		objectGrid = new GameObject[width][height];

		int enemyLayer = map.getLayerIndex("Enemies");
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int tileID = map.getTileId(x, y, enemyLayer);
				if (tileID != 0) {
					objects.add(new Enemy(x, y, map.getTileProperty(tileID,
							"name", null), Integer.parseInt(map
							.getTileProperty(tileID, "maxhp", null)), Integer
							.parseInt(map
									.getTileProperty(tileID, "speed", null)),
							Integer.parseInt(map.getTileProperty(tileID,
									"spriteOffset", null)), this));

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

		if (objectGrid[x][y] != null)
			return true;

		return (map.getTileId(x, y, blockLayer) != 0);
	}

	/**
	 * 
	 * @param oldX
	 * @param oldY
	 * @param x
	 * @param y
	 * @param object
	 */
	public void setObjectPlace(int oldX, int oldY, int x, int y,
			GameObject object) {
		objectGrid[oldX][oldY] = null;
		objectGrid[x][y] = object;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public GameObject getObjectPlace(int x, int y) {
		if (x < 0)
			return null;
		if (x >= width)
			return null;
		if (y < 0)
			return null;
		if (y >= height)
			return null;
		return objectGrid[x][y];
	}

	/**
	 * @return camera in use
	 */
	public Camera getCamera() {
		return camera;
	}

	/**
	 * @return array of objects on map
	 */
	public ArrayList<GameObject> getObjects() {
		return objects;
	}

	/**
	 * @param x
	 *            x location of player, in tiles
	 * @param y
	 *            y location of player, in tiles
	 * @return string with name of next map if tile should teleport player.
	 */
	public String[] getTeleported(int x, int y) {
		int teleportLayer = map.getLayerIndex("Teleportable");
		int tileID = map.getTileId(x, y, teleportLayer);
		if (tileID == 0)
			return null;

		String[] res = new String[3];
		res[0] = map.getTileProperty(tileID, "nextMap", null);
		res[1] = map.getTileProperty(tileID, "xCoord", null);
		res[2] = map.getTileProperty(tileID, "yCoord", null);
		return res;
	}

	/**
	 * @param x
	 *            x location to start rendering, in pixels
	 * @param y
	 *            y location to start rendering, in pixels
	 */
	public void render() {
		map.render(camera.getXOffset(), camera.getYoffset());
	}
}

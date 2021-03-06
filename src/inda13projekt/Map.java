package inda13projekt;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Class managing a map and its properties.
 * 
 * 
 */
public class Map {
	private TiledMap map;
	private int width;
	private int height;
	private int blockLayer;
	private int backgroundLayer;
	private ArrayList<GameObject> objects;
	private GameObject[][] objectGrid;
	private String name;

	private Camera camera;

	/**
	 * Creates a new map, initializes collision.
	 * 
	 * @param ref
	 *            Name of the mapfile
	 */
	public Map(String ref) {
		name = ref;

		objects = new ArrayList<>();

		try {
			map = new TiledMap("res/" + name + ".tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		width = map.getWidth();
		height = map.getHeight();

		camera = new Camera(width, height);

		blockLayer = map.getLayerIndex("Collidable");
		backgroundLayer = map.getLayerIndex("Background");

		objectGrid = new GameObject[width][height];

		int enemyLayer = map.getLayerIndex("Enemies");
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int tileID = map.getTileId(x, y, enemyLayer);
				if (tileID != 0) {
					//this looks horrible but allows us to dynamicly create objects using TILED
					objects.add(new Enemy(x, y, map.getTileProperty(tileID,
							"name", null), Integer.parseInt(map
							.getTileProperty(tileID, "maxhp", null)),
							Integer.parseInt(map.getTileProperty(tileID, "str",
									null)), Integer.parseInt(map
									.getTileProperty(tileID, "int", null)),
							Integer.parseInt(map.getTileProperty(tileID, "def",
									null)), Integer.parseInt(map
									.getTileProperty(tileID, "res", null)),
							Integer.parseInt(map.getTileProperty(tileID, "a1",
									null)), Integer.parseInt(map
									.getTileProperty(tileID, "a2", null)),
							Integer.parseInt(map.getTileProperty(tileID, "a3",
									null)), Integer.parseInt(map
									.getTileProperty(tileID, "a4", null)),
							Integer.parseInt(map.getTileProperty(tileID,
									"spriteOffset", null)), this, map
									.getTileProperty(tileID, "mes1", null), map
									.getTileProperty(tileID, "mes2", null)));

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
	 * Manages the object grid by recording where objects have moved.
	 * 
	 * @param oldX
	 *            x location, in tiles, to move from
	 * @param oldY
	 *            y location, in tiles, to move from
	 * @param x
	 *            x location, in tiles, to move to
	 * @param y
	 *            y location, in tiles, to move to
	 * @param object
	 *            object moving to said location
	 */
	public void setObjectPlace(int oldX, int oldY, int x, int y,
			GameObject object) {
		objectGrid[oldX][oldY] = null;
		objectGrid[x][y] = object;
	}

	/**
	 * @param x
	 *            x location, in tiles, to check
	 * @param y
	 *            y location, in tiles, to check
	 * @return Object on said tile, if there is one there
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
		res[1] = map.getTileProperty(tileID, "x", null);
		res[2] = map.getTileProperty(tileID, "y", null);
		return res;
	}

	/**
	 * @param x
	 *            x location to start rendering, in pixels
	 * @param y
	 *            y location to start rendering, in pixels
	 */
	public void render() {
		map.render(camera.getXOffset(), camera.getYoffset(), backgroundLayer);
		map.render(camera.getXOffset(), camera.getYoffset(), blockLayer);
	}

	/**
	 * @return name of the map, specifically map file without .tmx
	 */
	public String getName() {
		return name;
	}
}

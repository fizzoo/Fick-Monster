package inda13projekt;

/**
 * A class used to represent the player. Handles the players movements.
 * 
 * @author Alex
 * 
 */
public class Player extends GameObject {

	public Player(int gridX, int gridY, int velX, int velY, int spriteOffset,
			Map currentMap) {
		super(gridX, gridY, velX, velY, spriteOffset, currentMap);
	}

	/**
	 * @return map the player is in
	 */
	public Map getMap() {
		return currentMap;
	}

	/**
	 * @param x
	 *            coordinate to start on, next map
	 * @param y
	 *            coordinate to start on, next map
	 * @param ref
	 *            map name
	 */
	public void changeMap(int newGridX, int newGridY, String ref) {
		currentMap = new Map(ref);

		camera = currentMap.getCamera();
		gridX = newGridX;
		gridY = newGridY;
		x = gridX * 32;
		y = gridY * 32;
		isMoving = false;
	}

	/**
	 * Updates the player location
	 */
	@Override
	public void update() {
		super.update();

		String[] nextMap = currentMap.getTeleported(gridX, gridY);
		if (nextMap != null) {
			changeMap(Integer.parseInt(nextMap[1]),
					Integer.parseInt(nextMap[2]), nextMap[0]);
		}

		camera.setLocation((int) x, (int) y);
	}

}

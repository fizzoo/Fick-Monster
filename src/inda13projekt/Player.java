package inda13projekt;

/**
 * A class used to represent the player. Handles the players movements.
 * 
 * @author Alex
 * 
 */
public class Player extends GameObject {

	public Player(int gridX, int gridY, String name, int maxhp, int strength,
			int intelligence, int defense, int resistance, int spriteOffset,
			Map map) {
		super(gridX, gridY, name, maxhp, strength, intelligence, defense,
				resistance, spriteOffset, map);
	}

	private Enemy opponent;

	/**
	 * @return map the player is in
	 */
	public Map getMap() {
		return map;
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
		map = new Map(ref);

		camera = map.getCamera();
		gridX = newGridX;
		gridY = newGridY;
		x = gridX * 32;
		y = gridY * 32;
		isMoving = false;
	}

	public void talk() {
		int xOffset = getDirToX(direction);
		int yOffset = getDirToY(direction);

		GameObject tempOpponent = map.getObjectPlace(gridX + xOffset, gridY
				+ yOffset);
		if (tempOpponent instanceof Enemy)
			opponent = (Enemy) tempOpponent;
	}

	/**
	 * Updates the player location
	 */
	@Override
	public void update() {
		super.update();

		String[] nextMap = map.getTeleported(gridX, gridY);
		if (nextMap != null) {
			changeMap(Integer.parseInt(nextMap[1]),
					Integer.parseInt(nextMap[2]), nextMap[0]);
		}

		camera.setLocation((int) x, (int) y);
	}

	public Enemy getOpponent() {
		return opponent;
	}

	public void setOpponent(Enemy opponent) {
		this.opponent = opponent;
	}

}

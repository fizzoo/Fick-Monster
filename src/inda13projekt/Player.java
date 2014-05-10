package inda13projekt;

import java.io.IOException;

/**
 * A class used to represent the player. Handles the players movements.
 * 
 * @author Alex
 * 
 */
public class Player extends GameObject {

	/**
	 * 
	 * @param gridX
	 * @param gridY
	 * @param name
	 * @param maxhp
	 * @param strength
	 * @param intelligence
	 * @param defense
	 * @param resistance
	 * @param spriteOffset
	 * @param map
	 */
	public Player(int gridX, int gridY, String name, int maxhp, int strength,
			int intelligence, int defense, int resistance, int atk1, int atk2,
			int atk3, int atk4, int spriteOffset, Map map) {
		super(gridX, gridY, name, maxhp, strength, intelligence, defense,
				resistance, atk1, atk2, atk3, atk4, spriteOffset, map);
		this.atk1 = atk1;
		this.atk2 = atk2;
		this.atk3 = atk3;
		this.atk4 = atk4;
	}

	private Enemy opponent;
	private int atk1, atk2, atk3, atk4;

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

	/**
	 * 
	 */
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

	/**
	 * 
	 * @return
	 */
	public Enemy getOpponent() {
		return opponent;
	}

	/**
	 * 
	 * @param opponent
	 */
	public void setOpponent(Enemy opponent) {
		this.opponent = opponent;
	}

	/**
	 * Writes player state to file
	 * 
	 * @param stream
	 *            objectstream to use
	 * @throws IOException
	 */
	public void save(java.io.ObjectOutputStream stream) throws IOException {
		stream.writeInt(gridX);
		stream.writeInt(gridY);
		stream.writeInt(speed);
		stream.writeInt(maxhp);
		stream.writeInt(hp);
		stream.writeInt(strength);
		stream.writeInt(intelligence);
		stream.writeInt(defense);
		stream.writeInt(resistance);
		stream.writeInt(direction);
		stream.writeInt(atk1);
		stream.writeInt(atk2);
		stream.writeInt(atk3);
		stream.writeInt(atk4);

		stream.writeObject(name);
		stream.writeObject(map.getName());// STRINGOBJ

	}

	/**
	 * Reads player state from file
	 * 
	 * @param stream
	 *            objectstream to use
	 * @throws IOException
	 */
	public void load(java.io.ObjectInputStream stream) throws IOException {
		gridX = stream.readInt();
		gridY = stream.readInt();
		x = 32 * gridX;
		y = 32 * gridY;
		speed = stream.readInt();
		maxhp = stream.readInt();
		hp = stream.readInt();
		strength = stream.readInt();
		intelligence = stream.readInt();
		defense = stream.readInt();
		resistance = stream.readInt();

		direction = stream.readInt();
		sprite = spriteSheet.getSprite(direction, spriteOffset);

		atk1 = stream.readInt();
		atk2 = stream.readInt();
		atk3 = stream.readInt();
		atk4 = stream.readInt();

		attacks = new Attack[4];
		attacks[0] = new Attack(atk1, strength, intelligence);
		attacks[1] = new Attack(atk2, strength, intelligence);
		attacks[2] = new Attack(atk3, strength, intelligence);
		attacks[3] = new Attack(atk4, strength, intelligence);

		String mapname = "";
		try {
			name = (String) stream.readObject();
			mapname = (String) stream.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		map = new Map(mapname);
		this.map.setObjectPlace(gridX, gridY, gridX, gridY, this);
		camera = this.map.getCamera();

	}
}

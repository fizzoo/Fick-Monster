package inda13projekt;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * This class is the basis for every object that needs to be called in the
 * update and render methods in Game.java. This way all those objects can be
 * easily stored in one list.
 * 
 * 
 */
public abstract class GameObject {
	protected float x;
	protected float y;
	protected float width = 32;
	protected float height = 32;

	protected SpriteSheet spriteSheet;
	protected Image sprite;
	protected int spriteOffset;

	protected boolean isMoving;
	protected int direction; // W=0;S=1;A=2;D=3;
	protected Camera camera;

	protected int gridX;
	protected int gridY;
	protected int speed;
	protected Map map;

	protected String name;
	protected int maxhp;
	protected int hp;
	protected int strength;
	protected int intelligence;
	protected int defense;
	protected int resistance;

	protected Attack[] attacks;

	protected Random rand;

	/**
	 * 
	 * @param gridX	the x position of the object in the grid
	 * @param gridY the y position of the object in the grid
 	 * @param name the name of the object 
 	 * @param maxhp the maximum hitpoints of the object
 	 * @param strength the strength stat of the object 
 	 * @param intelligence the intelligence stat of the object
 	 * @param defense the defense stat of the object
 	 * @param resistance the resistance (magical) of the object
 	 * @param atk1 the attacks the object can use
 	 * @param atk2 the attacks the object can use
 	 * @param atk3 the attacks the object can use
 	 * @param atk4 the attacks the object can use
 	 * @param spriteOffset	the objects all use the same spritesheet, so this indicates the offset in that spiresheet so that it's possible to differentiate between objects
 	 * @param map	the map the object is located in
	 */
	public GameObject(int gridX, int gridY, String name, int maxhp,
			int strength, int intelligence, int defense, int resistance,
			int atk1, int atk2, int atk3, int atk4, int spriteOffset, Map map) {
		this.gridX = gridX;
		this.gridY = gridY;
		x = 32 * gridX;
		y = 32 * gridY;

		this.map = map;
		this.map.setObjectPlace(gridX, gridY, gridX, gridY, this);
		camera = this.map.getCamera();

		try {
			this.spriteSheet = new SpriteSheet("res/Gubbe.png", 32, 32);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		direction = 0;
		this.spriteOffset = spriteOffset;
		sprite = spriteSheet.getSprite(direction, spriteOffset);

		this.name = name;
		this.maxhp = maxhp;
		this.hp = this.maxhp;
		this.speed = 4;
		this.strength = strength;
		this.intelligence = intelligence;
		this.defense = defense;
		this.resistance = resistance;

		attacks = new Attack[4];
		attacks[0] = new Attack(atk1, strength, intelligence);
		attacks[1] = new Attack(atk2, strength, intelligence);
		attacks[2] = new Attack(atk3, strength, intelligence);
		attacks[3] = new Attack(atk4, strength, intelligence);

		rand = new Random();
	}

	/**
	 * @return name of object
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param attack
	 *            Attack to take damage from
	 * @return damage taken
	 */
	public int takeDamage(Attack attack) {
		int oldHP = hp;

		int dmg = attack.getNormalDamage() * (70 + rand.nextInt(50)) / 100
				- defense;
		hp -= (dmg > 0 ? dmg : 0);

		dmg = attack.getMagicDamage() * (70 + rand.nextInt(50)) / 100
				- resistance;
		hp -= (dmg > 0 ? dmg : 0);

		if (hp < 0)
			hp = 0;
		return oldHP - hp;
	}

	/**
	 * @param n
	 *            slot of the attack (0-3)
	 * @return Attack in said slot
	 */
	public Attack getAttack(int n) {
		return attacks[n];
	}

	/**
	 * @param dir
	 *            Direction, W=0;S=1;A=2;D=3;
	 * @return x offset of tile the object is looking at, +1 if east, -1 if
	 *         west, 0 otherwise
	 */
	public int getDirToX(int dir) {
		switch (dir) {
		case 2:
			return -1;
		case 3:
			return +1;
		default:
			return 0;
		}
	}

	/**
	 * @param dir
	 * @return y offset of tile the object is looking at, +1 if south, -1 if
	 *         north, 0 otherwise
	 */
	public int getDirToY(int dir) {
		switch (dir) {
		case 0:
			return -1;
		case 1:
			return +1;
		default:
			return 0;
		}
	}

	/**
	 * Tries to move in a direction, W=0;S=1;A=2;D=3;
	 * 
	 * @param dir
	 *            direction, W=0;S=1;A=2;D=3;
	 */
	public void move(int dir) {
		if (!isMoving) {
			setDir(dir);

			int x = getDirToX(dir);
			int y = getDirToY(dir);

			if (map.isBlocked(gridX + x, gridY + y))
				return;
			map.setObjectPlace(gridX, gridY, gridX + x, gridY + y, this);

			isMoving = true;
			gridX += x;
			gridY += y;
		}
	}

	/**
	 * Tries to move player up one grid, if possible.
	 */
	public void moveUp() {
		move(0);
	}

	/**
	 * Tries to move player down one grid, if possible.
	 */
	public void moveDown() {
		move(1);
	}

	/**
	 * Tries to move player left one grid, if possible.
	 */
	public void moveLeft() {
		move(2);
	}

	/**
	 * Tries to move player right one grid, if possible.
	 */
	public void moveRight() {
		move(3);
	}

	/**
	 * @param direction
	 *            to face, W=0;S=1;A=2;D=3;
	 */
	public void setDir(int direction) {
		this.direction = direction;
		sprite = spriteSheet.getSprite(direction, spriteOffset);
	}

	/**
	 * @return direction object is facing, W=0;S=1;A=2;D=3;
	 */
	public int getDir() {
		return direction;
	}

	/**
	 * @return maximum hp of object
	 */
	public int getMaxHp() {
		return maxhp;
	}

	/**
	 * @param hp
	 *            current hp to set object at
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * @return current hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * updates the objects position, ai etc.
	 */
	public void update() {
		if (y < (gridY * 32)) {
			y += speed;
		} else if (y > (gridY * 32)) {
			y -= speed;
		} else if (x < (gridX * 32)) { // else since you can't walk
										// diagonally anyway
			x += speed;
		} else if (x > (gridX * 32)) {
			x -= speed;
		} else { // finished walking
			isMoving = false;
		}
	}

	/**
	 * renders the objects sprite
	 * 
	 * @param g
	 *            Part of slick2d, used to render to the display
	 */
	public void render(Graphics g) {
		g.drawImage(sprite, x + camera.getXOffset(), y + camera.getYoffset());
	}

	/**
	 * renders the objects sprite at specified x,y position, in pixels
	 */
	public void render(Graphics g, int x, int y) {
		g.drawImage(sprite, x, y);
	}
}

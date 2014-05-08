package inda13projekt;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * This class is the basis for every object that needs to be called in the
 * update and render methods in Game.java. This way all those objects can be
 * easily stored in one list.
 * 
 * @author Alex
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

	protected Attack[] attacks;

	/**
	 * 
	 * @param gridX
	 * @param gridY
	 * @param velX
	 *            the velocity of the object when moving on the x-axis (in
	 *            pixels)
	 * @param velY
	 *            the velocity of the object when moving on the y-axis (in
	 *            pixels)
	 * @param map
	 */
	public GameObject(int gridX, int gridY, String name, int maxhp, int speed,
			int spriteOffset, Map map) {
		this.gridX = gridX;
		this.gridY = gridY;
		this.map = map;
		this.map.setObjectPlace(gridX, gridY, gridX, gridY, this);
		camera = this.map.getCamera();

		try {
			this.spriteSheet = new SpriteSheet("././res/Gubbe.png", 32, 32);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		direction = 0;
		sprite = spriteSheet.getSprite(direction, spriteOffset);

		x = 32 * gridX;
		y = 32 * gridY;

		this.spriteOffset = spriteOffset;

		this.name = name;
		this.maxhp = maxhp;
		hp = this.maxhp;
		this.speed = speed;

		attacks = new Attack[4]; // TODO:attacks, load from map
		attacks[0] = new Attack("punch");
		attacks[1] = new Attack("kick");
		attacks[2] = new Attack("poke");
		attacks[3] = new Attack("gross out");
	}

	public int takeDamage(Attack attack) {
		hp -= attack.getNormalDamage() / 2; // TODO: Add armor, stats
		return attack.getNormalDamage();
	}

	public Attack getAttack(int n) {
		return attacks[n];
	}

	/**
	 * 
	 * @param dir
	 * @return
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
	 * 
	 * @param dir
	 * @return
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
	 * 
	 * @param dir
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
	 * @return maxhp
	 */
	public int getMaxHp() {
		return maxhp;
	}

	/**
	 * @return hp
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

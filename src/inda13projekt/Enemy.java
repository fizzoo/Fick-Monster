package inda13projekt;


/**
 * 
 * @author Alex
 * 
 */
public class Enemy extends GameObject {
	private int timeToAction;
	private String normalMessage;
	private String defeatMessage;

	/**
	 * 
	 * @param gridX
	 * @param gridY
	 * @param name
	 * @param maxhp
	 * @param speed
	 * @param spriteOffset
	 * @param map
	 */
	public Enemy(int gridX, int gridY, String name, int maxhp, int strength,
			int intelligence, int defense, int resistance, int atk1, int atk2,
			int atk3, int atk4, int spriteOffset, Map map,
			String normalMessage, String defeatMessage) {

		super(gridX, gridY, name, maxhp, strength, intelligence, defense,
				resistance, atk1, atk2, atk3, atk4, spriteOffset, map);

		this.normalMessage = normalMessage;
		this.defeatMessage = defeatMessage;
		timeToAction = 0;
	}

	/**
	 * 
	 */
	@Override
	public void update() {
		if (timeToAction <= 0) {
			timeToAction = 20 + rand.nextInt(180);
			int dir = rand.nextInt(8);
			switch (dir) {
			case 0:
				moveUp();
				break;
			case 1:
				moveDown();
				break;
			case 2:
				moveLeft();
				break;
			case 3:
				moveRight();
				break;
			default:
				setDir(dir - 4);
				break;
			}
		} else {
			timeToAction--;
		}

		super.update();
	}

	/**
	 * 
	 * @return the message displayed in the message box
	 */
	public String getMessage() {
		if (hp <= 0)
			return defeatMessage;
		else
			return normalMessage;
	}
}

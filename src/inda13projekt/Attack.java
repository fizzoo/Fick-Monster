package inda13projekt;

import java.util.Scanner;

/**
 * An attack, responsible for reading the attack from a file and calculating
 * damage pre-defense
 * 
 */
public class Attack {
	private String name;
	private int normalDamage;
	private int magicDamage;

	/**
	 * Reads an attack and scales the damage.
	 * 
	 * @param attackID
	 *            Number of the attack, as row in attacks.txt
	 * @param strength
	 *            strength of attacker, used to calculate damage
	 * @param intelligence
	 *            intelligence of attacker, used to calculate damage
	 */
	public Attack(int attackID, int strength, int intelligence) {
		try (Scanner scanner = new Scanner((this.getClass().getResourceAsStream("res/attacks.txt")))){
			scanner.useDelimiter("\\n");
			for (int i = 0; i < attackID; i++) {
				scanner.next();
			}

			scanner.useDelimiter(",");
			name = scanner.next();
			int normal = scanner.nextInt();
			int strScale = scanner.nextInt();
			int magic = scanner.nextInt();
			int intScale = scanner.nextInt();

			normalDamage = normal + strScale * strength / 100;
			magicDamage = magic + intScale * intelligence / 100;
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * @return name of attack
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return normal damage to deal
	 */
	public int getNormalDamage() {
		return normalDamage;
	}

	/**
	 * @return magic damage to deal
	 */
	public int getMagicDamage() {
		return magicDamage;
	}
}

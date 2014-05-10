package inda13projekt;

import java.io.File;
import java.util.Scanner;

/**
 * @author Alex
 * 
 */
public class Attack {
	private String name;
	private int normalDamage;
	private int magicDamage;

	/**
	 * 
	 * @param attackID
	 * @param strength
	 * @param intelligence
	 */
	public Attack(int attackID, int strength, int intelligence) {
		try (Scanner scanner = new Scanner(new File("././res/attacks.txt"))) {

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
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return
	 */
	public int getNormalDamage() {
		return normalDamage;
	}

	/**
	 * 
	 * @return
	 */
	public int getMagicDamage() {
		return magicDamage;
	}
}

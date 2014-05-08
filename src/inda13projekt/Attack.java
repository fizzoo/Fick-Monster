package inda13projekt;

import java.io.File;
import java.util.Scanner;

public class Attack {
	private String name;
	private int normalDamage;
	private int magicDamage;

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
			System.err.println("attacksfile not loaded correctly");
		}
	}

	public String getName() {
		return name;
	}

	public int getNormalDamage() {
		return normalDamage;
	}

	public int getMagicDamage() {
		return magicDamage;
	}
}

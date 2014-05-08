package inda13projekt;

public class Attack {
	private String name;
	private int normalDamage;

	public Attack(String name) {
		this.name = name;
		normalDamage = 10;
	}

	public String getName() {
		return name;
	}

	public int getNormalDamage() {
		return normalDamage;
	}
}

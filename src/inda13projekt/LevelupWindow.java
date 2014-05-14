package inda13projekt;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/**
 * Window managing leveling up of the player.
 * 
 */
public class LevelupWindow implements Window {
	private Input input;
	private Player player;
	private Window nextWindow;
	private final TrueTypeFont ttf20 = new TrueTypeFont(new Font("Verdana",
			Font.PLAIN, 20), true);

	private int currentSelection;

	/**
	 * Creates a new leveling window
	 * 
	 * @param input
	 * 			  the input handler
	 * @param player
	 *            current player
	 */
	public LevelupWindow(Input input, Player player) {
		this.input = input;
		this.player = player;
		this.nextWindow = null;
		this.input.clearKeyPressedRecord();
		currentSelection = 0;
	}

	/**
	 * uppdates the position of the selection triangle and handles input
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if (player.getStatpoints() <= 0) {
			nextWindow = new OverWorldWindow(input, player);
			player.setHp(player.getHp() + player.getMaxHp() / 5);
			if (player.getHp() > player.getMaxHp()) {
				player.setHp(player.getMaxHp());
			}
			player.levelRecalc();
		}

		if (input.isKeyPressed(Input.KEY_X)
				|| input.isKeyPressed(Input.KEY_RCONTROL)) {
			switch (currentSelection) {
			case 0:
				player.incMaxhp();
				break;
			case 1:
				player.incStrength();
				break;
			case 2:
				player.incIntelligence();
				break;
			case 3:
				player.incDefense();
				break;
			case 4:
				player.incResistance();
				break;
			}

			player.setStatpoints(player.getStatpoints() - 1);
		}

		if (input.isKeyPressed(Input.KEY_D)
				|| input.isKeyPressed(Input.KEY_RIGHT)) {
			currentSelection++;
			if (currentSelection > 4)
				currentSelection = 0;
		}

		if (input.isKeyPressed(Input.KEY_A)
				|| input.isKeyPressed(Input.KEY_LEFT)) {
			currentSelection--;
			if (currentSelection < 0)
				currentSelection = 4;
		}
	}

	/**
	 * renders the text and selection triangle as well as the players current stats
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(new Image("res/triangel.png"), 40 + 120 * currentSelection,
				250);

		ttf20.drawString(230, 200, "Statpoints left: " + player.getStatpoints());

		ttf20.drawString(40, 320, "Max HP");
		ttf20.drawString(150, 320, "Strength");
		ttf20.drawString(250, 320, "Intelligence");
		ttf20.drawString(390, 320, "Defense");
		ttf20.drawString(500, 320, "Resistance");

		ttf20.drawString(60, 380, "" + player.getMaxHp());
		ttf20.drawString(180, 380, "" + player.getStrength());
		ttf20.drawString(300, 380, "" + player.getIntelligence());
		ttf20.drawString(420, 380, "" + player.getDefense());
		ttf20.drawString(540, 380, "" + player.getResistance());
	}

	/**
	 * handles window transition. 
	 */
	@Override
	public Window getNextWindow() {
		return nextWindow;
	}

}

package inda13projekt;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/**
 * Manages input, logic and drawing whilst in the battle screen.
 * 
 * @author Fizzo
 * 
 */
public class BattleWindow implements Window {
	private Input input;
	private Player player;
	private Enemy opponent;
	private Window nextWindow;
	private ArrayList<Button> buttons;
	private int currentButton;
	private Random rand;
	private int playerOldDir, opponentOldDir;
	private final TrueTypeFont ttf32 = new TrueTypeFont(new Font("Verdana",
			Font.PLAIN, 32), true);
	private final TrueTypeFont ttf20 = new TrueTypeFont(new Font("Verdana",
			Font.PLAIN, 20), true);
	private String textTop;
	private String textBottom;
	private boolean won;

	/**
	 * 
	 * @param input
	 * @param player
	 * @param opponent
	 * @throws SlickException
	 */
	public BattleWindow(Input input, Player player, Enemy opponent)
			throws SlickException {
		this.input = input;
		this.player = player;
		this.opponent = opponent;
		nextWindow = null;
		this.input.clearKeyPressedRecord();

		playerOldDir = player.getDir();
		this.player.setDir(3);
		opponentOldDir = opponent.getDir();
		this.opponent.setDir(2);

		rand = new Random();

		buttons = new ArrayList<Button>();
		buttons.add(new Button(70, 400, 128, 64, player.getAttack(0).getName(),
				0, new Image("././res/battle_button_hover.png"), new Image(
						"././res/battle_button_normal.png")));
		buttons.add(new Button(200, 400, 128, 64,
				player.getAttack(1).getName(), 1, new Image(
						"././res/battle_button_hover.png"), new Image(
						"././res/battle_button_normal.png")));
		buttons.add(new Button(330, 400, 128, 64,
				player.getAttack(2).getName(), 2, new Image(
						"././res/battle_button_hover.png"), new Image(
						"././res/battle_button_normal.png")));
		buttons.add(new Button(460, 400, 128, 64,
				player.getAttack(3).getName(), 3, new Image(
						"././res/battle_button_hover.png"), new Image(
						"././res/battle_button_normal.png")));

		// buttons.add(new Button(520, 400, 128, 64, "Run", 4, new Image(
		// "././res/battle_button_hover.png"), new Image(
		// "././res/battle_button_normal.png")));

		currentButton = 0;
		buttons.get(currentButton).setImage(true);

		textBottom = "";
		textTop = "";

		won = false;
	}

	/**
	 * 
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		handleBattleInput();
		for (Button button : buttons) {
			if (button.getID() == currentButton) {
				button.setImage(true);
			} else {
				button.setImage(false);
			}
		}

		if (opponent.getHp() <= 0) {
			won = true;
		}
		if (player.getHp() <= 0) {
			nextWindow = new GameOverWindow(input);
		}
	}

	/**
	 * 
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// bg
		g.setColor(Color.white);
		g.fillRect(0, 0, 640, 480);

		// hp
		g.setColor(Color.red);
		g.fillRect(10, 10, 300 * player.getHp() / player.getMaxHp(), 60); // hp
		g.fillRect(330, 10, 300 * opponent.getHp() / opponent.getMaxHp(), 60);

		// borders, text
		g.setColor(Color.black);
		g.drawRect(9, 9, 301, 61);
		g.drawRect(329, 9, 301, 61);

		ttf32.drawString(138, 20, "" + player.getHp(), Color.black);
		ttf32.drawString(458, 20, "" + opponent.getHp(), Color.black);

		ttf20.drawString(50, 200, textTop, Color.black);
		ttf20.drawString(50, 250, textBottom, Color.black);

		player.render(g, 144, 120);
		opponent.render(g, 464, 120);

		for (Button button : buttons) {
			button.render(g);
		}
	}

	/**
	 * handles the input of the battlewindow
	 */
	private void handleBattleInput() {
		if (won) {
			if (input.isKeyPressed(Input.KEY_X)
					|| input.isKeyPressed(Input.KEY_RCONTROL)) {
				player.setDir(playerOldDir);
				opponent.setDir(opponentOldDir);
				nextWindow = new OverWorldWindow(input, player);
			}
			return;
		}

		if (input.isKeyPressed(Input.KEY_LEFT)
				|| input.isKeyPressed(Input.KEY_A)) {
			if (currentButton > 0) {
				currentButton--;
			}
		}

		if (input.isKeyPressed(Input.KEY_RIGHT)
				|| input.isKeyPressed(Input.KEY_D)) {
			if (currentButton < buttons.size() - 1) {
				currentButton++;
			}
		}

		if (input.isKeyPressed(Input.KEY_X)
				|| input.isKeyPressed(Input.KEY_RCONTROL)) {
			if (currentButton < 4 && player.getHp() > 0 && opponent.getHp() > 0) {
				int dmg = opponent.takeDamage(player.getAttack(currentButton));
				textTop = player.getName() + " used "
						+ player.getAttack(currentButton).getName()
						+ ". Hit for " + dmg + " damage!";

				if (opponent.getHp() > 0) {
					int opponentAttack = rand.nextInt(4);
					dmg = player.takeDamage(opponent.getAttack(opponentAttack));
					textBottom = opponent.getName() + " used "
							+ opponent.getAttack(opponentAttack).getName()
							+ ". Hit for " + dmg + " damage!";
				} else {
					textBottom = "You have won! Press X / CTRL to continue.";
					won = true;
				}
			}
		}

	}

	/**
	 * Changes window when not null.
	 */
	@Override
	public Window getNextWindow() {
		return nextWindow;
	}

}

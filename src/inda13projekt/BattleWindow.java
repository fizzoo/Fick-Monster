package inda13projekt;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

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
	private Graphics red;
	private ArrayList<Button> buttons;
	private int currentButton;

	public BattleWindow(Input input, Player player, Enemy opponent)
			throws SlickException {
		this.input = input;
		this.player = player;
		this.opponent = opponent;
		nextWindow = null;
		this.input.clearKeyPressedRecord();

		red = new Graphics();
		red.setColor(new Color(255, 0, 0));

		this.player.setDir(3);
		this.opponent.setDir(2);

		buttons = new ArrayList<Button>();
		buttons.add(new Button(0, 400, 128, 64, player.getAttack(0).getName(),
				0, new Image("././res/battle_button_hover.png"), new Image(
						"././res/battle_button_normal.png")));
		buttons.add(new Button(130, 400, 128, 64,
				player.getAttack(1).getName(), 1, new Image(
						"././res/battle_button_hover.png"), new Image(
						"././res/battle_button_normal.png")));
		buttons.add(new Button(260, 400, 128, 64,
				player.getAttack(2).getName(), 2, new Image(
						"././res/battle_button_hover.png"), new Image(
						"././res/battle_button_normal.png")));
		buttons.add(new Button(390, 400, 128, 64,
				player.getAttack(3).getName(), 3, new Image(
						"././res/battle_button_hover.png"), new Image(
						"././res/battle_button_normal.png")));

		buttons.add(new Button(520, 400, 128, 64, "Run", 4, new Image(
				"././res/battle_button_hover.png"), new Image(
				"././res/battle_button_normal.png")));

		currentButton = 0;
		buttons.get(currentButton).setImage(true);
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

		if (opponent.getHp() <= 0 || player.getHp() <= 0) {
			player.setOpponent(null);
			nextWindow = new OverWorldWindow(input, player);
		}
	}

	/**
	 * 
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.fillRect(0, 0, 640, 480);

		red.fillRect(0, 0, 320 * player.getHp() / player.getMaxHp(), 100);
		g.drawString("" + player.getHp(), 160, 50);

		red.fillRect(320, 0, 320 * opponent.getHp() / opponent.getMaxHp(), 100);
		g.drawString("" + opponent.getHp(), 480, 50);

		player.render(g, 160, 120);
		opponent.render(g, 480, 120);

		for (Button button : buttons) {
			button.render(g);
		}
	}

	/**
	 * handles the input of the battlewindow
	 */
	private void handleBattleInput() {
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

		if (input.isKeyPressed(Input.KEY_X)) {
			if (currentButton < 4) {
				opponent.takeDamage(player.getAttack(currentButton));
				player.takeDamage(opponent.getAttack(currentButton));
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

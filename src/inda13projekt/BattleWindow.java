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
	private Graphics leftHp, rightHp;
	private ArrayList<Button> buttons;
	private int currentButton;

	public BattleWindow(Input input, Player player, Enemy opponent) {
		this.input = input;
		this.player = player;
		this.opponent = opponent;
		nextWindow = null;

		leftHp = new Graphics();
		leftHp.setColor(new Color(255, 0, 0));
		rightHp = new Graphics();
		rightHp.setColor(new Color(255, 0, 0));

		player.setDir(3);
		opponent.setDir(2);

	
	/**
	 * 
	 * @param input
	 * @param player
	 * @param opponent
	 */
	public BattleWindow(Input input, Player player, Enemy opponent) throws SlickException {
		this.input = input;
		input.clearKeyPressedRecord();
		buttons = new ArrayList<Button>();
		buttons.add(new Button(100, 400, 128, 64, "Attack", 0, new Image("././res/battle_button_hover.png"), new Image("././res/battle_button_normal.png")));
		buttons.add(new Button(300, 400, 128, 64, "Run", 1, new Image("././res/battle_button_hover.png"), new Image("././res/battle_button_normal.png")));
		currentButton = 0;
		buttons.get(currentButton).setImage(true);
		
		nextWindow = null;

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
	}

	/**
	 * 
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		leftHp.clear();
		rightHp.clear();

		leftHp.fillRect(0, 0, 320 * player.getHp() / player.getMaxHp(), 100);
		leftHp.drawString("" + player.getHp(), 160, 50);

		rightHp.fillRect(320, 0,
				320 + 320 * opponent.getHp() / opponent.getMaxHp(), 100);
		rightHp.drawString("" + opponent.getHp(), 480, 50);

		player.render(g, 160, 120);
		opponent.render(g, 480, 120);

		g.fillRect(0, 0, 640, 480);
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
	}
	
	/**
	 * Changes window when not null.
	 */
	@Override
	public Window getNextWindow() {
		return nextWindow;
	}

}

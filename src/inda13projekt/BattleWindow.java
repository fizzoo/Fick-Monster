package inda13projekt;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

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
	private boolean playerAttacking;
	private boolean opponentAttacking;
	private int playerx;
	private int opponentx;
	private ParticleSystem particles;
	private ConfigurableEmitter emitter;

	/**
	 * Starts a battle screen
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
				0, new Image("res/battle_button_hover.png"), new Image(
						"res/battle_button_normal.png")));
		buttons.add(new Button(200, 400, 128, 64,
				player.getAttack(1).getName(), 1, new Image(
						"res/battle_button_hover.png"), new Image(
						"res/battle_button_normal.png")));
		buttons.add(new Button(330, 400, 128, 64,
				player.getAttack(2).getName(), 2, new Image(
						"res/battle_button_hover.png"), new Image(
						"res/battle_button_normal.png")));
		buttons.add(new Button(460, 400, 128, 64,
				player.getAttack(3).getName(), 3, new Image(
						"res/battle_button_hover.png"), new Image(
						"res/battle_button_normal.png")));

		// buttons.add(new Button(520, 400, 128, 64, "Run", 4, new Image(
		// "res/battle_button_hover.png"), new Image(
		// "res/battle_button_normal.png")));

		currentButton = 0;
		buttons.get(currentButton).setImage(true);

		textBottom = "";
		textTop = "";

		won = false;

		playerx = 144;
		opponentx = 464;
		playerAttacking = false;
		opponentAttacking = false;

		try {
			particles = new ParticleSystem(new Image("res/particle.png"), 5000);
			emitter = ParticleIO.loadEmitter(new File("res/particle.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks for input and updates all objects.
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if (playerAttacking) {
			playerx += 8;
			if (playerx > opponentx - 32) {
				playerAttacking = false;
				int dmg = opponent.takeDamage(player.getAttack(currentButton));
				textTop = player.getName() + " used "
						+ player.getAttack(currentButton).getName()
						+ ". Hit for " + dmg + " damage!";

				emitter.resetState();
				emitter.setPosition(opponentx + 16, 136);
				particles.addEmitter(emitter);

				if (opponent.getHp() > 0) {
					opponentAttacking = true;
				} else {
					textBottom = "You have won! Press X / CTRL to continue.";
					won = true;
				}
			}
		} else if (playerx > 144) {
			playerx -= 8;
		} else if (opponentAttacking) {
			opponentx -= 8;
			if (opponentx < playerx + 32) {
				opponentAttacking = false;
				int opponentAttack = rand.nextInt(4);
				int dmg = player.takeDamage(opponent.getAttack(opponentAttack));
				textBottom = opponent.getName() + " used "
						+ opponent.getAttack(opponentAttack).getName()
						+ ". Hit for " + dmg + " damage!";

				emitter.resetState();
				emitter.setPosition(playerx + 16, 136);
				particles.addEmitter(emitter);
			}
		} else if (opponentx < 464) {
			opponentx += 8;
		} else { // Freeze stuff while someones attacking
			handleBattleInput();
			for (Button button : buttons) {
				if (button.getID() == currentButton) {
					button.setImage(true);
				} else {
					button.setImage(false);
				}
			}

			if (player.getHp() <= 0) {
				nextWindow = new GameOverWindow(input);
			}
		}
		this.input.clearKeyPressedRecord();
		particles.update(delta);
	}

	/**
	 * Renders all the objects in the window
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

		player.render(g, playerx, 120);
		opponent.render(g, opponentx, 120);

		for (Button button : buttons) {
			button.render(g);
		}

		particles.render();
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

				player.setDefeated(opponent.getName());
				player.setStatpoints(3);
				nextWindow = new LevelupWindow(input, player);

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
				playerAttacking = true;
			}
		}

	}

	/**
	 * Handles window transition
	 */
	@Override
	public Window getNextWindow() {
		return nextWindow;
	}

}

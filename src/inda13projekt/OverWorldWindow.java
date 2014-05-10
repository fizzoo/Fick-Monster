package inda13projekt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Manages input, logic and drawing whilst in the overworld.
 * 
 * @author Fizzo
 * 
 */
public class OverWorldWindow implements Window {
	private Input input;
	private Player player;
	private Window nextWindow;
	private MessageBox messageBox;

	/**
	 * Creates a new map and places the player on it.
	 * 
	 * @param input
	 *            User input
	 */
	public OverWorldWindow(Input input, Player player) {
		this.input = input;
		this.player = player;
		this.input.clearKeyPressedRecord();
		nextWindow = null;
	}

	/**
	 * Checks for input and updates all objects.
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if (player.getOpponent() == null) {
			if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP))
				player.moveUp();
			if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN))
				player.moveDown();
			if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT))
				player.moveLeft();
			if (input.isKeyDown(Input.KEY_D)
					|| input.isKeyDown(Input.KEY_RIGHT))
				player.moveRight();
			if (input.isKeyDown(Input.KEY_X)
					|| input.isKeyDown(Input.KEY_RCONTROL))
				player.talk();
			if (input.isKeyDown(Input.KEY_ESCAPE))
				nextWindow = new MenuWindow(input, player);

			if (input.isKeyDown(Input.KEY_F5)) {
				save();
			}
			if (input.isKeyDown(Input.KEY_F9)) {
				load();
			}

			Iterator<GameObject> it = player.getMap().getObjects().iterator();

			while (it.hasNext()) {
				it.next().update();
			}
			player.update();
		}

		Enemy opponent = player.getOpponent();

		if (opponent != null && opponent.getHp() > 0 && messageBox == null) {
			messageBox = new MessageBox(opponent.getName(),
					opponent.getMessage(), 5);
		} else if (opponent != null && opponent.getHp() > 0
				&& messageBox != null && !messageBox.getDone()) {
			messageBox.update();
		} else if (opponent != null && opponent.getHp() > 0
				&& messageBox != null && messageBox.getDone()) {
			nextWindow = new BattleWindow(input, player, opponent);
			messageBox = null;
		} else if (opponent != null && opponent.getHp() <= 0
				&& messageBox == null) {
			messageBox = new MessageBox(opponent.getName(),
					opponent.getMessage(), 5);
		} else if (opponent != null && opponent.getHp() <= 0
				&& messageBox != null && !messageBox.getDone()) {
			messageBox.update();
		} else if (opponent != null && opponent.getHp() <= 0
				&& messageBox != null && messageBox.getDone()) {
			player.setOpponent(null);
			messageBox = null;
		}

	}

	/**
	 * Renders map and all objects.
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		player.getMap().render();

		Iterator<GameObject> it = player.getMap().getObjects().iterator();

		while (it.hasNext()) {
			it.next().render(g);
		}
		player.render(g);
		if (messageBox != null)
			messageBox.render(g);
	}

	/**
	 * Changes window when not null.
	 */
	@Override
	public Window getNextWindow() {
		return nextWindow;
	}

	public void save() {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("././res/save"))) {
			player.save(out);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public void load() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				"././res/save"))) {
			player.load(in);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}

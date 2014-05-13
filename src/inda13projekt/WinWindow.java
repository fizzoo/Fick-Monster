package inda13projekt;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/**
 * 
 */
public class WinWindow implements Window {
	Input input;
	Window nextWindow;
	Image background;

	private final TrueTypeFont ttf42 = new TrueTypeFont(new Font("Verdana",
			Font.PLAIN, 42), true);

	/**
	 *
	 */
	public WinWindow(Input input) throws SlickException {
		this.input = input;
		this.input.clearKeyPressedRecord();
		background = new Image("res/very_win.bmp");
		nextWindow = null;
		this.input.clearKeyPressedRecord();
	}

	/**
	 * 
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if (input.isKeyPressed(Input.KEY_X)
				|| input.isKeyPressed(Input.KEY_RCONTROL)) {
			nextWindow = new MenuWindow(input, new Player(7, 6, "DU", 80, 5, 5,
					10, 10, 0, 1, 2, 3, 0, new Map("map1")));
		}
	}

	/**
	 * 
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.fillRect(0, 0, 640, 480);
		g.drawImage(background, 0, 0);
		g.setColor(Color.black);
		ttf42.drawString(400, 300, "wow", Color.green);
		ttf42.drawString(60, 70, "such win", Color.red);
		ttf42.drawString(370, 170, "very skill", Color.yellow);
		ttf42.drawString(100, 250, "much play", Color.blue);

		ttf42.drawString(5, 420, "Press X or CTRL to start over!", Color.black);
	}

	/**
	 * 
	 */
	@Override
	public Window getNextWindow() {
		return nextWindow;
	}

}

package inda13projekt;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

/**
 * A button used to navigate the menu.
 * 
 * @author Alex
 * 
 */
public class Button {

	private String text;
	private Image hover;
	private Image normal;
	private Image sprite;
	private int buttonID;
	private int x;
	private int y;
	private int width;
	private int height;
	private final TrueTypeFont ttf20 = new TrueTypeFont(new Font("Verdana",
			Font.PLAIN, 20), true);

	/**
  * 
  */
	public Button(final int x, final int y, final int width, final int height,
			final String text, final int buttonID, Image hover, Image normal) {
		this.hover = hover;
		this.normal = normal;
		this.text = text;
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * renders the button
	 */
	public void render(Graphics g) {
		if (sprite != null) {
			g.drawImage(sprite, x - width / 2, y - height / 2);
		}
		ttf20.drawString(x - 45, y - 10, text, Color.black);
	}

	/**
  * 
  */
	public void setImage(final boolean hovered) {
		if (hovered) {
			sprite = hover;
		} else {
			sprite = normal;
		}
	}

	/**
	 * 
	 * @return the id of the button
	 */
	public int getID() {
		return buttonID;
	}
}
package inda13projekt;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * A button used to navigate the menu. 
 * @author Alex
 *
 */
public class Button extends GameObject {
	
	private String text;
	private Image hover;
	private Image normal;
	
	/**
	 * 
	 */
	public Button(final int x, final int y, final String text) {
		try {
			normal = new Image("././res/button_normal.bmp", new Color(255, 0, 255));
			hover = new Image("././res/button_hover.bmp", new Color(255, 0, 255));
			super.init(x, y, 0, 0, 256, 128, normal);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.text = text;
	}
	/**
	 * renders the button
	 */
	@Override
	public void render(Graphics g) {
		if (sprite != null) {
			g.drawImage(sprite, x - width/2, y - height/2);
		}
		g.drawString(text, x - 45, y - 10);
	}
}

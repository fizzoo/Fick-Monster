package inda13projekt;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

/**
 * 
 * @author Alex
 *
 */
public class MessageBox{
	private String name;
	private String message;
	private int i;
	private int lines;
	private int pos;
	private int delay;
	private boolean done;
	
	private final String messageFull;
	private final int x = 320;
	private final int y = 240;
	private final int width = 256;
	private final int height = 128;
	private final int lineLength = 20;
	
	private final TrueTypeFont ttf20 = new TrueTypeFont(new Font("Verdana",
			Font.PLAIN, 20), true);
	
	/**
	 * 
	 * @param name	the name of the messenger
	 * @param message the message
	 * @apram delay the delay between every cahracter (in frames)
	 */
	public MessageBox(String name, String message, int delay) {
		this.name = name;
		this.messageFull = message;
		this.delay = delay;
		i = 0;
		pos = 0;
		done = false;
		this.message = "";
		lines = 1;
	}
	
	/**
	 * 
	 */
	public void update() {
		i++;
		if(pos >= messageFull.length()) {
			if (i >= delay * 5) {
				done = true;
			}
		}
		else if(i >= delay) {
			i = 0;
			message += (messageFull.substring(pos, pos + 1));
			pos++;
			if(pos % lineLength == 0)
				lines++;
		}
	}
	
	/**
	 * 
	 * @param g graphics from slick2d
	 */
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(x - width / 2, y - height / 2, width, height);
		g.setColor(Color.black);
		ttf20.drawString(x + 10 - width / 2, y + 10 - height / 2, name + ":", Color.black);
		for(int j = 0; j < lines; j++) {
			if(j == lines - 1) 
				ttf20.drawString(x + 10 - width / 2, y + 33 + (23 * j) - height / 2, message.substring(j * lineLength), Color.black);
			else
				ttf20.drawString(x + 10 - width / 2, y + 33 + (23 * j) - height / 2, message.substring(j * lineLength, (j + 1) * lineLength), Color.black);
		}
	}
	
	/**
	 * 
	 * @return the status of the message box
	 */
	public boolean getDone() {
		return done;
	}
}

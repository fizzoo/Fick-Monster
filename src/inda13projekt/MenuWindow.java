package inda13projekt;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The menu, the first window the user is presented. Here the user can 
 * press buttons which will change the game state
 * @author Alex
 *
 */
public class MenuWindow implements Window {
	private Image background;
	private ArrayList<Button> buttons;
	private Input input;
	private int currentButton;
	private Window nextWindow;
	
	final String name = "Menu";
	/**
	 * inits the window and it's buttons as well as the windows background image
	 * @throws SlickException 
	 * 
	 */
	public MenuWindow(Input input) throws SlickException {
		background = new Image("././res/bgi_test.bmp");
		buttons = new ArrayList<Button>();
		buttons.add(new Button(320, 128, "Start Game", 0));
		buttons.add(new Button(320, 320, "Exit  Game", 1));
		currentButton = 0;
		buttons.get(currentButton).setImage(true);
		this.input = input;
		nextWindow = null;
	}
	
	/**
	 * updates the window and checks for which button is currently selected. 
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		for(Button button : buttons) {
			button.update();
			if(button.getID() == currentButton) {
				button.setImage(true);
			}
			else {
				button.setImage(false);
			}
		}
		handleMenuInput();
	}

	/**
	 * draws the windows buttons
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		background.draw(0, 0);
		for(Button button : buttons) {
			button.render(g);
		}
	}
	
	/**
	 * handles input for the menu window. You can select the different buttons and press them to 
	 * either move to another screen or exit the game. 
	 */
	private void handleMenuInput() {
		if(input.isKeyPressed(Input.KEY_UP)) {
			if(currentButton > 0) {
				currentButton--;
			}
		}
		
		if(input.isKeyPressed(Input.KEY_DOWN)) {
			if(currentButton < buttons.size() - 1) {
				currentButton++;
			}
		}
		
		if(input.isKeyPressed(Input.KEY_X)) {
			switch(currentButton){
			case 0: nextWindow = new OverWorldWindow(input);
					break;
			case 1: nextWindow = new ExitWindow();
					break;
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public Window getNextWindow() {
		// TODO Auto-generated method stub
		return nextWindow;
	}
}

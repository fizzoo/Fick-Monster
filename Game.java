package inda13projekt;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.*;
import org.lwjgl.*;

public class Game extends BasicGame{
	public static AppGameContainer app;
	public static Input input;
	public static LinkedList<GameObject> objects;
	
	public Game() {
		super("Fick Monster");
	}
	
	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		app = new AppGameContainer(new Game());
		app.setDisplayMode(640, 480, false);
		app.setVSync(true);
		app.start();
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setShowFPS(false);
		input = gc.getInput();
		objects = new LinkedList<GameObject>();
		Player player = new Player(gc);
		player.init(100, 100, 1, 1, 20, 20, null);
		objects.add(player);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Iterator<GameObject> it = objects.iterator();
		
		while(it.hasNext()) {
			it.next().update();;
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		Iterator<GameObject> it = objects.iterator();
		
		while(it.hasNext()) {
			it.next().render(g);;
		}
	}

}

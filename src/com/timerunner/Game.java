package com.timerunner;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.timerunner.states.*;
 
/**
 * Classe principale.
 *
 * @author ASTIER Naji & ROBIN Yohann
 */
public class Game extends StateBasedGame
{ 
	/** The Constant WIDTH. */
    public final static int WIDTH = 800;
    /** The Constant HEIGHT. */
    public final static int HEIGHT = 600;
    /** The debug. */
    public static boolean DEBUG = false;
    /** The container holding the game. */
    public static AppGameContainer container;
 
	/**
	 * Nouvelle fenêtre.
	 */
	public Game() {
		super("Time Runner");
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.StateBasedGame#initStatesList(org.newdawn.slick.GameContainer)
	 */
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException 
	{
		addState(new StartMenuState());
		addState(new GameState());
		addState(new PauseMenuState());
	}
 
	/**
	 * Programme principal.
	 *
	 * @param argv The arguments passed into our test
	 */
	public static void main(String[] argv) 
	{
		Config.init();
		try 
		{
			container = new AppGameContainer(new Game());
			container.setDisplayMode(WIDTH, HEIGHT,false);
			container.setTargetFrameRate(60);
			container.setMinimumLogicUpdateInterval(20);
			container.setVSync(true);
			container.setShowFPS(DEBUG);
			container.start();
		} catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
}
package com.timerunner;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.timerunner.states.*;
 
/**
 * Classe principale
 * 
 * @author ASTIER Naji & ROBIN Yohann
 */
public class Game extends StateBasedGame
{ 
	/** The container holding the game */
    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;
    public static boolean DEBUG = true;
    public static AppGameContainer container;
 
	/**
	 * Nouvelle fenêtre
	 * @return 
	 */
	public Game() {
		super("Time Runner");
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException 
	{
		addState(new StartMenuState());
		addState(new GameState());
		addState(new PauseMenuState());
	}
 
	/**
	 * Programme principal
	 * 
	 * @param argv The arguments passed into our test
 	 */
	public static void main(String[] argv) 
	{
		try 
		{
			container = new AppGameContainer(new Game());
			container.setDisplayMode(WIDTH, HEIGHT,false);
			container.setTargetFrameRate(60);
			container.setMinimumLogicUpdateInterval(20);
			container.setVSync(true);
			if (DEBUG)
			{
				container.setShowFPS(true);
			}
			container.start();
		} catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
}
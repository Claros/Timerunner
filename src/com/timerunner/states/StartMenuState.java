package com.timerunner.states;

import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class StartMenuState extends BasicGameState implements ComponentListener 
{
	public static final int ID = 0;
	private StateBasedGame game;
	private GameContainer container;
	
	private MouseOverArea quit;
	private MouseOverArea play;

	@Override
	public void componentActivated(AbstractComponent source) 
	{
		if (source == quit) 
		{
			this.container.exit();
		}
		if (source == play) 
		{
			try {
				game.getState(GameState.ID).init(container, game);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			game.enterState(GameState.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException 
	{
		container = arg0;
		game = arg1;
		quit = new MouseOverArea(container,new Image("pics/quitter.png"), 287, 450,this);
		quit.setNormalColor(new Color(0.7f,0.7f,0.7f,1f));
		quit.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));

		play = new MouseOverArea(container,new Image("pics/jouer.png"), 310, 190, this);
		play.setNormalColor(new Color(0.7f,0.7f,0.7f,1f));
		play.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		quit.render(container, g);
		play.render(container, g);
		g.drawString("Time Runner", 350, 100);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException 
	{
		
	}

	@Override
	public int getID() 
	{
		return ID;
	}
}

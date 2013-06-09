package com.timerunner.states;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import org.newdawn.slick.GameContainer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import com.timerunner.Game;

/**
 * The Class PauseMenuState.
 */
public class PauseMenuState extends GlobalState implements ComponentListener 
{
	public static final int ID = 2;
	private GameContainer container;
	
	private MouseOverArea quit;
	private MouseOverArea play;
	private MouseOverArea restart;

	/* (non-Javadoc)
	 * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
	 */
	@Override
	public void componentActivated(AbstractComponent source) 
	{
		if (source == quit) 
		{
			this.container.exit();
		}
		if (source == play) 
		{
			//game.enterState(GameState.ID);
			resumeState(new FadeOutTransition(new Color (0.2f, 0.2f, 0.2f, 0.5f)), new FadeInTransition(new Color (0.2f, 0.2f, 0.2f, 0.5f))); 
		}
		if (source == restart) 
		{
			game.enterState(StartMenuState.ID);
		}
	}

	/* (non-Javadoc)
	 * @see com.timerunner.states.GlobalState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException 
	{
		super.init(arg0, arg1);
		container = arg0;
		quit = new MouseOverArea(container,new Image("pics/quitter.png"), 287, 450,this);
		quit.setNormalColor(new Color(0.7f,0.7f,0.7f,1f));
		quit.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));

		play = new MouseOverArea(container,new Image("pics/reprendre.png"), 245, 190, this);
		play.setNormalColor(new Color(0.7f,0.7f,0.7f,1f));
		play.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));

		restart = new MouseOverArea(container,new Image("pics/retour.png"), 290, 290, this);
		restart.setNormalColor(new Color(0.7f,0.7f,0.7f,1f));
		restart.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		g.drawImage(GameState.getScreen(), 0, 0);
		
		Rectangle rect = new Rectangle (0, 0, Game.WIDTH, Game.HEIGHT);
	    g.setColor(new Color (0.2f, 0.2f, 0.2f, 0.5f));
	    g.fill(rect);
	    
		quit.render(container, g);
		play.render(container, g);
		restart.render(container, g);
		
		g.setColor(Color.white);
		g.drawString("   Pause   ", 350, 100);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException 
	{
		
	}

	/* (non-Javadoc)
	 * @see com.timerunner.states.GlobalState#getID()
	 */
	@Override
	public int getID() 
	{
		return ID;
	}

	/* (non-Javadoc)
	 * @see com.timerunner.states.GlobalState#getGraphics()
	 */
	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}
}

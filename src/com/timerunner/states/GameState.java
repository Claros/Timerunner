package com.timerunner.states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import com.timerunner.Camera;
import com.timerunner.Entity;
import com.timerunner.Game;
import com.timerunner.Map;
import com.timerunner.Player;
import com.timerunner.Character;

/**
 * The Class GameState.
 */
public class GameState extends GlobalState
{
	public static final int ID = 1;
    
    private int mapHeight, mapWidth;    
    private Player player;
    private Camera camera;
	private Sound shoot;
    private Map map;
    private Graphics renderGraphics;
    private static ArrayList<Entity> characters;

	/* (non-Javadoc)
	 * @see com.timerunner.states.GlobalState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		super.init(container, game);
		this.shoot = new Sound("res/shoot.wav");
		// Spawn : 50, 3100
		player = new Player(1875, 1415, 32, 48, "pics/vx_chara02_c.png", 6, 4);
		characters = new ArrayList<Entity>();
		characters.add(player);
		
		characters.add(new Character(1875, 1515, 32, 48, "pics/vx_chara01_a.png", 0, 0));
		characters.add(new Character(1875, 1215, 32, 48, "pics/vx_chara01_a.png", 3, 0));
		characters.add(new Character(1875, 1315, 32, 48, "pics/vx_chara01_a.png", 0, 4));
		characters.add(new Character(1700, 1250, 32, 48, "pics/vx_chara01_b.png", 0, 4));
		characters.add(new Character(1700, 1300, 32, 48, "pics/vx_chara01_b.png", 3, 0));
		characters.add(new Character(1700, 1350, 32, 48, "pics/vx_chara01_b.png", 3, 4));
		characters.add(new Character(1900, 1200, 32, 48, "pics/vx_chara02_a.png", 6, 4));
		characters.add(new Character(1800, 1100, 32, 48, "pics/vx_chara02_b.png", 6, 4));
		characters.add(new Character(1850, 1200, 32, 48, "pics/vx_chara02_d.png", 6, 4));
		characters.add(new Character(1835, 1100, 32, 48, "pics/vx_chara03_a.png", 6, 4));
		characters.add(new Character(1925, 1300, 32, 48, "pics/vx_chara03_b.png", 6, 4));
		characters.add(new Character(1775, 1200, 32, 48, "pics/vx_chara03_c.png", 6, 4));
		characters.add(new Character(1750, 1300, 32, 48, "pics/vx_chara03_d.png", 3, 0));
		characters.add(new Character(1725, 1100, 32, 48, "pics/vx_chara03_e.png", 6, 4));
		characters.add(new Character(1700, 1200, 32, 48, "pics/vx_chara03_f.png", 6, 4));
		characters.add(new Character(1875, 1300, 32, 48, "pics/vx_chara03_g.png", 6, 4));
		
		map = new Map("res/map_moyen_age_exterieur.tmx");
		mapWidth = map.getWidth() * map.getTileWidth(); // Map size = Tile Size * number of Tiles
		mapHeight = map.getHeight() * map.getTileHeight();
		camera = new Camera(map, mapWidth, mapHeight);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		renderGraphics = g;
		camera.translate(g, player);
		for (int i = 0; i < map.getLayerCount()-1; i++)
		{
			map.render(0, 0, i);
		}
		
		for (Entity chara : characters)
		{
			chara.render();
		}
		
		map.render(0, 0, map.getLayerCount()-1);
    	
		/*
		 * Si on est en mode DEBUG, on affiche les rectangles pour les collisions,
		 * les FPS ainsi que les coordonnées du perso.
		 */
		if (Game.DEBUG)
		{
			for (Entity chara : characters)
			{
				g.draw(chara.getBox());
			}
			map.drawRect(g);
			g.drawString("Xp : " + player.getX() + ", Yp : " + player.getY(), 10 - camera.getTransX(), 500 - camera.getTransY());
			g.drawString("Xp : " + Math.round(player.getX()/32) + ", Yp : " + Math.round(player.getY()/32), 10 - camera.getTransX(), 550 -  camera.getTransY());
		}
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		ArrayList<Entity> chars = GameState.getCharacters();
		for (Entity chara : chars)
		{
			chara.update(container, mapWidth, mapHeight, delta, map);
		}
	}

	/* (non-Javadoc)
	 * @see com.timerunner.states.GlobalState#getID()
	 */
	@Override
	public int getID() 
	{
		return GameState.ID;
	}

	/**
	 * Gets the shoot sound.
	 *
	 * @return the shoot sound
	 */
	public Sound getShoot() 
	{
		return shoot;
	}
	
	/* (non-Javadoc)
	 * @see com.timerunner.states.GlobalState#getGraphics()
	 */
	@Override
	public Graphics getGraphics()
	{
		return this.renderGraphics;
	}

	/**
	 * @return the characters
	 */
	public static ArrayList<Entity> getCharacters() 
	{
		return characters;
	}
}

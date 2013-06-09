package com.timerunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The Enum Config.
 */
public enum Config 
{
	KEY_RIGHT("right", 205), 
	KEY_LEFT("left", 203),
	KEY_UP("up", 200), 
	KEY_DOWN("down", 208),
	KEY_TALK("talk", 46),
	KEY_SHOOT("shoot", 57),
	// Spawn : 50, 3100
	POS_X("posx", 50),
	POS_Y("posy", 3100),
	CURRENT_MAP("cmap", 0);
	
	private String property;
	private int value;
	private static Properties prop = new Properties();
	
	/**
	 * Instantiates a new config.
	 *
	 * @param pProperty the property
	 * @param pValue the value
	 */
	Config(final String pProperty, final int pValue)
	{
		this.value = pValue;
		this.property = pProperty;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Gets the property.
	 *
	 * @return the property
	 */
	public String getProperty()
	{
		return this.property;
	}
	
	/**
	 * Write file.
	 */
	public static void writeFile()
	{
		try 
		{
			// Si on ne trouve pas le fichier, on le créé
			prop.setProperty("right", "" + Config.KEY_RIGHT.getValue());
			prop.setProperty("left", "" + Config.KEY_LEFT.getValue());
			prop.setProperty("up", "" + Config.KEY_UP.getValue());
			prop.setProperty("down", "" + Config.KEY_DOWN.getValue());
			prop.setProperty("talk", "" + Config.KEY_TALK.getValue());
			prop.setProperty("shoot", "" + Config.KEY_SHOOT.getValue());
			prop.setProperty("posx", "" + Config.POS_X.getValue());
			prop.setProperty("posy", "" + Config.POS_Y.getValue());
			prop.setProperty("cmap", "" + Config.CURRENT_MAP.getValue());
			
			prop.store(new FileOutputStream("./config.properties"), null);
		} catch (IOException e)
		{
			
		}
	}
	
	/**
	 * Inits the configuration.
	 */
	public static void init()
	{
		try 
		{
			prop.load(new FileInputStream("./config.properties"));
			boolean rewrite = false;
			for (final Config config : values())
			{
				if (prop.getProperty(config.getProperty()) != null)
				{
					config.setValue( Integer.parseInt( prop.getProperty(config.getProperty()) ) );
				}
				else
				{
					rewrite = true;
				}
			}
			if (rewrite)
			{
				Config.writeFile();
			}
		} catch (FileNotFoundException ex) 
		{
			 Config.writeFile();
	    } catch (IOException pe)
	    {
	    	// On ne touche pas à la config
	    }
	}

}

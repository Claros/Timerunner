package zuul.timerunner.pkg_commands;

/**
 * Representations for all the valid command words for the game.
 * 
 * @author Michael Kolling and David J. Barnes
 * @author  ASTIER Naji & ROBIN Yohann
 * @version 25/03/2013
 */
public enum CommandWord
{
    // A value for each command word, plus one for unrecognised
    // commands.
    /** The go command. */
    GO ("go", new GoCommand()), 
    /** The quit command. */
    QUIT("quit", new QuitCommand()), 
    /** The help command. */
    HELP("help", new HelpCommand()), 
    /** The look command. */
    LOOK ("look", new LookCommand()), 
    /** The eat command. */
    EAT("eat", new EatCommand()), 
    /** The back command. */
    BACK("back", new BackCommand()),
    /** The test command. */
    TEST("test", new TestCommand()), 
    /** The take command. */
    TAKE("take", new TakeCommand()), 
    /** The drop command. */
    DROP("drop", new DropCommand()),
    /** The alea command. */
    ALEA("alea", new AleaCommand()),
    /** The unknown command. */
    UNKNOWN ("?", null), 
    /** The load command. */
    LOAD("load", new TeleportCommand()), 
    /** The teleport command. */
    TELEPORT("teleport", new TeleportCommand()), 
    /** The items command. */
    ITEMS("items", new ItemsCommand()),
    /** The give command. */
    GIVE("give", new GiveCommand()); 
    
    /** The a command string. */
    private String aCommandString;
    /** The a command. */
    private Command aCommand;
    
    /**
     * Initialise with the corresponding command word.
     *
     * @param pCommandString the command string
     * @param pCommand the command
     */
    CommandWord(final String pCommandString, final Command pCommand)
    {
        this.aCommandString = pCommandString;
        this.aCommand = pCommand;
    }
    
    /**
     * To string.
     *
     * @return The command word as a string.
     */
    public String toString()
    {
        return this.aCommandString;
    }

    /**
     * Gets the command.
     *
     * @return the command
     */
    public Command getCommand()
    {
        return this.aCommand;
    }

}
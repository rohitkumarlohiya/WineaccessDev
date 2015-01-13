package com.wineaccess.cmd;



/**
 * Command Handler interface for handling one specific type of command.
 */
public interface CommandHandler {
    /**
     * Makes a <code>CommandHandler</code> self-describing. o that each
     * different implementation of Command Handler may be queried for the *
     * <cite>command</cite> that it handles.
     * 
     * @return a String that much be matched with -e argument for this handler
     *         to be invoked. It must be one of the commands mentioned in
     *         <code>CIGcmdConstants</code>
     * @see #CIGcmdConstants
     */
    public String getCommandHandled();

    /**
     * Handles the <cite>command</cite>. For which this
     * <code>CommandHandler</code> has been implemented, using the
     * corresponding <cite>JPD Proxy</cite>
     * 
     * @param cliHandler -
     *            a <code>CommonsCLIHandler</code> object containing parsed,
     *            verified and ready to use values from command line
     */
    public void handleCommand(String [] args) throws Exception;
}

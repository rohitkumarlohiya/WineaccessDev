package com.wineaccess.cmd;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.cmd.handler.DecryptTextHandler;
import com.wineaccess.cmd.handler.EncryptTextHandler;


public class WACmd {
	
	
	private static Log logger = LogFactory.getLog(WACmd.class);
	
	/**
	 * List of all Handlers that implement CommandHandler interface.
	 * <p>
	 * New functionality can be automatically added to <code>CIGcmd</code> by
	 * writing a class that implements <code>CommandHandler</code>, and then
	 * adding the Handler's classname to <code>commandHandlers[]</code> array
	 * below
	 */
	static CommandHandler[] commandHandlers = {new EncryptTextHandler(), new DecryptTextHandler()};

	static Properties properties;

	/**
	 * Executes the command specified on the command lime.
	 * <p>
	 * Calls the matching command handler found in
	 * <code>commandHandlers[]</code>. Prints error if no matching handler
	 * found.
	 * 
	 * @param argv [] -
	 *            command line argeuments
	 */
	private void run(String[] argv) {
		boolean commandFound = false;
		for (int i = 0; i < commandHandlers.length; i++) {
			if (argv[0].equals(commandHandlers[i].getCommandHandled())) {
				commandFound = true;
				try {
					commandHandlers[i].handleCommand(argv);
				} catch (Exception e) {
					logger.error(e.getMessage() + "\n\n");
					logger.error(e);
				}
				break;
			}
		}
		if (!commandFound) {
			System.err.println(CIDPcmdConstants.INVALID_COMMAND_ERROR);
		}
		return;
	}

	/**
	 * main method for invoking cigcmd command line.
	 * 
	 * @param argv
	 *            the command line arguments passed to cigcmd command
	 */
	public static void main(String[] argv) {
		WACmd cigcmd = new WACmd();
		cigcmd.run(argv);
	}
}

package com.wineaccess.cmd;

/**
 * Useful constants used by <code>CIDPcmd</code>.
 * <ul>
 * <li>List of Commands supported by <code>CIDPcmd</code></li>
 * <li>default configuration file name</li>
 * <li>error message text</li>
 * </ul>
 */
public class CIDPcmdConstants {
	
	public static final String ENCRYPT_COMMAND = "encrypt";
	
	public static final String DECRYPT_COMMAND = "decrypt";

	/** CIDPcmd_COMMAND_LINE_NAME.*/
	public static final String CIDP_COMMAND_LINE_NAME = "cidpcmd";

	/** CIDPcmdCMD_BASEDIR. */
	public static final String CIDPCMD_BASEDIR = "CIDPCMD_HOME";

	/** DEFAULT_CONFIG_FILE_NAME.*/
	public static final String DEFAULT_CONFIG_FILE_NAME = "cidpcmd.conf";

	/** COMMAND_NAME_PROPS.*/
	public static final String COMMAND_NAME_PROPS = "cidp.commandline.command";

	/** UPDATE_COMMAND_NAME_PROPS. */
	public static final String UPDATE_COMMAND_NAME_PROPS = "cidp.commandline.command.update";

	/** XML_FILE_NAME_PROPS.*/
	public static final String XML_FILE_NAME_PROPS = "cidp.commandline.xmlfile";

	/** NO_COMMAND_FOUND_ERROR.*/
	public static final String NO_COMMAND_FOUND_ERROR = "No command to execute.\n"
			+ "A command must be specified either on the command line (--execute) "
			+ "or via a property called \""
			+ COMMAND_NAME_PROPS
			+ "\" in the configuration file.\n"
			+ "Specify --help for Usage Help.";

	/** INVALID_COMMAND_ERROR.*/
	public static final String INVALID_COMMAND_ERROR = "Invalid command to execute.\n"
			+ "A command must be a valid command, as specified in userguide.txt";

	
	public static final String PUBLISH_COMMAND = "publish";
	
	
	/** RELOADCACHE_COMMAND.*/
	public static final String RELOADCACHE_COMMAND = "reloadcache";

	/** Command for definition.*/
	public static final String PROCESS_DEFINITON = "definition";

	/** Command for retrieval.*/

	/** Command for adding.*/
	public static final String ADD_COMMAND = "add";
	
	public static final String CH_DEPLOY_COMMAND = "copperheadDeploy";
	
	public static final String CREATE_COMMAND = "create";
	
	public static final String SUSPEND = "suspend";
	
	public static final String UNDEPLOY = "undeploy";
	
	public static final String UNSUSPEND = "unsuspend";
	
	public static final String MIGRATE = "migrate";
	
	public static final String LIST_INVOCATION_MODES = "listInvocationModes";
	
	public static final String RESET_COMMAND = "reset";
	
	public static final String TERMINATE_COMMAND = "terminate";
	
	/** String DELETE_COMMAND = "delete".*/
	public static final String DELETE_COMMAND = "delete";
	
	/** Command to invoke task.*/
	public static final String TASK_COMMAND = "invoke";

	/** Command for updation.*/
	public static final String UPDATE_COMMAND = "update";

	/** Command for hotdeploy.*/
	public static final String HOTDEPLOY_COMMAND = "hotdeploy";
	
	public static final String TEST_HOTDEPLOY_COMMAND = "testHotdeploy";
	
	public static final String SWITCH_INVOCATION_MODE = "switchInvocationMode";
	
	public static final String RESET_INVOCATION_MODE = "resetInvocationMode";

	/** Command for reload.*/
	public static final String RELOAD_COMMAND = "reload";
	
	/** Command for allocate schema to client.*/
	public static final String ALLOCATE_SCHEMA_COMMAND ="allocateschema";
	
	/** Command for generating an encrypted account id.*/
	public static final String GEN_ACCID_COMMAND ="genaccid";

	/** Command for submitting result xml. Helpful for testing anonymous inbounds etc. */
	public static final String SUBMIT_RESULT ="submitresult";

}

package com.wineaccess.cmd.handler;

import com.wineaccess.cmd.CIDPcmdConstants;
import com.wineaccess.cmd.CommandHandler;
import com.wineaccess.crypto.util.CryptoUtil;

public class EncryptTextHandler implements CommandHandler{

	@Override
	public String getCommandHandled() {
		return CIDPcmdConstants.ENCRYPT_COMMAND;
	}

	@Override
	public void handleCommand(String [] args) throws Exception {
		System.out.println("The Encrypted Value is -> " + CryptoUtil.encrypt(args[1]));
	}
}

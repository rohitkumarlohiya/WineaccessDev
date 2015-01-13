package com.wineaccess.cmd.handler;

import com.wineaccess.cmd.CIDPcmdConstants;
import com.wineaccess.cmd.CommandHandler;
import com.wineaccess.crypto.util.CryptoUtil;

public class DecryptTextHandler implements CommandHandler{

	@Override
	public String getCommandHandled() {
		return CIDPcmdConstants.DECRYPT_COMMAND;
	}

	@Override
	public void handleCommand(String[] args) throws Exception {
		System.out.println("The Decrypted Value is -> " + CryptoUtil.decrypt(args[1]));
	}
}

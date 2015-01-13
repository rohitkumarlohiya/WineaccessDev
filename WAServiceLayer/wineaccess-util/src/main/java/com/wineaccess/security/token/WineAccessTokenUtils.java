package com.wineaccess.security.token;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class WineAccessTokenUtils implements TokenUtils {

	static Map<String, WineAccessUserDetails> tokens = new ConcurrentHashMap<String, WineAccessUserDetails>();

	@Override
	public synchronized String getToken(WineAccessUserDetails userDetails)  {
		
		try {
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

			// generate a random number
			String randomNum = new Integer(prng.nextInt()).toString();

			//Change for JIRA WA-781 on 21-07-2014 
			String token=  DigestUtils.shaHex(randomNum);
			tokens.put(token, userDetails);
			
			/*// get its digest
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] result = sha.digest(randomNum.getBytes());

			String token=  hexEncode(result);
			tokens.put(token, userDetails);*/
			
			return token;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String hexEncode(byte[] aInput) {
		StringBuilder result = new StringBuilder();
		char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		for (int idx = 0; idx < aInput.length; ++idx) {
			byte b = aInput[idx];
			result.append(digits[(b & 0xf0) >> 4]);
			result.append(digits[b & 0x0f]);
		}
		return result.toString();
	}

	@Override
	public boolean validate(String token) {
		return tokens.containsKey(token);
	}

	@Override
	public WineAccessUserDetails getUserFromToken(String token) {
		return tokens.get(token);
	}

	@Override
	public void removerToken(String token) {
		tokens.remove(token);
	}

	@Override
	public void refreshLastAccessTimestamp(String token) {
		tokens.get(token).setLastAccessTime(System.currentTimeMillis());
	}

	@Override
	public List<String> markTokenExpired(long expirationTime) {
		List<String> tokensRemoved = new ArrayList<String>();
		for (String token : tokens.keySet()) {
			if ((System.currentTimeMillis() - tokens.get(token).getLastAccessTime()) > expirationTime) {
				removerToken(token);
				tokensRemoved.add(token);
			}
		}
		return tokensRemoved;
	}
	
	public void logoutUser(String userName) {
		for (WineAccessUserDetails wineAccessUserDetails : tokens.values()) {
			if (wineAccessUserDetails.getUserName().equals(userName)){
				removerToken(wineAccessUserDetails.getToken());
				break;
			}
		}
	}

	@Override
	public void recoverTokens(WineAccessUserDetails userDetails) {
		tokens.put(userDetails.getToken(), userDetails);
	}
}

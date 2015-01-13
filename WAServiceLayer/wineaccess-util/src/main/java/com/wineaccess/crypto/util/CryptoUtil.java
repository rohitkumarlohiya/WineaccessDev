package com.wineaccess.crypto.util;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Encryption and Decryption of String data; PBE(Password Based Encryption and Decryption)
 */
public class CryptoUtil {
	
	private static final String SECRET_KEY = "123456";
	private static final String ENCODING = "8859_1";
	private static final int iterationCount = 19;
	
    // 8-byte Salt
    static byte[] salt = {
        (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
        (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
    };
    // Iteration count
    
    private CryptoUtil() { 
    }
    
    /**
     * 
     * @param secretKey Key used to encrypt data
     * @param plainText Text input to be encrypted
     * @return Returns encrypted text
     * 
     */
    public static String encrypt(String plainText) throws Exception {
        //Key generation for enc and desc
    	
        KeySpec keySpec = new PBEKeySpec(SECRET_KEY.toCharArray(), salt, iterationCount);
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
         // Prepare the parameter to the ciphers
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

        //Enc process
        Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
        ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        
        //String charSet="UTF-8";  
        
        byte[] in = plainText.getBytes(ENCODING);
        byte[] out = ecipher.doFinal(in);
        return new sun.misc.BASE64Encoder().encode(out);
    }
     /**     
     * @param secretKey Key used to decrypt data
     * @param encryptedText encrypted text input to decrypt
     * @return Returns plain text after decryption
     */
    public static String decrypt(String encryptedText) throws Exception {
         //Key generation for enc and desc
        KeySpec keySpec = new PBEKeySpec(SECRET_KEY.toCharArray(), salt, iterationCount);
        
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
         // Prepare the parameter to the ciphers
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
        //Decryption process; same key will be used for decr
        Cipher dcipher=Cipher.getInstance(key.getAlgorithm());
        dcipher.init(Cipher.DECRYPT_MODE, key,paramSpec);
        byte[] enc = new sun.misc.BASE64Decoder().decodeBuffer(encryptedText);
        byte[] decriptedValue = dcipher.doFinal(enc);
        
        //String charSet="UTF-8";     
        return  new String(decriptedValue, ENCODING);
    }    
    
    public static void main(String [] args) throws Exception {
    	System.out.println(CryptoUtil.encrypt("passw0rd#"));
    }
}

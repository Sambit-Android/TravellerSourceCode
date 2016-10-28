//Ravi Sajjan

package com.bcits.utility;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.util.PropertyResourceBundle;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Encryption_MrLocator {

	Cipher ecipher;
	Cipher dcipher;
	SecretKey key = null;
	KeyGenerator desKeyGen;     
	PropertyResourceBundle propBundle;
	protected static String KEYGEN_STR = "";

	public Encryption_MrLocator() 
	{
		KEYGEN_STR = "723934574823439249230423042340234";
	}

	private Key getKey()
	{
		SecretKey sKey = null;
		try
		{
			byte[] bytes = getbytes(KEYGEN_STR);
			DESKeySpec pass = new DESKeySpec(bytes); 
			SecretKeyFactory sKeyFactory = SecretKeyFactory.getInstance("DES");
			sKey= sKeyFactory.generateSecret(pass);
			return sKey;
		}
		catch(Exception ex)
		{
			BCITSLogger.logger.info("in getting key:"+ex);
		}
		return sKey;
	}

	private byte[] getbytes(String str)
	{
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

		StringTokenizer sTokenizer = new StringTokenizer(str, "-", false);
		while(sTokenizer.hasMoreTokens())
		{
			try
			{
				byteOutputStream.write(sTokenizer.nextToken().getBytes());
			}
			catch(IOException ex)
			{
			}
		}

		byteOutputStream.toByteArray();
		return byteOutputStream.toByteArray();
	}


	public String encrypt(String sourceStr) 
	{        
		try
		{
			Key key = getKey();
			ecipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			ecipher.init(Cipher.ENCRYPT_MODE, key);            

			byte[] enc = ecipher.doFinal((new String(sourceStr)).getBytes("UTF-8"));
			return new sun.misc.BASE64Encoder().encode(enc);            

		}
		catch(Exception ex)
		{
			return null;
			//CobraUtils.writeLogFile("in password encrypt:"+ex);
		}
		//return null;
	}


	public String decrypt(String sourceStr) 
	{
		try
		{
			// Get secret key
			Key key = getKey();

			dcipher = Cipher.getInstance("DES/ECB/PKCS5Padding");                      
			dcipher.init(Cipher.DECRYPT_MODE, key);            

			// Decode base64 to get bytes
			byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(sourceStr);

			//Decrypt data in a single step

			byte[] utf8 = dcipher.doFinal(dec);

			// Decode using utf-8
			return new String(utf8, "UTF-8");
		}
		catch(Exception ex)
		{
			BCITSLogger.logger.info("error in decrypt"+ex);
			ex.printStackTrace();
		}
		return null;
	}    

}



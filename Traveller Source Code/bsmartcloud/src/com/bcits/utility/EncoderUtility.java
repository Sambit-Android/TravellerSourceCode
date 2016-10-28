package com.bcits.utility;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class EncoderUtility {

	 public String getHashPassword(String password)
	    {
		  ShaPasswordEncoder sha = new ShaPasswordEncoder(256);		
		  String hash = sha.encodePassword(password, null);
		 
		  return hash;
		 }
}

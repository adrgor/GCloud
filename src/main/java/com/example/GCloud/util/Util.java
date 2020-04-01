package com.example.GCloud.util;

import java.util.regex.Pattern;

public class Util {
	public static boolean isValidEmail(String email) {
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
		Pattern pattern = Pattern.compile(emailRegex);
		if(email == null) {
			return false;
		}
		return pattern.matcher(email).matches();
	}
}

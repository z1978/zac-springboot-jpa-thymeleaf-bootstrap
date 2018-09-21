package com.zac.spring.configuration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Encrypt {
	
	public static String encryptMD5(String rawPassword) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		md.update(rawPassword.getBytes());
		
		byte[] hashBytes = md.digest();
		
		int[] hashInts = new int[hashBytes.length];
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i < hashBytes.length; i++) {
			hashInts[i] = (int)hashBytes[i] & 0xff;
			if (hashInts[i] <= 15) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(hashInts[i]));
		}
		
		return sb.toString();
	}
	
	public static String encryptBCrypt(String rawPassword) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(rawPassword);
	}
	
	public static boolean verifyBcrypt(String plaintext, String hash) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(plaintext, hash);
	}

}

//public boolean isValidPassword(AccountMst accountMst,
//   String loginPassword) throws NoSuchAlgorithmException {
// // BCrypt値によるチェック
// if (Encrypt.verifyBcrypt(loginPassword, accountMst.password)) {
//   return true;
// }
// // MD5値によるチェック
// String Md5Password = Encrypt.encryptMD5(loginPassword);
// return StringUtils.equals(accountMst.loginPassword, Md5Password);
//}

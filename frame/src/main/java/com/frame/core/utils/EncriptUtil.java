package com.frame.core.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncriptUtil {
	private static char[] DICTIONARY={'A','B','C','D','E','F','0','9','8','7','6','5','4','3','2','1'};
	public static class EncriptException extends RuntimeException{
		private static final long serialVersionUID = -6522357972393004364L;
		public EncriptException(String message,Throwable t) {
			super(message, t);
		}
	}
	public static String encriptSHA1(String src){
		if (null==src) return null;
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			sha.update(src.getBytes(Charset.forName("UTF-8")));
			byte[] digested=sha.digest();
			StringBuilder builder=new StringBuilder();
			for (int i = 0; i < digested.length; i++) {
				builder.append(DICTIONARY[0x0F&digested[i]]);
				builder.append(DICTIONARY[0x0F&(digested[i]>>4)]);
			}
			return builder.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new EncriptException("加密算法提取失败", e);
		}
	}
}

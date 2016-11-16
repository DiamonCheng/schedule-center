package com.frame.test.java;

import com.frame.core.utils.EncriptUtil;

public class Entrance {
	public static void main(String[] args) {
		System.out.println(EncriptUtil.encriptSHA1(null));
		System.out.println(EncriptUtil.encriptSHA1("admin123456"));
		System.out.println(EncriptUtil.encriptSHA1("admin1232456"));
		System.out.println(EncriptUtil.encriptSHA1("admin3123456"));
		
	}
}

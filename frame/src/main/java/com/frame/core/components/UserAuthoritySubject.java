package com.frame.core.components;

import java.util.Set;

import javax.servlet.http.HttpSession;

public class UserAuthoritySubject {
	private static final String ACCOUNT_SUBJECY_KEY=UserAuthoritySubject.class.getName()+".ACCOUNT_SUBJECY_KEY";
	private static final String AUTHORITY_SUBJECY_KEY=UserAuthoritySubject.class.getName()+".AUTHORITY_SUBJECY_KEY";
	public static HttpSession getSession(){
		return (HttpSession)ThreadBinder.get(ThreadBinder.SESSION);
	}
	@SuppressWarnings("unchecked")
	public static <T> T getAccountSubject(){
		return (T) getSession().getAttribute(ACCOUNT_SUBJECY_KEY);
	}
	public static <T> void setAccountSubject(T subject){
		getSession().setAttribute(ACCOUNT_SUBJECY_KEY, subject);
	}
	@SuppressWarnings("rawtypes")
	public static void setAuthorities(Set authorities){
		getSession().setAttribute(AUTHORITY_SUBJECY_KEY, authorities);
	}
	@SuppressWarnings("rawtypes")
	public static <T> boolean isContainAuthority(T authority){
		Set authorities=(Set) getSession().getAttribute(AUTHORITY_SUBJECY_KEY);
		return authorities==null?false:authorities.contains(authority);
	}
	public static boolean isUserVerify(){
		return getAccountSubject()!=null;
	}
}

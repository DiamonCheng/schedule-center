package com.frame.core.components;

import javax.servlet.http.HttpSession;

public class UserAuthoritySubject {
	private static final String ACCOUNT_SUBJECT_KEY=UserAuthoritySubject.class.getName()+".ACCOUNT_SUBJECT_KEY";
	private static final String AUTHORITY_SUBJECT_KEY=UserAuthoritySubject.class.getName()+".AUTHORITY_SUBJECT_KEY";
	private static final String USER_MENUS_KEY=UserAuthoritySubject.class.getName()+".USER_MENUS_KEY";
	public static HttpSession getSession(){
		return (HttpSession)ThreadBinder.get(ThreadBinder.SESSION);
	}
	@SuppressWarnings("unchecked")
	public static <T> T getAccountSubject(){
		return (T) getSession().getAttribute(ACCOUNT_SUBJECT_KEY);
	}
	public static <T> void setAccountSubject(T subject){
		getSession().setAttribute(ACCOUNT_SUBJECT_KEY, subject);
	}
	public static void clear(){
		getSession().removeAttribute(ACCOUNT_SUBJECT_KEY);
	}
	/*@SuppressWarnings("rawtypes")
	public static void setAuthorities(Set authorities){
		getSession().setAttribute(AUTHORITY_SUBJECY_KEY, authorities);
	}
	@SuppressWarnings("rawtypes")
	public static <T> boolean isContainAuthority(T authority){
		Set authorities=(Set) getSession().getAttribute(AUTHORITY_SUBJECY_KEY);
		return authorities==null?false:authorities.contains(authority);
	}*/
	public static boolean isUserVerify(){
		return getAccountSubject()!=null;
	}
}

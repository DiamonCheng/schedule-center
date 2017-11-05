package com.frame.core.utils;

import com.frame.core.components.ThreadBinder;
import com.frame.core.components.UserAuthoritySubject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Defferson.Cheng on 2017/1/6.
 */
public class HttpContextUtil extends UserAuthoritySubject {
    public static HttpServletRequest getCurrentRequest(){
        return (HttpServletRequest) ThreadBinder.get(ThreadBinder.REQUEST);
    }
    public static HttpServletResponse getCurrentResponse(){
        return (HttpServletResponse) ThreadBinder.get(ThreadBinder.RESPONSE);
    }
    public static boolean isAjaxRequest(){
        HttpServletRequest request=getCurrentRequest();
        return request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equals("XMLHttpRequest");
    }

}

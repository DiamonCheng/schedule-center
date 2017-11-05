package com.frame.core.webapp.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Descriptions...
 * <p>Created by Defferson.Cheng on 2017/11/4.
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    private static final String DEFAULT_ERROR_VIEW = "error";
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", e);
        mav.addObject("URL", request.getRequestURL());
        mav.addObject("status",response.getStatus());
        mav.addObject("timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}

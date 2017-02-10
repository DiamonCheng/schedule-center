package com.frame.core.utils;

import com.sun.istack.internal.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.lang.reflect.Method;

/**
 * Created by Defferson.Cheng on 2017/1/8.
 *
 */
public class ReflectUtil {
    public static class ReflectException extends RuntimeException{
        private  static final long serialVersionUID = -233333333333333L;
        ReflectException(Throwable t){super(t);}
    }
    public static <T> T setValueByField( @NotNull T target, @NotNull String field, @Nullable Object value){
        String setMethodName="set"+upperCaseFirst(field);
        try {
            Method setMethod=null;
            Method[] methods=target.getClass().getMethods();
            for(Method m:methods){
                if (m.getName().equals(setMethodName)){
                    setMethod=m;
                    break;
                }
            }
            if (setMethod==null) throw new NoSuchMethodException(target.getClass().getName()+"."+setMethodName);
            setMethod.invoke(target,value);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
        return target;
    }
    @SuppressWarnings("unchecked")
    public static <T> T getValueByField( @NotNull Object target, @NotNull String field){
        try {
            Method getMethod=getMethodByName(target.getClass(),"get"+upperCaseFirst(field));
            return (T) getMethod.invoke(target);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
    private static String upperCaseFirst(String s){
        return Character.toUpperCase(s.charAt(0))+s.substring(1);
    }
    public static Method getMethodByName(Class<?> cls,String methodName){
        try {
            return cls.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            throw new ReflectException(e);
        }
    }
    public static Class<?> resolveFieldClass(Class<?> cls,String field) {
        String methodName="get"+upperCaseFirst(field);
        return getMethodByName(cls,methodName).getReturnType();
    }
}

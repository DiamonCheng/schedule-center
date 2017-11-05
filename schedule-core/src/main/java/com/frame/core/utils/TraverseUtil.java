package com.frame.core.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;

public class TraverseUtil {
	public static interface  TraverseCallBack<T> {
		public void run(T current);
	}
	public static <T> void traverse(T root,String childrenFieldName,TraverseCallBack<T> callBack){
		callBack.run(root);
		Iterable<T> list=ReflectUtil.getValueByField(root, childrenFieldName);
		if (list!=null)
		for (T t : list) {
			traverse(t,childrenFieldName,callBack);
		}
	}
	public static interface  TraverseCopyCallBack<A,B> {
		public void afterCopy(A currentSource, B currentTarget);
	}
	public static <A,B> void traverseCopy(
			A sourceRoot,B targetRoot,
			String childrenFieldName1,String childrenFieldName2,
			TraverseCopyCallBack<A,B> callback){
		BeanUtils.copyProperties(sourceRoot, targetRoot);
		Collection<A> list=ReflectUtil.getValueByField(sourceRoot, childrenFieldName1);
		try {
			Collection targetList=new ArrayList();
			ReflectUtil.setValueByField(targetRoot, childrenFieldName2, targetList);
			callback.afterCopy(sourceRoot, targetRoot);
			if (targetList!=null) for (A t : list) {
				B target=(B) targetRoot.getClass().newInstance();
				targetList.add(target);
				traverseCopy(t, target, childrenFieldName1, childrenFieldName2, callback);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

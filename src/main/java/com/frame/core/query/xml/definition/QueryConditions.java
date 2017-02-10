package com.frame.core.query.xml.definition;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import com.frame.core.components.ThreadBinder;
import org.springframework.beans.BeanUtils;

import com.frame.core.components.GsonFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServletRequest;

public class QueryConditions {
	private String paramString;
	private static final Gson gson=GsonFactory.buildDefaultGson();
	private List<QueryCondition> conditions;
	private int page=1;
	private int pageSize=10;
	private List<SortEntry> sortEntries=new ArrayList<SortEntry>();

	/**
	 * 将字符串类型的查询参数转换成为结构化数据
	 * @return this
	 */
	public QueryConditions parseFromParamString(){
		if (paramString!=null){
			if (((HttpServletRequest)ThreadBinder.get(ThreadBinder.REQUEST)).getMethod().equalsIgnoreCase("get"))try {
				paramString= URLDecoder.decode(paramString,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
			QueryConditions qcp=gson.fromJson(paramString, new TypeToken<QueryConditions>(){}.getType());
			BeanUtils.copyProperties(qcp, this,"paramString");
		}
		return this;
	}
	public List<QueryCondition> getConditions() {
		return conditions;
	}
	public void setConditions(List<QueryCondition> conditions) {
		this.conditions = conditions;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getParamString() {
		return paramString;
	}
	public void setParamString(String paramString) {
		this.paramString = paramString;
	}

	public List<SortEntry> getSortEntries() {
		return sortEntries;
	}

	public void setSortEntries(List<SortEntry> sortEntries) {
		this.sortEntries = sortEntries;
	}
}

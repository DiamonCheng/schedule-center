package com.frame.model.dto.media;

import java.util.List;

import com.frame.model.vo.media.ClassDynamicVO;

/** 
* @author wowson
* @version create time：2017年3月30日 下午7:33:01 
* 类说明 ：
*/
public class ClassDynamicDTO {
	private Long page =1l;
	private Long pageSize=10l;
	private Long totalPage;
	private Long totalCount;
	private List<ClassDynamicVO> list ;
	public Long getPage() {
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public List<ClassDynamicVO> getList() {
		return list;
	}
	public void setList(List<ClassDynamicVO> list) {
		this.list = list;
	}
	
}

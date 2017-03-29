package com.frame.model.dto.media;

import java.util.List;

import com.frame.entity.media.VideoEntity;
import com.frame.entity.media.VideoUploadEntity;

/** 
* @author wowson
* @version create time：2017年3月28日 下午2:09:05 
* 类说明 ：
*/
public class VideoUploadDTO {
	private Long page = 1l;
	private Long pageSize = 10l;
	private Long totalPage;
	private Long totalCount;
	private List<VideoUploadEntity> list;

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

	public List<VideoUploadEntity> getList() {
		return list;
	}

	public void setList(List<VideoUploadEntity> list) {
		this.list = list;
	}
}

package com.frame.model.dto.media;

import java.util.List;

import com.frame.model.vo.media.MessageVO;

/** 
* @author wowson
* @version create time：2017年3月21日 下午11:19:08 
* 类说明 ：
*/
public class MessageDTO {
	private Long page =1l;
	private Long pageSize=10l;
	private Long totalPage;
	private Long totalCount;
	private Long albumId;
	private List<MessageVO> list;
	public Long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}
	public List<MessageVO> getList() {
		return list;
	}
	public void setList(List<MessageVO> list) {
		this.list = list;
	}
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
}

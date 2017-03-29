package com.frame.service.front;

import com.frame.model.dto.media.VideoDTO;
import com.frame.model.dto.media.VideoUploadDTO;

/** 
* @author wowson
* @version create time：2017年3月24日 上午10:21:30 
* 类说明 ：
*/
public interface FrontVideoService {
	public void selectVideo(VideoDTO dto);
	public void selectVideoUpload(VideoUploadDTO dto);
}

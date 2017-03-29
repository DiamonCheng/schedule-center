package com.frame.webapp.controller.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frame.model.dto.media.VideoDTO;
import com.frame.model.dto.media.VideoUploadDTO;
import com.frame.service.front.FrontVideoService;
import com.frame.utils.VideoPathConfig;

/** 
* @author wowson
* @version create time：2017年3月24日 上午10:40:25 
* 类说明 ：
*/
@Controller
@RequestMapping("/front/video")
public class FrontVideoController {
	@Autowired
	FrontVideoService service;
	@RequestMapping("/outlist")
	public  Object index(VideoDTO dto,HttpServletRequest request){
		service.selectVideo(dto);
		return  new ModelAndView("/video/video-list").addObject("video",dto).addObject("nav", "外链视频");
	}
	@RequestMapping("/uplist")
	public  Object index(VideoUploadDTO dto,HttpServletRequest request){
		String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		service.selectVideoUpload(dto);
		if(dto.getList().size()>0){
			for(int i=0;  i<dto.getList().size();i++){
				dto.getList().get(i).setSavePath(basepath+VideoPathConfig.GET_VIDEO_URL);
			}
		}
		return  new ModelAndView("/video/video-list").addObject("video", dto).addObject("nav", "自传视频");
	}
	
}

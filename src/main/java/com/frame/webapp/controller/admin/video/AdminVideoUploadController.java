package com.frame.webapp.controller.admin.video;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.frame.core.components.AjaxResult;
import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.entity.UserEntity;
import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.entity.media.VideoUploadEntity;
import com.frame.service.admin.VideoService;
import com.frame.utils.FileUtils;
import com.frame.utils.VideoPathConfig;

/**
 * @author wowson
 * @version create time：2017年3月27日 上午9:37:46 类说明 ：
 */
@Controller
@RequestMapping("/admin/video/uploads")
@PageDefinition("adminVideoUploadDefinetion.xml")
public class AdminVideoUploadController extends GeneralController<VideoUploadEntity> {
	
	@Autowired
	VideoService service;
	public boolean beforeUpdate(VideoUploadEntity entity) {
		if (entity.getId() == null) {
			entity.setCreateUserKey(UserAuthoritySubject.<UserEntity>getAccountSubject().getId());
		}
		return true;
	}

	public boolean beforeDelete(VideoUploadEntity entity) {
		System.out.println(UserAuthoritySubject.<UserEntity>getAccountSubject().getId());
		if(!FileUtils.isEmpty(VideoPathConfig.SAVE_VIDEO_DIR)){
			if(FileUtils.deleteLocalFile(VideoPathConfig.SAVE_VIDEO_DIR+"/"+entity.getVideoNewName())){
				return true;
			}
		}
		return false;
	}

	@RequestMapping("/target")
	@ResponseBody
	public Object uploadVideo(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws IOException {
		AjaxResult result = new AjaxResult();
		Map<String, Object> data = new HashMap<String, Object>();
		if (file != null) { // 判断文件是否为空
			String path = null; // 路径
			String type = null; // 文件类型
			String fileName = file.getOriginalFilename(); // 原文件名
			System.out.println("原文件名：" + file.getOriginalFilename());
			type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {
				if ("MP4".equals(type.toUpperCase())) {
					String newFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
					FileUtils.saveFile(newFileName, file,type);
					data.put("fileName", fileName);
					data.put("newFileName", newFileName);
				} else {
					System.out.println("非mp4格式！");
					result.setCode("-1");
					result.setMessage("非mp4格式！");
				}
			} else {
				System.out.println("不能识别的文件类型！");
				result.setCode("-1");
				result.setMessage("不能识别的文件类型！");
			}
		} else {
			System.out.println("文件上传失败！");
			result.setCode("-1");
			result.setMessage("文件上传失败！");
		}
		result.setData(data);
		
		return result;
	}
	/**
	 * 
	 * @param request
	 * @param videoName  原文件名
	 * @param videoNewName  新文件名
	 * @return
	 */
	@RequestMapping("/addInfo")
	@ResponseBody
	public Object addImgInfo(HttpServletRequest request, String videoName,String videoNewName,String description) {
		AjaxResult result = new AjaxResult();
		UserEntity user = (UserEntity) UserAuthoritySubject.getAccountSubject();// 当前登录用户
		//图片服务器地址  没有 = = ， 默认服务器地址
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		if (!StringUtils.isEmpty(videoName) && !StringUtils.isEmpty(videoNewName)) {
			VideoUploadEntity entity = new VideoUploadEntity();
				entity.setCreateUserKey(user.getId());
				entity.setVideoName(videoName);
				entity.setHttpLink(VideoPathConfig.GET_VIDEO_URL);  //保存访问地址
				entity.setType("1");
				entity.setVideoNewName(videoNewName);
				entity.setDescription(description);
				service.save(entity);
		} else {
			result.setData("");
		}

		return result;
	}

}

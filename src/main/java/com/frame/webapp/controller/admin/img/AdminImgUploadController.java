package com.frame.webapp.controller.admin.img;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.frame.core.components.AjaxResult;
import com.frame.core.components.UserAuthoritySubject;
import com.frame.core.entity.UserEntity;
import com.frame.core.query.xml.GeneralController;
import com.frame.core.query.xml.annoation.PageDefinition;
import com.frame.entity.media.ImgEntity;
import com.frame.service.admin.AdminImgService;
import com.frame.utils.ImgDirConfig;
import com.frame.utils.FileUtils;

/**
 * @author wowson
 * @version create time：2017年3月13日 下午4:34:54 类说明 ： 图片 控制类 图片查询图片显示问题和图片上传问题
 * 
 */
@Controller
@RequestMapping("/admin/img/imgUpload")
@PageDefinition("adminImgUploadDefinetion.xml")
public class AdminImgUploadController extends GeneralController<ImgEntity> {
	@RequestMapping("/target")
	@ResponseBody
	public Object uploadImg(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
		AjaxResult result = new AjaxResult();
		Map<String, Object> data = new HashMap<String, Object>();
		if (file != null) { // 判断文件是否为空
			String path = null; // 路径
			String type = null; // 文件类型
			String fileName = file.getOriginalFilename(); // 原文件名
			System.out.println("图片文件名：" + file.getOriginalFilename());
			// 判断文件类型
			type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
					// String basePath = request.getScheme() + "://" +
					// request.getServerName() + ":" + request.getServerPort();
					String newFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
					FileUtils.saveFile(newFileName, file,type );
					// String imgUrl = ImgDirConfig.GET_PIC_URL + newFileName;
					// System.out.println(imgUrl);
					data.put("newFileName", newFileName);
					data.put("fileName", fileName);
					// path = ImgDirConfig.SAVE_PIC_DIR + newFileName;
					// file.transferTo(new File(path));
				} else {
					System.out.println("非正常的图片格式！");
					result.setCode("-1");
					result.setMessage("非正常的图片格式！");
				}
			} else {
				System.out.println("文件类型为空");
				result.setMessage("文件类型为空！");
				result.setCode("-1");
			}
		} else {
			System.out.println("上传文件为空");
			result.setMessage("上传文件为空！");
			result.setCode("-1");
		}
		result.setData(data);
		return result;
	}

	@Autowired
	AdminImgService imgService;

	// 图片信息保存
	@RequestMapping("/addInfo")
	@ResponseBody
	public Object addImgInfo(HttpServletRequest request, Long imgGroupKey, String imgName,String imgOriginalName) {
		AjaxResult result = new AjaxResult();
		UserEntity user = (UserEntity) UserAuthoritySubject.getAccountSubject();// 当前登录用户
		String[] imgNames = null;
		String[] imgOriginalNames = null;
		//图片服务器地址  没有 = = ， 默认服务器地址
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		if (!StringUtils.isEmpty(imgName) && !StringUtils.isEmpty(imgGroupKey)) {
			imgNames = FileUtils.splitStr("\\|", imgName);
			ImgEntity[] entity = new ImgEntity[imgNames.length];
			for (int i = 0; i < imgNames.length; i++) {
				entity[i] = new ImgEntity();
 				entity[i].setImgName(imgNames[i]);
				entity[i].setSavePath(ImgDirConfig.GET_PIC_URL);
				entity[i].setImgGroupKey(imgGroupKey);
				entity[i].setSrcName(basePath);
				entity[i].setCreateUserKey(user.getId());
			}
			imgOriginalNames = FileUtils.splitStr("\\|", imgOriginalName);
			for (int i = 0; i < imgOriginalNames.length; i++) {
				entity[i].setImgOriginalName(imgOriginalNames[i]);
				imgService.addImgInfo(entity[i]);
			}
		} else {
			result.setData("");
		}

		return result;
	}

	@RequestMapping("/deleteInfo")
	@ResponseBody
	public Object deleteInfoImg(HttpServletRequest request) {
		AjaxResult result = new AjaxResult();
		String id = request.getParameter("id");
		if (StringUtils.isEmpty(id)) {
			result.setCode("-1");
			result.setMessage("id为空！");
		} else {
			ImgEntity entity = new ImgEntity();
			entity.setId(Long.parseLong(id));
			if (imgService.deleteImgInfo(entity))
				result.setMessage("图片删除成功！");
			else {
				result.setMessage("图片删除失败！");
			}
		}
		return result;
	}
}

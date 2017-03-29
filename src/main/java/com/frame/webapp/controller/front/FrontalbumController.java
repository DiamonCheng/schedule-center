package com.frame.webapp.controller.front;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frame.entity.media.ImgEntity;
import com.frame.entity.media.ImgGroupEntity;
import com.frame.service.front.AldumService;

/**
 * @author wowson
 * @version create time：2017年3月20日 上午11:08:33 类说明 ：
 */
@Controller
@RequestMapping("/front/album")
public class FrontalbumController {
	@Autowired
	AldumService service;

	// 相册首页
	@RequestMapping("/index")
	public Object albumShow(HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		data.put("basePath", basePath);
		List<ImgGroupEntity> list = service.selectAldum(data);
		return new ModelAndView("index/album").addObject("list", list);
	}

	// 图片列表
	@RequestMapping("/pic")
	public Object albumPic(HttpServletRequest request) {
		String imgGroupKey = request.getParameter("id");
		String page = request.getParameter("page"); // 当前页
		if (StringUtils.isEmpty(page))
			page = "1";
		int pageSize =18; // 每页计数
		Long totalPage = (long) 0; // 总页数
		Long totalRow = (long) 0; // 总计数
		Long pageNumber = (long) 0;
		Map<String, Object> data = new HashMap<>();
		data.put("page", page);
 		data.put("pageSize", pageSize);
		data.put("totalPage", totalPage);
		data.put("totalRow", totalRow);
		List<ImgEntity> list = null;
		Map<String, Object> count = new HashMap<String, Object>();
		ImgGroupEntity entity = new ImgGroupEntity();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		if (!StringUtils.isEmpty(imgGroupKey)) {
			data.put("imgGroupKey", imgGroupKey);
			list = service.selectAldumPic(data);
			if (list.size() > 0)
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setSrcName(basePath);
				}
			entity = service.getAldum(imgGroupKey);
			totalRow = service.countImg(Long.parseLong(imgGroupKey));
			totalPage = (totalRow + pageSize - 1) / pageSize;
			count.put("totalRow", totalRow);
			count.put("totalPage", totalPage);
			count.put("pageSize", pageSize);
			count.put("pageNumber", Integer.parseInt(page));
			count.put("albumKey", imgGroupKey);
		}
		return new ModelAndView("index/albumPic").addObject("listImg", list).addObject("entity", entity).addObject("page", count);
	}
}

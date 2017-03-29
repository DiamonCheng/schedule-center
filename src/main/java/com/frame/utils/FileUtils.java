package com.frame.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author wowson
 * @version create time：2017年3月14日 下午3:21:00 类说明 ：
 */
public class FileUtils {
	/**
	 * 判断文件夹是否为空
	 * 
	 * @param path
	 * @return true 空 false 非空
	 */
	public final static boolean isEmpty(String path) {
		boolean flag = true;
		File file = new File(path);
		if (file.isDirectory()) {
			String[] files = file.list();
			if (files.length > 0)
				flag = false;
			System.out.println("目录 " + file.getPath() + " 不为空！");
		}
		return flag;
	}

	/**
	 * 保存文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param file
	 *            文件
	 * @param type
	 *            文件类型
	 * @throws IOException
	 */
	public final static void saveFile(String fileName, MultipartFile file, String type) throws IOException {
		/**
		 * 读取配置的路径
		 */
		File fileDir =null;
		if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
			fileDir = new File(ImgDirConfig.SAVE_PIC_DIR);
			// 建目录
			if (!fileDir.exists())
				fileDir.mkdirs();
			// 写入文件
			// file.transferTo(fileDir);
			FileOutputStream fileOutputStream = new FileOutputStream(ImgDirConfig.SAVE_PIC_DIR + "\\" + fileName);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
		}
		if("MP4".equals(type.toUpperCase())){
			fileDir = new File(VideoPathConfig.SAVE_VIDEO_DIR);
			// 建目录
			if (!fileDir.exists())
				fileDir.mkdirs();
			// 写入文件
			// file.transferTo(fileDir);
			FileOutputStream fileOutputStream = new FileOutputStream(VideoPathConfig.SAVE_VIDEO_DIR + "\\" + fileName);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
		}
	}

	/**
	 * 获取文件 文件流的形式
	 * 
	 * @param fileDir
	 *            文件目录
	 * @return
	 * @throws FileNotFoundException
	 */
	public final static Object getImgUrl(String fileDir) throws FileNotFoundException {
		File file = new File(fileDir);
		FileInputStream in = new FileInputStream(file);

		return in;
	}

	/**
	 * 分割字符串
	 * 
	 * @param regex
	 *            分割点
	 * @param str
	 *            分割的字符串
	 * @return
	 */
	public final static String[] splitStr(String regex, String str) {
		return str.split(regex);
	}

	/**
	 * 
	 * @param filePath
	 *            文件路径
	 * @return true 删除成功 反之失败
	 */
	public final static boolean deleteLocalFile(String filePath) {
		File file = new File(filePath);
		boolean flag = true;
		if (file.exists()) {
			file.delete();
		} else {
			flag = true;
		}
		return flag;
	}
}

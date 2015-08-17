package com.lichee.core.cpt.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传组件工具类
 * 
 * @author lichee
 */
@Service
public class ImageUpload {

	@Autowired
	private ImageScale imageScale;
	@Autowired
	private ImageSizeFactory imageSizeFactory;
	
	private static Logger logger = LoggerFactory.getLogger(ImageUpload.class);
	private static final String ERROR_MSG = "上传图片文件出错...";
	
	private File mkDirs(String fileFolder) {
		
		File savedir = new File(fileFolder);
		if (!savedir.exists()) {
			savedir.mkdirs();
		}
		return savedir;
	}
	
	/**
	 * 图片上传 one file(原图)
	 * 
	 * @param String fileFolder 上传的路径
	 * @param MultipartFile file multipartFile对象
	 * @return String 上传后的文件名
	 */
	public String imageUpload(String fileFolder, MultipartFile file){    
		
		try {
			String fileName = null;
			if (!file.isEmpty()) {
				File savedir = mkDirs(fileFolder);
				fileName = imageScale.getImageName() + "." + imageScale.contentType;
				File saveFile = new File(savedir, fileName);
				FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile); 
			}
			return fileName;
		} catch (IOException e) {
			logger.error(ERROR_MSG);
			return null;
		}
	}  

	private String imageUpload(String fileFolder, MultipartFile file, List<ImageSize> list) {

		try {
			String fileName = null;
			if (!file.isEmpty()) {
				File savedir = mkDirs(fileFolder);
				BufferedImage src = ImageIO.read(file.getInputStream());
				fileName = imageUpload(fileFolder, file);
				for (int j = 1; j <= list.size(); j++) {
					ImageSize imageSize = list.get(j-1);
					String imageName = j + fileName;
					File saveFile = new File(savedir, imageName);
					imageScale.scalesImage(src, saveFile, imageSize);
				}
			}
			return fileName;
		} catch (IOException e) {
			logger.error(ERROR_MSG);
			return null;
		}
	}
	
	/**
	 * 图片上传 one file(切割多种尺寸)
	 * 
	 * @param String fileFolder 上传的路径
	 * @param MultipartFile file multipartFile对象
	 * @param String listStr 尺寸对象
	 * @return String 上传后的文件名
	 */
	public String imageUpload(String fileFolder, MultipartFile file,
			String listStr) {
		List<ImageSize> list = imageSizeFactory.getImageSizeList(listStr);
		return imageUpload(fileFolder, file, list);
	}
	
	/**
	 * 图片上传 many file(原图)
	 * 
	 * @param String fileFolder 上传的路径
	 * @param List<MultipartFile> files multipartFile集合对象
	 * @return List<String> fileNames 存放图片文件名集合
	 */
	public List<String> imageUpload(String fileFolder, List<MultipartFile> files){    
		
		try {
			List<String> fileNames = new ArrayList<String>();
			if (files != null) {
				for (int i = 0; i < files.size(); i++) {
					MultipartFile file = files.get(i);
					if(!file.isEmpty()){
						String imageName = imageUpload(fileFolder, file);
						fileNames.add(i, imageName);
					}
				}
			}
			return fileNames;
		} catch (Exception e) {
			logger.error(ERROR_MSG);
			return null;
		}
	}  
	

	private List<String> imageUpload(String fileFolder, List<MultipartFile> files, List<ImageSize> list) {

		try {
			File savedir = mkDirs(fileFolder);
			List<String> fileNames = new ArrayList<String>();
			if (files != null) {
				for (int i = 0; i < files.size(); i++) {
					MultipartFile file = files.get(i);
					if(!file.isEmpty()){
						BufferedImage src = ImageIO.read(file.getInputStream());
						String imageName = imageUpload(fileFolder, file);
						for (int j = 1; j < list.size(); j++) {
							ImageSize imageSize = list.get(j-1);
							String imageNameTemp = j + imageName;
							File saveFile = new File(savedir, imageNameTemp);
							imageScale.scalesImage(src, saveFile, imageSize);
						}
						fileNames.add(i, imageName);
					}
				}
			}
			return fileNames;
		} catch (IOException e) {
			logger.error(ERROR_MSG);
			return null;
		}
	}
	
	
	
	/**
	 * 图片上传 many file(切割多种尺寸)
	 * 
	 * @param String fileFolder 上传的路径
	 * @param List<MultipartFile> files multipartFile集合对象
	 * @param String listStr 尺寸对象
	 * @return List<String> fileNames 存放图片文件名集合
	 */
	public List<String> imageUpload(String fileFolder, List<MultipartFile> files, String listStr) {
		List<ImageSize> list = imageSizeFactory.getImageSizeList(listStr);
		return imageUpload(fileFolder, files, list);
	}
	
}

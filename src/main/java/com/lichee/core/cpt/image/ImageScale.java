package com.lichee.core.cpt.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 图片工具类
 * 
 * @author lichee
 */
@Service
public class ImageScale {
	
	private static Logger logger = LoggerFactory.getLogger(ImageScale.class);
	public static int generateCount = 0;
	@Value("#{corePropertise['photo.contentType']}")
	public String contentType;
	private static final String ERROR_MSG = "文件处理出错...";

	/**
	 * 图片处理
	 */
	public boolean scalesImage(BufferedImage srcBufferedImage, File destFile,
			ImageSize imageSize) {

		try {
			int imgWidth = imageSize.getWidth();
			int imgHeight = imageSize.getHeight();
			int srcWidth = srcBufferedImage.getWidth();
			int srcHeight = srcBufferedImage.getHeight();
			double scaleW = imageSize.getWidth() * 1.0 / srcWidth;
			double scaleH = imageSize.getHeight() * 1.0 / srcHeight;
			if (scaleW >= scaleH) {
				double imgWidth1 = scaleH * srcWidth;
				double imgHeight1 = scaleH * srcHeight;
				imgWidth = (int) imgWidth1;
				imgHeight = (int) imgHeight1;
			} else {
				double imgWidth1 = scaleW * srcWidth;
				double imgHeight1 = scaleW * srcHeight;
				imgWidth = (int) imgWidth1;
				imgHeight = (int) imgHeight1;
			}
			BufferedImage destBufferedImage = new BufferedImage(imageSize.getWidth(),
					imageSize.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = destBufferedImage.createGraphics();
			graphics2D.setBackground(Color.WHITE);
			graphics2D.clearRect(0, 0, imageSize.getWidth(), imageSize.getHeight());
			graphics2D.drawImage(srcBufferedImage.getScaledInstance(imgWidth,
					imgHeight, Image.SCALE_SMOOTH), (imageSize.getWidth() / 2)
					- (imgWidth / 2), (imageSize.getHeight() / 2) - (imgHeight / 2), null);
			graphics2D.dispose();
			ImageIO.write(destBufferedImage, contentType, destFile);
			return true;
		} catch (IOException e) {
			logger.error(ERROR_MSG);
			return false;
		}
	}

	/**
	 * 得到不重复的图排序名字
	 * 
	 * @return 图片名称
	 */
	public String getImageName() {

		if (generateCount > 99999) {
			generateCount = 0;
		}
		String uniqueNumber = Long.toString(System.currentTimeMillis())
				+ Integer.toString(generateCount);
		generateCount++;
		return uniqueNumber;
	}

	/**
	 * 获取图片文件的类型.
	 * 
	 * @param imageFile 图片文件对象.
	 * @return 图片文件类型
	 */
	public String getImageFormatName(File imageFile) {

		try {
			ImageInputStream imageInputStream = ImageIO.createImageInputStream(imageFile);
			Iterator<ImageReader> iterator = ImageIO.getImageReaders(imageInputStream);
			if (!iterator.hasNext()) {
				return null;
			}
			ImageReader imageReader = (ImageReader) iterator.next();
			imageInputStream.close();
			return imageReader.getFormatName().toLowerCase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
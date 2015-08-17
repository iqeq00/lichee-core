package com.lichee.core.cpt.image;

/**
 * 图片尺寸对象
 * 
 * @author lichee
 */
public class ImageSize {

	/**图片宽度*/
	private int width;
	/**图片高度*/
	private int height;
	
	public ImageSize(){
		
	}
	
	public ImageSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}

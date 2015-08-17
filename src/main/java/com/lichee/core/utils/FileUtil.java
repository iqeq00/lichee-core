package com.lichee.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * 常用文件操作工具类
 *
 * @author lpf
 */

public class FileUtil {

	/**
	 * 创建文件夹
	 * 
	 * @param strFilePath
	 *        文件夹路径
	 */
	public static boolean mkdirFolder(String strFilePath) {
		boolean bFlag = false;
		try {
			File file = new File(strFilePath.toString());
			if (!file.exists())
				bFlag = file.mkdir();
		} catch (Exception e) {
			return bFlag;
		}
		return bFlag;
	}

	/**
	 * 新建文件
	 * 
	 * @param strFilePath
	 *        文件路径
	 *        
	 * @param strFileContent
	 *        文件内容
	 */
	public static boolean createFile(String strFilePath, String strFileContent) {
		boolean bFlag = false;
		try {
			File file = new File(strFilePath.toString());
			if (!file.exists())
				bFlag = file.createNewFile();
			if (bFlag == Boolean.TRUE) {
				FileWriter fw = new FileWriter(file);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(strFileContent.toString());
				pw.close();
			}
		} catch (Exception e) {
			return bFlag;
		}
		return bFlag;
	}

	/**
	 * 删除文件
	 * 
	 * @param strFilePath
	 *        文件路径
	 */
	public static boolean removeFile(String strFilePath) {
		boolean result = false;
		if (strFilePath == null || "".equals(strFilePath))
			return result;
		File file = new File(strFilePath);
		if (file.isFile() && file.exists())
			result = file.delete();
		return result;
	}

	/**
	 * 删除文件夹(包括文件夹中的文件内容，文件夹)
	 * 
	 * @param strFolderPath
	 *        文件夹路径
	 */
	public static boolean removeFolder(String strFolderPath) {
		boolean bFlag = false;
		try {
			if (strFolderPath == null || "".equals(strFolderPath))
				return bFlag;
			File file = new File(strFolderPath.toString());
			bFlag = file.delete();
		} catch (Exception e) {
			return bFlag;
		}
		return bFlag;
	}

	/**
	 * 移除所有文件
	 * 
	 * @param strPath
	 *        文件夹路径
	 */
	public static boolean removeAllFile(String strPath) {
		File file = new File(strPath);
		if (!file.exists())
			return false;
		if (!file.isDirectory())
			return false;
		String[] fileList = file.list();
		File tempFile = null;
		for (int i = 0; i < fileList.length; i++) {
			if (strPath.endsWith(File.separator))
				tempFile = new File(strPath + fileList[i]);
			else
				tempFile = new File(strPath + File.separator + fileList[i]);
			if (tempFile.isFile())
				tempFile.delete();
			if (tempFile.isDirectory()) {
				removeAllFile(strPath + "/" + fileList[i]);// 下删除文件夹里面的文件
				removeFolder(strPath + "/" + fileList[i]);// 删除文件夹
			}
		}
		return true;
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *        原文件路径
	 *        
	 * @param newPath
	 *        复制后文件路径
	 */
	public static boolean copyFile(String oldPath, String newPath) {
		boolean result = false;
		try {
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

}

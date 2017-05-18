package com.poka.app.util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static void main(String[] args) {
		FileUtil fileUtil = new FileUtil();
		String fromPath = "C:\\fromPath\\";
		String toPath = "C:\\moveFile";
		fileUtil.moveFile(fromPath, toPath);
	}

	public void moveFile(String fromPath, String toPath) {
		File fromFolder = new File(fromPath);
		File[] fromFiles = fromFolder.listFiles();
		if (fromFiles == null) {
			return;
		}
		File toFolder = new File(toPath);
		if (!toFolder.exists()) {
			toFolder.mkdirs();
		}
		for (int i = 0; i < fromFiles.length; i++) {
			File file = fromFiles[i];
			if (file.isDirectory()) {
				moveFile(file.getPath(), toPath + File.separator + file.getName());
				// 亦可删除
				file.delete();
			}
			if (file.isFile()) {
				File toFile = new File(toFolder + File.separator + file.getName());
				if (toFile.exists()) {
					// 亦可删除
					toFile.delete();
				}
				// 移动文件
				file.renameTo(toFile);
			}
		}
	}

	// 删除文件夹
	// param folderPath 文件夹完整绝对路径

	public static boolean delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + File.separator + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 获取指定路径下的指定文件类型（如.ZT）
	 * 
	 * @param file
	 * @return
	 */
	public static List<String> getFileList(File file) {
		List<String> result = new ArrayList<String>();
		if (!file.isDirectory()) {
			System.out.println(file.getAbsolutePath());
			result.add(file.getAbsolutePath());
		} else {
			File[] directoryList = file.listFiles(new FileFilter() {
				public boolean accept(File file) {
					if (file.isFile() && file.getName().indexOf("ZT") > -1) {
						return true;
					} else {
						return false;
					}
				}
			});
			for (int i = 0; i < directoryList.length; i++) {
				result.add(directoryList[i].getPath());
			}
		}
		return result;
	}

}

package com.poka.app.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
				moveFile(file.getPath(), toPath + "\\" + file.getName());
				// 亦可删除
				 file.delete();
			}
			if (file.isFile()) {
				File toFile = new File(toFolder + "\\" + file.getName());
				if (toFile.exists()) {
					// 亦可删除
					toFile.delete();
				}
				// 移动文件
				file.renameTo(toFile);
			}
		}
	}
}

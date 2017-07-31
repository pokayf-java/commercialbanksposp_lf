package com.poka.app.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

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

	public static void main(String[] args) throws IOException {
//		String FILE_IN = "C:\\12345";
//		File f = new File(FILE_IN);
//		List<String> list = new ArrayList<String>();
//		list = getFileList(f);
//		System.out.println(list.size());
//		for (String l : list) {
//			String fileName = new File(l).getName();
//			String strName[] = fileName.split("_");
//			System.out.println("银行号:"+strName[0]);
//			System.out.println("网点号:"+strName[1]);
//			System.out.println("盒子号:"+strName[2]);
//			BufferedReader br = new BufferedReader(new FileReader(new File(l)));
//			String tempString = null;
//			int line = 1;
//			while ((tempString = br.readLine()) != null) {
//				System.out.println("line " + line + ": " + tempString);
//				System.out.println("["+tempString.substring(0,24).trim()+"]");
//				System.out.println("["+tempString.substring(24,43).trim()+"]");
//				System.out.println("["+tempString.substring(43,51).trim()+"]");
//				System.out.println("["+tempString.substring(51,65).trim()+"]");
//				line++;
//			}
//			br.close();
//		}
		String date1 = "2017-04-09";
		String date2 = "2017-04-09";
		if(date1.equals(date2)){
			System.out.println(true);
			System.out.println(PokaDateUtil.getLastDayStr("2017-04-11"));
		}else{
			System.out.println(false);
		}
	}
}

package ljh.java.exam02;

import java.io.File;

public class FileEx2 {
	public static void main(String[] args) {
		File directory = new File("C:/");
		if (directory.exists()) {
			if (directory.isDirectory()) {
				// 현재 디렉토리 내의 모든 파일 디렉토리의 이름 얻기/
				String[] fileNameList = directory.list();
				for (String fileName : fileNameList) {
					File myFile = new File(directory,fileName);
					if (myFile.isDirectory()) {
						System.out.println("폴더 이름 : " + myFile.getName());
					}else {
						System.out.println("파일 이름 : " + myFile.getName() + ", 파일 크기 : " + myFile.length() + "byte");
					}
				}
			}
		}
	}
}

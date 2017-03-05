package com.dbjina.imagecombiner.imp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.dbjina.imagecombiner.exception.FileExtensionException;
import com.dbjina.imagecombiner.inter.ImageWriter;

public class LocalImageWriter implements ImageWriter {

	public void write(String outputPath, String outputFilename, ArrayList<BufferedImage> images) {
		File file;

		file = new File(outputPath);

		// 폴더 체크 후, 없으면 폴더 생성
		if (file.isDirectory() == false) {

			if (file.mkdirs() == true) {
				System.out.println(" " + outputPath + " 폴더를 생성 하였습니다.");
				System.out.println();
			}
		}

		//
		for (int i = 0; i < images.size(); i++) {
			if (images.size() > 1) {
				file = new File(outputPath + outputFilename.substring(0, outputFilename.lastIndexOf('.')) + "_"
						+ (i + 1) + "." + getFileExtension(outputFilename));
			} else {
				file = new File(outputPath + outputFilename);
			}
			try {
				
				if(file.exists() == true) {
					System.out.println(" " + file.getName() + " 파일이 이미 존재합니다.");
					System.out.println(" 덮어 쓰시겠습니까? (y/n)");
					String str = "";

					@SuppressWarnings("resource")
					Scanner scan = new Scanner(System.in);
					str = scan.next();

					while (str.equals("y") == false && str.equals("n") == false) {
						System.out.println(" 제대로된 명령어를 입력해주세요.");
						System.out.println(" y 는 파일을 덮어쓰고 계속 진행");
						System.out.println(" n 은 프로그램 종료");
						System.out.println();
						System.out.print(" 입력 : ");

						str = scan.nextLine();
					}

					if (str.equals("n") == true) {
						CommonUtil.shutdown();
					}
					System.out.println(" " + file.getName() + " 파일 덮어 쓰는 중...");
					System.out.println();
				}
				
				boolean isSuccess = ImageIO.write(images.get(i), getFileExtension(outputFilename), file); 
				if(isSuccess == true) {
					System.out.println(" " + file.getName() + " 파일이 생성되었습니다.");
					System.out.println();
				}
				else {
					System.err.println(" " + file.getName() + " 파일을 만드는데 실패했습니다.");
					System.out.println();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public String getFileExtension(String filename) {
		String extension;
		int extIndex = filename.lastIndexOf('.');

		if (extIndex == -1) {
			throw new FileExtensionException(filename);
		}

		extension = filename.substring(extIndex + 1);

		return extension;
	}

}

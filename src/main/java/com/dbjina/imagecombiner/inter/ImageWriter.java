package com.dbjina.imagecombiner.inter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface ImageWriter {
	/**
	 * 
	 * @param outputPath 파일이 저장될 경로
	 * @param outputFilename 저장될 파일 이름
	 * @param images 저장될 이미지 파일들
	 */
	void write(String outputPath, String outputFilename, ArrayList<BufferedImage> images);
	
	/**
	 * 
	 * @param filename 파일 이름
	 * @return 파일의 확장자를 String으로 반환
	 */
	String getFileExtension(String filename);
}

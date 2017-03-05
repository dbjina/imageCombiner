package com.dbjina.imagecombiner.exception;

import com.dbjina.imagecombiner.imp.CommonUtil;

public class ImageNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 950051124901688468L;

	public ImageNotFoundException(String path) {
		super(path + " 경로에 이미지 파일이 없습니다.");
		System.out.println();
		System.err.println(super.getMessage());
		CommonUtil.shutdown();
	}
	
	public ImageNotFoundException() {
		super("이미지 파일이 없습니다.");
		System.out.println();
		System.err.println(super.getMessage());
		CommonUtil.shutdown();
	}
	
}

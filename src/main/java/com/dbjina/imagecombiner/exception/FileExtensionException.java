package com.dbjina.imagecombiner.exception;

public class FileExtensionException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 950051124901688468L;

	public FileExtensionException(String filename) {
		super(filename + " 파일에 확장자가 정의되어 있지 않습니다.");
	}
	
}

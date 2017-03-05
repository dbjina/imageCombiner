package com.dbjina.imagecombiner.exception;

public class FolderNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5350268149583090878L;

	public FolderNotFoundException(String path) {
		super(path + " 경로를 찾을 수 없습니다.");
	}
	
}

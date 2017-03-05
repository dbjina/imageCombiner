package com.dbjina.imagecombiner.exception;

public class ImageCombineException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2476178050851002570L;

	public ImageCombineException() {
		super("합쳐진 이미지가 없습니다.");
	}
	
}

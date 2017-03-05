package com.dbjina.imagecombiner.inter;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public interface ImageCombiner {
	/**
	 * JPG(JPEG)로 저장할 경우 픽셀 최대 길이
	 */
	int JPG_MAX_PIXCEL = 65500;
	
	/**
	 * 
	 * @param images 
	 * @return 합쳐진 이미지를 List<BufferedImage>로 반환
	 */
	List<BufferedImage> combineImages(List<BufferedImage> images);
	
	/**
	 * 
	 * @param images 이미지들 중에서 최대 큰 맥스를 구함
	 * @return
	 */
	Map<String, Integer> getMaxLength(List<BufferedImage> images);
	
}

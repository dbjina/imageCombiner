package com.dbjina.imagecombiner.inter;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ImageLoader {
	/**
	 * path 경로에 담겨진 이미지 파일들을 List로 반환합니다.
	 * 
	 * @author dbjina
	 * @param path 불러올 이미지가 들어 있는 경로
	 * @return 이미지들을 리스트 형태로 반환
	 */
	List<BufferedImage> loadImages(String path);
}

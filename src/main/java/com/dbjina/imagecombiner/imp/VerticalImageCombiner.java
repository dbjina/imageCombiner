package com.dbjina.imagecombiner.imp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dbjina.imagecombiner.exception.ImageCombineException;
import com.dbjina.imagecombiner.inter.ImageCombiner;

public class VerticalImageCombiner implements ImageCombiner{

	public List<BufferedImage> combineImages(List<BufferedImage> images) {
		Map<String, Integer> maxLength = getMaxLength(images);
		int maxWidth = maxLength.get("maxWidth");
		int maxHeight = maxLength.get("maxHeight");
		
		List<BufferedImage> combinedImages = new ArrayList<BufferedImage>();
		BufferedImage combined = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);
		Graphics g = combined.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, maxWidth, maxHeight);
		g.setColor(Color.BLACK);
		
		int lastHeight = 0;
		
		// maxHeight 가 JPG_MAX_PIXCEL 을 넘을 경우, BufferedImage를 놔눠서 저장함.
		if(maxHeight > JPG_MAX_PIXCEL) {
			int repeatCount = (int) Math.ceil(maxHeight / (double)JPG_MAX_PIXCEL);
			int imgCount = 0;
			boolean isFirstImage = true;
			
			for(int i=1; i<=repeatCount; i++) {
				int extraHeight = 0;
				
				for(int j=imgCount; j<images.size(); j++) {
					extraHeight += images.get(j).getHeight();
					
					if(extraHeight > JPG_MAX_PIXCEL) {
						extraHeight -= images.get(j).getHeight();
						break;
					}
				}
				
				combined = new BufferedImage(maxWidth, extraHeight, BufferedImage.TYPE_INT_RGB);
				g = combined.getGraphics();

				for(int j=imgCount; j<images.size(); j++, imgCount++) {
					if(isFirstImage == true) {
						isFirstImage = false;
					}
					else {
						lastHeight += images.get(j-1).getHeight();
					}

					if(lastHeight > (JPG_MAX_PIXCEL - images.get(j).getHeight())) {
						lastHeight = 0;
						isFirstImage = true;
						break;
					}

					g.drawImage(images.get(j), 0, lastHeight, null);
				}
				
				combinedImages.add(combined);
			}
		}
		else {
			for(BufferedImage img : images) {
				g.drawImage(img, 0, lastHeight, null);
				lastHeight += img.getHeight();
			}
			
			combinedImages.add(combined);
		}
		
		g.dispose();
		if(combinedImages.size() < 1) {
			throw new ImageCombineException();
		}
		
		return combinedImages;
	}


	public Map<String, Integer> getMaxLength(List<BufferedImage> images) {
		Map<String, Integer> maxLength = new HashMap<String, Integer>();
		
		if(images.size() == 0) {
			System.out.println("이미지 파일이 존재하지 않습니다.");
			return null;
		}
		
		int maxHeight = 0;
		int maxWidth = 0;
		
		for(BufferedImage image : images) {
			maxHeight += image.getHeight();
			
			maxWidth = Math.max(maxWidth, image.getWidth());
		}
		
		maxLength.put("maxWidth", maxWidth);
		maxLength.put("maxHeight", maxHeight);
		
		return maxLength;
	}
	
}

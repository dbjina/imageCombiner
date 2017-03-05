package com.dbjina.imagecombiner.imp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.dbjina.imagecombiner.exception.FolderNotFoundException;
import com.dbjina.imagecombiner.exception.ImageNotFoundException;
import com.dbjina.imagecombiner.inter.ImageLoader;

public class LocalImageLoader implements ImageLoader {
	private FilenameFilter imageFilter;
	
	public FilenameFilter getImageFilter() {
		return imageFilter;
	}

	public void setImageFilter(FilenameFilter imageFilter) {
		this.imageFilter = imageFilter;
	}

	public List<BufferedImage> loadImages(String path) {
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

		// 경로 유효성 체크
		File folderPath = new File(path);

		if (folderPath.isDirectory() == false) {
			throw new FolderNotFoundException(path);
		}

		// 이미지 파일 유효성 체크
		File[] files = folderPath.listFiles(this.imageFilter);
		
		if(files.length == 0) {
			throw new ImageNotFoundException(path);
		}
		
		// BufferedImage 리스트에 이미지 파일 등록
		System.out.println("===========================");
		System.out.println("로드된 이미지 파일");
		System.out.println("===========================");
		
		for(int i=0; i<files.length; i++) {
			try {
				images.add(ImageIO.read(files[i]));
				System.out.println((i+1) + ". " + files[i].getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("===========================");
		System.out.println();
		return images;
	}
	
}

 

package com.dbjina.imagecombiner;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dbjina.imagecombiner.inter.ImageLoader;
import com.dbjina.imagecombiner.exception.FolderNotFoundException;
import com.dbjina.imagecombiner.exception.ImageCombineException;
import com.dbjina.imagecombiner.exception.ImageNotFoundException;
import com.dbjina.imagecombiner.imp.CommonUtil;
import com.dbjina.imagecombiner.inter.ImageCombiner;
import com.dbjina.imagecombiner.inter.ImageWriter;

public class ImageCombinerAppManager {
	private String outputFilename;
	private String inputPath;
	private String outputPath;
	private List<BufferedImage> images;
	private List<BufferedImage> combinedImages;

	private ImageLoader imageLoader;
	private ImageCombiner imageCombiner;
	private ImageWriter imageWriter;

	public List<BufferedImage> getImages() {
		return images;
	}

	public void setImages(List<BufferedImage> images) {
		if(images.size() < 1) {
			throw new ImageNotFoundException();
		}
		this.images = images;
	}

	public List<BufferedImage> getCombinedImages() {
		return combinedImages;
	}

	public void setCombinedImages(List<BufferedImage> combinedImages) {
		if(combinedImages.size() < 1) {
			throw new ImageCombineException();
		}
		this.combinedImages = combinedImages;
	}

	public String getOutputFilename() {
		return outputFilename;
	}

	public void setOutputFilename(String outputFilename) {
		if (CommonUtil.isValidFilename(outputFilename) == true) {
			this.outputFilename = outputFilename;
		}
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		if (CommonUtil.isValidPath(inputPath) == true) {
			this.inputPath = CommonUtil.makeFullPath(inputPath);
		}
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		if (CommonUtil.isValidPath(outputPath) == true) {
			this.outputPath = CommonUtil.makeFullPath(outputPath);
		} else {
			throw new FolderNotFoundException(outputPath);
		}
	}

	public ImageLoader getImageLoader() {
		return imageLoader;
	}

	public void setImageLoader(ImageLoader imageLoader) {
		this.imageLoader = imageLoader;
	}

	public ImageCombiner getImageCombiner() {
		return imageCombiner;
	}

	public void setImageCombiner(ImageCombiner imageCombiner) {
		this.imageCombiner = imageCombiner;
	}

	public ImageWriter getImageWriter() {
		return imageWriter;
	}

	public void setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
	}

	void exec() {

		// 이미지 로드
		System.out.println(getInputPath() + " 경로에서 이미지를 불러 옵니다...");
		setImages(imageLoader.loadImages(getInputPath()));
		System.out.println();

		// 이미지 합치기
		System.out.println("총 " + images.size() + " 개의 이미지 파일을 합치고 있습니다...");
		setCombinedImages(imageCombiner.combineImages((ArrayList<BufferedImage>) images));
		System.out.println();
		
		// 이미지 저장
		System.out.println("총 " + getCombinedImages().size() + " 개의 이미지 파일로 생성 되고 있습니다...");
		imageWriter.write(getOutputPath(), getOutputFilename(), (ArrayList<BufferedImage>) getCombinedImages());
		System.out.println();
		
		
		// 완료 메시지
		System.out.println("작업이 완료되었습니다.");
		System.out.print("종료를 위해 Enter키를 눌러주세요.");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		System.exit(0);
	}

}

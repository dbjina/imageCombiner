package com.dbjina.imagecombiner;

import java.util.Scanner;

import com.dbjina.imagecombiner.filter.ImageFilter;
import com.dbjina.imagecombiner.imp.CommonUtil;
import com.dbjina.imagecombiner.imp.LocalImageLoader;
import com.dbjina.imagecombiner.imp.LocalImageWriter;
import com.dbjina.imagecombiner.imp.VerticalImageCombiner;
import com.dbjina.imagecombiner.inter.ImageCombiner;
import com.dbjina.imagecombiner.inter.ImageWriter;

public class ImageCombinerApp {

	public static void main(String[] args) {
		String currentPath = System.getProperty("user.dir");

		// App manager 생성
		ImageCombinerAppManager manager = new ImageCombinerAppManager();

		// Intro 출력
		displayIntro();

		// InputPath 설정
		manager.setInputPath(currentPath);

		// OutputPath 설정
		manager.setOutputPath(currentPath + "\\combined_image");

		// OutputFilename 설정
		manager.setOutputFilename(currentPath.substring(currentPath.lastIndexOf('\\') + 1) + ".jpg");

		// ImageLoader 바인딩
		LocalImageLoader imageLoader = new LocalImageLoader();
		imageLoader.setImageFilter(new ImageFilter()); // png, jpg, jpeg, gif,
														// bmp를 찾는 ImageFilter
														// 셋팅
		manager.setImageLoader(imageLoader);

		// ImageCombiner 바인딩
		ImageCombiner imageCombiner = new VerticalImageCombiner();
		manager.setImageCombiner(imageCombiner);

		// ImageWriter 바인딩
		ImageWriter imageWriter = new LocalImageWriter();
		manager.setImageWriter(imageWriter);

		// 실행
		manager.exec();

	}

	private static void displayIntro() {

		System.out.println("**************************************");
		System.out.println();
		System.out.println(" Image Combiner는 여러 이미지를 합치기 위해서 만들어졌습니다.");
		System.out.println(" Version 0.1");
		System.out.println();
		System.out.println(" - JPG로만 저장됩니다.");
		System.out.println(" - 합친 이미지의 최대 길이가 65500 넘어갈 경우 그 다음으로 넘어갑니다.");
		System.out.println(" - Image Combiner 실행 폴더의 이미지만 검색하고, 하위 폴더는 검색하지 않습니다.");
		System.out.println();
		System.out.println("**************************************");
		System.out.println();

		System.out.println(" 진행 하시겠습니까? (y/n)");
		System.out.print(" 입력 : ");

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String str = "";

		str = scan.next();

		while (str.equals("y") == false && str.equals("n") == false) {
			System.out.println(" 제대로된 명령어를 입력해주세요.");
			System.out.println(" y 는 계속 진행");
			System.out.println(" n 은 프로그램 종료");
			System.out.println();
			System.out.print("입력 : ");

			str = scan.next();
			
			System.out.println();
		}

		if (str.equals("n") == true) {
			CommonUtil.shutdown();
		}
		
	}

	// private static void displayMenu(ImageCombinerAppManager manager) {
	// String currentPath = System.getProperty("user.dir");
	// @SuppressWarnings("resource")
	// Scanner userInput = new Scanner(System.in);
	// String str;
	//
	// System.out.println("경로를 지정하지 않고 Enter를 누르면 현재 실행 경로로 지정 됩니다.");
	// System.out.println("합칠 이미지가 들어 있는 경로를 지정해 주세요 : ");
	// str = userInput.nextLine().trim();
	//
	// if(str.equals("")) {
	// manager.setInputPath(currentPath);
	// }
	// else {
	// manager.setInputPath(str);
	// }
	//
	// str = "";
	//
	// System.out.println();
	// System.out.println("경로를 지정하지 않고 Enter를 누르면 현재 실행 경로에 combined_image 폴더로
	// 지정 됩니다.");
	// System.out.println("합쳐진 이미지를 저장할 경로를 지정해 주세요 : ");
	// str = userInput.nextLine().trim();
	//
	// if(str.equals("")) {
	// manager.setOutputPath(currentPath + "\\combined_image");
	// }
	// else {
	// manager.setOutputPath(str);
	// }
	//
	// str = "";
	//
	// System.out.println();
	// System.out.println("경로를 지정하지 않고 Enter를 누르면 현재 폴더의 이름으로 지정 됩니다.");
	// System.out.println("합쳐질 이미지의 파일명(확장자는 제외)을 지정해 주세요 : ");
	// str = userInput.nextLine();
	//
	// if(str.equals("")) {
	// str = currentPath.substring(currentPath.lastIndexOf('\\') + 1);
	// }
	//
	// manager.setOutputFilename(str.concat(".jpg"));
	// System.out.println();
	//
	// }
}

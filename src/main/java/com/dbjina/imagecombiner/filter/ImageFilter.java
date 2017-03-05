package com.dbjina.imagecombiner.filter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class ImageFilter implements FilenameFilter {
	private ArrayList<String> extensions;

	public ArrayList<String> getExtensions() {
		return extensions;
	}

	public void setExtension(ArrayList<String> extensions) {
		String extension;

		// 확장자 . 제거
		for (int i = 0; i < extensions.size(); i++) {
			extension = extensions.get(i);
			if (extension.charAt(0) == '.') {
				extension = extension.substring(1);
				extensions.set(i, extension);
			}
		}

		this.extensions = extensions;
	}
	
	public ImageFilter() {
		// 기본 이미지 확장자 정의
		this.extensions = new ArrayList<String>();
		this.extensions.add("jpg");
		this.extensions.add("png");
		this.extensions.add("jpeg");
		this.extensions.add("gif");
		this.extensions.add("bmp");
	}

	public boolean accept(File dir, String name) {
		if (name.lastIndexOf('.') != -1) {

			int lastIndex = name.lastIndexOf('.');

			String str = name.substring(lastIndex + 1);

			if (extensions.contains(str.toLowerCase()) == true) {
				return true;
			}
		}

		return false;
	}

}

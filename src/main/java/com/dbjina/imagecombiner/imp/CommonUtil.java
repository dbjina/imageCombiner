package com.dbjina.imagecombiner.imp;

import java.util.ArrayList;
import java.util.Scanner;

import com.dbjina.imagecombiner.exception.FileExtensionException;
import com.dbjina.imagecombiner.exception.FolderNotFoundException;
import com.dbjina.imagecombiner.exception.ImageNotFoundException;
import com.dbjina.imagecombiner.filter.ImageFilter;

public class CommonUtil {
	
	public static boolean isValidPath(String path) {
		
		if(path.lastIndexOf('/') == -1 && path.lastIndexOf('\\') == -1) {
			return false;
		}
			
		return true;
	}
	
	public static boolean isValidFilename(String filename) {
		
		int extensionIndex = filename.lastIndexOf('.'); 
		
		if(extensionIndex == -1) {
			throw new FileExtensionException(filename);
		}
		
		ImageFilter imageFilter = new ImageFilter();
		ArrayList<String> extensionFilter = imageFilter.getExtensions();
		
		if(extensionFilter.contains(filename.substring(extensionIndex + 1)) == false) {
			throw new ImageNotFoundException();
		}
		
		return true;
	}
	
	public static String makeFullPath(String path) {
		String fullPath = path;
		
		fullPath = fullPath.replace('/', '\\');
		
		if(fullPath.contains("\\") == false) {
			throw new FolderNotFoundException(fullPath);
		}
		
		if(fullPath.endsWith("\\") == false) {
			fullPath = fullPath.concat("\\");
		}

		return fullPath;
	}
	
	public static void shutdown() {
		System.out.print("종료를 위해 Enter키를 눌러주세요.");
		
		Scanner scan = new Scanner(System.in);
		@SuppressWarnings("unused")
		String str = "";
		str = scan.nextLine();
		scan.close();
		System.exit(0);
	}
}

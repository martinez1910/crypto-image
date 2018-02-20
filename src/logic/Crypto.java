package logic;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


import javax.imageio.ImageIO;

public class Crypto<T> {
	private final static char CHAR ='X';
	
	public static int[][] loadImage(String relativePath) throws IOException {
		BufferedImage img = ImageIO.read(Crypto.class.getResource(relativePath));
		int[][] rgbImg = new int[img.getWidth()][img.getHeight()];
		for(int i=0; i<img.getWidth(); i++)
			for(int j=0; j<img.getHeight(); j++)
				rgbImg[i][j] = img.getRGB(j, i);
		
		return rgbImg;
	}
	
	public static boolean saveImage(int[][] rgbImgEncrypted, String newName) throws IOException {
		BufferedImage bufferedImage = new BufferedImage(rgbImgEncrypted.length, rgbImgEncrypted.length, BufferedImage.TYPE_INT_RGB);
		for(int i=0; i<rgbImgEncrypted.length; i++)
			for(int j=0; j<rgbImgEncrypted.length; j++) {
				bufferedImage.setRGB(j, i, rgbImgEncrypted[i][j]);
				/*int a = rgbImgEncrypted[i][j];
				Color newColor = new Color(a,a,a);
	            image.setRGB(j,i,newColor.getRGB());*/
			}
		File output = new File(newName);
	    ImageIO.write(bufferedImage, "jpg", output);
		
		return true;
	}
	
	public static int[][] spiralMethod(int[][] rgbImg) {
		int[][] rgbImgEncrypted = new int[rgbImg.length][rgbImg.length];
		LinkedList<Integer> rgbImgLinkedList = matrixToLinkedList(rgbImg);
		int middlePoint = rgbImg.length / 2;
		
		int xPoint = middlePoint, yPoint = middlePoint;
		int step = 1, currentStep = 0;
		
		try {
			rgbImgEncrypted[yPoint][xPoint] = rgbImgLinkedList.poll();
			while(true){
				while(currentStep < step) {
					xPoint++;
					rgbImgEncrypted[yPoint][xPoint] = rgbImgLinkedList.poll();
					//rgbImgToString(rgbImgEncrypted);
					currentStep++;
				}currentStep = 0;
				
				while(currentStep < step) {
					yPoint++;
					rgbImgEncrypted[yPoint][xPoint] = rgbImgLinkedList.poll();
					//rgbImgToString(rgbImgEncrypted);
					currentStep++;
				}currentStep = 0;
				
				step++;
				
				while(currentStep < step) {
					xPoint--;
					rgbImgEncrypted[yPoint][xPoint] = rgbImgLinkedList.poll();
					//rgbImgToString(rgbImgEncrypted);
					currentStep++;
				}currentStep = 0;
				
				while(currentStep < step) {
					yPoint--;
					rgbImgEncrypted[yPoint][xPoint] = rgbImgLinkedList.poll();
					//rgbImgToString(rgbImgEncrypted);
					currentStep++;
				}currentStep = 0;

				System.out.println("Current step: " +step);
				step++;
			}			
		}catch(ArrayIndexOutOfBoundsException | NullPointerException e) {//NPE caused by poll()
			//Expected to throw it.
			//rgbImgToString(rgbImgEncrypted);
			System.out.println("FINISHED SPIRAL METHOD");
		}
		
		return rgbImgEncrypted;
	}
	



	

	public static boolean setSpiral(char[][] matrix) {
		if(matrix.length < 10 || matrix.length % 2 == 0 || matrix.length != matrix[0].length)
			return false;
		
		int middlePoint = matrix.length / 2;
		
		int xPoint = middlePoint, yPoint = middlePoint;
		int step = 1;
		
		try {
			matrix[yPoint][xPoint] = CHAR;
			while(true){				
				xPoint += step;
				matrix[yPoint][xPoint] = CHAR;
				yPoint += step;
				matrix[yPoint][xPoint] = CHAR;
				step++;
				xPoint -= step;
				matrix[yPoint][xPoint] = CHAR;
				yPoint -= step;
				matrix[yPoint][xPoint] = CHAR;
				step++;
			}			
		}catch(ArrayIndexOutOfBoundsException e) {
			//Expected to throw it.
			matrixToString(matrix);
		}
		
		return true;
	}
	
	
	
	private static LinkedList<Integer> matrixToLinkedList(int[][] rgbImg) {
		LinkedList<Integer> rgbImgLinkedList = new LinkedList<Integer>();
		for(int i=0; i<rgbImg.length; i++) {
			System.out.println("-- 'i' matrix index: " +i +" --");
			displayMemoryUsage();
			for(int j=0; j<rgbImg.length; j++)
				rgbImgLinkedList.add(rgbImg[i][j]);
		}
		
		return rgbImgLinkedList;
	}
	
	
	private static void matrixToString(char[][] matrix) {
		String str = "";
		for(int i=0; i<matrix.length; i++) {
			str += "[";
			for(int j=0; j<matrix.length; j++) {
				if(j != 0)
					str += ", ";
				str += matrix[i][j];
			}
			str += "]\n";
		}
		
		System.out.println(str);
	}
	
	private static void rgbImgToString(int[][] rgbImg) {
		String str = "";
		for(int i=0; i<rgbImg.length; i++) {
			str += "[";
			for(int j=0; j<rgbImg.length; j++) {
				if(j != 0)
					str += ", ";
				str += rgbImg[i][j];
			}
			str += "]\n";
		}
		
		System.out.println(str);
	}
	
	private static void displayMemoryUsage() {
		System.out.println("########### MEMORY USAGE ###########");
		System.out.println("Maximum Memory: " +Runtime.getRuntime().maxMemory());
		System.out.println("Total Memory: " +Runtime.getRuntime().totalMemory());
		System.out.println("Free Memory: " +Runtime.getRuntime().freeMemory());
		System.out.println("Memory Usage: " +(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
		System.out.println("####################################");
	}
}
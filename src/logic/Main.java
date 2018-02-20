package logic;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		int[][] matrix = new int[11][11];
		int counter = 0;
		for(int i=0; i<matrix.length; i++)
			for(int j=0; j<matrix.length; j++) {
				matrix[i][j] = counter;
				counter++;
			}
		
		Crypto.spiralMethod(matrix);
		
		try {
			String name = "imageLowRes";
			Crypto.saveImage(Crypto.spiralMethod(Crypto.loadImage("/img/" +name +".jpg")), name +"-ENCRYPTED.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Crypto.setSpiral(matrix);
	}

}

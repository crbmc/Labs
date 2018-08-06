import java.util.Scanner;

public class Main{
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) {
			String plainText = scan.nextLine();
			plainText = plainText.toUpperCase();
			int[] o = str2int(plainText);
			for(int i = 0; i < o.length; i++) {
				System.out.print(o[i] + " ");
			}
			System.out.println();
		}
	}
	
	public static int [] str2int(String plainText) {
		int [] output = new int[plainText.length()];
		plainText = plainText.toUpperCase();
		for(int i = 0; i < plainText.length(); i++) {
			char c = plainText.charAt(i);
			if(c == 32)
				output [i] = ((int) c) - 65;
		}
		return output;
		}
	}
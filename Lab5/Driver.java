/*
   * author: Christopher Byrnes
   * course: MSCS630
   * assignment: Lab 5
   * due date: April 24, 2018
   * version: 1.0
   * 
   * This file contains the Driver class to generate an AEA Cipher*/
import java.util.Arrays;
import java.util.Scanner;

class Driver {

	public static void main(String[] args) {
		/*Scanner input = new Scanner(System.in);
		String key = input.next();
		//System.out.println(key);
		
		String[] roundKeysHex = AESCipher.aesRoundKeys(key);
		
		for(int i = 0; i<roundKeysHex.length; i++) {
			System.out.println(roundKeysHex[i]);
		}*/
		
		String[][] sHex = {{"54", "4F", "4E", "20"},{"77", "6E", "69", "54"},{"6F", "65", "6E", "77"},{"20", "20", "65", "6F"}};
		String[][] keyHex = {{"54", "73", "20", "67"},{"68", "20", "4B", "20"},{"61", "6D", "75", "46"},{"74", "79", "6E", "75"}};
		
		String [][] outStateHex = AESCipher.AESStateXOR(sHex, keyHex);
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(outStateHex[i][j] + ", ");
			}
			System.out.println();
		}
		outStateHex = AESCipher.AESNibbleSub(outStateHex);
		
		System.out.println();
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(outStateHex[i][j] + ", ");
			}
			System.out.println();
			}
		
		outStateHex = AESCipher.AESShiftRow(outStateHex);
		
		System.out.println();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(outStateHex[i][j] + ", ");	
				}
			System.out.println();
		}
	}
}
	

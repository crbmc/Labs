   /* file: Driver.java
   * author: Christopher Byrnes
   * course: MSCS630
   * assignment: Lab 4
   * due date: April 11, 2018
   * version: 1.0
   * 
   * This file contains the Driver class to generate an AEA Cipher*/

import java.util.Arrays;
import java.util.Scanner;

class Driver {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String key = input.next();
		System.out.println(key);
		
		String[] roundKeysHex = AESCipher.aesRoundKeys(key);
		//for(int i = 0; i<roundKeysHex.length; i++) {
		//	System.out.println(Arrays.toString(roundKeysHex[i]));
		//}
		}
	}
	

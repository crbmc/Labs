
public class AESCipher {

	public static void main(String[] args) {
		System.out.println(aesRcon(3));
	}

public static String[] aesRoundKeys(String KeyHex) {
	String K[][] = new String[4][4];
	String W[][] = new String [4][44];
	String [] results = new String[11];
	
	//step 1
	String [] temp = new String[16];
	for(int i = 0; i<KeyHex.length(); i= i+2) {
		temp[i/2] = (KeyHex.substring(i,i+2));
	}
	
	for(int i = 0, j = 0; i<temp.length; i = i+4, j++) {
		K[0][j] = temp[i];
		K[1][j] = temp[i+1];
		K[2][j] = temp[i+2];
		K[3][j] = temp[i+3];
	}
	
	//step 2
	for(int i = 0; i < 4; i++) {
		W[0][i] = K[0][i];
		W[1][i] = K[1][i];
		W[2][i] = K[2][i];
		W[3][i] = K[3][i];
		
	}
	// Part 3 (a) 
		for(int j = 4; j<44; j++) {
			// if not multiple of 4
			if(j % 4 != 0) {
				for(int i = 0; i < 4; i++) {
					int w4 = Integer.parseInt(W[i][j-4], 16);
					int w5 = Integer.parseInt(W[i][j-1], 16);
					
					W[i][j] = Integer.toHexString(w4 ^ w5);
				}
			// if multiple of 4; part (b) call aesRcon method
			}else {
				String[] Wnew = new String[4];
				for(int i = 0; i<4; i++)
					Wnew[i] = W[i][j-1]; // Wnew[i] is element in array
				
				// shifting rows
				String a = Wnew[0];
				
				for(int i = 0; i<3; i++) {
					Wnew[i] = Wnew[i+1];
				}
				Wnew[3] = a;
			
				// transform Wnew  with Sbox function
				for(int i = 0; i<4; i++) {
					Wnew[i] = aesSBox(Wnew[i]);
				}
				
				int w6 = Integer.parseInt(aesRcon(j/4), 16);
				int w7 = Integer.parseInt(Wnew[0], 16);
				
				Wnew[0] = Integer.toHexString(w6 ^ w7);
				
				
				// final transformation
				for(int i = 0; i < 4; i++) {
					int w4 = Integer.parseInt(W[i][j-4], 16);
					int w5 = Integer.parseInt(Wnew[i], 16);
					
					W[i][j] = Integer.toHexString(w4 ^ w5);
				}	
				
			}
	}

	//Separate ind round keys
		
//	for(int i = 0;  i< W.length; i += 4) {
//		StringBuffer str = new StringBuffer();
//		for(int j = 0; j<43; j++) {
//			str.append(W[0][j]);
//			str.append(W[1][j]);
//			str.append(W[2][j]);
//			str.append(W[3][j]);
//		}
//		results[] = str;
		
	StringBuffer str = new StringBuffer();
	for(int j = 0; j<43; j++) {
		if (j%4==0) {
			results[j/4] = str.toString();
		}
		str.append(W[0][j]);
		str.append(W[1][j]);
		str.append(W[2][j]);
		str.append(W[3][j]);
		
	}
	for (String s: results)
		System.out.println(s);
	
// final STEP
	//return W;
	return results;
}
public static String aesSBox(String inHex) {
	int it = Integer.parseInt(inHex, 16);
	char x = s[it];

	 return Integer.toHexString((int) x);
	
}
/*public static String[][] aesSBox(String inHex) {
 * 
	String[][] K = new String[4][4];
	String [] temp = new String[16];
	for(int i = 0; i<inHex.length(); i= i+2) {
		temp[i/2] = (inHex.substring(i,i+2));
	}
	for(int i = 0, j = 0; i<temp.length; i = i+4, j++) {
		K[j][0] = temp[i];
		K[j][1] = temp[i+1];
		K[j][2] = temp[i+2];
		K[j][3] = temp[i+3];
	}
	return K;
}

static String sBoxMe(String in) {
	
	int it = Integer.parseInt(in, 16);
	char x = s[it];

	 return Integer.toHexString((int) x);
}*/

static String aesRcon(int round) {
	String hex = String.format("%04x",  (int) rcon[round]).substring(2,4);
	return hex;
}
private static final char[] rcon= {
		
	    0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 
	    0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 
	    0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 
	    0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 
	    0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 
	    0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 
	    0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 
	    0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 
	    0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 
	    0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 
	    0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 
	    0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 
	    0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 
	    0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 
	    0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 
	    0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d
	};



private static final char [] s = {
		 0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76,
		 0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0,
		 0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15,
		 0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75,
		 0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84,
		 0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF,
		 0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8,
		 0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2,
		 0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73,
		 0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB,
		 0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79,
		 0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08,
		 0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A,
		 0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E,
		 0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF,
		 0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16
};
}



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		File file = new File("Inputs/Input1.txt");
		try {
			Scanner input = new Scanner(file);
			long a = input.nextLong();
			long b = input.nextLong();
			long r = euclidAlgo(a,b);  // gcd(a,b);
			long[] p = extEucAlgo(r,a,b);
			
			 //  System.out.println(r); // prints gcd(a,b);
			for(int l = 0; l< p.length; l++) {
				System.out.println(p[l]);
			 }
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
    
	static long euclidAlgo(long a, long b) {
      	long temp = 0;
      	long q = 0;
      	long r = 0;
      	
      	if(b>a){
      		temp = a;
      		a = b;
      		b = temp;
      	}
      	q = Math.floorDiv(a,b);
      	r = a - q*b;
      
      	if (r == 0) {
      		return b;
      }
      	else {
      		return euclidAlgo(b,r);
    		  }
	}
   
      static long[] extEucAlgo(long r, long a, long b) {
		long [] U = new long[3];
		long [] V = new long[3];
		long [] W = new long[3];
		
		U[0] = a;
		U[1] = 1;
		U[2] = 0;
		
		V[0] = b;
		V[1] = 0;
		V[2] = 1;
		
		while (V[0] > 0) {
			for(int k = 0; k < W.length; k++) {
				W[k] = U[k] - Math.floorDiv(U[0],V[0])* V[k];
		}
			for(int j = 0; j < U.length; j++){
				U[j] = V[j];
				}
			for(int q = 0; q < V.length; q++){
				V[q] = W[q];
		}
		
	}
		return U;
	}
}



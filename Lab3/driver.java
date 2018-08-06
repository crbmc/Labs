package matrixDeterminant;
import java.util.Scanner;

// header goes here
public class driver {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    int m = input.nextInt();
    int n = input.nextInt();
    	int [][] A = new int[n][n];
    	for(int i = 0; i<n; i++)
    		for(int j = 0; j<n; j++)
    			A[i][j] = input.nextInt();
    	
    int det = cofModDet(m, A);
    System.out.println(det);
  }
  
  public static int cofModDet(int m, int [][] A) {
    
    int det = 0;
    int n = A[0].length;
    int [][] B = new int[n-1][n-1];
    		
    if(n==1) {
    	  det = A[0][0];
    }else if(n==2) {
    	  det = ((A[0][0])*(A[1][1]))-((A[0][1])*(A[1][0]));
    }else if(n>=3) {

    		// System.out.println("test");
    		int i = 0;
    		boolean doAddition = false;
    	  	for(int j = 0 ; j<n; j++) {
    	  		int Bi = 0;
    	  		int Bj = 0;
    	  		for(int k= 0; k<n; k++) {
    	  			Bj = 0;
    	  			if (i != k) {
    	  				for(int l = 0; l<n; l++) {
    	  					
    	  					if (j != l) {
    	  						B[Bi][Bj] = A[k][l];
    	  						Bj++;
    	  					}
    	  				}
    	  				Bi++;
    	  			}
    	  		}
	    	  	int subDet = cofModDet(m, B);
	    	  	int temp = A[i][j]*subDet;
	    	  	if(j==0) det = temp;
	    	  	else {
	    	  		if (doAddition) {
	    	  			det += temp;
	    	  		} else {
	    	  			det -= temp;
	    	  		}
	    	  		doAddition = !doAddition;
	    	  	}
    	  	}
      }
	  return det;
  }
}

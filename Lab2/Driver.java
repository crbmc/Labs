import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
      Scanner input = new Scanner(System.in);
      long a = input.nextLong(); // dividend
      long b = input.nextLong(); // divisor
      
      long r = euclidAlgo(a,b);  // gcd(a,b);

      System.out.println(r); // prints gcd(a,b);
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
      
      	if (a - b*q == 0) {
      		return b;
      }
      	else if(r >=1 && b == 0) {
      		
    		  }
      	return euclidAlgo(b,r);
     
      }
      }
     

    
   
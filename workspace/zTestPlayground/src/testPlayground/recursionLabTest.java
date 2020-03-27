package testPlayground;

public class recursionLabTest {
	public static void main(String[] args) {
		System.out.print(biPower(10));

	}
	
	public static boolean isEven(int val) {
		if(val<0) val*=-1;
		if(val==1) return false;
		if(val==0) return true;
		return isEven(val-2);	
	}
	public static int sumN(int n) {
		if(n>0) return n+sumN(n-1);
		if(n<0) return n+sumN(n+1);
		else return 0;
		
	}
	
	public static int biPower(int n) {
		if(n<0) return 0;
		if(n>0) return 2*biPower(n-1);
		else return 1;
			
	}


}

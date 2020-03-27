package testPlayground;

public class nullGetter {
	
	static String myNull=null;
	public static void main(String[] args) {
		if(getString()==null) System.out.println("equality, yay!");
		if(!(getString()!=null)) System.out.println("inequality is the sad truth though :(");
		
	}
	
	public static String getString() {
		return myNull;
	}
}

package largeinteger;

import largeinteger.LLNode;

/** The LargeInteger class
 *  This class represents a large, non-negative integer using a linked list.
 *  Each node stores a single digit. The nodes represent all digits in *reverse* order:
 *  the least significant digit is the first node, and the most significant the last node.
 *  For example, 135642 is represented as 2->4->6->5->3->1 in that order.
 */
public class LargeInteger {
	private LLNode<Integer> head;	// head of the list
	private int size;				// size (i.e. number of digits)
	
	// Returns size
	public int size() { return size; }
	// Returns the linked list (used only for JUnit test purpose)
	public LLNode<Integer> getList() { return head; }
	
	public LargeInteger() {
		head = null; size = 0;
	}
	
	/** Constructor that takes a String as input and constructs the linked list.
	 *  You can assume that the input is guaranteed to be valid: i.e. every character
	 *  in the string is between '0' and '9', and the first character is never '0'
	 *  (unless '0' is the only character in the string). You can use input.charAt(i)-'0'
	 *  to convert the character at index i to the integer value of that digit.
	 *  Remember: the list nodes must be in reverse order as the characters in the string.
	 */
	public LargeInteger(String input) {
		// TODO
		for(int i=0;i<input.length();i++){
			if(head==null) head=new LLNode<Integer>(Integer.parseInt(input.charAt(i)+""),null);
			else {
				LLNode<Integer> temp=new LLNode<Integer>(Integer.parseInt(input.charAt(i)+""),head);
				head=temp;
			}
		}
		size=input.length();
		
	}
	
	/** Divide *this* large integer by 10 and return this.
	 *  Assume integer division: for example, 23/10 = 2, 8/10 = 0 and so on.
	 */
	public LargeInteger divide10() {
		// TODO
		if(size>1) {
			head=head.link;
			size--;
		}
		else head.data=0;
		return this;
	}
	
	/** Multiply *this* large integer by 10 and return this.
	 *  For example, 23*10 = 230, 0*10 = 0 etc.
	 */
	public LargeInteger multiply10() {
		// TODO
		if(size==1 && head.data==0) /*do nothing*/ ;
		else {
			LLNode<Integer> temp= new LLNode<Integer>(0,head);
			head=temp;
			size++;
		}
		return this;
	}
	
	/** Returns a *new* LargeInteger object representing the sum of this large integer
	 *  and another one (given by that). Your code must correctly handle cases such as
	 *  the two input integers have different sizes (e.g. 2+1000=1002), or there is a
	 *  carry over at the highest digit (e.g. 9999+2=10001).
	 */
	public LargeInteger add(LargeInteger that) {
		// TODO
		String tempString="";
		LargeInteger tempInteger;
		boolean carryTen=false;
		LLNode<Integer> cur1=this.head;
		LLNode<Integer> cur2=that.head;
		int size2;
		if(this.size>that.size)size2=this.size;
		else size2=that.size;
		for(int i=0;i<size2;i++) {
			int digit1;
			int digit2;
			if(cur1==null) digit1=0;
			else digit1=cur1.data;
			if(cur2==null) digit2=0;
			else digit2=cur2.data;
			int tempSum;
			if(carryTen==true) tempSum=digit1+digit2+1;
			else tempSum=digit1+digit2;
			if(tempSum>9) carryTen=true;
			else carryTen=false;
			tempString= tempSum%10 + tempString;
			if(cur1!=null)cur1=cur1.link;
			if(cur2!=null)cur2=cur2.link;
		}
		if(carryTen==true) tempString= 1 + tempString;
		tempInteger=new LargeInteger(tempString);
		return tempInteger;
	}
	
	/** Returns a new LargeInteger object representing the result of multiplying
	 *  this large integer with a non-negative integer x. You can assume x is either
	 *  a positive integer or 0. Hint: you can use a loop and call the 'add' method
	 *  above to accomplish the 'multiply'.
	 */
	public LargeInteger multiply(int x) {
		// TODO
		LargeInteger temp = new LargeInteger("0");
		for(int i=0; i<x; i++) {
			temp=temp.add(this);
		}
		return temp;
	}

	/** Recursive method that converts the list referenced by curr_node back to
	 *  a string representing the integer. Think about what's the base case and
	 *  what it should return. Then think about what it should return in non-base case.
	 *  Hint: refer to the 'printing a list backwards' example we covered in lectures.
	 */
	private String toString(LLNode<Integer> curr_node) {
		// TODO
		String temp="";
		if(curr_node !=null) temp=toString(curr_node.link)+curr_node.data;
		return temp;
	}
	
	/** Convert this list back to a string representing the large integer.
	 *  This is a public method that jump-starts the call to the recursive method above.
	 */
	public String toString() {
		return toString(head);
	}
	
	// Recursive method to compute factorial
	public static LargeInteger factorial(int n) {
		if(n==0) return new LargeInteger("1");
		return factorial(n-1).multiply(n);
	}
	
	// Recursive method to compute power
	public static LargeInteger pow(int x, int y) {
		if(y==0) return new LargeInteger("1");
		return pow(x, y-1).multiply(x);
	}
}

package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game
 */
public class LinkedListGame {

	// TODO: declare data members as necessary
	private LLIntegerNode priorGuesses;
	private LLIntegerNode possibleGuesses;
	private int guess;
	private int priorMatches;
	
	
	/********************************************************
	 * NOTE: for this project you must use linked lists
	 * implemented by yourself. You are NOT ALLOWED to use
	 * Java arrays of any type, or any class in the java.util
	 * package (such as ArrayList).
	 *******************************************************/	 
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, and do NOT add
	 * new files (as they will be ignored by the autograder).
	 *******************************************************/
	
	// LinkedListGame constructor method
	public LinkedListGame() {
		reset();
		
	}
	
	// Resets data members and game state so we can play again
	public void reset() {
		possibleGuesses=null;
		for(int i=9999;i>999;i--) {
			possibleGuesses=insertHead(possibleGuesses,i);
		}											      
		priorGuesses=null;
		guess=1000;
		priorMatches=0;
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		LLIntegerNode node=priorGuesses;
		while(node!=null) {
			if(node.getNum()==n) {
				return true;
			}
			node=node.getLink();
		}
		return false;
		
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
		int numGuesses=0;
		LLIntegerNode node=priorGuesses;
		if(priorGuesses==null) {
			return 0;
		}
		while(node!=null) {
			numGuesses++;
			node=node.getLink();
		}
		return numGuesses;
	}
	
	/**
	 * Returns the number of matches between integers a and b.
	 * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
	 * The return value must be between 0 and 4.
	 * 
	 * A match is the same digit at the same location. For example:
	 *   1234 and 4321 have 0 match;
	 *   1234 and 1114 have 2 matches (1 and 4);
	 *   1000 and 9000 have 3 matches (three 0's).
	 */
	public static int numMatches(int a, int b) {
		int matches = 0;
		for (int i=1; i<=4; i++){
			if(a%10 == b%10){
				matches++;
			}
			if(a>9 && b>9){
				a/=10;
				b/=10;
			}
		}
		return matches;
	}
	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if no candidate is left.
	 */
	public boolean isOver() {
		if(priorMatches==4) {
			return true;
		}
		else if(possibleGuesses==null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the guess number and adds it to the list of prior guesses.
	 * The insertion should occur at the end of the prior guesses list,
	 * so that the order of the nodes follow the order of prior guesses.
	 */	
	public int getGuess() {
		if(priorGuesses==null) {
			priorGuesses=new LLIntegerNode(guess,null);
		}
		else {
			insertTail(priorGuesses,guess);

		}
		return guess;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if no candidate 
	 * is left (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		if(nmatches==4){
			priorMatches=nmatches;
			return true;
		}
		else{
			removeHead();
		}
		LLIntegerNode temp=null;
		LLIntegerNode tempTail=null;
		LLIntegerNode node=possibleGuesses;
		while(node!=null) {
			if(temp==null && numMatches(node.getNum(),guess)==nmatches) {
				temp=node;
				tempTail=temp;
			}
			else if(temp!=null && numMatches(node.getNum(),guess)==nmatches) {
				LLIntegerNode tempNode= new LLIntegerNode(node.getNum(),null);
				tempTail.setLink(tempNode);
				tempTail=tempNode;
			}
			node=node.getLink();
		}
		possibleGuesses=temp;
		
		priorMatches=nmatches;
		if(isOver()==true ){
			return false;
		}
		else{
			guess=possibleGuesses.getNum();
			return true;
		}
		
	}
	
	// Returns the head of the prior guesses list.
	// Returns null if there hasn't been any prior guess
	public LLIntegerNode priorGuesses() {
		if(priorGuesses==null) {
			return null;
		}
		return priorGuesses;
	}
	
	/**
	 * Returns the list of prior guesses as a String. For example,
	 * if the prior guesses are 1000, 2111, 3222, in that order,
	 * the returned string should be "1000, 2111, 3222", in the same order,
	 * with every two numbers separated by a comma and space, except the
	 * last number (which should not be followed by either comma or space).
	 *
	 * Returns an empty string if here hasn't been any prior guess
	 */
	public String priorGuessesString() {
		// TODO
		String tempString="";
		LLIntegerNode node=priorGuesses;
		while(node!=null) {
			tempString+=node.getNum();
			node=node.getLink();
			if(node!=null) {
				tempString+= ", ";
			}
		}
		return tempString;
	}
	
	
	//My Methods
	
	public void removeHead() {
		possibleGuesses=possibleGuesses.getLink();
		
	}
	
	public LLIntegerNode insertHead(LLIntegerNode currentHead, int nodeNum) {
		LLIntegerNode temp=new LLIntegerNode(nodeNum, null);
		temp.setLink(currentHead);
		return temp;
	}
	
	public void insertTail(LLIntegerNode nodeHead, int nodeNum) {
		LLIntegerNode node=nodeHead;
		LLIntegerNode temp=new LLIntegerNode(nodeNum, null);
		while(node.getLink() != null) {
			node = node.getLink();
		}
		node.setLink(temp);
		
	}
	
	//for testing purposes
	public String possibleGuessesString() {
		String tempString="Possible Guesses: \n";
		LLIntegerNode node=possibleGuesses;
		while(node!=null) {
			tempString+=node.getNum();
			node=node.getLink();
			if(node!=null) {
				tempString+= ", ";
			}
		}
		return tempString;
	}
	
}

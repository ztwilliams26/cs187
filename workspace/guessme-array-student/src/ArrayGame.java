package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game
 */
public class ArrayGame {

	// stores the next number to guess
	private int guess;
	
	// TODO: declare additional data members, such as arrays that store
	// prior guesses, eliminated candidates etc.
	
	private int[] priorGuesses;
	private int numGuesses;
	private boolean[] eliminated;
	private int priorMatches;
	
	
	// NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
	// You MAY NOT use any Collection type (such as ArrayList) provided by Java.
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, otherwise your
	 * code will fail the JUnit tests!
	 * Also DO NOT create any new Java files, as they will
	 * be ignored by the autograder!
	 *******************************************************/
	
	// ArrayGame constructor method
	public ArrayGame() {
		guess=1000;
		priorGuesses = null;
		numGuesses = 0;
		eliminated = new boolean[9000];
		for(int i=0; i<9000;i++){
			eliminated[i]=false;
		}
		priorMatches=0;
		

		
	}
	
	// Resets data members and game state so we can play again
	public void reset() {
		guess=1000;
		priorGuesses = null;
		numGuesses = 0;
		priorMatches=0;
		for(int i=0; i<9000;i++){
			eliminated[i]=false;
		}
		
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		for (int i=0; i<numGuesses; i++){
			if (priorGuesses[i]==n) return true;
		}
		return false;
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
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
	public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
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
	 * or if all candidates have been eliminated.
	 */
	public boolean isOver() {
		if(priorMatches==4)return true;
		return false;
	}
	
	// Returns the guess number and adds it to the list of prior guesses.               	OUT OF BOUNDS ERROR HERE
	public int getGuess() {
		int[] tempArray;
		if(numGuesses==0){
			tempArray=new int[1];
			tempArray[0]=guess;
		}
		else{
			tempArray=new int[priorGuesses.length +1];
			for(int i=0;i<priorGuesses.length;i++){
				tempArray[i]=priorGuesses[i];
			}
		}
		priorGuesses=tempArray;
		priorGuesses[numGuesses]=guess;
		numGuesses++;
		
		return guess;
	}
	
	// Returns the list of guesses so far as an integer array.
	// The size of the array must be the number of prior guesses.
	// Returns null if there has been no prior guess
	public int[] priorGuesses() {
		if(numGuesses>0)return priorGuesses;
		else return null;
	}
	

	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if all candidates
	 * have been eliminated (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		if(nmatches==4){
			priorMatches=nmatches;
			return true;
		}
		else{
			eliminated[guess-1000]=true;
		}
		for(int i=0;i<9000;i++){
			if(nmatches==0){
				if(numMatches(i+1000,guess)>0){
					eliminated[i]=true;
				}
				
			}
			else if(numMatches(i+1000,guess)!=nmatches){
				eliminated[i]=true;
			}
			
		}
		for(int i=8999;i>=0;i--){
			if(eliminated[i]==false){
				guess=i+1000;
			}
		}
		
		priorMatches=nmatches;

		if(noAnswer()==true){
			return false;
		}
		else{
			return true;
		}
	}

	
	

/*
 * TODO:
 * 1)
 * 2)
 * 3)
 * 4)
 * 5)
 */


	
	
	
	/*********************************************************************************************************************
	 ********************************************************************************************************************* 
	 *********************************************************************************************************************
	 *********************************************************************************************************************
	 *********************************************************************************************************************
	 *********************************************************************************************************************
	 ***********************************    All of my custom methods      ************************************************
	 *********************************************************************************************************************
	 ************************************     Mostly useless trash       *************************************************
	 *********************************************************************************************************************
	 *********************************************************************************************************************
	*/

	
/*
	//Gets the number at a specified position.
		public int numAtPos(int num, int pos){
			int temp = num;
			for(int i=0;i<pos;i++){
				temp /= 10;
			}
			temp %= 10;
			return temp;
		}
		
	//Build 4 digit number, probably a guess, out of 4 separate digits.
		public int buildNum(int a, int b, int c, int d){
			return 1000*a+100*b+10*c+d;
		}
		
	//Gets next possible digit or the digit that is already confirmed correct for a position.
		public int getNextDigit(int position){
			int tempGuess=-1;
			for(int i=9;i>=0;i--){
				if(elimCandids[position][i]==YES){
					tempGuess=i;
					return tempGuess;
				}
			}
			for(int i=9;i>=0;i--){
				if(elimCandids[position][i]==MAYBE || elimCandids[position][i]==null){
					tempGuess=i;
				}
			}
			
			return tempGuess;
		}
		
	//
		public int getControlDigit(int position){
			int tempDigit=-1;
			for(int i=9;i>=0;i--){
				if(elimCandids[position][i]==NO){
					tempDigit=i;
					return tempDigit;
				}
			}
			return tempDigit;
		}
		
	//
		public void clearNumExcept(int num, int pos){
			for(int i=0; i<4; i++){
				if(i!=pos) elimCandids[i][num]=NO;
			}
			
		}
		
	//
		public int lastNum(int pos){
			int count=0;
			int tempNum=-1;
			for(int i=0;i<10;i++){
				if(elimCandids[pos][i]==MAYBE){
					count++;
					tempNum=i;
				}
			}
			if (count==1) return tempNum;
			else return -1;
		}
*/
	
	
	//
		public boolean noAnswer(){
			boolean temp=true;
			for (int i=0;i<9000;i++){
				if(eliminated[i]==false){
					return false;
				}
			}
			return temp;
		}
}		

/*
		public void printElimCandids(){
			for (int i=0;i<4;i++){
				System.out.print("Digit Position " + i + ": ");
				for (int j=0;j<10;j++){
					System.out.print(j+ elimCandids[i][j]+" ");
				}
				System.out.println(" ");
			}
		}

public boolean updateGuess(int nmatches) {
		// TODO
		guessMatches=nmatches;
		if (numGuesses<10){
			if (nmatches<1){
				for(int i=0; i<4; i++){
					elimCandids[i][numGuesses]=false;
				}
			}
			else{
				possibleDigits++;
			}
			numGuesses++;
			guess=(numGuesses)*1000 + (numGuesses)*100 + (numGuesses)*10 + (numGuesses)*1;
		}
		else if(numGuesses==10){
			int tempGuess=0;
			tempGuess = repopulateTwoDigits(tempGuess)*100;
			for (int i=0; i<10;i++){
				if (elimCandids[0][i]==false){
					tempGuess+=(i*10)+i;
				}
			}
			
			guess=tempGuess;
		}
		else if(numGuesses>10){
			matchesArray[numGuesses-10]=nmatches;
			if((4-currentPos)+1==nmatches){   //if all matches so far are correct...
				if(possibleDigits==4){
					
					//Dispose of the current two digits being worked on ***************
					for(int i=0; i<=3; i++){
						if(i!=currentPos-1){
							elimCandids[i][numAtPos(guess, currentPos-1)]=false;
						}
					}
					for(int i=0; i<=3; i++){
						if(i!=currentPos){
							elimCandids[i][numAtPos(guess, currentPos)]=false;
						}
					}
					//****************************************************************
					
				}
				currentPos--;
			}
			else if((4-currentPos)==nmatches){    //if one of the current position numbers is currently wrong...
				
			}
			else if((4-currentPos)-1==nmatches){   //if both current position numbers are wrong...
				
			}
			
		}
		
		
		return true;
	}
	//Gets next two guesses for the digits.      MIGHT NEED TO FIX THIS
	public int repopulateTwoDigits(int tempGuess){
		int tempCheck=-1;
		for(int i=0; i<10; i++){
			for (int j=0; j<=1; j++){
				if (elimCandids[currentPos-j][i]==true){
					if(possibleDigits==4){
						if(tempCheck!=i){
							tempGuess+=i;
							tempCheck=i;
							if(j==0){
								tempGuess*=10;
							}
						}
					}
					else{
						tempGuess+=i;
						tempCheck=i;
						if(j==0){
							tempGuess*=10;
						}
					}
				}	
			}
			
		}
		return tempGuess;
	}

*/




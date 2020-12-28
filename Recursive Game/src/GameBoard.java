import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * GameBoard containing pathfinder, inAlist, placeZero,
 * fillBoard, commitToTxt, randomNumbers and main methods.
 * Recursively returns true/false value of the of a randomly generated 
 * board configuration.
 *
 * @author Vaansh Lakhwara
 * @version 1.0
 */
public class GameBoard {
	/**
	 * pathfinder method.
	 * To check if the board can be solved.
	 * 
	 * @param b Board as a 2D array.
	 * @param x X-coordinate of 2D array.
	 * @param y Y-coordinate of 2D array.
	 * @param goingthere ArrayList to keep track of coordinates visited.
	 * @return True is board can be solved, false otherwise.
	 */
	public static boolean pathfinder(int[][] b, int x, int y, ArrayList<Integer[]> goingthere ) {		
		//dimension of square and coordinate.
		int d=b.length;		
		Integer[] coord = {x,y};
		
		//if coordinates outside scope, return false.
		if((x>=d || x<=-1) || (y>=d || y<=-1)) {
			return false;
		} 
		
		//if circle is on 0, return true.
		int circle=b[x][y];			
		if(circle==0) {
			return true;
		}
						
		//checks if coordinate has been visited by calling inAlist method.
		if((goingthere.size()==0 || !inAlist(goingthere,coord,0))) {
			goingthere.add(coord);
			
			int up,down,left,right;
			up=x-circle;
			down=x+circle;			
			left=y-circle;
			right=y+circle;
			
			//Goes north if possible.
			if(down<b.length) {				
				if(pathfinder(b,down,y, goingthere)) {
					System.out.println("[" + down + "," + y + "]");
					return true;
				} 
			}
			
			//Goes south if possible.			
			if(up>-1) {
				if(pathfinder(b,up,y,goingthere)) {
					System.out.println("[" + up + "," + y + "]");
					return true;
				} 
			}
			
			//Goes east if possible.
			if(right<b.length) {
				if(pathfinder(b,x,right,goingthere)) {
					System.out.println("[" + x + "," + right + "]");
					return true;
				} 
			}
			
			//Goes west if possible.
			if(left>-1) {
				if(pathfinder(b,x,left,goingthere)) {
					System.out.println("[" + x + "," + left + "]");
					return true;
				} 
			}
		}
		
		//false, if coordinate has been visited.
		return false;
	}
	
	/**
	 * inAlist method.
	 * Checks if integer coordinate exists in ArrayList 
	 * that is not at the last index position by calling 
	 * itself recursively.  
	 * 
	 * @param g ArrayList to check.
	 * @param arr Coordinates to check.
	 * @param a Integer to keep track of recursive call.
	 * @return Boolean value of existence of array as not the last element.
	 */
	public static boolean inAlist(ArrayList<Integer[]> g, Integer[] arr,int a) {
		//Duplicates list.
		ArrayList<Integer[]> g2=g;
		Integer[] b=g2.get(a);
		
		//Compares the two Integer[] arrays by value, not path and makes sure it isn't the last index. 
		if(Arrays.deepEquals(b, arr) && a!=g.size()-1) {			
			return true;
		}		
		
		//Increment a for next recursive call, if applicable.
		a+=1;
		
		//Next call made if a is smaller than the last index.
		if(a<=g.size()-1) {
			return inAlist(g, arr, a);	
		}
		
		//Return false by default.
		return false;
	}
	
	/**
	 * randomNumbers method.
	 * Random number generated between two given values.
	 * 	
	 * @param min Inclusive lowest value to be generated.
	 * @param max Exclusive highest value to be generated.
	 * @return Random integer generated between the min and max values passed through method.  
	 */
	public static int randomNumbers(int min, int max) {		        
		//min is inclusive while max exclusive
		Random random = new Random();        
        int i = random.nextInt(max - min) + min;	        
		return i;
	}
	
	/**
	 * placeZero method. 
	 * Randomly places a 0 on the board.
	 * 
	 * @param board 2D array representing board.
	 * @param dim Dimension of the square board.
	 * @return Chosen value for the coordinate as an array of integers.
	 */
	public static int[] placeZero(int[][] board, int dim) {
		//Coordinates initialized.
		int x = randomNumbers(0, dim-1);
		int y = randomNumbers(0, dim-1);
		
		//In case randomly chosen coordinate is not one of the fours corners, 
		//new value is chosen by calling method recursively.
		if((x==0 || x==dim-1) && (y==0||y==dim-1)) {
			placeZero(board, dim);
		}
		
		//Returns.
		return new int[] {x,y};		
	}
	
	/**
	 * fillBoard method.
	 * Fills rest of the array with random numbers 
	 * less than the board size. 
	 * 
	 * @param board 2D array representing board. 
	 * @param x X-coordinate of board.
	 * @param y Y-coordinate of board.
	 * @param zero Coordinates of zero placed on board.
	 * @param dim Dimension of the square board.
	 */
	public static void fillBoard(int[][] board, int x, int y, int[] zero, int dim) {
		//If position is not zero, a random less than dimension is assigned.
		if (x < board.length) {
	        if (y < board[x].length) {	        	
	        	if(x==zero[0] && y==zero[1]) {
	        		System.out.print("");
	        	}	        		
	        	else {
	        		board[x][y] = randomNumbers(1, dim);
	        	}	        			        		        	
	            //To go to the next column of the board.
	        	fillBoard(board, x, y + 1, zero, dim);	            
	        } 
	        else {
	        	//To go to the next row of the board.
	        	fillBoard(board, x + 1, 0, zero, dim);
	        }
	    }
	}
	
	/**
	 * commitToTxt method.
	 * Append relevant output to text file.
	 * 
	 * @param filledBoard Board with all values assigned.
	 * @param x X-coordinate.
	 * @param y Y-coordinate.
	 * @param p PrintWriter to write.
	 */
	public static void commitToTxt(int[][] filledBoard, int x, int y, PrintWriter p) {
		//in order to display output in a coherent manner.
		if(x < filledBoard.length){			
	        if(y < filledBoard[x].length){	        	
	        	if(filledBoard[x][y]/10>=1) {	        		
	        		System.out.print(filledBoard[x][y] + " ");
	        		p.write(filledBoard[x][y] + " ");
	        	}	        			        	
	        	else {
	        		System.out.print(filledBoard[x][y] + "  ");
	        		p.write(filledBoard[x][y] + "  ");
	        	}	   
	            //To go to the next column of the board.
	            commitToTxt(filledBoard, x, ++y, p);	            
	            return;	            
	        } 
	        System.out.println();
	        p.write("\n");
            //To go to the next row of the board.
	        commitToTxt(filledBoard, ++x, y = 0, p);
	    }
		//Flush values.
		p.flush();
	}
	
	/**
	 * main method.
	 * To handle relevant method calling and 
	 * execution of the program.
	 * 
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		//try-with-resource block used (works with Java 7 or later).
		try(FileWriter f = new FileWriter("log.txt", true); 
			BufferedWriter b = new BufferedWriter(f); 
			PrintWriter p = new PrintWriter(b);
			Scanner scan = new Scanner(System.in);) {
			
			//Arguments to be used later.
			int x,y;	
			boolean solvable;
			ArrayList<Integer[]> goingthere = new ArrayList<>();			
			//To keep track of time taken for program timing.
			double startTime, endTime, elapsed;

			//Random dimension of board.
			int dim = randomNumbers(5, 21);
			int[][] board = new int[dim][dim];
			
			//Place 0 on board.
			int[] zero = placeZero(board, dim);			
			System.out.println("Generated dimension of the board: " + dim + "x" + dim + "\n");			
			p.write("\n \nGenerated dimension of the board: " + dim + "x" + dim + "\n");												
			System.out.println("Destination position (in index starts from 0): Row=" + zero[0]+", Column=" +zero[1]);
			
			//Fill board and store the configuration in text file. 
			fillBoard(board, 0, 0, zero, dim);
			commitToTxt(board, 0, 0, p);						
			
			//Get coordinates of first circle.
			System.out.print("\nChoose a starting point (one of the four corners of the board, separated by a space): ");			
			x = scan.nextInt();
			y = scan.nextInt();						
			scan.close();
			
			//If not one of the four corners, terminate program.
			if(!((x==0 || x==dim-1) && (y==0||y==dim-1))) {
				System.out.println("You did not choose a valid position, try again with a new board! :(");
		        System.out.println("==============================================");
				p.write("You did not choose a valid position, try again with a new board! :(\n");
		        p.write("==============================================");	        	        
				p.flush();
				System.exit(0);
			}
			
			//Store starting coordinates.
			int[] start = {x,y};			
			System.out.println("Starting point chosen: [" + x + ", " + y + "]");
			p.write("\nStarting point chosen: [" + x + ", " + y + "]");																																		        	        	        
	        System.out.println("Path taken/tried (in reverse):");
	        
//	        int[][] falseboard = {{1,4,1,3,1},{4,3,2,1,4}, {3,2,3,1,4}, {1,3,4,2,3}, {3,4,1,2,0}};

	        //Call method on board generated and track time.
	        startTime = System.currentTimeMillis();
	        solvable = pathfinder(board, x,y, goingthere);	        
	        
//	        solvable = pathfinder(falseboard, 0,0, goingthere);
	        endTime = System.currentTimeMillis();
	        elapsed = endTime - startTime;
	        
	        //Store tracked time and whether board is solvable or not in text file along with relevant message. 
	        String sol = solvable?", it is solvable. Time taken: ":", it is NOT solvable. Time taken: ";
	        System.out.println(solvable + sol + elapsed +" ms\n");
	        System.out.println("==============================================");
	        p.write("\n" + solvable + sol + elapsed +" ms.\n");
	        p.write("==============================================");	        	        
	        
		} catch (IOException i)	//In case there is some error with the file handling. 
		{
			System.out.println("Error in file handling.");
			System.out.println("Try resolving the issue and come again, bye! :(");
			System.exit(0);
		}
	}
}

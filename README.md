# GameBoard

## Description

This program is a 1-player game utilizing a chess-like board comprising of n×n squares with n somewhere in the range of 5 and 20, and each square contains a number among 0 and n - 1. The principles of the game are straight forward. The circle denotes the beginning point on the board. The number (x) in the surrounded square means the circle can move x squares on the board. In one stage the circle can move either x squares east, west, north or south. At each progression the picked bearing is fixed. The circle can't move off the board. The lone lawful beginning positions are the four corner squares. The board should contain precisely one objective square with zero as worth that can be found anyplace on the board, however its position can't be indistinguishable from the beginning position.

### Folder structure

Here's the complete folder structure of the project:
```
|-- Assignments
    |-- .gitignore
    |-- Information.txt
    |-- README.md
    |-- pseudocode.txt
    |-- Iterative Game
    |   |-- .classpath
    |   |-- .project
    |   |-- Iterative.jar
    |   |-- log.txt
    |   |-- .settings
    |   |   |-- org.eclipse.jdt.core.prefs
    |   |-- bin
    |   |   |-- GameBoard.class
    |   |   |-- frame.class
    |   |-- doc
    |   |   |-- MagicBoard.html
    |   |   |-- allclasses-frame.html
    |   |   |-- allclasses-noframe.html
    |   |   |-- constant-values.html
    |   |   |-- deprecated-list.html
    |   |   |-- help-doc.html
    |   |   |-- index.html
    |   |   |-- overview-tree.html
    |   |   |-- package-frame.html
    |   |   |-- package-list
    |   |   |-- package-summary.html
    |   |   |-- package-tree.html
    |   |   |-- package-use.html
    |   |   |-- script.js
    |   |   |-- stylesheet.css
    |   |   |-- class-use
    |   |   |   |-- MagicBoard.html
    |   |   |-- index-files
    |   |       |-- index-1.html
    |   |       |-- index-2.html
    |   |       |-- index-3.html
    |   |       |-- index-4.html
    |   |       |-- index-5.html
    |   |       |-- index-6.html
    |   |       |-- index-7.html
    |   |-- src
    |       |-- GameBoard.java
    |-- Recursive Game
        |-- .classpath
        |-- .project
        |-- Recursive.jar
        |-- log.txt
        |-- .settings
        |   |-- org.eclipse.jdt.core.prefs
        |-- bin
        |   |-- GameBoard.class
        |-- doc
        |   |-- MagicBoard.html
        |   |-- allclasses-frame.html
        |   |-- allclasses-noframe.html
        |   |-- constant-values.html
        |   |-- deprecated-list.html
        |   |-- help-doc.html
        |   |-- index.html
        |   |-- overview-tree.html
        |   |-- package-frame.html
        |   |-- package-list
        |   |-- package-summary.html
        |   |-- package-tree.html
        |   |-- package-use.html
        |   |-- script.js
        |   |-- stylesheet.css
        |   |-- class-use
        |   |   |-- MagicBoard.html
        |   |-- index-files
        |       |-- index-1.html
        |       |-- index-2.html
        |       |-- index-3.html
        |       |-- index-4.html
        |       |-- index-5.html
        |       |-- index-6.html
        |-- src
            |-- GameBoard.java
```

## Pseudocode

### Iterative
```
ALGORITHM pathfinder(b, x, y, goingthere)
INPUT board b (2d array) starting coordinates x,y and empty going there array list to keep track of path.
OUTPUT True/false value of the board being solvable.
	
	DECLARE frameStack, d, circle
		
	d=b.length	
	circle=b[x][y]		
	IF(circle=0)
		RETURN TRUE
	ENDIF
		
	DECLARE nextFrame
	frameStack.push(nextFrame)

	WHILE(!frameStack.isEmpty()) 
	DO						
		INITIALIZE tempFrame←frameStack.peek()
		DECLARE newFrame
			
		DECLARE Xcoord, Ycoord, XYvalue
		Xcoord=←tempFrame.getXcoord()
		Ycoord←tempFrame.getYcoord()
		XYvalue←tempFrame.getXYvalue()
			
		INITIALIZE direction ← tempFrame.getdirection()
		goingthere.add(new Integer[] {Xcoord,Ycoord})
			
	
		IF(tempFrame.direction.size()=0) 
			frameStack.pop();	
			goingthere.remove(goingthere.size()-1)
			CONTINUE
		ENDIF

		INITIALIZE lastdirection ← direction.get(direction.size()-1)
		tempFrame.deleteLastDir()
			
		IF(lastdirection='n') 
			IF(b[Xcoord-XYvalue][Ycoord]=0) 
					RETURN TRUE			
				IF(!inAlist(goingthere,new Integer[] {Xcoord-XYvalue,Ycoord},0)) 
					newFrame ← new frame(Xcoord-XYvalue,Ycoord,b[Xcoord-XYvalue][Ycoord],
					getDirections(Xcoord-XYvalue, Ycoord, b[Xcoord-XYvalue][Ycoord], d, 
          goingthere),goingthere)
					frameStack.push(newFrame)
					CONTINUE
				ENDIF
			ENDIF
		ENDIF
			
		IF(lastdirection='s') 
			IF(b[Xcoord+XYvalue][Ycoord]=0) 
					RETURN TRUE			
				IF(!inAlist(goingthere,new Integer[] {Xcoord+XYvalue,Ycoord},0)) 
					newFrame ← new frame(Xcoord+XYvalue,Ycoord,b[Xcoord+XYvalue][Ycoord],
					getDirections(Xcoord+XYvalue, Ycoord, b[Xcoord+XYvalue][Ycoord], d, 
          goingthere),goingthere)
					frameStack.push(newFrame)
					CONTINUE
				ENDIF
			ENDIF
		ENDIF
		
		IF(lastdirection='e') 
			IF(b[Xcoord][Ycoord]=0) 
					RETURN TRUE			
				IF(!inAlist(goingthere,new Integer[] {Xcoord,Ycoord-XYvalue},0)) 
					newFrame ← new frame(Xcoord,Ycoord-XYvalue,b[Xcoord][Ycoord-XYvalue],
					getDirections(Xcoord, Ycoord-XYvalue, b[Xcoord][Ycoord-XYvalue], d, 
          goingthere),goingthere)
					frameStack.push(newFrame)
					CONTINUE
				ENDIF
			ENDIF
		ENDIF
		
		IF(lastdirection='w') 
			IF(b[Xcoord][Ycoord+XYvalue]=0) 
					RETURN TRUE			
				IF(!inAlist(goingthere,new Integer[] {Xcoord,Ycoord+XYvalue},0))
					newFrame ← new frame(Xcoord,Ycoord+XYvalue,b[Xcoord][Ycoord+XYvalue],
					getDirections(Xcoord, Ycoord+XYvalue, b[Xcoord][Ycoord+XYvalue], d, 
          goingthere),goingthere)
					frameStack.push(newFrame)
					CONTINUE
				ENDIF
			ENDIF
		ENDIF
			
	ENDWHILE
	
	RETURN FALSE
```
### Recursive
```
ALGORITHM pathfinder(b, x, y, goingthere)
INPUT board b (2d array) starting coordinates x,y and empty going there array list to keep track of path.
OUTPUT True/false value of the board being solvable.

	DECLARE d,coord
	d←b.length;		
	coord←{x,y};
		
	IF((x>=d OR x<=-1) OR (y>=d OR y<=-1)) 
			RETURN FALSE
	ENDIF
		
	circle←b[x][y]
	IF (circle=0) 
		RETURN TRUE
	ENDIF
						
	IF((goingthere.size()=0 OR !inAlist(goingthere,coord,0))) 
		goingthere.add(coord)			
		
		DECLARE up,down,left,right
		u←x-circle;
		down←x+circle;			
		left←y-circle;
		right←y+circle;
			
		IF(down<b.length) 
			IF(pathfinder(b,down,y, goingthere)) 
				RETURN TRUE
			ENDIF
		ENDIF
	
			
		IF(up>-1) {
			IF(pathfinder(b,up,y,goingthere))
				RETURN TRUE
			ENDIF
		ENDIF
		
		IF(right<b.length) 
			IF(pathfinder(b,x,right,goingthere))
				RETURN TRUE
			ENDIF
		ENDIF
		
		IF(left>-1) 
			pathfinder(b,x,left,goingthere))
				RETURN TRUE
			ENDIF
		ENDIF
	ENDIF
		
	return false;
```

## Output

### Iterative

Examples:
#### Generated Board Is Not Solvable From Selected Starting Point:
```
Generated dimension of the board: 13x13
5  4  1  3  7  11 10 3  10 9  1  3  4  
5  5  8  8  4  9  2  7  4  3  3  9  7  
7  1  4  9  1  5  4  7  10 3  5  8  7  
11 4  10 8  1  9  9  11 7  6  12 12 10 
5  7  7  2  8  5  7  2  1  10 11 2  11 
8  12 3  0  9  6  9  8  8  3  11 10 8  
8  8  3  5  2  2  3  3  3  8  3  11 5  
12 3  5  7  6  8  5  4  10 4  6  3  4  
11 6  10 9  1  6  10 8  12 1  11 6  1  
9  12 5  8  11 12 3  5  9  4  6  2  6  
10 5  7  11 10 6  9  9  8  4  2  10 9  
4  11 11 3  6  7  8  3  7  1  3  5  2  
4  11 11 12 8  8  8  9  10 7  12 8  10 

Starting point chosen: [0, 0]
false, it is NOT solvable. Time taken: 7.0 ms.
==============================================
```
#### Generated Board Is Not Solvable From Selected Starting Point:
```
Generated dimension of the board: 15x15
7  11 14 3  5  9  8  3  13 3  9  1  14 6  12 
8  13 8  14 2  2  5  13 5  11 4  14 13 5  13 
1  9  5  9  5  6  5  8  10 2  8  2  3  3  2  
12 1  4  5  12 9  14 14 13 3  5  11 12 12 5  
5  12 14 3  13 8  13 8  1  9  4  10 8  14 11 
2  2  9  6  1  3  10 11 3  10 6  11 1  11 13 
3  14 4  6  4  2  2  14 13 14 0  4  11 8  5  
4  13 13 6  12 1  8  11 2  7  3  3  3  9  13 
10 4  3  12 4  4  9  3  9  11 13 12 11 11 14 
10 11 1  12 3  5  14 2  2  13 7  12 2  12 2  
11 4  6  1  9  7  8  5  6  14 9  3  8  4  14 
8  2  14 7  4  7  1  8  12 4  11 10 12 9  12 
7  2  11 5  14 9  4  10 11 10 4  4  10 12 8  
8  14 6  5  9  4  2  4  13 2  10 4  12 1  11 
8  11 7  13 8  12 14 4  10 5  14 10 10 10 1  

Starting point chosen: [14, 0]
false, it is NOT solvable. Time taken: 8.0 ms.
==============================================
```
#### Generated Board Is Solvable From Selected Starting Point:
```
Generated dimension of the board: 16x16
3  1  9  8  12 10 9  13 5  9  1  13 6  7  3  10 
3  12 12 8  5  12 7  9  10 14 5  13 13 13 10 3  
15 3  15 2  1  14 15 1  14 7  2  9  13 11 1  6  
9  14 9  5  5  8  10 2  11 12 5  14 10 8  2  8  
2  5  13 13 13 13 5  2  2  1  7  9  9  9  15 11 
9  7  7  4  4  11 12 14 12 0  4  2  9  5  2  14 
15 7  13 13 14 8  11 11 10 10 7  11 14 9  6  11 
11 1  13 13 12 8  12 14 12 7  1  3  14 3  4  14 
12 3  9  7  4  5  12 7  8  5  3  14 4  2  3  1  
4  1  3  5  12 10 3  6  3  10 9  6  1  8  12 13 
12 10 4  12 12 7  9  14 15 8  14 9  7  12 4  1  
10 8  11 11 9  3  3  15 1  3  9  10 5  5  9  5  
11 11 12 6  10 2  9  7  3  12 6  1  4  7  7  13 
8  9  12 9  5  1  10 1  5  13 1  8  15 9  15 5  
2  1  3  2  11 13 7  9  11 3  12 15 6  6  7  4  
7  8  9  5  13 8  1  11 15 11 7  15 12 1  2  2  

Starting point chosen: [0, 15]
true, it is solvable. Time taken: 2.0 ms.
==============================================
```
#### Valid Starting Position Was Not Selected (1 of the 4 Corners):
```
Generated dimension of the board: 17x17
5  1  8  11 2  2  9  3  15 2  6  2  1  8  4  11 9  
15 12 16 12 8  6  5  14 4  12 9  1  11 3  3  1  9  
5  10 16 6  15 7  11 13 5  6  2  3  1  10 15 2  16 
15 2  5  12 1  15 8  5  14 7  6  15 16 1  12 2  4  
15 16 4  1  8  12 1  15 16 15 0  15 14 4  10 5  10 
15 3  4  5  16 8  11 10 8  15 16 9  12 15 1  8  2  
7  15 8  16 15 12 11 8  4  16 1  1  15 4  4  15 5  
11 5  13 13 15 12 6  1  10 7  12 12 12 13 9  7  11 
15 5  3  14 12 1  15 11 7  11 7  14 12 2  1  5  11 
10 4  14 7  6  6  7  13 13 8  6  14 8  7  10 3  9  
5  3  11 3  7  4  12 2  6  8  11 14 11 1  14 2  15 
13 11 9  9  8  12 2  5  6  15 13 12 6  12 4  12 2  
7  10 9  8  10 3  1  14 16 4  16 12 9  16 15 7  9  
6  2  7  7  11 15 1  10 15 13 7  11 6  2  6  15 6  
5  7  4  13 8  14 16 16 1  10 4  14 9  13 11 7  16 
3  14 5  9  12 11 10 3  9  7  2  1  9  11 13 4  13 
5  12 4  1  1  7  15 15 1  12 16 7  16 13 12 5  6  
You did not choose a valid position, try again with a new board! :(
==============================================
```

### Recursive

Examples:
#### Generated Board Is Solvable From Selected Starting Point:
```
Generated dimension of the board: 6x6
1  3  2  4  1  4  
1  1  3  4  3  3  
4  4  1  1  4  4  
2  0  4  3  1  4  
1  1  1  5  5  4  
1  4  3  2  2  3  

Starting point chosen: [0, 5]
true, it is solvable. Time taken: 0.0 ms.
==============================================
```
#### Valid Starting Position Was Not Selected (1 of the 4 Corners):
```
Generated dimension of the board: 7x7
4  6  4  4  3  6  6  
6  6  5  6  1  4  4  
1  6  2  6  6  4  3  
6  4  4  2  6  3  1  
3  5  2  4  4  6  2  
2  5  3  3  0  1  5  
5  3  4  4  4  2  2  
You did not choose a valid position, try again with a new board! :(
==============================================
```
#### Generated Board Is Not Solvable From Selected Starting Point:
```
Generated dimension of the board: 10x10
8  9  7  4  6  4  2  1  5  6  
8  9  9  3  3  9  6  3  7  3  
1  1  8  7  9  3  9  4  0  9  
7  2  7  7  9  7  3  3  5  8  
2  2  5  2  1  9  7  7  6  2  
5  5  4  6  9  4  7  3  2  7  
5  5  2  5  3  5  4  1  7  5  
7  9  3  7  6  1  4  4  1  9  
4  8  7  7  1  9  4  9  1  4  
9  1  4  8  9  4  2  6  9  6  

Starting point chosen: [0, 9]
false, it is NOT solvable. Time taken: 3.0 ms.
==============================================
```
#### Generated Board Is Solvable From Selected Starting Point:
```
Generated dimension of the board: 20x20
12 18 16 7  5  7  8  14 10 18 18 16 6  11 8  15 3  3  14 19 
18 6  1  4  11 14 13 3  14 13 7  17 16 13 4  18 16 1  5  14 
14 10 2  18 19 18 1  14 11 9  12 8  17 12 1  3  15 15 6  2  
3  17 4  8  19 10 19 8  9  18 1  1  2  8  9  11 3  15 11 6  
15 19 8  12 19 4  11 3  14 16 13 9  9  6  12 8  15 15 1  4  
11 5  17 14 11 12 12 19 11 11 14 15 1  17 13 13 7  1  2  4  
14 3  11 16 12 12 5  16 9  19 8  4  13 10 16 10 3  7  1  1  
14 16 17 14 6  13 7  9  8  13 10 8  4  7  18 16 6  11 15 10 
16 17 8  13 7  18 5  9  12 16 9  11 16 7  1  8  11 9  3  19 
13 18 14 9  16 15 11 10 17 4  9  6  17 3  9  10 6  14 13 12 
2  18 18 8  11 8  15 5  9  19 2  12 5  18 3  18 15 18 16 2  
8  14 19 8  11 9  8  12 17 3  5  13 2  16 4  18 6  2  12 16 
10 9  7  13 6  16 7  12 5  7  18 15 18 10 4  11 2  5  13 5  
6  11 4  7  11 7  19 7  15 5  5  19 1  10 7  6  11 10 4  18 
5  6  8  19 14 3  6  14 15 19 9  12 16 19 19 12 15 10 14 13 
12 10 7  16 16 2  13 14 14 4  17 19 10 14 11 19 6  7  8  19 
4  12 12 18 3  6  18 7  1  1  7  4  4  1  10 14 7  6  16 6  
16 7  11 19 7  16 19 9  14 15 13 10 9  8  4  11 0  12 5  19 
11 14 10 10 1  7  2  8  17 13 2  11 16 15 11 15 4  18 8  15 
15 14 17 1  10 4  9  18 11 12 13 11 9  1  6  7  15 7  9  3  

Starting point chosen: [19, 0]
true, it is solvable. Time taken: 6.0 ms.
==============================================
```

## Complexities

### Time complexity
Iterative: O(n^2) 
Recursive: O(n^2)

Worst case: 
Go through entire matrix - n^2
O(edges+vertices)
DFS (Depth First Search)

### Memory complexity
Iterative: O(n^2)
Recursive: O(n^2)

Worst case: 
O(h) - h is height of the tree
DFS (Depth First Search)

## Conclusion
The purpose of this program was to come up with a solution iteratively and recursively, then compare their complexities. We find that the overall time complexity of both the programs is the same, with samples tested for recursive solutions being slightly faster when tested with the given boards.

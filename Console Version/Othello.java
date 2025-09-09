/* Jason Rudinsky
* AP Computer Science A
* February 1, 2021

* This is the code for the class 'Player'. In this code I will have an instance variable for the board,
* methods to check for available spaces depending on which player's turn it is, methods that will replace
* the pieces depending where a piece is placed, and methods to determine where a specific player has
* available moves for where they can place their pieces.  

* 2/1/2021 - Original Version

* 2/25/2021 - Created Methods to get the coordinate as a String,
*  the methods to place a piece on the board, and the methods to check to see 
*  if a piece can be placed in at a possible coordinate

* 3/3/2021 - Added the Horizontal  Methods that will be used to determine if at a specific index
*  a certain player can place their piece

* 3/6/2021 - Added the Vertical and Diagonal Methods that will be used to determine if at
*  a specific index a certain player can place their piece, and also fixed the horizontal
*  and diagonal methods for placing and finding possible places a player can place their
*  specific color piece.

* 3/9/2021 - Fixed a problem with the Method addingAscendingDiagonal
*/

import java.util.Scanner;                                                                                                                       // Imports Java Scanner for User Input
import java.util.ArrayList;                                                                                                                     // Imports the ArrayList structures

public class Othello{                                                                                                                           // Class Block

   String[][] othelloBoard = new String[8][8];                                                                                                  // Defines othelloBoard
                                             
   public Othello(){                                                                                                                            // No Argument Constructor
      othelloBoard = emptyArray(othelloBoard);                                                                                                  // Call to Method emptyArray
   }
   
   public ArrayList<String> emptyList(){                                                                                                        // GETTER Method
      
      ArrayList<String> emptyList = new ArrayList<String>();                                                                                    // Creates an Empty ArrayList
      return emptyList;                                                                                                                         // Returns the ArrayList to the user
   }
   
   
   // This is the Method that is being used for the starting board
   // when the game just starts
   public String[][] emptyArray(String[][] theArray){                                                                                           // Method Block
   
                                                                                                                                                // VARIABLE DEFINITION   
      int row = 0;                                                                                                                              // Defines row
      int column = 0;                                                                                                                           // Defines column
      
      for(row = 0; row < othelloBoard.length; row++){                                                                                           // For Loop
         for(column = 0; column < othelloBoard[row].length; column++){                                                                          // Nested For Loop
            theArray[row][column] = "-";                                                                                                        // Sets the value at the index of row and column
         }
      }
      row = othelloBoard.length / 2;                                                                                                            // Sets the value of row
      column = othelloBoard[row].length / 2;                                                                                                    // Sets the value of column
      
      theArray[row - 1][column - 1] = "B";                                                                                                      // Sets the value at the index of row and column
      theArray[row][column] = "B";                                                                                                              // Sets the value at the index of row and column
      
      theArray[row - 1][column] = "W";                                                                                                          // Sets the value at the index of row and column
      theArray[row][column - 1] = "W";                                                                                                          // Sets the value at the index of row and column
      
      return theArray;                                                                                                                          // Returns 'theArray' to the user
   }
   
   
   
   // This is the method that is being used to print out the 
   // board to the user everytime
   public void printBoard(){                                                                                                                    // Method Block
      
                                                                                                                                                // VARIABLE DEFINITION
      int row = 0;                                                                                                                              // Defines row
      int column = 0;                                                                                                                           // Defines column
      
      char letter = 'a';                                                                                                                        // Defines letter
      
      System.out.printf("\t");                                                                                                                  // Prints out to the user  
      for(row = 0; row < othelloBoard.length; row++){                                                                                           // For Loop
         System.out.printf("%d ", row);                                                                                                         // Prints out to the user
      }
      System.out.println();                                                                                                                     // Prints out a blank line
      
      for(row = 0; row < othelloBoard.length; row++){                                                                                           // For Loop
         System.out.printf("%s\t", letter);                                                                                                     // Prints out to the user
          
         for(column = 0; column < othelloBoard[row].length; column++){                                                                          // Nested For Loop
            System.out.printf("%s ", othelloBoard[row][column]);                                                                                // Prints out to the user
         }
         letter++;                                                                                                                              // Adds to the value of letter
         System.out.println();                                                                                                                  // Prints out a blank line
      }
   }
   
   
   
   // This is the method that is being displayed when the players
   // first start the game. It details what to do for the game and 
   // how they are supposed to enter where they want there pieces to be
   // placed
   public void welcomingStatement(){                                                                                                            // Method Block
      Scanner input = new Scanner(System.in);                                                                                                   // Creates Scanner Object
      
      System.out.println("Thank you for choosing to play my game of Othello.");                                                                 // Prints out to the user
      System.out.println("\nIn this game you will choose between playing as white or " + 
                        "color black. \nThe objective of the game is to have the most number of your pieces " + 
                        "on the board. \nThe game ends when either the other player has no more of their pieces " + 
                        "on the \nboard, or when the entire board is filled.");                                                                   // Prints out to the user
      System.out.println("\nIn the game, you can place a piece on the board where there is a '*' on " +
                         "the board.");
      System.out.println("You can place a piece on a designated spot by first entering the letter of the " + 
                        "\nrow, and then the column number. Like so \"a6\"");                                                                     // Prints out to the user 
      System.out.println("\nWhen you are ready to begin the game please press the \"Enter\" key: ");                                            // Prints out to the user
      input.nextLine();                                                                                                                         // Gets the user's next input
   }
   
   
   
   // This is the method that is being used to eliminate the 
   // possible positions of the most recent player after said
   // player has placed a piece on the board
   public void eliminatePossiblePositions(){                                                                                                    // Method Block
      
                                                                                                                                                // VARIABLE DEFINITION
      int row = 0;                                                                                                                              // Defines row
      int column = 0;                                                                                                                           // Defines column
      
      for(row = 0; row < othelloBoard.length; row++){                                                                                           // For Loop
         for(column = 0; column < othelloBoard[row].length; column++){                                                                          // Nested For Loop
         
            if(othelloBoard[row][column].equals("*")){                                                                                          // If statement
               othelloBoard[row][column] = "-";                                                                                                 // Sets the value at the index of row and column
            }
         }
      }
   }
   
   
   
   // This is the method that is being used to determine whether
   // or not the game is over
   public boolean gameOver(String checkedString){                                                                                               // Method Block
      
                                                                                                                                                // VARIABLE DEFINITION
      int row = 0;                                                                                                                              // Defines row
      int column = 0;                                                                                                                           // Defines column
      
      boolean returnValue = true;                                                                                                               // Defines returnValue
      
      for(row = 0; row < othelloBoard.length; row++){                                                                                           // For Loop
         for(column = 0; column < othelloBoard[row].length; column++){                                                                          // Nested For Loop
                                                   
            if(othelloBoard[row][column].equals("-")){                                                                                          // If statement
               returnValue = false;                                                                                                             // Sets the value of returnValue
            }
         }
      }
      
      if(returnValue){                                                                                                                          // If statement
         return returnValue;                                                                                                                    // Returns the value of returnValue
      }
      returnValue = true;
      for(row = 0; row < othelloBoard.length; row++){                                                                                           // For Loop
         for(column = 0; column < othelloBoard[row].length; column++){                                                                          // Nested For Loop
            
            if(returnValue){                                                                                                                    // If statement
               if(othelloBoard[row][column].equals(checkedString)){                                                                             // Nested If statement
                  returnValue = false;                                                                                                          // Sets the value of returnValue
               }
            }
         }
      }
      
      return returnValue;                                                                                                                       // Returns the value of returnValue to the user 
   }
   
   
   
   // This will be the new hub method to call the different methods to 
   // find the possible coordinates in which a piece can be placed upon the board
   public ArrayList<String> findPossibleCoordinates(String desiredColor){                                                                                    // Method Block
      
                                                                                                                                                // VARIABLE DEFINITIONS
      int LCV, LCV2, row, column;                                                                                                               // Defines int variables
      ArrayList<String> coordinates = new ArrayList<String>();                                                                                  // Creates an arraylist of strings
      
      for(LCV = 0, LCV2 = othelloBoard.length * othelloBoard[0].length; LCV < LCV2; LCV++){                                                     // For Loop
         //System.out.printf("Looking at coordinate: %s %d\n", getCoordinate(LCV / othelloBoard.length, LCV % othelloBoard.length), LCV2);                 // Debug print statement
         row = LCV / othelloBoard.length;                                                                                                     // Sets the value of row
         column = LCV % othelloBoard.length;                                                                                                  // Sets the value of column
         
         if(othelloBoard[row][column].equals(desiredColor)){                                                                                    // If the color is the same as the current player
            //System.out.printf("Looking at coordinate: %s %s\n", getCoordinate(LCV / othelloBoard.length, LCV % othelloBoard.length), desiredColor);     // Debug print statement

            coordinates.add(findCoordinate(desiredColor, row + 1, column, 1, 0));
            coordinates.add(findCoordinate(desiredColor, row - 1, column, -1, 0));
   
            coordinates.add(findCoordinate(desiredColor, row + 1, column + 1, 1, 1));
            coordinates.add(findCoordinate(desiredColor, row + 1, column - 1, 1, -1));
            coordinates.add(findCoordinate(desiredColor, row - 1, column + 1, -1, 1));
            coordinates.add(findCoordinate(desiredColor, row - 1, column - 1, -1, -1));
   
            coordinates.add(findCoordinate(desiredColor, row, column + 1, 0, 1));
            coordinates.add(findCoordinate(desiredColor, row, column - 1, 0, -1));
            
         }
         
      }
      
      coordinates = pruneBlankSpaces(coordinates);
      System.out.println(coordinates);
      
      
      
      return coordinates;
   }
   
   
   
   // This is the method that will be used to place onto the board
   // all of the possible positions that the current player can 
   // place their piece 
   public void placingPossibilities(ArrayList<String> thePossibilities, String thePiece){                                                       // Method Block
      
      // VARIABLE DEFINITION
      int LCV = 0;                                                                                                                              // Defines LCV
      int rowValue = 0;                                                                                                                         // Defines rowValue
      int columnValue = 0;                                                                                                                      // Defines columnValue

      String currentCoordinate = "";                                                                                                            // Defines currentCoordinate

      for(LCV = 0; LCV < thePossibilities.size(); LCV++){                                                                                       // For Loop
         currentCoordinate = thePossibilities.get(LCV);                                                                                         // Sets the value of currentCoordinate

         if(currentCoordinate.length() > 1 && !currentCoordinate.equals("")){                                                                   // If statement
            rowValue = getRowValue(currentCoordinate);                                                                                          // Call to Method getRowValue
            columnValue = getColumnValue(currentCoordinate);                                                                                    // Call to Method getColumnValue
            othelloBoard[rowValue][columnValue] = thePiece;                                                                                     // Sets the value at the index of rowValue and columnValue

         }
      }
   }
   
   
   // This is the method that will be used to find coordinates of where a piece can possibly placed on the board
   public String findCoordinate(String color, int row, int column, int rowInc, int colInc){                                                     // Method Block
      
      //System.out.printf("Looking at coordinate %s %b\n", getCoordinate(row, column), inBounds(row, column));
      if(!inBounds(row, column)){
         return "";
      }
      else if(othelloBoard[row][column].equals(color)){
         return "";
      }
      else if(othelloBoard[row][column].equals("-") && (!othelloBoard[row - rowInc][column - colInc].equals(color) &&
                                                         !othelloBoard[row - rowInc][column - colInc].equals("-"))){
         return getCoordinate(row, column);
      }
      return findCoordinate(color, row + rowInc, column + colInc, rowInc, colInc);
   }
   
   
   
   // This is the method that will be used to eliminate empty strings from the arraylist so only valid spaces remain
   public ArrayList<String> pruneBlankSpaces(ArrayList<String> myList){                                                                         // Method Block
      
      int LCV = 0;                                                                                                                              // Defines LCV
      
      for(LCV = 0; LCV < myList.size(); LCV++){                                                                                                 // For Loop
         if(myList.get(LCV).equals("")){                                                                                                        // Encountered an empty string
            myList.remove(LCV);                                                                                                                 // Removes the element at index LCV
            LCV--;                                                                                                                              // Subtracts from the value of LCV
         }
      }
      return myList;                                                                                                                            // Returns myList back to the user
   }
   
   
   // This is the method that is being used to get the current 
   // count of the number of pieces that each player has
   public int gettingTheCount(String checkedString){                                                                                            // Method Block
   
                                                                                                                                                // VARIABLE DEFINITION
      int row = 0;                                                                                                                              // Defines row
      int column = 0;                                                                                                                           // Defines column
      int counter = 0;                                                                                                                          // Defines counter   
      
      for(row = 0; row < othelloBoard.length; row++){                                                                                           // For Loop
         for(column = 0; column < othelloBoard[row].length; column++){                                                                          // Nested For Loop
         
            if(othelloBoard[row][column].equals(checkedString)){                                                                                // If statement
               counter++;                                                                                                                       // Adds to the value of counter
            }
         }
      }
      return counter;                                                                                                                           // Returns the value of counter
   }
   
   
   
   
   // This is the method that is being used to determine if the
   // String that the user inputted is valid
   public boolean isValid(String theCoordinate){                                                                                                // Method Block
      
                                                                                                                                                // VARIABLE DEFINITION
      char charInString = ' ';                                                                                                                  // Defines charInString
      
      int rowValue = 0;                                                                                                                         // Defines rowValue
      int columnValue = 0;                                                                                                                      // Defines columnValue
      
      boolean returnValue = true;                                                                                                               // Defines returnValue
      
      if(theCoordinate.length() != 2){                                                                                                          // If statement
         returnValue = false;                                                                                                                   // Sets the value of returnValue
      }
      else{                                                                                                                                     // Else statement
         charInString = theCoordinate.charAt(0);                                                                                                // Sets the value of charInString
         rowValue = charInString - 97;                                                                                                          // Sets the value of rowValue
         
         charInString = theCoordinate.charAt(1);                                                                                                // Sets the value of charInString
         columnValue = charInString - 48;                                                                                                       // Sets the value of columnValue
         
         if((rowValue < 0 || rowValue >= othelloBoard.length) || 
               (columnValue < 0 || columnValue >= othelloBoard.length) || !othelloBoard[rowValue][columnValue].equals("*")){                    // If statement
               
            return false;                                                                                                                       // Returns false to the user
         }
      }
      return returnValue;                                                                                                                       // Returns the value of returnValue to the user
   }
   
   
   
   
   
   // This is the method that is being used to determine if the 
   // String that the user entered is in an available spot
   public boolean availableSpot(String theCoordinate){                                                                                          // Method Block
      
                                                                                                                                                // VARIABLE DEFINITION
      char charInString = ' ';                                                                                                                  // Defines charInString
      
      int rowValue = 0;                                                                                                                         // Defines rowValue
      int columnValue = 0;                                                                                                                      // Defines columnValue
      
      boolean returnValue = false;                                                                                                               // Defines returnValue
      
      if(theCoordinate.length() < 2){                                                                                                           // If statement
         return false;                                                                                                                          // Returns false to the user
      }
      
      charInString = theCoordinate.charAt(0);                                                                                                   // Sets the value of charInString
      rowValue = charInString - 97;                                                                                                             // Sets the value of rowValue
         
      charInString = theCoordinate.charAt(1);                                                                                                   // Sets the value of charInString
      columnValue = charInString - 48;                                                                                                          // Sets the value of columnValue
      
      if((rowValue >= 0 && rowValue < othelloBoard.length) && (columnValue >= 0 && columnValue < othelloBoard.length) 
      && othelloBoard[rowValue][columnValue].equals("*")){                                                                                      // If statement
         return true;                                                                                                                           // Returns true to the user
      }  
      else{                                                                                                                                     // Else statement
         return false;                                                                                                                          // Returns false to the user
      }
   }
   
   
   
   // This is the Method that will be used if the current
   // Player has no available moves
   public void noMoves(String certainPlayer){                                                                                                   // Method Block
      
      System.out.printf("There are no moves available for %s\n", certainPlayer);                                                                // Prints out to the user
   }
   
   
   // This is the method that will be used to return the row
   // value of the String that is passed by the user
   public int getRowValue(String currentInput){                                                                                                 // Method Block
      
                                                                                                                                                // VARIABLE DEFINTION
      char charInString = ' ';                                                                                                                  // Defines charInString
      
      int rowValue = 0;                                                                                                                         // Defines rowValue
      int columnValue = 0;                                                                                                                      // Defines columnValue
            
      charInString = currentInput.charAt(0);                                                                                                    // Sets the value of charInString
      rowValue = charInString - 97;                                                                                                             // Sets the value of rowValue
      
      return rowValue;                                                                                                                          // Returns the value of rowValue to the user
   }
   
   
   
   
   // This is the method that will be used to return the row
   // value of the String that is passed by the user
   public int getColumnValue(String currentInput){                                                                                              // Method Block
      
                                                                                                                                                // VARIABLE DEFINTION
      char charInString = ' ';                                                                                                                  // Defines charInString
      
      int columnValue = 0;                                                                                                                      // Defines columnValue
         
      charInString = currentInput.charAt(1);                                                                                                    // Sets the value of charInString
      columnValue = charInString - 48;                                                                                                          // Sets the value of columnValue
      
      return columnValue;                                                                                                                       // Returns the value of columnValue to the user
   }
   
   
   
   // This is the method that will be used to return a 
   // String to the user with the String being a representation
   // of the coordinates at a certain row and column
   public String getCoordinate(int row, int column){                                                                                            // Method Block
      
                                                                                                                                                // VARIABLE DEFINITION
      String returnValue = "";                                                                                                                  // Defines returnValue
      
      char valueForRow = ' ';                                                                                                                   // Defines valueForRow
      
      row = row + 97;                                                                                                                           // Adds to the value of row
      
      valueForRow = (char)row;                                                                                                                  // Sets the value of valueForRow
      returnValue += valueForRow + "" + column;                                                                                                 // Sets the value of returnValue
      
      return returnValue;                                                                                                                       // Returns the String
   }
   
   
   
   // This is just a helper method to make sure the coordinates are within the bounds of the board
   public boolean inBounds(int row, int column){                                                                                                // Method Block
      
      if(row < 0 || row >= othelloBoard.length){
         return false;
      }
      if(column < 0 || column >= othelloBoard.length){
         return false;
      }
      return true;
   }
}
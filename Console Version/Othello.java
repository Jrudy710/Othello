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
   
   
   
   
   // This is the method that will be used to turn the color of the other
   // player's piece's to the current players piece to 
   public void placePiece(String coordinatePlaced, String currentPlayer, String otherPlayer){                                                   // Method Block
      
                                                                                                                                                // VARIABLE DEFINITION
      int rowValue = 0;                                                                                                                         // Defines rowValue
      int columnValue = 0;                                                                                                                      // Defines columnValue
      
      ArrayList<String> changingTheseCoordinateValues = new ArrayList<String>();                                                                // Defines changingTheseCoordinateValues
      
      rowValue = getRowValue(coordinatePlaced);                                                                                                 // Sets the value of rowValue
      columnValue = getColumnValue(coordinatePlaced);                                                                                           // Sets the value of columnValue
      
      othelloBoard[rowValue][columnValue] = currentPlayer;                                                                                      // Sets the value at the index of rowValue and columnmValue
      
      if(canBeHorizontal(rowValue, columnValue, columnValue - 1, otherPlayer, currentPlayer)){                                                  // If statement
                                                                                                                                                // Call to Method addingHorizontalCoordinates
         changingTheseCoordinateValues = addingHorizontalCoordinates(rowValue, columnValue, columnValue - 1, otherPlayer, currentPlayer, changingTheseCoordinateValues);
      }
      if(canBeHorizontal(rowValue, columnValue, columnValue + 1, otherPlayer, currentPlayer)){                                                  // If statement
                                                                                                                                                // Call to Method addingHorizontalCoordinates
         changingTheseCoordinateValues = addingHorizontalCoordinates(rowValue, columnValue, columnValue + 1, otherPlayer, currentPlayer, changingTheseCoordinateValues);
      }
      
      if(canBeVertical(rowValue, columnValue, rowValue - 1, otherPlayer, currentPlayer)){                                                       // If statement
                                                                                                                                                // Call to Method addingVerticalCoordinates
         changingTheseCoordinateValues = addingVerticalCoordinates(rowValue, columnValue, rowValue - 1, otherPlayer, currentPlayer, changingTheseCoordinateValues);
      }
      if(canBeVertical(rowValue, columnValue, rowValue + 1, otherPlayer, currentPlayer)){                                                       // If statement
                                                                                                                                                // Call to Method addingVerticalCoordinates
         changingTheseCoordinateValues = addingVerticalCoordinates(rowValue, columnValue, rowValue + 1, otherPlayer, currentPlayer, changingTheseCoordinateValues);
      }

      if(canBeAscendingDiagonal(rowValue, rowValue + 1, columnValue - 1, currentPlayer, otherPlayer)){                                          // If statement
                                                                                                                                                // Call to Method addingAscendingDiagonal
         changingTheseCoordinateValues = addingAscendingDiagonal(rowValue, rowValue + 1, columnValue - 1, currentPlayer, otherPlayer, changingTheseCoordinateValues);
      }
      if(canBeAscendingDiagonal(rowValue, rowValue - 1, columnValue + 1, currentPlayer, otherPlayer)){                                          // If statement
                                                                                                                                                // Call to Method addingAscendingDiagonal
         changingTheseCoordinateValues = addingAscendingDiagonal(rowValue, rowValue - 1, columnValue + 1, currentPlayer, otherPlayer, changingTheseCoordinateValues);
      }
      
      if(canBeDescendingDiagonal(rowValue, rowValue - 1, columnValue - 1, currentPlayer, otherPlayer)){                                         // If statement
                                                                                                                                                // Call to Method addingDescendingDiagonal
         changingTheseCoordinateValues = addingDescendingDiagonal(rowValue, rowValue - 1, columnValue - 1, currentPlayer, otherPlayer, changingTheseCoordinateValues);
      }
      if(canBeDescendingDiagonal(rowValue, rowValue + 1, columnValue + 1, currentPlayer, otherPlayer)){                                         // If statement
                                                                                                                                                // Call to Method addingDescendingDiagonal
         changingTheseCoordinateValues = addingDescendingDiagonal(rowValue, rowValue + 1, columnValue + 1, currentPlayer, otherPlayer, changingTheseCoordinateValues);
      }
      
      placingPossibilities(changingTheseCoordinateValues, currentPlayer);                                                                       // Call to Method placingPossibilities
   }
   
   
   
   
   // This is the method that will be used to determine if when the user
   // places a piece, if that piece replaces any of the pieces that
   // are in the same row as the placed piece  
   public boolean canBeHorizontal(int row, int col, int newCol, String currentPlayer, String searchingForThisPlayer){                           // Method Block
      
      if(newCol < col){                                                                                                                         // If statement
         if(newCol < 0 || othelloBoard[row][newCol].equals("-") || othelloBoard[row][newCol].equals("*")){                                      // Nested If statement
            return false;                                                                                                                       // Returns false to the user
         }
         else if(othelloBoard[row][newCol].equals(currentPlayer)){                                                                              // Nested Else If statement
            return canBeHorizontal(row, col, newCol - 1, currentPlayer, searchingForThisPlayer);                                                // Recursive call to canBeHorizontal
         }
         else{                                                                                                                                  // Nested Else statement
            if(col - newCol > 1){                                                                                                               // Double Nested If statement
               return true;                                                                                                                     // Returns true to the user
            }
            else{                                                                                                                               // Double Nested Else statement
               return false;                                                                                                                    // Returns false to the user
            }
         }
      }
      else{                                                                                                                                     // Else statement
         if(newCol >= othelloBoard.length || othelloBoard[row][newCol].equals("-") || othelloBoard[row][newCol].equals("*")){                   // Nested If statement
            return false;                                                                                                                       // Returns false to the user
         }
         else if(othelloBoard[row][newCol].equals(currentPlayer)){                                                                              // Nested Else If statement
            return canBeHorizontal(row, col, newCol + 1, currentPlayer, searchingForThisPlayer);                                                // Recursive call to canBeHorizontal
         }
         else{                                                                                                                                  // Nested Else statement
            if(newCol - col > 1){                                                                                                               // Double Nested If statement
               return true;                                                                                                                     // Returns true to the user
            }
            else{                                                                                                                               // Double Nested Else statement
               return false;                                                                                                                    // Returns false to the user
            }
         }
      }
   }
   
   
   
   
   // This is the method that will be used to determine if when the user
   // places a piece, if that piece replaces any of the pieces that
   // are in the same column as the placed piece
   public boolean canBeVertical(int row, int col, int newRow,  String currentPlayer, String searchingForThisPlayer){                            // Method Block
      if(newRow < row){                                                                                                                         // If statement
         if(newRow < 0 || othelloBoard[newRow][col].equals("-") || othelloBoard[newRow][col].equals("*")){                                      // Nested If statement
            return false;                                                                                                                       // Returns false to the user
         }
         else if(othelloBoard[newRow][col].equals(currentPlayer)){                                                                              // Nested Else If statement
            return canBeVertical(row, col, newRow - 1, currentPlayer, searchingForThisPlayer);                                                  // Recursive call to canBeVertical
         }
         else{                                                                                                                                  // Nested Else statement
            if(row - newRow > 1 && othelloBoard[newRow][col].equals(searchingForThisPlayer)){                                                   // Double Nested If statement
               return true;                                                                                                                     // Returns true to the user
            }
            else{                                                                                                                               // Double Nested Else statement
               return false;                                                                                                                    // Returns false to the user
            }
         }
      }
      else{                                                                                                                                     // Else statement
         if(newRow >= othelloBoard.length || othelloBoard[newRow][col].equals("-") || othelloBoard[newRow][col].equals("*")){                   // Nested If statement
            return false;                                                                                                                       // Returns false to the user
         }
         else if(othelloBoard[newRow][col].equals(currentPlayer)){                                                                              // Nested Else If statement
            return canBeVertical(row, col, newRow + 1, currentPlayer, searchingForThisPlayer);                                                  // Recursive call to canBeVertical
         }
         else{                                                                                                                                  // Nested Else statement
            if(newRow - row > 1 && othelloBoard[newRow][col].equals(searchingForThisPlayer)){                                                   // Double Nested If statement
               return true;                                                                                                                     // Returns true to the user
            }
            else{                                                                                                                               // Double Nested Else statement
               return false;                                                                                                                    // Returns false to the user
            }
         }
      }
      
            
   }


   // This is the method that will be used to determine if when the user
   // places a piece, if that piece replaces any of the pieces that
   // are in the same upwards diagonal as the placed piece      
   public boolean canBeAscendingDiagonal(int row, int newRow, int newCol, String currentPlayer, String searchingForThisPlayer){                 // Method Block
      
      if(newRow < row){                                                                                                                         // If statement
         if(newRow < 0 || newCol >= othelloBoard.length || othelloBoard[newRow][newCol].equals("-") 
         || othelloBoard[newRow][newCol].equals("*")){                                                                                          // Nested If statement
            return false;                                                                                                                       // Returns false to the user
         }
         else if(othelloBoard[newRow][newCol].equals(searchingForThisPlayer)){                                                                  // Nested Else If statement
            return canBeAscendingDiagonal(row, newRow - 1, newCol + 1, currentPlayer, searchingForThisPlayer);                                  // Recursive call to canBeAscendingDiagonal
         }
         else{                                                                                                                                  // Nested Else statement
            if(row - newRow > 1 && othelloBoard[newRow][newCol].equals(currentPlayer)){                                                         // Double Nested If statement
               return true;                                                                                                                     // Returns true to the user
            }
            else{                                                                                                                               // Double Nested Else statement
               return false;                                                                                                                    // Returns false to the user
            }
         }
      }
      else{                                                                                                                                     // Else statement
         if(newRow >= othelloBoard.length || newCol < 0 || othelloBoard[newRow][newCol].equals("-") 
            || othelloBoard[newRow][newCol].equals("*")){                                                                                       // Nested If statement
            return false;                                                                                                                       // Returns false to the user
         }
         else if(othelloBoard[newRow][newCol].equals(searchingForThisPlayer)){                                                                  // Nested Else If statement
            return canBeAscendingDiagonal(row, newRow + 1, newCol - 1, currentPlayer, searchingForThisPlayer);                                  // Recursive call to canBeAscendingDiagonal
         }
         else{                                                                                                                                  // Nested Else statement
            if(newRow - row > 1 && othelloBoard[newRow][newCol].equals(currentPlayer)){                                                         // Double Nested If statement
               return true;                                                                                                                     // Returns true to the user   
            }
            else{                                                                                                                               // Double Nested Else statement
               return false;                                                                                                                    // Returns false to the user
            }
         }
      }
   }
   
   
   
   
   // This is the method that will be used to determine if when the user
   // places a piece, if that piece replaces any of the pieces that
   // are in the same descending diagonal as the placed piece 
   public boolean canBeDescendingDiagonal(int row, int newRow, int newCol, String currentPlayer, String searchingForThisPlayer){                // Method Block

      if(newRow < row){                                                                                                                         // If statement
         if(newRow < 0 || newCol < 0 || othelloBoard[newRow][newCol].equals("-") || othelloBoard[newRow][newCol].equals("*")){                  // Nested If statement
            return false;                                                                                                                       // Returns false to the user
         }
         else if(othelloBoard[newRow][newCol].equals(searchingForThisPlayer)){                                                                  // Nested Else If statement
            return canBeDescendingDiagonal(row, newRow - 1, newCol - 1, currentPlayer, searchingForThisPlayer);                                 // Recursive call to canBeDescendingDiagonal
         }
         else{                                                                                                                                  // Nested Else statement
            if(row - newRow > 1 && othelloBoard[newRow][newCol].equals(currentPlayer)){                                                         // Double Nested If statement
               return true;                                                                                                                     // Returns true to the user   
            }
            else{                                                                                                                               // Double Nested Else statement
               return false;                                                                                                                    // Returns false to the user
            }
         }
      }
      else{                                                                                                                                     // Else statement
         if(newRow >= othelloBoard.length || newCol >= othelloBoard.length || othelloBoard[newRow][newCol].equals("-") 
            || othelloBoard[newRow][newCol].equals("*")){                                                                                       // Nested If statement
            return false;                                                                                                                       // Returns false to the user 
         }
         else if(othelloBoard[newRow][newCol].equals(searchingForThisPlayer)){                                                                  // Nested Else If statement
            return canBeDescendingDiagonal(row, newRow + 1, newCol + 1, currentPlayer, searchingForThisPlayer);                                 // Recursive call to canBeDescendingDiagonal
         }
         else{                                                                                                                                  // Nested Else statement
            if(newRow - row > 1 && othelloBoard[newRow][newCol].equals(currentPlayer)){                                                         // Double Nested If statement
               return true;                                                                                                                     // Returns true to the user
            }
            else{                                                                                                                               // Double Nested Else statement
               return false;                                                                                                                    // Returns false to the user
            }
         }
      }
   }
   
   
   
         
   // This is the method that will be used to replace pieces horizontally 
   // on the board
   public ArrayList<String> addingHorizontalCoordinates(int row, int col, int newCol, String searchingForThisPlayer, 
                                                         String currentPlayer, ArrayList<String> theCoordinates){                               // Method Block
      
      if(newCol < col){                                                                                                                         // If statement
         if(newCol < 0 || othelloBoard[row][newCol].equals(currentPlayer)){                                                                     // Nested If statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
         else if(othelloBoard[row][newCol].equals(searchingForThisPlayer)){                                                                     // Nested Else If statement
            theCoordinates.add(getCoordinate(row, newCol));                                                                                     // Adds to the ArrayList theCoordinates
            return addingHorizontalCoordinates(row, col, newCol - 1, searchingForThisPlayer, currentPlayer, theCoordinates);                    // Recursive call to method addingHorizontalCoordinates
         }
         else{                                                                                                                                  // Nested Else statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
      }
      else{                                                                                                                                     // Else statement
         if(newCol >= othelloBoard.length || othelloBoard[row][newCol].equals(currentPlayer)){                                                  // Nested If statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
         else if(othelloBoard[row][newCol].equals(searchingForThisPlayer)){                                                                     // Nested Else If statement
            theCoordinates.add(getCoordinate(row, newCol));                                                                                     // Adds to the ArrayList theCoordinates
            return addingHorizontalCoordinates(row, col, newCol + 1, searchingForThisPlayer, currentPlayer, theCoordinates);                    // Recursive call to method addingHorizontalCoordinates
         }
         else{                                                                                                                                  // Nested Else statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
      }
   }
   
   
   
   
   // This is the method that will be used to replace pieces horizontally 
   // on the board
   public ArrayList<String> addingVerticalCoordinates(int row, int col, int newRow, String searchingForThisPlayer, 
                                                         String currentPlayer, ArrayList<String> theCoordinates){                               // Method Block
      
      if(newRow < row){                                                                                                                         // If statement
         if(newRow < 0 || othelloBoard[newRow][col].equals(currentPlayer)){                                                                     // Nested If statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
         else if(othelloBoard[newRow][col].equals(searchingForThisPlayer)){                                                                     // Nested Else If statement
            theCoordinates.add(getCoordinate(newRow, col));                                                                                     // Adds to the ArrayList theCoordinates
            return addingVerticalCoordinates(row, col, newRow - 1, searchingForThisPlayer, currentPlayer, theCoordinates);                      // Recursive call to method addingVerticalCoordinates
         }
         else{                                                                                                                                  // Nested Else statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
      }
      else{                                                                                                                                     // Else statement
         if(newRow >= othelloBoard.length || othelloBoard[newRow][col].equals(currentPlayer)){                                                  // Nested If statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
         else if(othelloBoard[newRow][col].equals(searchingForThisPlayer)){                                                                     // Nested Else If statement
            theCoordinates.add(getCoordinate(newRow, col));                                                                                     // Adds to the ArrayList theCoordinates
            return addingVerticalCoordinates(row, col, newRow + 1, searchingForThisPlayer, currentPlayer, theCoordinates);                      // Recursive call to method addingVerticalCoordinates
         }
         else{                                                                                                                                  // Nested Else statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
      }
   }
   
   
   
   
   // This is the method that will be used to replace pieces
   // upwards diagonally on the board
   public ArrayList<String> addingAscendingDiagonal(int row, int newRow, int newCol, String searchingForThisPlayer, 
                                                      String currentPlayer, ArrayList<String> theCoordinates){                                  // Method Block
      if(newRow < row){                                                                                                                         // If statement
         if(newRow < 0 || newCol >= othelloBoard.length || othelloBoard[newRow][newCol].equals("-") 
               || othelloBoard[newRow][newCol].equals("*")){                                                                                    // Nested If statement
               
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
         else if(othelloBoard[newRow][newCol].equals(searchingForThisPlayer)){                                                                  // Nested Else If statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
         else{                                                                                                                                  // Nested Else statement
            theCoordinates.add(getCoordinate(newRow, newCol));                                                                                  // Adds to the ArrayList theCoordinates
            return addingAscendingDiagonal(row, newRow - 1, newCol + 1, searchingForThisPlayer, currentPlayer, theCoordinates);                 // Recursive call to addingAscendingDiagonal
         }
      }
      else{                                                                                                                                     // Else statement
         if(newRow >= othelloBoard.length || newCol < 0 || othelloBoard[newRow][newCol].equals("-")
            || othelloBoard[newRow][newCol].equals("*")){                                                                                       // Nested If statement

            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
         else if(othelloBoard[newRow][newCol].equals(searchingForThisPlayer)){                                                                  // Nested Else If statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
         else{                                                                                                                                  // Nested Else statement
            theCoordinates.add(getCoordinate(newRow, newCol));                                                                                  // Adds to the ArrayList theCoordinates
            return addingAscendingDiagonal(row, newRow + 1, newCol - 1, searchingForThisPlayer, currentPlayer, theCoordinates);                 // Recursive call to addingAscendingDiagonal
         }   
      }
   }
   
   
   
   
   // This is the method that will be used to replace pieces
   // descending down diagonally on the board
   public ArrayList<String> addingDescendingDiagonal(int row, int newRow, int newCol, String searchingForThisPlayer, 
                                                      String currentPlayer, ArrayList<String> theCoordinates){                                  // Method Block
      if(newRow < row){                                                                                                                         // If statement
         if(newRow < 0 || newCol < 0 ){                                                                                                         // Nested If statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
         else if(othelloBoard[newRow][newCol].equals(currentPlayer)){                                                                           // Nested Else If statement
            theCoordinates.add(getCoordinate(newRow, newCol));                                                                                  // Adds to the ArrayList theCoordinates
            return addingDescendingDiagonal(row, newRow - 1, newCol - 1, searchingForThisPlayer, currentPlayer, theCoordinates);                // Recursive call to addingDescendingDiagonal
         }
         else{                                                                                                                                  // Nested Else statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
      }
      else{                                                                                                                                     // Else statement
         if(newRow >= othelloBoard.length || newCol >= othelloBoard.length){                                                                    // Nested If statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
         else if(othelloBoard[newRow][newCol].equals(currentPlayer)){                                                                           // Nested Else If statement
            theCoordinates.add(getCoordinate(newRow, newCol));                                                                                  // Adds to the ArrayList theCoordinates
            return addingDescendingDiagonal(row, newRow + 1, newCol + 1, searchingForThisPlayer, currentPlayer, theCoordinates);                // Recursive call to addingDescendingDiagonal
         }
         else{                                                                                                                                  // Nested Else statement
            return theCoordinates;                                                                                                              // Returns the ArrayList to the user
         }
      }
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
   
   
   
   public ArrayList<String> findPossibleCoordinates(String currentPlayer, String searchingForThisPlayer){                                       // Method Block
      
                                                                                                                                                // VARIABLE DEFINITION  
      ArrayList<String> thePossibleCoordinates = new ArrayList<String>();                                                                       // Defines thePossibleCoordinates
      
      int row = 0;                                                                                                                              // Defines row
      int column = 0;                                                                                                                           // Defines column
      
      for(row = 0; row < othelloBoard.length; row++){                                                                                           // For Loop
         for(column = 0; column < othelloBoard[row].length; column++){                                                                          // Nested For Loop
            
            if(othelloBoard[row][column].equals(currentPlayer)){                                                                                // If statement
               
               if(!findHorizontal(row, column, column - 1, currentPlayer, searchingForThisPlayer).equals("")){                                  // Nested If statement
                  thePossibleCoordinates.add(findHorizontal(row, column, (column - 1), currentPlayer, searchingForThisPlayer));                 // Adds to the ArrayList thePossibleCoordinates
               }
               
               if(!findHorizontal(row, column, column + 1, currentPlayer, searchingForThisPlayer).equals("")){                                  // Nested If statement
                  thePossibleCoordinates.add(findHorizontal(row, column, (column + 1), currentPlayer, searchingForThisPlayer));                 // Adds to the ArrayList thePossibleCoordinates
               }
               
               if(!findVertical(row, column, row + 1, currentPlayer, searchingForThisPlayer).equals("")){                                       // Nested If statement
                  thePossibleCoordinates.add(findVertical(row, column, row + 1, currentPlayer, searchingForThisPlayer));                        // Adds to the ArrayList thePossibleCoordinates
               }
               
               if(!findVertical(row, column, row - 1, currentPlayer, searchingForThisPlayer).equals("")){                                       // Nested If statement
                  thePossibleCoordinates.add(findVertical(row, column, row - 1, currentPlayer, searchingForThisPlayer));                        // Adds to the ArrayList thePossibleCoordinates
               }
               
               if(!findDescendingDiagonal(row, row + 1, column + 1, currentPlayer, searchingForThisPlayer).equals("")){                         // Nested If statement
                  thePossibleCoordinates.add(findDescendingDiagonal(row, row + 1, column + 1, currentPlayer, searchingForThisPlayer));          // Adds to the ArrayList thePossibleCoordinates                  
               }
               
               if(!findDescendingDiagonal(row, row - 1, column - 1, currentPlayer, searchingForThisPlayer).equals("")){                         // Nested If statement
                  thePossibleCoordinates.add(findDescendingDiagonal(row, row - 1, column - 1, currentPlayer, searchingForThisPlayer));          // Adds to the ArrayList thePossibleCoordinates                  
               }
               
               if(!findAscendingDiagonal(row, row - 1, column + 1, currentPlayer, searchingForThisPlayer).equals("")){                          // Nested If statement
                  thePossibleCoordinates.add(findAscendingDiagonal(row, row - 1, column + 1, currentPlayer, searchingForThisPlayer));           // Adds to the ArrayList thePossibleCoordinates                  
               }
               
               if(!findAscendingDiagonal(row, row + 1, column - 1, currentPlayer, searchingForThisPlayer).equals("")){                          // Nested If statement
                  thePossibleCoordinates.add(findAscendingDiagonal(row, row + 1, column - 1, currentPlayer, searchingForThisPlayer));           // Adds to the ArrayList thePossibleCoordinates                  
               }
            }
         }
      }
      return thePossibleCoordinates;                                                                                                            // Returns the Arraylist thePossibleCoordinates      
   }
   
   
   
   
   
   // This is one of the methods that will be used for finding the possible coordinates of 
   // potential places a specific player could potentially be able to place a piece
   // horizontally on the board
   public String findHorizontal(int row, int col, int newCol, String currentPlayer, String searchingForThisPlayer){                             // Method Block
      
      if(newCol < col){                                                                                                                         // If statement
         if(newCol < 0 || othelloBoard[row][newCol].equals(currentPlayer)){                                                                     // Nested If statement
            return "";                                                                                                                          // Returns an empty String to the user
         }
         else if(othelloBoard[row][newCol].equals(searchingForThisPlayer)){                                                                     // Nested Else If statement
            return findHorizontal(row, col, newCol - 1, currentPlayer, searchingForThisPlayer);                                                 // Recursive call to Method findHorizontal 
         }
         else{                                                                                                                                  // Nested Else statement
            if((col - newCol) >= 1 && !othelloBoard[row][newCol + 1].equals(currentPlayer)){                                                    // Double Nested If statement
               return getCoordinate(row, newCol);                                                                                               // Returns the String to the user
            }
            else{                                                                                                                               // Double Nested Else statement
               return "";                                                                                                                       // Returns an empty String to the user
            }
         }
      }
      else{                                                                                                                                     // Else statement
         if(newCol >= othelloBoard.length || othelloBoard[row][newCol].equals(currentPlayer)){                                                  // Nested If statement
            return "";                                                                                                                          // Returns an empty String to the user
         }
         else if(othelloBoard[row][newCol].equals(searchingForThisPlayer)){                                                                     // Nested Else If statement
            return findHorizontal(row, col, newCol + 1, currentPlayer, searchingForThisPlayer);                                                 // Recusrive call to Method findHorizontal
         }
         else{                                                                                                                                  // Nested Else statement
            if((newCol - col) >= 1 && !othelloBoard[row][newCol - 1].equals(currentPlayer)){                                                    // Double Nested If statement
               return getCoordinate(row, newCol);                                                                                               // Returns the String to the user
            }
            else{                                                                                                                               // Double Nested Else statement
               return "";                                                                                                                       // Returns an empty String to the user
            }
         }
         
      }
   }
   
   
   
   
   // This is one of the method that will be used for finding the possible coordinates of
   // potential places a spceific player could potentially be able to place a piece 
   // vertically on the board
   public String findVertical(int row, int col, int newRow, String currentPlayer, String searchingForThisPlayer){                               // Method Block
   
      if(newRow < row){                                                                                                                         // If statement
         if(newRow < 0 || othelloBoard[newRow][col].equals(currentPlayer)){                                                                     // Nested If statement
            return "";                                                                                                                          // Returns an empty String to the user
         }
         else if(othelloBoard[newRow][col].equals(searchingForThisPlayer)){                                                                     // Nested Else If statement
            return findVertical(row, col, newRow - 1, currentPlayer, searchingForThisPlayer);                                                   // Recursive call to findVertical
         }
         else{                                                                                                                                  // Nested Else statement
            if(row - newRow > 1 && othelloBoard[newRow + 1][col].equals(searchingForThisPlayer)){                                               // Double Nested If statement
               return getCoordinate(newRow, col);                                                                                               // Returns the String to the user   
            }
            else{                                                                                                                               // Double Nested Else statement
               return "";                                                                                                                       // Returns an empty String to the user
            }
         }
      }
      else{                                                                                                                                     // Else statement
         if(newRow >= othelloBoard.length || othelloBoard[newRow][col].equals(currentPlayer)){                                                  // Nested If statement
            return "";                                                                                                                          // Returns an empty String to the user
         }
         else if(othelloBoard[newRow][col].equals(searchingForThisPlayer)){                                                                     // Nested Else If statement
            return findVertical(row, col, newRow + 1, currentPlayer, searchingForThisPlayer);                                                   // Recursive call to findVertical
         }
         else{                                                                                                                                  // Nested Else statement
            if(newRow - row > 1 && othelloBoard[newRow - 1][col].equals(searchingForThisPlayer)){                                               // Double Nested If statement
               return getCoordinate(newRow, col);                                                                                               // Returns the String to the user   
            }
            else{                                                                                                                               // Double Nested Else statement
               return "";                                                                                                                       // Returns an empty Sring to the user
            }
         }
      }
   }
   
   
   
   
   
   // This is one of the methods that will be used for finding the possible coordinates of
   // potential places a specific player could potentially be able to place a piece
   // diagonally on the board
   public String findDescendingDiagonal(int row, int newRow, int newCol, String currentPlayer, String searchingForThisPlayer){                  // Method Block

      if(newRow < row){                                                                                                                         // If statement
         if(newRow < 0 || newCol < 0 || othelloBoard[newRow][newCol].equals(currentPlayer)){                                                    // Nested If statement
            return "";                                                                                                                          // Returns an empty String to the user
         }
         else if(othelloBoard[newRow][newCol].equals(searchingForThisPlayer)){                                                                  // Nested Else If statement
            return findDescendingDiagonal(row, newRow - 1, newCol - 1, currentPlayer, searchingForThisPlayer);                                  // Recursive call to findDescendingDiagonal
         }
         else{                                                                                                                                  // Nested Else statement
            if(row - newRow > 1 && !othelloBoard[newRow + 1][newCol + 1].equals(currentPlayer)){                                                // Nested If statement
               return getCoordinate(newRow, newCol);                                                                                            // Returns the String to the user   
            }
            else{                                                                                                                               // Nested Else statement
               return "";                                                                                                                       // Returns an empty String to the user
            }
         }
      }
      else{                                                                                                                                     // Else statement
         if(newRow >= othelloBoard.length || newCol >= othelloBoard.length || othelloBoard[newRow][newCol].equals(currentPlayer)){              // Nested If statement
            return "";                                                                                                                          // Returns an empty String to the user
         }
         else if(othelloBoard[newRow][newCol].equals(searchingForThisPlayer)){                                                                  // Nested Else If statement
            return findDescendingDiagonal(row, newRow + 1, newCol + 1, currentPlayer, searchingForThisPlayer);                                  // Recursive call to findDescendingDiagonal
         }
         else{                                                                                                                                  // Nested Else statement
            if(newRow - row > 1 && !othelloBoard[newRow][newCol].equals(currentPlayer)){                                                        // Nested If statement
               return getCoordinate(newRow, newCol);                                                                                            // Returns the String to the user   
            }
            else{                                                                                                                               // Nested Else statement
               return "";                                                                                                                       // Returns an empty String to the user
            }
         }
      }
   }
   
   
   
   
   
   // This is one of the method that will be used for finding the possible coordinates of
   // potential places a specific player could potentially be able to place a piece 
   // diagonally on the board
   public String findAscendingDiagonal(int row, int newRow, int newCol, String currentPlayer, String searchingForThisPlayer){                   // Method Block
      
      if(newRow < row){                                                                                                                         // If statement
         if(newRow < 0 || newCol >= othelloBoard.length || othelloBoard[newRow][newCol].equals(currentPlayer)){                                 // Nested If statement
            return "";                                                                                                                          // Returns an empty String to the user
         }
         else if(othelloBoard[newRow][newCol].equals(searchingForThisPlayer)){                                                                  // Nested Else If statement
            return findAscendingDiagonal(row, newRow - 1, newCol + 1, currentPlayer, searchingForThisPlayer);                                   // Recursive call to findAscendingDiagonal
         }
         else{                                                                                                                                  // Nested Else statement
            if(row - newRow > 1 && !othelloBoard[newRow + 1][newCol - 1].equals(currentPlayer)){                                                // Nested If statement
               return getCoordinate(newRow, newCol);                                                                                            // Returns the String to the user   
            }
            else{                                                                                                                               // Nested Else statement
               return "";                                                                                                                       // Returns an empty String to the user
            }
         }
      }
      else{                                                                                                                                     // Else statement
         if(newRow >= othelloBoard.length || newCol < 0 || othelloBoard[newRow][newCol].equals(currentPlayer)){                                 // Nested If statement
            return "";                                                                                                                          // Returns an empty String to the user
         }
         else if(othelloBoard[newRow][newCol].equals(searchingForThisPlayer)){                                                                  // Nested Else If statement
            return findAscendingDiagonal(row, newRow + 1, newCol - 1, currentPlayer, searchingForThisPlayer);                                   // Recursive call to findAscendingDiagonal
         }
         else{                                                                                                                                  // Nested Else statement
            if(newRow - row > 1 && !othelloBoard[newRow - 1][newCol + 1].equals(currentPlayer)){                                                // Nested If statement
               return getCoordinate(newRow, newCol);                                                                                            // Returns the String to the user   
            }
            else{                                                                                                                               // Nested Else statement
               return "";                                                                                                                       // Returns an empty String to the user
            }
         }
      }
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
}
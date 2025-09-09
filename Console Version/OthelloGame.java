/* Jason Rudinsky
* AP Computer Science A
* February 1, 2021

* This is the program that will be used for the game 'Othello', in this program it will call the classes Othello,
* Player, and Pieces and the program will run until the game is declared over by the program and when the game 
* has been declared over the program will print out to the user who won the game and the score of each player.

* 2/1/2021 - Original Version
* 2/22/2021 - Added the input for the players names and the color of their respective pieces
*/

import java.util.Scanner;                                                                                                                    // Imports Java Scanner for User Input
import java.util.ArrayList;                                                                                                                  // Imports the ArrayList structures 

public class OthelloGame{                                                                                                                    // Class Block
   public static void main(String[] args){                                                                                                   // Method Block
      Scanner input = new Scanner(System.in);                                                                                                // Creates Scanner Object
      
                                                                                                                                             // VARIABLE DEFINITION
      String userInput = "";                                                                                                                 // Defines userInput
      String secondInput = "";                                                                                                               // Defines secondInput
      String checkingString = "";                                                                                                            // Defines checkingString
      String placingPiece = "";                                                                                                              // Defines placingPiece
      
      int theInput = 0;                                                                                                                      // Defines theInput
      int counter  = 0;                                                                                                                      // Defines counter
      int playerTurn = 0;                                                                                                                    // Defines playerTurn
      int noMovesCounter = 0;                                                                                                                // Defines noMovesCounter
            
      ArrayList<String> coordinatePossibilities = new ArrayList<String>();                                                                   // Defines coordinatePossibilities
      
      Player possiblePositions = new Player();                                                                                               // Creates an Object of the Player class
      gamePiece firstPlayer;                                                                                                                 // Defines firstPlayer
      gamePiece secondPlayer;                                                                                                                // Defines secondPlayer
      Othello game = new Othello();                                                                                                          // Creates an Object of the Othello class
      
      
      game.welcomingStatement();                                                                                                             // Call to Method welcomingStatement
      
      System.out.println("What is the name of the first player? ");                                                                          // Prints out to the user
      userInput = input.nextLine();                                                                                                          // Sets the value of userInput
      
      System.out.println("What is the name of the second player? ");                                                                         // Prints out to the user
      secondInput = input.nextLine();                                                                                                        // Sets the value of secondInput
      
      
      System.out.println("\nWho wants to play as the color Black? (Black goes first) \n" + 
                        "Type \"1\" for " + userInput + "\nType \"2\"" +
                        " for " + secondInput);                                                                                              // Prints out to the user
      theInput = input.nextInt();                                                                                                            // Sets the value of theInput
      
      
      while(theInput != 1 && theInput != 2){                                                                                                 // While Loop
         System.out.println("I said pick the number 1 or 2.");                                                                               // Prints out to the user
         theInput = input.nextInt();                                                                                                         // Sets the value of theInput
      }
      input.nextLine();                                                                                                                      // Takes the next line

      System.out.println();                                                                                                                  // Prints out a blank line
      if(theInput == 1){                                                                                                                     // If statement
      
         firstPlayer = new gamePiece(userInput, "Black");                                                                                    // Initializes firstPlayer
         secondPlayer = new gamePiece(secondInput, "White");                                                                                 // Initializes secondPlayer
         System.out.printf("That means that %s will be white", secondInput);                                                                 // Prints out to the user
         
         playerTurn = 0;                                                                                                                     // Sets the value of playerTurn
         checkingString = firstPlayer.getLetterPiece();                                                                                      // Sets the value of checkingString
      }
      else{                                                                                                                                  // Else statement
         
         firstPlayer = new gamePiece(userInput, "White");                                                                                    // Initializes firstPlayer
         secondPlayer = new gamePiece(secondInput, "Black");                                                                                 // Initializes secondPlayer
         System.out.printf("That means that %s will be white", userInput);                                                                   // Prints out to the user
         
         playerTurn = 1;                                                                                                                     // Sets the value of playerTurn
         checkingString = secondPlayer.getLetterPiece();                                                                                     // Sets the value of checkingString
      }
      
      
      System.out.println("\n\n\n");                                                                                                          // Prints out 4 blank lines
            
      
      while(!game.gameOver(checkingString)){                                                                                                 // While Loop
         
         if(playerTurn % 2 == 1){                                                                                                            // If statement
                        
            System.out.printf("It is %s's turn\n\n", secondPlayer.getName());                                                                // Prints out to the user            
            coordinatePossibilities = game.findPossibleCoordinates(secondPlayer.getLetterPiece());                                           // Call to method findPossibleCoordinates
            placingPiece = secondPlayer.getLetterPiece();                                                                                    // Sets the value of placingPiece
            userInput = secondPlayer.getName();                                                                                              // Sets the value of userInput
            
         }
         else{                                                                                                                               // Else statement
            
            System.out.printf("It is %s's turn\n\n", firstPlayer.getName());                                                                 // Prints out to the user
            coordinatePossibilities = game.findPossibleCoordinates(firstPlayer.getLetterPiece());                                            // Call to method findPossibleCoordinates
            placingPiece = firstPlayer.getLetterPiece();                                                                                     // Sets the value of placingPiece
            userInput = firstPlayer.getName();                                                                                               // Sets the value of userInput
         }
         game.placingPossibilities(coordinatePossibilities, possiblePositions.getColor());                                                   // Call to Method placingPossibilities

         game.printBoard();                                                                                                                  // Call to Method printBoard
         
         
         firstPlayer.setCounter(game.gettingTheCount(firstPlayer.getLetterPiece()));                                                         // Call to Method setCounter
         secondPlayer.setCounter(game.gettingTheCount(secondPlayer.getLetterPiece()));                                                       // Call to Method setCounter
         
         if(playerTurn % 2 == 1){                                                                                                            // If statement
            System.out.println("\n" + secondPlayer + "\t\t" + firstPlayer);                                                                  // Prints out to the user
            checkingString = firstPlayer.getLetterPiece();                                                                                   // Sets the value of checkingString
         }
         else{                                                                                                                               // Else statement
            System.out.println("\n" + firstPlayer + "\t\t" + secondPlayer);                                                                  // Prints out to the user
            checkingString = secondPlayer.getLetterPiece();                                                                                  // Sets the value of checkingString
         }
         
         
         if(coordinatePossibilities.size() > 0){                                                                                             // If statement
            System.out.println(userInput + " please enter a spot to place your piece: ");                                                    // Prints out to the user
            userInput = input.nextLine();                                                                                                    // Sets the value of userInput
            noMovesCounter = 0;                                                                                                              // Sets hte value of noMovesCounter
            
            System.out.println(userInput.equals("skip\n"));
            if(userInput.equals("skip")){
               playerTurn++;
               game.eliminatePossiblePositions();                                                                                                  // Call to Method eliminatePossiblePositions
               System.out.println();                                                                                                               // Prints out a blank line
         
               continue;
            }
            
            while(!game.isValid(userInput) || !game.availableSpot(userInput)){                                                               // While Loop
               System.out.printf("\n%s is not a valid coordinate. You are supposed to enter a coordinate " + 
                  "something like\n \"a6\" and it needs to be the coordinates need to match a spot where " + 
                  "there is a '*'\n", userInput);                                                                                            // Prints out to the user
               
               userInput = input.nextLine();                                                                                                 // Sets the value of userInput
            }
            
            
            if(playerTurn % 2 == 1){                                                                                                         // If statement
               //game.placePiece(userInput, secondPlayer.getLetterPiece(), firstPlayer.getLetterPiece());                                      // Call to Method placePiece
            }
            else{                                                                                                                            // Else statement
               //game.placePiece(userInput, firstPlayer.getLetterPiece(), secondPlayer.getLetterPiece());                                      // Call to Method placePiece
            }
         }
         else{                                                                                                                               // Else statement
            if(coordinatePossibilities.size() == 0){                                                                                         // Nested If statement
               if(playerTurn % 2 == 1){                                                                                                      // Double Nested If statement
                  game.noMoves(secondPlayer.getName());                                                                                      // Call to method noMoves
                  noMovesCounter++;                                                                                                          // Adds to the value of noMovesCounter
               }
               else{                                                                                                                         // Double Nested Else statement
                  game.noMoves(firstPlayer.getName());                                                                                       // Call to method noMoves
                  noMovesCounter++;                                                                                                          // Adds to the value of noMovesCounter
               }
               System.out.println("Press \'Enter\' to acknowledge: ");                                                                       // Prints out to the user
               input.nextLine();                                                                                                             // Takes the next line
            }
         }

         game.eliminatePossiblePositions();                                                                                                  // Call to Method eliminatePossiblePositions
         System.out.println();                                                                                                               // Prints out a blank line
         
         if(noMovesCounter >= 2){                                                                                                            // If statement
            break;                                                                                                                           // Breaks out of the while loop
         }
         
         
         playerTurn++;                                                                                                                       // Adds to the value of playerTurn
      }
      game.printBoard();                                                                                                                     // Call to Method printBoard
      firstPlayer.setCounter(game.gettingTheCount(firstPlayer.getLetterPiece()));                                                            // Call to Method setCounter
      secondPlayer.setCounter(game.gettingTheCount(secondPlayer.getLetterPiece()));                                                          // Call to Method setCounter
      
      if(firstPlayer.getCounter() > secondPlayer.getCounter()){                                                                              // If statement
         System.out.println("\n" + firstPlayer + "\t\t" + secondPlayer);                                                                     // Prints out to the user
         System.out.println(firstPlayer.getName() + " Wins!");
      }
      else if(secondPlayer.getCounter() > firstPlayer.getCounter()){                                                                         // Else If statement
         System.out.println("\n" + secondPlayer + "\t\t" + firstPlayer);                                                                     // Prints out to the user
         System.out.println(secondPlayer.getName() + " Wins!");                                                                              // Prints out to the user
      }   
      else{                                                                                                                                  // Else statement
         System.out.println("\n" + secondPlayer + "\t\t" + firstPlayer);                                                                     // Prints out to the user
         System.out.println("Tie!");                                                                                                         // Prints out to the user
      }
   }
}
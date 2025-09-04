/* Jason Rudinsky
* AP Computer Science A
* February 22, 2021

* This is the program that will be used for the game pieces of a player. 
* It is inheriting the name of the player, and the color of the game piece,
* and will also be used to determine if a player can place their piece in a designated position.

* 2/22/2021 - Original Version
*/

public class gamePiece extends Player{                                                          // Class Block
   
                                                                                                // VARIABLE DEFINITION
   String firstLetter = "";                                                                     // Defines firstLetter
   
   public gamePiece(String name, String gameColor){                                             // Argument Constructor
      super(name, gameColor);                                                                   // Inherits the attributes from its Parent class
      firstLetter = gameColor.substring(0, 1);                                                  // Sets the value of firstLetter
   }
   
   public String getLetterPiece(){                                                              // Method Block
      return firstLetter;                                                                       // Returns firstLetter to the user
   }  
}
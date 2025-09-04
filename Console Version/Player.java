/* Jason Rudinsky
* AP Computer Science A
* January 29, 2021

* This is the code for the class 'Player'. In this code, I will have two instance variables, one
* for the name of the player, and one for the color of the piece that they will be using, either
* being black or white. For this class, there will be three objects, two for the players, and 
* one used for where player's will be able to make available moves.

* 1/29/2021 - Original Version
* 2/1/2021 - Added the Instance variable 'counter' to the class
*/


public class Player{                                                                            // Class Block
   
                                                                                                // VARIABLE DEFINITION
   String name = "";                                                                            // Defines name
   String gameColor = "";                                                                       // Defines gameColor  
   
   int counter = 0;                                                                             // Defines counter
   
   public Player(){                                                                             // No Argument Constructor
      name = "Possible positions";                                                              // Sets the value of name
      gameColor = "*";                                                                          // Sets the value of gameColor
   }
   
   public Player(String name, String gameColor){                                                // Argument Constructor
      this.name = name;                                                                         // Sets the value of name
      this.gameColor = gameColor;                                                               // Sets the value of gameColor
   }
   
   public String getName(){                                                                     // Method Block
      return name;                                                                              // Returns 'name' to the user
   }
   
   public String getColor(){                                                                    // Method Block
      return gameColor;                                                                         // Returns 'gameColor' to the user
   }
   
   public int getCounter(){                                                                     // Method Block
      return counter;                                                                           // Returns 'counter' to the user
   }
   
   public void setName(String newName){                                                         // Method Block
      name = newName;                                                                           // Sets the value of 'name'
   }
   
   public void setColor(String color){                                                          // Method Block
      gameColor = color;                                                                        // Sets the value of 'gameColor'
   }
   
   public void setCounter(int theCount){                                                        // Method Block 
      counter = theCount;                                                                       // Sets the value of 'counter'
   }
   
   public String toString(){                                                                    // toString Method
      return name + " (" + gameColor + "): " + counter;                                         // Returns the String to the user
   }
}
/* Jason Rudinsky
* AP Computer Science A
* April 15, 2021

* This is the class for the graphical user interface of the othello/reversi Java Swing Program. The program will
* display a button to start over, the current player's score and a timer that shows how much time the player has
* until they forfeit the game in the panel of the JFrame. The program will display the actual board in the center
* panel of the JFrame showing the current board and where the current player can place a piece. The program will 
* display the scores of both players in the bottom panel and if the current player has no available moves, it will
* display a button that allows the current player to skip their turn.  

* Original Version - 4/15/2021
* Eliminated the JMenu and the JMenuBoard - 4/15/2021
* Changed the Bounds so that the Frame appears more so in the center
   * of a 1920 by 1040 Monitor - 4/19/2021
* Commented out lines 537 - 540 to remove showing the player's where
   * they can place possible pieces - 4/19/2021
* Brought back the option to show possible moves for players - 4/19/2021
* Removed the setCount method - 4/21/2021
* Added a dialogue Box to be displayed if the current player has 
   * no available moves - 4/30/2021
*/

import javax.swing.JFrame;                                                                                                             // Imports the Java Swing JFrame capabilities
import javax.swing.JButton;                                                                                                            // Imports the Java Swing JButton capabilities
import javax.swing.JLabel;                                                                                                             // Imports the Java Swing JLabel capabilities
import javax.swing.JPanel;                                                                                                             // Imports the Java Swing JPanel capabilities
import javax.swing.*;                                                                                                                  // Imports all of the other Java Swing libraries 

import java.util.ArrayList;                                                                                                            // Imports the Java ArrayList Structures

import java.awt.*;                                                                                                                     // Imports all of the Java awt structures
import java.awt.event.*;                                                                                                               // Imports all of thr Java awt event structures

public class overallBoard extends graphicLogicForOthello{                                                                              // Class Block

                                                                                                                                       // VARIABLE DEFINITIONS
   JFrame board;                                                                                                                       // Defines board
   
   Container thePane = null;                                                                                                           // Defines thePane
    
   JLabel timeLabel = new JLabel();                                                                                                    // Defines timeLabel
   JLabel otherTimeLabel = new JLabel();                                                                                               // Defines otherTimeLabel
      
   int elapsedTime = 0;                                                                                                                // Defines elapsedTime
   int seconds = 57;                                                                                                                   // Defines seconds
   int minutes = 29;                                                                                                                   // Defines minutes
   
   int otherSeconds = 57;                                                                                                              // Defines otherSeconds
   int otherMinutes = 29;                                                                                                              // Defines otherMinutes
    
   int theCount = 0;                                                                                                                   // Defines theCount
   int noMovesCounter = 0;                                                                                                             // Defines noMovesCounter
   
   boolean timeToEnd = false;                                                                                                          // Defines timeToEnd
   boolean displayNoMovesMessage = false;                                                                                              // Defines displayNoMoveMessage
   
   String checking = "B";                                                                                                              // Defines checking
      
   String seconds_string = String.format("%02d", seconds);                                                                             // Defines seconds_string
   String minutes_string = String.format("%02d", minutes);                                                                             // Defines minutes_strin
      
   String other_seconds_string = String.format("%02d", otherSeconds);                                                                  // Defines other_seconds_string
   String other_minutes_string = String.format("%02d", otherMinutes);                                                                  // Defines other_minutes_string
   
   // Fundamentals for the Timer is from Bro Code on YouTube
   // Creates the Timer for the Black Player
   Timer blackPlayerTimer = new Timer(1000, new ActionListener(){                                                                      // Creates an object of the Timer class
         
      public void actionPerformed(ActionEvent e){                                                                                      // Action performance
         elapsedTime = 1000;                                                                                                           // Sets the value of elapsedTime
            
            if(seconds - ((elapsedTime/1000) % 60) < 0){                                                                               // If statement
               
               areWeInTheEndgame(board);                                                                                               // Call to Method 'areWeInTheEndgame'
               seconds = 59;                                                                                                           // Sets the value of seconds
               
               if(minutes - 1 < 0){                                                                                                    // Nested If statement
                  blackPlayerTimer.stop();                                                                                             // Stops the timer
               }
               else{                                                                                                                   // Nested Else statement
                  minutes = minutes - 1;                                                                                               // Subtracts from the value of minutes
               }
            }
            else{                                                                                                                      // Else statement
               seconds = seconds - ((elapsedTime/1000) % 60);                                                                          // Subtracts from the value of seconds
            }
            
            seconds_string = String.format("%02d", seconds);                                                                           // Setting the value of seconds_string
            minutes_string = String.format("%02d", minutes);                                                                           // Setting the value of minutes_string
            timeLabel.setText(minutes_string + " : " + seconds_string);                                                                // Sets the text of timeLabel
      }
   });
            
   // Fundamentals for the Timer is from Bro Code on YouTube
   // Creates the Timer for the White Player
   Timer whitePlayerTimer = new Timer(1000, new ActionListener(){                                                                      // Creates an object of the Timer class
         
      public void actionPerformed(ActionEvent e){                                                                                      // Action performance
         elapsedTime = 1000;                                                                                                           // Sets the value of elapsedTime
            
         if(otherSeconds - ((elapsedTime/1000) % 60) < 0){                                                                             // If statement
               
            areWeInTheEndgame(board);                                                                                                  // Call to method 'areWeInTheEndgame'
            otherSeconds = 59;                                                                                                         // Sets the value of otherSeconds
            
            if(otherMinutes - 1 < 0){                                                                                                  // Nested If statement
               whitePlayerTimer.stop();                                                                                                // Stops the timer
            }  
            else{                                                                                                                      // Nested Else statement
               otherMinutes = otherMinutes - 1;                                                                                        // Subtracts from the value of otherMinutes
            }
         }
         else{                                                                                                                         // Else statement
            otherSeconds = otherSeconds - ((elapsedTime/1000) % 60);                                                                   // Subtracts from the value of otherSeconds
         }
            
         other_seconds_string = String.format("%02d", otherSeconds);                                                                   // Sets the value of other_seconds_string
         other_minutes_string = String.format("%02d", otherMinutes);                                                                   // Sets the value of other_minutes_string
            
         otherTimeLabel.setText(other_minutes_string + " : " + other_seconds_string);                                                  // Sets the text of otherTimeLabel
      }
   });
    
    
   
   public overallBoard(int counter){                                                                                                   // Argument Constructor
      super();                                                                                                                         // Inherits the values from the SUPERCLASS
      theCount = counter;                                                                                                              // Sets the value of theCount
      
      otherTimeLabel.setText(other_minutes_string + " : " + other_seconds_string);                                                     // Sets the text of otherTimeLabel
      
      otherTimeLabel.setFont(new Font("Verdana",Font.PLAIN,15));                                                                       // Sets the font of otherTimeLabel
      otherTimeLabel.setBorder(BorderFactory.createBevelBorder(1));                                                                    // Sets the border of otherTimeLabel
      otherTimeLabel.setOpaque(true);                                                                                                  // Sets the opaque to true for otherTimeLabel
      otherTimeLabel.setHorizontalAlignment(JTextField.RIGHT);                                                                         // Sets the horizontal alignment of otherTimeLabel
       
      timeLabel.setText(minutes_string + " : " + seconds_string);                                                                      // Sets the text of timeLabel
      
      timeLabel.setFont(new Font("Verdana",Font.PLAIN,15));                                                                            // Sets the fond of timeLabel
      timeLabel.setBorder(BorderFactory.createBevelBorder(1));                                                                         // Sets the border of timeLabel
      timeLabel.setOpaque(true);                                                                                                       // Sets the opaque to true for timeLabel
      timeLabel.setHorizontalAlignment(JTextField.RIGHT);                                                                              // Sets the horizontal alignment of timeLabel
         
      board = new JFrame("Othello/Reversi Board");                                                                                     // Initializes board
         
      board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                                                            // Sets the Default Close Operation of board
      board.setBounds(520, 150, 650, 650);                                                                                             // Setting the bounds of board
      
      makeBoard(board);                                                                                                                // Call to method 'makeBoard'
      gettingPlayerClick(board);                                                                                                       // Call to method 'gettingPlayerClick'
            
      if(theCount % 2 == 1){                                                                                                           // If statement
         checking = "B";                                                                                                               // Sets the value of checking
      }
      else{                                                                                                                            // Else statement
         checking= "W";                                                                                                                // Sets the value of checking
      }
      
      board.setResizable(false);                                                                                                       // Fixes the size of the JFrame
      board.setVisible(true);                                                                                                          // Setting the visibility of the JFrame to true
   }
   
   
   
   // This is the intermediary method that will be used to make
   // the entirety of the JFrame that will be shown to the user
   public void makeBoard(JFrame board){                                                                                                // Method Block
   
      Container thePane = board.getContentPane();                                                                                      // Sets the value of thePane
      thePane.setLayout(new BorderLayout());                                                                                           // Sets the layout of the Container

      board.add(topFunctions(thePane, board), BorderLayout.NORTH);                                                                     // Adding the top functions to board
      board.add(centerBoard(thePane), BorderLayout.CENTER);                                                                            // Adding the center functions to board
      board.add(bottomFunctions(thePane, board), BorderLayout.SOUTH);                                                                  // Adding the bottom functions to board      
   }
   
   
      
   // This is the method that will be used to create the top 
   // functions for the game. It will consist of a start over 
   // button and the current player's score as well as a timer
   // specific to each player
   public JPanel topFunctions(Container thePane, JFrame board){                                                                        // Method Block
      
                                                                                                                                       // VARIABLE DEFINITIONS
      JPanel theTop = new JPanel(new FlowLayout());                                                                                    // Defines theTop with a new FlowLayout
      JButton startOver = new JButton("Start Over");                                                                                   // Defines startOver
      JLabel player = new JLabel();                                                                                                    // Defines player
      JLabel blank = new JLabel("                              ");                                                                     // Defines blank
      
      
      startOver.addActionListener(new ActionListener(){                                                                                // Adds an Action Listener to the JButton startOver
         public void actionPerformed(ActionEvent e){                                                                                   // Action Performed
            
            startOver(board);                                                                                                          // Call to Method startOver
         }
      });
      
      theTop.add(startOver);                                                                                                           // Adds startOver to theTop
      
      if(theCount % 2 == 0){                                                                                                           // If statement
      
         player.setText("Black: " + gettingTheCount("B"));                                                                             // Sets the text of player
         theTop.add(player);                                                                                                           // Adds player to theTop
         blank.setVisible(true);                                                                                                       // Sets the visibility of blank
         theTop.add(blank);                                                                                                            // Adds blank to theTop 
         
         whitePlayerTimer.stop();                                                                                                      // Stops whitePlayerTimer
         blackPlayerTimer.start();                                                                                                     // Starts blackPlayerTimer
         theTop.add(timeLabel);                                                                                                        // Adds timeLabel to theTop
      }
      else{                                                                                                                            // Else statement
      
         player.setText("White: " + gettingTheCount("W"));                                                                             // Sets the text of player
         theTop.add(player);                                                                                                           // Adds player to theTop
         blank.setVisible(true);                                                                                                       // Sets the visibility of blank
         theTop.add(blank);                                                                                                            // Adds blank to theTop 
         
         whitePlayerTimer.start();                                                                                                     // Starts whitePlayerTimer
         blackPlayerTimer.stop();                                                                                                      // Stops blackPlayerTimer
         theTop.add(otherTimeLabel);                                                                                                   // Adds otherTimerLabel to theTop  
      }
      
      return theTop;                                                                                                                   // Returns theTop to the user
   }
   
   
   
   // This is the method that will be used to start a new game
   // when the user press the JButton startOver 
   public void startOver(JFrame board){                                                                                                // Method Block
      
      emptyArray(getBoard());                                                                                                          // Call to Method emptyArray
      
      theCount = 0;                                                                                                                    // Sets the value of theCount
      noMovesCounter = 0;                                                                                                              // Sets the value of noMovesCounter
      
      checking = "B";                                                                                                                  // Sets the value of checking
      
      resetTimers();                                                                                                                   // Call to Method resetTimers

      refresh(board);                                                                                                                  // Call to Method refresh
   }
   
   
   // This is the Method that will be used to reset
   // both of the player's timers if the startOver button
   // is pressed
   public void resetTimers(){                                                                                                          // Method Block
   
      seconds = 58;                                                                                                                    // Sets the value of seconds
      minutes = 29;                                                                                                                    // Sets the value of minutes
   
      otherSeconds = 58;                                                                                                               // Sets the value of otherSeconds
      otherMinutes = 29;                                                                                                               // Sets the value of otherMinutes
   }
   
   
   // This is the method that will be used to create the grid for the board 
   public JPanel centerBoard(Container thePane){                                                                                       // Method Block
      
                                                                                                                                       // VARIABLE DEFINITION
      JPanel boardPanel = new JPanel(new GridLayout(8,8));                                                                             // Defines boardPanel and Sets the GridLayout
      boardPanel.setSize(450, 450);                                                                                                    // Sets the size of boardPanel
      
      for(int row  = 0; row < 8; row++){                                                                                               // For Loop
         for(int column = 0; column < 8; column++){                                                                                    // Nested For Loop
            JPanel cell = new JPanel(new BorderLayout());                                                                              // Defines cell with a borderLayout
            cell.setSize(70, 70);                                                                                                      // Sets the size of cell
            cell.setBackground(new Color(57, 163, 59));                                                                                // Sets the background color of cell
            
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));                                                               // Sets the border of cell
            boardPanel.add(cell);                                                                                                      // Adds cell to boardPanel
            
         }
      }
      
      addingPieces(boardPanel, checking);                                                                                              // Call to Method addingPieces
      thePane.add(boardPanel, BorderLayout.CENTER);                                                                                    // Adds boardPanel to the center of the Content Pane
      
      return boardPanel;                                                                                                               // Returns boardPanel to the user
   }
   
   
   // This is the code that will be used to add pieces to the graphical
   // user interface to show the updating of the game board
   public void addingPieces(JPanel boardPanel, String color){                                                                          // Method Block
      
                                                                                                                                       // VARIABLE DEFINITION
      ArrayList<String> possibleCoordinates = new ArrayList<String>();                                                                 // Defines possibleCoordinates
      String[][] actualBoard;                                                                                                          // Defines actualBoard
      
      eliminatePossiblePositions();                                                                                                    // Call to Method eliminatePossiblePositions
      
      if(color.equals("W")){                                                                                                           // If statement
         possibleCoordinates = findPossibleCoordinates(color, "B");                                                                    // Call to Method findPossibleCoordinates
      }
      else{                                                                                                                            // Else statement
         possibleCoordinates = findPossibleCoordinates(color, "W");                                                                    // Call to Method findPossibleCoordinates
      } 
       
      placingPossibilities(possibleCoordinates, "*");                                                                                  // Call to Method placingPossibilities
      
      actualBoard = getBoard();                                                                                                        // Initializes getBoard  
      
      // This is the code that will be used to add
      // the pieces to the GUI board
      for(int row = 0; row < 8; row++){                                                                                                // For Loop
         for(int column = 0; column < 8; column++){                                                                                    // Nested For Loop
            ImageIcon piece;                                                                                                           // Defines piece
            
            String imageName = "";                                                                                                     // Defines imageName
            
            if(actualBoard[row][column].equals("B")){                                                                                  // If statement
               imageName = "dark";                                                                                                     // Sets the value of imageName
            }  
            else if(actualBoard[row][column].equals("W")){                                                                             // Else If statement
               imageName = "light";                                                                                                    // sets the value of imageName
            }
            else if(actualBoard[row][column].equals("*")){                                                                             // Else If statement
               imageName = "transparent";                                                                                              // Sets the value of imageName
            }
            
            if(!imageName.equals("")){                                                                                                 // If statement
               piece = new ImageIcon(imageName + ".png");                                                                              // Initializes piece
               JLabel pieceAtIndex = new JLabel(piece);                                                                                // Defines pieceAtIndex
               
               JPanel myPiece = (JPanel)boardPanel.getComponent(findComponentNum(row, column));                                        // Defines myPiece
               myPiece.removeAll();                                                                                                    // Removes everything from myPiece
               myPiece.add(pieceAtIndex);                                                                                              // Adds pieceAtIndex to myPiece
               
            }
         }
      }
   }

   
   
   
   // This is the method that will be used to return the bottom functions  
   // for the JFrame that will be displayed to the user
   public JPanel bottomFunctions(Container thePane, JFrame board){                                                                     // Method Block
   
                                                                                                                                       // VARIABLE DEFINITIONS
      JPanel bottom = new JPanel(new FlowLayout());                                                                                    // Defines bottom with a new FlowLayout
      
      JButton passTurn = new JButton("Pass Turn");                                                                                     // Defines the JButton passTurn
      
      JLabel blank = new JLabel("                     ");                                                                              // Defines the JLabel blank
      JLabel blackPlayer = new JLabel();                                                                                               // Defines the JLabel blackPlayer
      JLabel whitePlayer = new JLabel();                                                                                               // Defines the JLabel whitePlayer
      
      ArrayList<String> thePossibilities;                                                                                              // Defines the ArrayList thePossibilities
      
      blackPlayer.setText("Black: " + gettingTheCount("B"));                                                                           // Setting the text of blackPlayer
      whitePlayer.setText("White: " + gettingTheCount("W"));                                                                           // Setting the text of whitePlayer
      
      passTurn.addActionListener(new ActionListener(){                                                                                 // Adds an actionListener to passTurn
         public void actionPerformed(ActionEvent e){                                                                                   // Action Performed event
            
            theCount++;                                                                                                                // Adds to the value of theCount
            noMovesCounter++;                                                                                                          // Adds to the value of noMovesCounter
            refresh(board);                                                                                                            // Call to method refresh
         }
      });
      
      
      if(checking.equals("W")){                                                                                                        // If statement
         thePossibilities = findPossibleCoordinates("W", "B");                                                                         // Initializes thePossibilities
      }
      else{                                                                                                                            // Else statement
         thePossibilities = findPossibleCoordinates("B", "W");                                                                         // Initializing thePossibilities
      }
      
      
      if(thePossibilities.size() > 0){                                                                                                 // If statement
         bottom.add(blackPlayer);                                                                                                      // Adds blackPlayer to the JPanel bottom
         bottom.add(blank);                                                                                                            // Adds blank to the JPanel bottom
         
         noMovesCounter = 0;                                                                                                           // Sets the value of noMovesCounter
         
         bottom.add(whitePlayer);                                                                                                      // Adds whitePlayer to the JPanel bottom
         
         displayNoMovesMessage = false;                                                                                                // Sets the value of displayNoMovesMessage
      }
      else{                                                                                                                            // Else statement
         bottom.add(blackPlayer);                                                                                                      // Adds blackPlayer to the JPanel Bottom
         bottom.add(passTurn);                                                                                                         // Adds passTurn to the JPanel bottom
         
         noMovesCounter++;                                                                                                             // Adds to the value of noMovesCounter
         
         bottom.add(whitePlayer);                                                                                                      // Adds whitePlayer to the JPanel bottom
         
         displayNoMovesMessage = true;                                                                                                 // Sets the value of displayNoMovesMessage
      }
                        
            
      return bottom;                                                                                                                   // Returns the JPanel bottom to the user
   }
   
   
   // This is the method that will be used to get where the player 
   // clicks on the screen and if that area matches the spot of a 
   // placeable piece then a piece will be placed in that spot
   public void gettingPlayerClick(JFrame board){                                                                                       // Method Block
      
      board.addMouseListener(new MouseListener() {                                                                                     // Mouse Listener Event for the JFrame
         public void mousePressed(MouseEvent me) { }
        
         public void mouseReleased(MouseEvent me) { }
        
         public void mouseEntered(MouseEvent me) { }
        
         public void mouseExited(MouseEvent me) { }
         
         public void mouseClicked(MouseEvent me) {                                                                                     // Method for if the mouse if clicked
            
                                                                                                                                       // VARIABLE DEFINITIONS
            int x = me.getX();                                                                                                         // Defines x
            int y = me.getY();                                                                                                         // Defines y
            
            double xRounded = (double)x / 71.2;                                                                                        // Defines xRounded
            double yRounded = (double)y / 71.5;                                                                                        // Defines yRounded
            
            xRounded += 0.5;                                                                                                           // Adding to the value of xRounded
            yRounded += 0.5;                                                                                                           // Adding to the value of yRounded
            
            x = (int)xRounded - 1;                                                                                                     // Sets the value of x
            y = (int)yRounded - 1;                                                                                                     // Sets the value of y
            
            
            if(isValid(y, x) && availableSpot(y, x)){                                                                                  // If statement
               if(checking.equals("W")){                                                                                               // Nested If statement
                  placePiece(getCoordinate(y, x), "B", "W");                                                                           // Call to Method placePiece
                  theCount++;                                                                                                          // Adds to the value of theCount
               }
               else{                                                                                                                   // Nested Else statement
                  placePiece(getCoordinate(y, x), "W", "B");                                                                           // Call to Method placePiece
                  theCount++;                                                                                                          // Adds to the value of theCount
               }            
               refresh(board);                                                                                                         // Call to Method refresh
            }
         }
      });
   }
      
   
   
   
   
   
   
   // This is the method that will be used to determine if it is
   // time for the current game to end and if it is time for the game
   // to be over then an instance variable will be set to true
   public void areWeInTheEndgame(JFrame board){                                                                                        // Method Block
                                                                                                           
                                                                                                                                       // VARIABLE DEFINITIONS
      JFrame results = new JFrame("Results of the Game");                                                                              // Defines results
      
      String theChecker = "";                                                                                                          // Defines theChecker
      
      if(checking.equals("W")){                                                                                                        // If statement 
         theChecker = "B";                                                                                                             // Sets the value of theChecker
      }
      else{                                                                                                                            // Else statement
         theChecker = "W";                                                                                                             // Sets the value of theChecker
      }
      
      if(((gettingTheCount("B") + gettingTheCount("W")) == 64) || (gameOver(theChecker)) || noMovesCounter >= 2){                      // If statement
         
         eliminatePossiblePositions();                                                                                                 // Call to Method eliminatePossiblePositions

         timeToEnd = true;                                                                                                             // Sets the value of the INSTANCE VARIABLE timeToEnd
         
         blackPlayerTimer.stop();                                                                                                      // Stops blackPlayerTimer
         whitePlayerTimer.stop();                                                                                                      // Stops whitePlayerTimer
      }
      
      
      // This is the code for if the black
      // player runs out of time 
      if(seconds == 0 && minutes == 0){                                                                                                // If statement
         JOptionPane.showMessageDialog(results, "White Wins!");                                                                        // Displays the Message to the user
         System.exit(0);                                                                                                               // Closes the game when the player press 'ok'
      }
      
      
      // This is the code for if the white
      // player runs out of time
      if(otherSeconds == 0 && otherMinutes == 0){                                                                                      // If statement
         JOptionPane.showMessageDialog(results, "Black Wins!");                                                                        // Displays the Message to the user
         System.exit(0);                                                                                                               // Closes the game when the player press 'ok'
      }
      
   }
   
   
   
   // This is the method that will be used to refresh the 
   // board for the next player 
   public void refresh(JFrame board){                                                                                                  // Method Block
      
      eliminatePossiblePositions();                                                                                                    // Call to Method eliminatePossiblePositions
      
      Container thePane = board.getContentPane();                                                                                      // Initializes thePane
      thePane.setLayout(new BorderLayout());                                                                                           // Sets the Layout to a new BorderLayout
      
      thePane.removeAll();                                                                                                             // Removes everything from thePane
      
      otherTimeLabel.setText(other_minutes_string + " : " + other_seconds_string);                                                     // Sets the text of otherTimeLabel
      
      otherTimeLabel.setFont(new Font("Verdana",Font.PLAIN,15));                                                                       // Sets the font of otherTimeLabel
      otherTimeLabel.setBorder(BorderFactory.createBevelBorder(1));                                                                    // Sets the border of otherTimeLabel
      otherTimeLabel.setOpaque(true);                                                                                                  // Sets the opaque to true for otherTimeLabel
      otherTimeLabel.setHorizontalAlignment(JTextField.RIGHT);                                                                         // Sets the horizontal alignment of otherTimeLabel
       
      timeLabel.setText(minutes_string + " : " + seconds_string);                                                                      // Sets the text of timeLabel
      
      timeLabel.setFont(new Font("Verdana",Font.PLAIN,15));                                                                            // Sets the fond of timeLabel
      timeLabel.setBorder(BorderFactory.createBevelBorder(1));                                                                         // Sets the border of timeLabel
      timeLabel.setOpaque(true);                                                                                                       // Sets the opaque to true for timeLabel
      timeLabel.setHorizontalAlignment(JTextField.RIGHT);                                                                              // Sets the horizontal alignment of timeLabel
         
      makeBoard(board);                                                                                                                // Call to Method makeBoard
      
      gettingPlayerClick(board);                                                                                                       // Call to Method gettingPlayerClick


      if(theCount % 2 == 1){                                                                                                           // If statement
         checking = "B";                                                                                                               // Sets the value of the INSTANCE VARIABLE checking
      }
      else{                                                                                                                            // Else statement
         checking= "W";                                                                                                                // Sets the value of the INSTANCE VARIABLE checking
      }
      
      areWeInTheEndgame(board);                                                                                                        // Call to Method areWeInTheEndgame

      board.setResizable(false);                                                                                                       // Sets the resizablility of the JFrame to false
      board.setVisible(true);                                                                                                          // Sets the visibility of the JFrame to true
      
      if(displayNoMovesMessage && !timeToEnd){                                                                                         // If statement
         showSkipTurn();                                                                                                               // Call to Method showSkipTurn
      }
      
      if(timeToEnd){                                                                                                                   // If statement
         displayMessage();                                                                                                             // Call to Method displayMessage
      }
   }
   
   
   
   // This is the method that will be used to display 
   // who won the game if the game ends without either
   // of the players running out of time
   public void displayMessage(){                                                                                                       // Method Block
      
      Frame results = new JFrame("Results of the Game");                                                                               // Defines results
         
      if(gettingTheCount("B") > gettingTheCount("W")){                                                                                 // If statement
      
         JOptionPane.showMessageDialog(results, "Black Wins!");                                                                        // Displays the Message to the user
      }
      else if(gettingTheCount("B") < gettingTheCount("W")){                                                                            // Else If statement
      
         JOptionPane.showMessageDialog(results, "White Wins!");                                                                        // Displays the Message to the user
      }
      else{                                                                                                                            // Else statement
      
         JOptionPane.showMessageDialog(results, "Tie!");                                                                               // Displays the Message to the user
      }
         
      System.exit(0);                                                                                                                  // Closes all of the open JFrames when the user clicks 'ok'
   }
   
   
   
   // This is the method that will be used to display
   // to the user if a certain player does not have any
   // available moves and will advice the current player
   // to hit the Skip Turn button
   public void showSkipTurn(){
      
      Frame skipTurnMessage = new JFrame("Message to player");                                                                         // Defines skipTurnMessage
      
      if(checking.equals("W")){
         
         JOptionPane.showMessageDialog(skipTurnMessage, "White has no available moves. Please press the \"Pass Turn\" button");        // Displays the Message to the user                                                   
      }
      else{
         
         JOptionPane.showMessageDialog(skipTurnMessage, "Black has no available moves. Please press the \"Pass Turn\" button");        // Displays the Message to the user
      }
   }
}
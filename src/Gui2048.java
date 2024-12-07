////////////////////////////////////////////////////////////////////////////////
// Title:              The game 2048
// Files:              Board.java, Direction.java, Constants2048.java
// Quarter:            Winter 2021
//
// Author:             Rachel Chung, Juan Dominguez
// Email:              (your email address)
// Instructor's Name:  Christine Alvarado, Greg Miranda
//
//////////////////////////// 80 columns wide //////////////////////////////////

import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;
import java.io.*;

/**
 * Gui2048 class which contains the basic start method to create a GUI
 * for the game 2048.
 *
 * Bugs: None known
 *
 * @author Juan Domingues, Rachel Chung
 */
public class Gui2048 extends Application
{
    // Instance variables
    private GridPane pane;          // Pane to hold the game tiles
    private String outputBoard;       // The filename for where to save the Board
    private Board board;            // The 2048 Game Board

    
    /** Add your own Instance Variables here */

    private StackPane stackPane; 
    private int score;
    private int boardSize;
    private Text scoreText = new Text();
    private Rectangle scoreSquare;
    private ArrayList<Rectangle> tileList = new ArrayList<Rectangle>();
    private ArrayList<Text> textList = new ArrayList<Text>();
    private int[][] grid;
    private double tileSize = 100;
    private int gameOverCounter = 0; 

    
    /**
     * The purpose of this method is to construct the initial state of
     * the board using GUI. This method will be called on once, and so only
     * initializes the board to its beginning state.
     *
     * @param primaryStage the stage on which all the components
     *                     of the GUI will be drawn
     */
    @Override
    public void start(Stage primaryStage)
    {
        // Process Arguments and Initialize the Game Board
        processArgs(getParameters().getRaw().toArray(new String[0]));

        // Create the pane that will hold all of the visual objects
        stackPane = new StackPane();
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setStyle("-fx-background-color: rgb(187, 173, 160)");
        // Set the spacing between the Tiles
        pane.setHgap(15); 
        pane.setVgap(15);

        //set title of game
        Rectangle titleSquare = new Rectangle();
        Text titleText = displayText(titleSquare, "2048");

        createScoreDisplay();

        //save board to be able to read certain information
        File file = trySaveForTemp("temporaryFile.board");
        updateBoardInfo("temporaryFile.board");
        file.delete();

        scoreText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        scoreText.setFill(Color.BLACK);

        //adds the desired number of tiles to the stage and stores them in
        //ArrayLists so they may be updated later
        resizableGrid();
        createTiles();
        displayTiles();
        
        //add title and score to GridPane, placed on a StackPane
        pane.add(titleSquare, 0, 0);
        pane.add(titleText, 0, 0);
        GridPane.setHalignment(titleText, HPos.CENTER);
        //set the 1 column before the end and let it span multiple columns
        pane.add(scoreSquare, this.boardSize-2, 0);
        pane.add(scoreText, this.boardSize-2, 0);
        GridPane.setHalignment(scoreText, HPos.CENTER);
        GridPane.setColumnSpan(scoreText, 3);
        stackPane.getChildren().add(pane);

        //adds the panes to a scene and the scene to the stage
        Scene scene = new Scene(stackPane);
        BoardKeyHandler keyHandler = new BoardKeyHandler();
        scene.setOnKeyPressed(keyHandler);
        primaryStage.setTitle("Gui2048");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Creates the tiles that will be displayed on the GUI (the board squares)
     */
    private void createTiles() {
        for (int row = 0; row < boardSize; row++) {
           for (int column = 0; column < boardSize; column++) {
              Rectangle tile = new Rectangle();
              tile.setWidth(tileSize);
              tile.setHeight(tileSize);
              tileList.add(tile);
              Text tileText = new Text();
              tileText.setText("");
              textList.add(tileText);
              pane.add(tile, column, row+1);
              pane.add(tileText,column, row+1);
              GridPane.setHalignment(tileText, HPos.CENTER);
           }
        }
    }

    /**
     * create 100x100 text element to display
     * @param rect Rectangle to overlay text on
     * @param text text to display
     * @return Text object created on rectangle rect
     */
    private Text displayText(Rectangle rect, String text) {
        rect.setWidth(100);
        rect.setHeight(100);
        rect.setFill(null);
        Text titleText = new Text();
        titleText.setText(text);
        // Feel free to change
        titleText.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
        titleText.setFill(Color.BLACK);
        return titleText;
    }

    /**
     * Initialize the score display
     */
    private void createScoreDisplay() {
        this.scoreSquare = new Rectangle();
        scoreSquare.setWidth(100);
        scoreSquare.setHeight(100);
        scoreSquare.setFill(null);
    }

    /**
     * Resizes the size of the tiles for grids of size greater than four.
     */
    private void resizableGrid() {
       //if the size is greater than four resize by a set percentage for every
       //extra tile
       if ( this.boardSize > 4 ) {
          int size = this.boardSize - 4;
          double percentageResizing = size * .075;
          this.tileSize = this.tileSize - (this.tileSize * percentageResizing);
       }
    }

    /** Add your own instance methods here*/

    /**
     * Saves the board temporarily.
     *
     * @param output the name of the file
     *
     * @return the file where the board is temporarily saved
     */
    private File trySaveForTemp(String output) {
       try {
          this.board.saveBoard(output);
       }
       catch (IOException e) {
          System.out.println("Temp file not saved");
       }
       File file = new File(output);
       return file;
    }

    /**
     * Updates the board to current values. This method uses the arraylist
     * fields to access the tiles and update them accordingly. 
     *
     */
    protected void displayTiles(){
       int counter = 0;
       //loop through the array to update each tile accordingly
       for (int row = 0; row < this.boardSize; row++) {
          for (int column = 0; column < this.boardSize; column++) {
             int tileValue = this.grid[row][column];
             Color color = decideColor(tileValue);
             tileList.get(counter).setFill(color);
             //decides what to display as the value
             if ( tileValue == 0 ) {
                textList.get(counter).setText("");
             }
             else {
                String tileValueString = String.valueOf(tileValue);
                textList.get(counter).setText(tileValueString);
                textList.get(counter).setFont(Font.font("Times New Roman", FontWeight.BOLD,15));
             }
             //decides color of tile's value 
             if( tileValue == 2 || tileValue == 4 ) {
                textList.get(counter).setFill(Color.GRAY);
             }
             else {
                textList.get(counter).setFill(Color.WHITE);
             } 
             counter++; 
          }
       }
    }

    /**
     * updates the information of the baord after every move.
     *
     * @param inputBoard a file where the board is temporarily saved to retrieve
     * the information
     */
    protected void updateBoardInfo(String inputBoard){
        Scanner input = null;
        try {
           //uses a Scanner object to read from the temporary file after every
           //move and update the board's information
           File file = new File(inputBoard);
           input = new Scanner(file);
        }
        catch (Exception e) {
            System.out.println("Update of the board failed!" + e.toString());
        }
        while ( input.hasNext() ) {
            this.boardSize = input.nextInt();
            this.score = input.nextInt();
            scoreText.setText("Score: " + this.score);
            this.grid = new int[boardSize][boardSize];

            //use the file to populate a grid of the board
            for (int row = 0; row < grid.length; row++) {
                for( int column = 0; column < grid.length; column++){
                    this.grid[row][column] = input.nextInt();
                }
            }
      }

    }

    /**
     * adds an overlayed pane  to the window if the game is over.
     */
    protected void gameOver() {
       //Prevents method from being called > 1 time so it wont't keep showing
       if ( gameOverCounter == 0 ) {
          StackPane pane = new StackPane();
          //creates a new pane with a more transparent background "Game Over!"
          pane.setStyle("-fx-background-color: rgb(187, 173, 160, .25)");
          Text text = new Text();
          text.setText("Game Over!");
          text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
          pane.getChildren().add(text);
          pane.setAlignment(text, Pos.CENTER);
          stackPane.getChildren().add(pane);
          gameOverCounter++;
       }
    }

    /**
     * Decides the color of every tile.
     *
     * @param tileValue the value of the tile, used to decide its color
     *
     * @return the color of the tile
     */
    private static Color decideColor(int tileValue) {
       if (tileValue == 0) {
          return Constants2048.COLOR_EMPTY;
       }
       else if ( tileValue == 2 ) {
          return Constants2048.COLOR_2;
       }
       else if ( tileValue == 4 ) {
          return Constants2048.COLOR_4;
       }
       else if ( tileValue == 8 ) {
          return Constants2048.COLOR_8;
       }
       else if ( tileValue == 16 ) {
          return Constants2048.COLOR_16;
       }
       else if ( tileValue == 32 ) {
          return Constants2048.COLOR_32;
       }
       else if ( tileValue == 64 ) {
          return Constants2048.COLOR_64;
       }
       else if ( tileValue == 128 ) {
          return Constants2048.COLOR_128;
       }
       else if ( tileValue == 256 ) {
          return Constants2048.COLOR_256;
       }
       else if( tileValue == 512 ) {
          return Constants2048.COLOR_512;
       }
       else if ( tileValue == 1024 ) {
          return Constants2048.COLOR_1024;
       }
       else if ( tileValue == 2048 ) {
          return Constants2048.COLOR_2048;
       }
       return Constants2048.COLOR_OTHER;
    }
    

    /** 
     * Inner class which contains a single method, handle(KeyEvent e) to handle
     * KeyEvents from the user.
     */
    private class BoardKeyHandler implements EventHandler<KeyEvent> {

       /**
        * handles key events for arrow keys and S key.
        *
        * @param e the key even being handled
        */
       @Override
       public void handle(KeyEvent e) {
          KeyCode code = e.getCode();
          if ( code == KeyCode.UP ) {
             handleMove(board.UP);
          }
          else if ( code == KeyCode.LEFT ) {
             handleMove(board.LEFT);
          }
          else if( code == KeyCode.DOWN ) {
             handleMove(board.DOWN);
          }
          else if ( code == KeyCode.RIGHT ) {
             handleMove(board.RIGHT);
          }
          if ( board.isGameOver() ) {
             gameOver();
          }
          else if ( code == KeyCode.S ) {
             if (!board.isGameOver()) {
                try {
                   board.saveBoard(outputBoard);
                   System.out.println("Saving board to " + outputBoard);
                }
                catch (IOException exception) {
                   System.out.println("saveBoard threw an Exception");
                }
             }
          }
       }

        /**
         * Moves the board according to the specified direction
         * @param String one of up, down, left, right directions to the move the board in
         */
       private void handleMove(String direction) {
           if (!board.canMove(direction)) {
               return;
           }
           board.move(direction);
           board.addRandomTile();
           File file = trySaveForTemp("temporaryFile.board");
           updateBoardInfo("temporaryFile.board");
           file.delete();
           displayTiles();
           System.out.println("Moving " + direction);
       }

    }


    /** DO NOT EDIT BELOW */

    // The method used to process the command line arguments
    private void processArgs(String[] args)
    {
        String inputBoard = null;   // The filename for where to load the Board
        int boardSize = 0;          // The Size of the Board

        // Arguments must come in pairs
        if((args.length % 2) != 0)
        {
            printUsage();
            System.exit(-1);
        }

        // Process all the arguments 
        for(int i = 0; i < args.length; i += 2)
        {
            if(args[i].equals("-i"))
            {   // We are processing the argument that specifies
                // the input file to be used to set the board
                inputBoard = args[i + 1];
            }
            else if(args[i].equals("-o"))
            {   // We are processing the argument that specifies
                // the output file to be used to save the board
                outputBoard = args[i + 1];
            }
            else if(args[i].equals("-s"))
            {   // We are processing the argument that specifies
                // the size of the Board
                boardSize = Integer.parseInt(args[i + 1]);
            }
            else
            {   // Incorrect Argument 
                printUsage();
                System.exit(-1);
            }
        }

        // Set the default output file if none specified
        if(outputBoard == null)
            outputBoard = "2048.board";
        // Set the default Board size if none specified or less than 2
        if(boardSize < 2)
            boardSize = 4;

        // Initialize the Game Board
        try{
            if(inputBoard != null)
                board = new Board(new Random(), inputBoard);
            else
                board = new Board(new Random(), boardSize);
        }
        catch (Exception e)
        {
            System.out.println(e.getClass().getName() + 
                               " was thrown while creating a " +
                               "Board from file " + inputBoard);
            System.out.println("Either your Board(String, Random) " +
                               "Constructor is broken or the file isn't " +
                               "formated correctly");
            System.exit(-1);
        }
    }

    // Print the Usage Message 
    private static void printUsage()
    {
        System.out.println("Gui2048");
        System.out.println("Usage:  Gui2048 [-i|o file ...]");
        System.out.println();
        System.out.println("  Command line arguments come in pairs of the "+ 
                           "form: <command> <argument>");
        System.out.println();
        System.out.println("  -i [file]  -> Specifies a 2048 board that " + 
                           "should be loaded");
        System.out.println();
        System.out.println("  -o [file]  -> Specifies a file that should be " + 
                           "used to save the 2048 board");
        System.out.println("                If none specified then the " + 
                           "default \"2048.board\" file will be used");  
        System.out.println("  -s [size]  -> Specifies the size of the 2048" + 
                           "board if an input file hasn't been"); 
        System.out.println("                specified.  If both -s and -i" + 
                           "are used, then the size of the board"); 
        System.out.println("                will be determined by the input" +
                           " file. The default size is 4.");
    }
}

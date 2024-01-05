package picross;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Model class for storing information, updating it, and
 * having like 50 different images and tags
 * Man I hate docs
 * @author Not Luis Antonio Resendiz Campos
 */
public class Model {

    /*                           TAGS                   */
    //Do I really need to document all this?????
    //It should be obvious enough what this does

    //NOTE TO SELF: KEEP ALL TAGS PROTECTED SINCE THERE ARE A LOT AND
    //ALL OF THEM CHANGE OFTEN

    /**
     * Tag for window's title
     */
    protected static String title = "PICROSS";

    /**
     * Tag for color menu item
     */
    protected static String colorsTag = "Colors";

    /**
     * Tag for new game menu item
     */
    protected static String newGameTag = "New Game";

    /**
     * Tag for about meni item
     */
    protected static String aboutTag = "About";

    /**
     * Tag for the Game menu
     */
    protected static String gameTag = "Game";

    /**
     * Tag for the Design Menu
     */
    protected static String designTag = "Design";

    /**
     * Tag for the Game menu
     */
    protected static String playTag = "Play";

    /**
     * Tag for the load menu item
     */
    protected static String loadTag = "Load";

    /**
     * Tag for the Help menu
     */
    protected static String helpTag = "Help";

    /**
     * Tag for the language menu
     */
    protected static String languageTag = "Language";

    /**
     * Tag for the english menu item
     */
    protected static String englishTag = "English";

    /**
     * Tag for the spanish menu item
     */
    protected static String spanishTag = "Spanish";

    /**
     * Tag for the instructions menu item
     */
    protected static String solutionTag = "Solution";

    /*---------------NEW TAGS---------------*/
    /*---------------!!!!!!!!!!!!!NEW TAGS!!!!!!!!!!!!!!--------------*/
    /**
     * Tag for network menu
     */
    protected static String networkTag = "Network";

    /**
     * Tag for client menu item
     */
    protected static String clientTag = "Client";

    /**
     * Tag for connect button in client window
     */
    protected static String connectTag = "Connect";

    /**
     * KNOOT KNOOT
     */
    protected static String KNOOTKNOOT = "KNOOTKNOOT";

    /**
     * Tag for the Exit menu
     */
    protected static String exitTag = "Exit";

    /**
     * Tag for the close menu item
     */
    protected static String closeTag = "Close";

    /**
     * Tag for the timer
     */
    protected static String timeTag = "Time: ";

    /**
     * Tag for the score
     */
    protected static String scoreTag = "Score: ";

    /**
     * Tag for the reset button
     */
    protected static String resetTag = "Reset";

    /**
     * Tag for the pressing buttons text
     */
    protected static String pressStatus = "Button pressed at [";

    /**
     * Tag for the reset message
     */
    protected static String resetStatus = "Game has been reset\n";

    //NEEEEEEEEEEW Tags for client splash
    /**
     * Tag for user... this is getting waaaaaaay to repetitive
     */
    protected static String userTag = "User: ";

    /**
     * Server tag, yeah same drill
     */
    protected static String serverTag = "Server: ";

    /**
     * Port tag, port tag
     */
    protected static String portTag = "Port: ";

    /**
     * End tag, fiiiiiiiiiiiiiinnnnnnn T ODO tiene un fiiiiiiiiiiiiiiin
     * fcking bad translations
     */
    protected static String endTag = "End";

    /**
     * Tag for sending game
     */
    protected static String sendGameTag = "Send Game";

    /**
     * Tag for sending data
     */
    protected static String sendDataTag = "Send Data";

    /**
     * Tag for receiving game
     */
    protected static String receiveGameTag = "Receive Game";

    /*----------------NEW TAGS... UGH, TOO MANY TAGS------------------*/

    protected static String colorTitleTag = "Color Picker";

    protected static String defaultCellTag = "Default cell color: ";

    protected static String correctCellTag = "Correct cell color: ";

    protected static String wrongCellTag = "Wrong cell color: ";

    protected static String aboutText = "Minecraft Picross game, made by Luis Resendiz";

    /*                GRAPHICS          */

    //All images are static to avoid weird errors that crashed my entire app, I wish
    //I could say more about it but this is like the 4th time this term
    //that weird code works and, it gives me trouble when I update it
    //Man I hate myself
    /**
     * Image for default minecraft stone texture, used in default cells
     */
    protected static final Image stone = new Image("stone.png");

    /**
     * Image for default minecraft bedrock texture, used if incorrect cell selected
     */
    protected static final Image bdrock = new Image("bdrock.png");

    /**
     * Image for labels, so they match the whole thematic
     * I swear finding sizing and adjusting images was the hardest part of this
     * assignment, I hate myself
     */
    protected static final Image MCLabel = new Image("mcLabel.png");

    /**
     * Image for PICROSS logo, used for... aesthetic purposes I guess
     */
    protected static final Image logo = new Image("picrossLogo.png");

    /**
     * Image for minecraft logo, I can just pray to some superior being
     * that I don't get in trouble for using so many copyrighted content
     * but anyway, I'll just add this to make it look nicer
     * alongside the picross logo PLS don't come after me
     * Microsoft
     */
    protected static final Image MINECRAFTLOGO = new Image("mineLogo.png");

    /**
     * Image for default minecraft diamond ore texture, used for tiles to show puzzle hints
     */
    protected static final Image diamondOre = new Image("dimondOre.png");

    /**
     * Splash screen Image
     */
    protected static final Image splashFirst = new Image("PICROSS1.png");

    /**
     * Icon image for the exit option within the menu
     */
    protected static final Image exitIcon = new Image("piciconext.gif");

    /**
     * Icon image for about menu option
     */
    protected static final Image aboutIcon = new Image("piciconabt.gif");

    /**
     * Icon image for color menu option
     */
    protected static final Image colorIcon = new Image("piciconcol.gif");

    /**
     * Icon image for design menu option
     */
    protected static final Image designIcon = new Image("picicondes.gif");

    /**
     *  Icon image for english menu option
     */
    protected static final Image englishIcon = new Image("united (1).png");

    /**
     * Icon image for spanish menu option
     */
    protected static final Image spanishIcon = new Image("spain (1).png");

    /**
     * Icon image for language menu option
     */
    protected static final Image languageIcon = new Image("piciconlang.gif");

    /**
     * Icon image for load menu option
     */
    protected static final Image loadIcon = new Image("piciconload.gif");

    /**
     * Icon image for new game menu option
     */
    protected static final Image newGameIcon = new Image("piciconnew.gif");

    /**
     * Icon image for solution menu option
     */
    protected static final Image solutionIcon = new Image("piciconsol.gif");

    /*------------------NEW ICONS-----------------*/
    /*-----------------OMG NEW ICONS-------------*/

    /**
     * Icon for network menu
     * Git it from <a target="_blank" href="https://icons8.com/icon/60995/internet">Internet</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>
     */
    protected static final Image networkIcon = new Image("icons8-internet-30.png");

    /**
     * Icon image for client
     * Stole it from <a target="_blank" href="https://icons8.com/icon/69336/thin-client">Thin Client</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>
     */
    protected static final Image clientIcon = new Image("client.png");


    /**
     * Icon image for surprise feature
     * Stoled from <a target="_blank" href="https://icons8.com/icon/69330/linux-server">Linux Server</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>
     */
    protected static final Image surpriseIcon = new Image("icons8-linux-server-30.png");

    //NEEEEEEEEEEEEEEEEW IMAGE FOR CLIENT WOW OMG
    /**
     * Image for client window
     */
    protected static final Image clientImage = new Image("gameclient.png");

    /*                CONSTANTS                */
    /**
     * Default int for the dimension of the game... this changes I know, but
     * I hard coded it anyway for... testing
     */
    private  int DIMENSION_COUNT;

    /**
     * Default height for cells, calculated based on the size of the images
     * that will be used in the buttons(cells), calculated based on the minimum
     * usable size for images that wouldn't bug and wouldn't be so pixelated
     */
    protected  final double cellHeight = stone.getHeight();

    /**
     * Default width for cells, calculated based on the size of the images
     * that will be used in the buttons(cells), calculated based on the minimum
     * usable size for images that wouldn't bug and wouldn't be so pixelated
     */
    protected  final double cellWidth = stone.getWidth();

    /**
     * Default reusable border for all grids, so they look... nice and clear
     */
    protected static final Border black = new Border(new BorderStroke(Color.BLACK,
            BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(1)));

    /*                  RESOURCES       */
    /**
     * Resource bundle for internationalization
     */
    protected static ResourceBundle texts;

    /**
     * Locale for setting language
     */
    protected Locale currentLocale;

    /**
     * Resource file name.
     */
    protected String SYSTEMMESSAGES = "texts";

    /*          SOLUTION AND METHODS          */

    /**
     * Score for the current game
     */
    private int score;

    /**
     * Amount of correct guesses
     */
    protected int hits;

    /**
     * Dimension of the selected design custom solution
     */
    private int designDimension;

    /**
     * Max point available for current game, used for determining perfect scores
     */
    protected int maxPoints;

    /**
     * Color for default cell
     */
    protected Color cell;

    /**
     * Color for right cell
     */
    protected Color rCell;

    /**
     * Color for wrong cell
     */
    protected Color wCell;

    /**
     * Boolean for new game generation
     */
    private boolean firstMenu = true;

    private boolean requiresUpdate = true;

    protected int time;

    /**
     * Private boolean[][] for storing the solution
     */
    private boolean[][] currentSolution;

    private final View anotherView = new View(this);

    public boolean[][] getCurrentSolution(){
        return currentSolution;
    }

    public boolean isFirstMenu() {
        return firstMenu;
    }

    public boolean isRequiresUpdate(){return requiresUpdate;}

    public void setFirstMenu(boolean firstMenu) {
        this.firstMenu = firstMenu;
    }

    public void setRequiresUpdate(boolean requiresUpdate){this.requiresUpdate = requiresUpdate;}

    /**
     * Default constructor, used for other classes constructors
     */
    public Model(){
        cell = Color.GRAY;
        rCell = Color.GREEN;
        wCell = Color.RED;
        score = 0;
        hits = 0;
    }

    /**
     * Getter for the current dimension of puzzle
     * @return current dimension
     */
    public int getDimension(){
        return DIMENSION_COUNT;
    }

    /**
     * Setter for the current dimension of puzzle
     * @param nDimension new dimension for the game
     */
    public void setDimension(int nDimension){
        DIMENSION_COUNT = nDimension;
    }

    /**
     * Method for getting the amount of tile clues for the game
     * Calculated using upper rounding of dimensions/2
     * and used for setting the clues
     * Ex(5 by 5 game will have max 3 clues per row/col)
     * @return Amount of tiles for clues
     */
    public int getTiles(){
        return (int) Math.ceil(getDimension() / 2.0);
    }

    /**
     * Getter for dimensions of designed puzzle
     * @return dimension of design puzzle
     */
    public int getDesignDimension(){
        return designDimension;
    }

    /**
     * Setter for dimensions of designed puzzle
     * @param DD Dimensions selected for designing puzzle
     */
    public void setDesignDimension(int DD){
        designDimension = DD;
    }

    /**
     * Method for starting a new game, whether it is random or loaded
     * @param random value of new game(random or loaded)
     */
    public void newGame(boolean random){
        setRequiresUpdate(true);
        resetScore();
        anotherView.updateBoard(random);
    }

    /**
     * Method for updating score when buttons are pressed
     * @param correct value of button(correct or incorrect)
     */
    public void setScore(boolean correct){
        if(correct){
            score++;
        }else{
            score--;
            if(score<0){
                score=0;
            }
        }
    }

    /**
     * Method for resetting the score to 0
     */
    public void resetScore(){
        score = 0;
        hits = 0;
        //System.out.println(score);
    }

    /**
     * Method for resetting game grid graphics and clearing game log
     * @param grid buttons of current grid, to be reset to default graphics
     * @param box log to be cleared and append reset status
     */
    public void resetGrid(Button[][] grid, TextArea box){
        box.clear();
        box.appendText(resetStatus);
        for(int row=0; row<getDimension();row++){
            for(int col=0;col<getDimension();col++){
                grid[row][col].setStyle("-fx-background-color: gray;");
                grid[row][col].setGraphic(new ImageView(diamondOre));
                grid[row][col].setDisable(false);
            }
        }
        resetScore();
    }

    /**
     * Getter for current score
     * @return current score
     */
    public int getScore(){
        return score;
    }

    /**
     * Method for creating a new random game, randomly selects new dimensions
     * and creates random solution
     */
    public void randomGame() {
        maxPoints = 0;
        Random r = new Random();
        int ranDim = r.nextInt(9) + 2;
        setDimension(ranDim);
        //System.out.println(getDimension());
        boolean[][] solution = new boolean[getDimension()][getDimension()];
        int ranCell;
        boolean cell;
        for(int i=0;i<getDimension();i++){
            for(int j=0;j<getDimension();j++){
                ranCell = (int) (Math.random() * 2);
                if(ranCell==0){
                    cell = false;
                }else {
                    cell = true;
                    maxPoints++;
                }
                solution[i][j] = cell;
            }
        }
        setRequiresUpdate(false);
        currentSolution = solution;
    }

    /**
     * Method for loading game from local machine using a txt file with custom solution
     */
    public void loadGame(){
        //Resting points for loaded game
        maxPoints = 0;
        // Create a file chooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Picross Puzzle File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        // Show the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(null);

        // Count the number of lines in the file to determine the size of the puzzle
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(selectedFile));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        int rows = 0;
        int cols;
        String line;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }
            rows++;
        }
        cols = rows;
        setDimension(cols);
        System.out.println(getDimension());

        // Create the boolean array representing the solution
        boolean[][] solution = new boolean[rows][cols];
        try {
            reader = new BufferedReader(new FileReader(selectedFile));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
           return;
        }
        int row = 0;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }
            for (int col = 0; col < line.length(); col++) {
                solution[row][col] = line.charAt(col) == '1';
                if(line.charAt(col) == '1') { maxPoints++;}
            }
            row++;
        }
        setRequiresUpdate(false);
        currentSolution = solution;
    }

    /**
     * Method for updating solution of current puzzle
     * @param random to check whether solution is loaded from random method
     *               or loaded method
     */
    public void updateSolution(boolean random){
        if(random){
            randomGame();
            System.out.println(Arrays.deepToString(currentSolution));
        }else {
            loadGame();
            System.out.println(Arrays.deepToString(currentSolution));
        }
    }

    /*-------NOTE: NEXT TWO METHODS FOR CLUES ARE SIMILAR I KNOW, BUT-------------
     * -------SINCE ROW AND COL FOR CLUES ARE GENERATED SEPARATELY(TOP ROW OF CLUES-----------
     * -------IS PART OF MAIN GRID, LEFT MOST COL FOR CLUES IS SEPARATE FROM MAIN GRID)-------------
     * -------METHODS ARE SEPARATE AS WELL-----------*/

    /**
     * Method for generating clues for the rows of the game
     * @param sol solution to be used for clues
     * @param correctDimension dimension of current puzzle(used to ensure correct clue generation)
     * @return String array with clues for each row of game
     */
    public String[] getSideClues(boolean[][] sol, int correctDimension){
        String []sideClues = new String[correctDimension];
        for(int j=0; j<correctDimension; j++){
            StringBuilder colClue = new StringBuilder();
            int blockLength = 0;
            for(int i=0; i<correctDimension; i++){
                if(sol[i][j]){ // if the cell is true
                    blockLength++;
                } else if(blockLength > 0) {
                    colClue.append(blockLength).append(" "); // add the length of the block to the colClue
                    blockLength = 0; // reset the block length
                }
            }
            if(blockLength > 0){
                colClue.append(blockLength).append(" ");
            }
            if(colClue.length() == 0){
                colClue.append(0); // If there are no blocks in the column, add a zero
            }
            sideClues[j] = colClue.toString().trim(); // trim the string to remove any leading/trailing spaces
        }
        return sideClues;
    }

    /**
     * Method for generating clues for the cols of the game
     * @param sol solution to be used for clues
     * @param correctDimension dimension of current puzzle(used to ensure correct clue generation)
     * @return String array with clues for each column of game
     */
    public String[] getTopClues(boolean[][] sol, int correctDimension){
        String[] topClues = new String[correctDimension];

        // Generate clues for the rows
        for(int i=0; i<correctDimension; i++){
            StringBuilder rowClue = new StringBuilder();
            int blockLength = 0;
            for(int j=0; j<correctDimension; j++){
                if(sol[i][j]){ // if the cell is true
                    blockLength++;
                } else if(blockLength > 0) {
                    rowClue.append(blockLength).append(" "); // add the length of the block to the rowClue
                    blockLength = 0; // reset the block length
                }
            }
            if(blockLength > 0){
                rowClue.append(blockLength).append(" ");
            }
            if(rowClue.length() == 0){
                rowClue.append(0); // If there are no blocks in the row, add a zero
            }
            topClues[i] = rowClue.toString().trim(); // trim the string to remove any leading/trailing spaces
        }
        return topClues;
    }

    /**
     * Method for internationalization
     * @param selection selected language index
     */
    public void updateLanguage(int selection){
        String language = "";
        String country = "";
        switch (selection) {
            case 1 -> {
                language = "en";
                country = "CA";
                System.out.println("English selected");
            }
            case 2 -> {
                language = "es";
                country = "ES";
            }
            default -> {
            }
        }
        try {
            currentLocale = new Locale.Builder().setLanguage(language).setRegion(country).build();
            texts = ResourceBundle.getBundle(SYSTEMMESSAGES, currentLocale);
            // Uploading properties
            colorsTag = texts.getString("colorsTag");
            newGameTag = texts.getString("newGameTag");
            gameTag = texts.getString("gameTag");
            designTag = texts.getString("designTag");
            playTag = texts.getString("playTag");
            aboutTag = texts.getString("aboutTag");
            loadTag = texts.getString("loadTag");
            solutionTag = texts.getString("solutionTag");
            helpTag = texts.getString("helpTag");
            languageTag = texts.getString("languageTag");
            englishTag = texts.getString("englishTag");
            spanishTag = texts.getString("spanishTag");
            exitTag = texts.getString("exitTag");
            closeTag = texts.getString("closeTag");
            timeTag = texts.getString("timeTag");
            scoreTag = texts.getString("scoreTag");
            resetTag = texts.getString("resetTag");
            pressStatus = texts.getString("pressStatus");
            resetStatus = texts.getString("resetStatus");
            networkTag = texts.getString("networkTag");
            connectTag = texts.getString("connectTag");
            clientTag = texts.getString("clientTag");
            defaultCellTag = texts.getString("defaultCellTag");
            correctCellTag = texts.getString("correctCellTag");
            wrongCellTag = texts.getString("wrongCellTag");
            aboutText = texts.getString("aboutText");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        setFirstMenu(true);
        setRequiresUpdate(false);
        resetScore();
        Label score = anotherView.getScoreLabel();
        if(score != null){
            score.setText("\t" + Model.scoreTag + getScore());
        }
        anotherView.updateStage(Controller.getMainStage(), isFirstMenu());
    }

    /**
     * Method for launching design splash in view
     */
    public void design(){
        anotherView.designSplash();
    }

}//End of class


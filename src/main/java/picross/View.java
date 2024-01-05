package picross;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.*;

/**
 * View class for initializing the game's interface and... IDK
 * Man I hate javadoc
 * It has the code for updating the grid and uh...
 * Showing the splash and new stage for the design mode and...
 * shows the game's view or whatever
 * @author Luis Resendiz
 */
public class View extends Application {

    /**
     * Private model instance for usage throughout the class
     */
    private final Model thaModel;

    /**
     * Private final controller
     */
    private final Controller thaController;

    /**
     * Constructor instantiating a model
     * @param ANOTHERMODEL model for assigning to view and thus, create a controller
     */
    public View(Model ANOTHERMODEL){
        thaModel = ANOTHERMODEL;
        thaController = new Controller(this, thaModel);
    }

    /**
     * Private int for displaying the time
     */
    private int time=0;

    /**
     * Private label for displaying the time
     */
    private Label timerLabel;

    /**
     * Private textArea for the game's log
     */
    protected static TextArea logArea;

    /**
     * Private label for displaying the score
     */
    private Label scoreLabel;

    /**
     * Private button array for building the grid
     */
    private Button[][] butArray;

    /**
     * Private gridPane for the main grid
     */
    GridPane grid;

    /**
     * Private gridPane for the left grid
     */
    GridPane leftGrid;

    /**
     * Private border pane used as the root of the scene for the main stage
     */
    private final BorderPane root = new BorderPane();

    /**
     * Log area used for the game interface
     */
    protected static TextArea log = new TextArea();

    /*-----------------STAGES----------------*/
    /**
     * Private stage used for design splash
     */
    private Stage splashStage;

    /**
     * Private stage for actual design interface
     */
    private Stage designStage;

    /*----------------Menu and menu items----------*/

    //Main MenuBar
    private final MenuBar mainMenu = new MenuBar();

    Menu playMenu;
    Menu helpMenu;

    Menu languageMenu;

    Menu networkMenu;
    Menu connectMenu;

    Menu exitMenu;

    //Game Menu
    Menu gameMenu;
    //Play Menu
    MenuItem newGame;
    MenuItem loadGame;
    MenuItem solution;
    MenuItem design;
    MenuItem spanish;
    MenuItem english;
    MenuItem color;
    MenuItem about;
    MenuItem sendGame;
    MenuItem receiveGame;
    MenuItem sendData;

    MenuItem KBYE;
    /*-------------NEW MENU ITEMS, WOW NEW MENU ITEMS--------------*/
    MenuItem client;
    MenuItem surprise;
    //Exit Menu
    MenuItem close;

    /*-----------------METHODS--------------*/

    /**
     * Method for returning the score label
     * @return score label
     */
    public Label getScoreLabel(){
        return scoreLabel;
    }

    /**
     * Method for returning log area
     * @return log area
     */
    public TextArea getLogArea(){
        return logArea;
    }

    /**
     * Method for getting the time
     * @return time
     */
    public int getTime(){
        return time;
    }

    /**
     * Method for setting the time
     * @param nTime new time
     */
    public void setTime(int nTime){
        time = nTime;
        timerLabel.setText("\t" + Model.timeTag + getTime());
    }

    /**
     * Method for starting menu and setting it up
     * as well as adding action listener
     */
    public void startMenu(){
        //Inner menu
        ImageView nGameIcon = new ImageView(Model.newGameIcon);
        playMenu = new Menu(Model.playTag,nGameIcon);
        helpMenu = new Menu(Model.helpTag);
        //Inner menu
        ImageView lnIcon = new ImageView(Model.languageIcon);
        languageMenu = new Menu(Model.languageTag,lnIcon);
        ImageView nwIcon = new ImageView(Model.networkIcon);
        //OMG NEW MENU
        networkMenu = new Menu(Model.networkTag);
        connectMenu = new Menu(Model.connectTag, nwIcon);

        exitMenu = new Menu(Model.exitTag);

        //Game Menu
        gameMenu = new Menu(Model.gameTag);
        //Play
        newGame = new MenuItem(Model.newGameTag, nGameIcon);
        newGame.setId("NewGame");
        newGame.addEventHandler(ActionEvent.ACTION, thaController);
        ImageView ldIcon = new ImageView(Model.loadIcon);
        loadGame = new MenuItem(Model.loadTag,ldIcon);
        loadGame.setId("Load");
        loadGame.addEventHandler(ActionEvent.ACTION, thaController);
        playMenu.getItems().addAll(newGame, loadGame);
        ImageView slIcon = new ImageView(Model.solutionIcon);
        solution = new MenuItem(Model.solutionTag, slIcon);
        solution.setId("Solution");
        solution.addEventHandler(ActionEvent.ACTION, thaController);
        ImageView dsIcon = new ImageView(Model.designIcon);
        design = new MenuItem(Model.designTag, dsIcon);
        design.setId("Design");
        design.addEventHandler(ActionEvent.ACTION ,thaController);
        gameMenu.getItems().addAll(playMenu, solution, design);
        mainMenu.getMenus().add(gameMenu);
        //Options Menu
        ImageView spIcon = new ImageView(Model.spanishIcon);
        spanish = new MenuItem(Model.spanishTag,spIcon);
        spanish.setId("Spanish");
        spanish.addEventHandler(ActionEvent.ACTION ,thaController);
        ImageView enIcon = new ImageView(Model.englishIcon);
        english = new MenuItem(Model.englishTag,enIcon);
        english.setId("English");
        english.addEventHandler(ActionEvent.ACTION ,thaController);
        languageMenu.getItems().addAll(english, spanish);
        ImageView colIcon = new ImageView(Model.colorIcon);
        color = new MenuItem(Model.colorsTag, colIcon);
        color.setId("Color");
        color.addEventHandler(ActionEvent.ACTION, thaController);
        ImageView abIcon = new ImageView(Model.aboutIcon);
        about = new MenuItem(Model.aboutTag, abIcon);
        about.setId("About");
        about.addEventHandler(ActionEvent.ACTION, thaController);
        helpMenu.getItems().addAll(languageMenu, color, about);
        mainMenu.getMenus().add(helpMenu);
        /*-------------NEW MENU ITEMS, WOW NEW MENU ITEMS--------------*/
        ImageView clIcon = new ImageView(Model.clientIcon);
        client = new MenuItem(Model.clientTag,clIcon);
        client.setId("Client");
        client.addEventHandler(ActionEvent.ACTION, thaController);
        ImageView knootknoot = new ImageView(Model.surpriseIcon);
        surprise = new MenuItem(Model.KNOOTKNOOT, knootknoot);
        surprise.setId("surprise");
        surprise.addEventHandler(ActionEvent.ACTION, thaController);
        networkMenu.getItems().addAll(connectMenu, client);
        connectMenu.getItems().add(surprise);
        mainMenu.getMenus().add(networkMenu);
        //Exit Menu
        ImageView exIcon = new ImageView(Model.exitIcon);
        close = new MenuItem(Model.closeTag, exIcon);
        close.setId("Close");
        close.addEventHandler(ActionEvent.ACTION, thaController);
        exitMenu.getItems().addAll(close);
        mainMenu.getMenus().add(exitMenu);
    }

    /**
     * Method for returning the button array, updated throughout the model
     * @return 2d button array
     * todo maybe needs use???? idk yet lol
     */
    public Button[][] getButArray(){
        return butArray;
    }

    /**
     * Method for generating window for client info
     * used to connect, and is optional until client wishes to
     * actually connect and use online functions
     */
    public void clientSplash(){
        Stage clientSplash = new Stage();
        clientSplash.initModality(Modality.APPLICATION_MODAL);
        ImageView clientView = new ImageView(Model.clientImage);
        HBox dataSection = new HBox();
        Label userLabel = new Label(Model.userTag);
        TextField userInput = new TextField();
        Label serverLabel = new Label(Model.serverTag);
        TextField serverInput = new TextField();
        Label portLabel = new Label(Model.portTag);
        TextField portInput = new TextField();
        Button THECONECTION = new Button(Model.connectTag);
        THECONECTION.setOnAction(e -> thaController.handleConnect(userInput, serverInput, portInput, clientSplash));
        dataSection.getChildren().addAll(userLabel, userInput, serverLabel, serverInput, portLabel, portInput,
                THECONECTION);
        dataSection.setSpacing(8.0);
        dataSection.setPadding(new Insets(10, 5, 5, 5));
        VBox XBOX = new VBox(clientView, dataSection);
        XBOX.setAlignment(Pos.CENTER);
        Scene scene = new Scene(XBOX);
        clientSplash.setScene(scene);
        clientSplash.setTitle(Model.clientTag);
        clientSplash.sizeToScene();
        clientSplash.show();
    }

    /**
     * Method for generating design splash prompt
     * basically just shows available dimensions for custom designs
     * and prompts the user to select one in order to launch design interface
     */
    public void designSplash(){
        splashStage = new Stage();
        ComboBox<Integer> dimensionComboBox;
        Button confirmButton;
        splashStage.setTitle("Design Mode");
        splashStage.initModality(Modality.APPLICATION_MODAL);
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 200);
        splashStage.setScene(scene);
        splashStage.show();
        dimensionComboBox = new ComboBox<>();
        dimensionComboBox.getItems().addAll(2, 3, 4, 5, 6, 7, 8, 9, 10);
        dimensionComboBox.setValue(2);

        confirmButton = new Button("Confirm");

        confirmButton.setOnAction(e -> thaController.handleDesign(splashStage, (int) dimensionComboBox.getSelectionModel().getSelectedItem()));

        root.getChildren().addAll(new Label("Select dimension:"), dimensionComboBox, confirmButton);
    }

    /**
     * Method for showing design interface, closed either when user saves designed game
     * or if he closes the interface before saving
     */
    public void designMode(){
        designStage = new Stage();
        GridPane grid;
        Button saveButton;
        int dimension = thaModel.getDesignDimension();
        boolean[][] design;

        grid = new GridPane();
        grid.setHgap(2);
        grid.setVgap(2);
        design = new boolean[dimension][dimension];
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                Button button = new Button();
                button.setMinSize(50, 50);
                button.setOnAction(e -> {
                    int rowIndex = GridPane.getRowIndex(button);
                    int colIndex = GridPane.getColumnIndex(button);
                    design[rowIndex][colIndex] = !design[rowIndex][colIndex];
                    button.setStyle(design[rowIndex][colIndex] ? "-fx-background-color: black;" : "-fx-background-color: white;");
                });
                grid.add(button, col, row);
            }
        }

        saveButton = new Button("Save");
        saveButton.setOnAction(e -> thaController.handleSave(design, designStage));


        VBox root = new VBox(grid, saveButton);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root);
        designStage.setScene(scene);
        designStage.sizeToScene();
        designStage.setResizable(false);
        designStage.setTitle(Model.designTag);
        designStage.show();
    }

    /**
     * Method for generating the left grid used for displaying clues
     * generated separetly from main grid since I don't know
     * how to do this properly and I'm just stacking shit up
     * as of this was tetris because I hate UI
     * @param clues string array containing clues for game rows
     * @return grid pane containing left grid
     */
    public GridPane generateLGrid(String[] clues) {
        GridPane left = new GridPane();

        //Creation of left side grid for clues for the puzzle
        for(int row = 0; row <= thaModel.getDimension(); row++){
            for (int col = 0; col < 1; col++){
                Button cell = new Button();
                Text num;
                if (row > 0) {
                    num = new Text(clues[row-1]);
                }else{
                    num = new Text("");
                }
                cell.setPrefSize(thaModel.cellHeight, thaModel.cellWidth);
                cell.setGraphic(new ImageView(Model.stone));
                //Keep default color here, since it's not a playable button
                //it doesn't need to change colors
                cell.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
                cell.setBorder(Model.black);
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(cell);
                stackPane.getChildren().add(num);
                StackPane.setAlignment(num, Pos.CENTER);
                stackPane.setVisible(row >= 1);
                left.add(stackPane, col, row);
            }
        }
        return left;
    }

    /**
     * Method for updating size of current game button array
     * @param dimension new dimension of game
     * @return new button array of correct dimensions
     */
    public Button[][] updateButArray(int dimension){
        return new Button[dimension][dimension];
    }

    /**
     * Method for generating main grid, containing the buttons for the game,
     * and the top row for column clues, again, I have no clue what I'm doing
     * and at this point I don't wanna know anyway
     * @return grid pane with game grid and top row for clues
     */
    public GridPane generateMGrid(){
        GridPane main = new GridPane();
        String[] currentClues = thaModel.getTopClues(thaModel.getCurrentSolution(), thaModel.getDimension());
        butArray = updateButArray(thaModel.getDimension());

        //Generate the main grid
        for (int row = 0; row < thaModel.getDimension(); row++) {
            int gridRow = row + thaModel.getTiles();
            for (int col = 0; col < 1; col++) {
                int gridCol = col + thaModel.getTiles();
                Button cell = new Button();
                Text num = new Text(currentClues[row]);
                cell.setPrefSize(thaModel.cellHeight, thaModel.cellWidth);
                cell.setGraphic(new ImageView(Model.stone));
                //Keep default color here as well
                cell.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
                cell.setBorder(Model.black);
                cell.setVisible(true);
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(cell);
                stackPane.getChildren().add(num);
                StackPane.setAlignment(num, Pos.CENTER);
                main.add(stackPane, gridRow, gridCol);
            }

            for (int col = 0; col < thaModel.getDimension(); col++) {
                int gridCol = col + thaModel.getTiles() + 3;
                Button cell = new Button();
                cell.setPrefSize(thaModel.cellHeight, thaModel.cellWidth);
                cell.setGraphic(new ImageView(Model.diamondOre));
                cell.setBackground(new Background(new BackgroundFill(thaModel.cell, null, null)));
                cell.addEventHandler(ActionEvent.ACTION, thaController);
                cell.setId(row + " " + col);
                butArray[row][col] = cell;
                int finalRow = row;
                int finalCol = col;
                butArray[row][col].setOnAction(e -> thaController.handleButton(cell, finalRow, finalCol, thaModel.getCurrentSolution()));
                butArray[row][col].setBorder(Model.black);
                main.add(butArray[row][col], gridRow, gridCol);
            }
        }
        return main;
    }

    /**
     * UNFINISHED METHOD SO UNFINISHED DOCS
     * @param random boolean for checking if random game or loaded game
     */
    public void updateBoard(boolean random){
        if(thaModel.isRequiresUpdate()){
            thaModel.updateSolution(random);
        }
        HBox gridBox = new HBox();
        grid = new GridPane();
        leftGrid = new GridPane();
        grid = generateMGrid();
        leftGrid = generateLGrid(thaModel.getSideClues(thaModel.getCurrentSolution(), thaModel.getDimension()));
        gridBox.getChildren().addAll(leftGrid, grid);
        root.setCenter(gridBox);
        root.autosize();
        //Center setting and insets for separating a bit the elements
        root.setPadding(new Insets(5, 0, 5, 5));
    }

    /**
     * Hehe, help I'm bored
     * and don't want to deal with my own
     * intrusive thoughts
     */
    public void surprise(){
        //Creating a dialog
        Dialog<String> dialog = new Dialog<>();
        //Setting the title
        dialog.setTitle("KNOOT KNOOT");
        ButtonType type = new ButtonType("KNOOT KNOOT", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText("KNOOT KNOOT");
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        Stage surpriseStage = (Stage) dialog.getDialogPane().getScene().getWindow();
        surpriseStage.getIcons().add(Model.surpriseIcon);
        surpriseStage.showAndWait();
    }

    /**
     * Method for updating menu in case user goes online, new menu
     * items are used for managing server actions
     */
    public void updateConnection() {
        PicrossClient.connect();
        sendGame = new MenuItem(Model.sendGameTag);
        receiveGame = new MenuItem(Model.receiveGameTag);
        sendData = new MenuItem(Model.sendDataTag);
        KBYE = new MenuItem("KBYE");
        sendGame.setId("SendGame");
        receiveGame.setId("ReceiveGame");
        sendData.setId("SendData");
        KBYE.setId("NUKETURKEY");
        sendGame.addEventHandler(ActionEvent.ACTION, thaController);
        receiveGame.addEventHandler(ActionEvent.ACTION, thaController);
        sendData.addEventHandler(ActionEvent.ACTION, thaController);
        KBYE.setOnAction(e -> thaController.SHUTEVERYTHINGDOWNANDFLEETHECOUNTRY());
        connectMenu.getItems().addAll(sendGame, receiveGame, sendData, KBYE);
    }

    /**
     * Method for generating vbox containing grids and menus, basically the whole ui, used
     * for setting scene of main stage
     * @return VBox containing everything in the UI
     */
    public VBox generateRoot(){

        //Setting for the background
        double bckGroundHeight = root.getHeight();
        double bckGroundWidth = root.getWidth();
        Image MCbackGround = new Image("ngDirt.png", bckGroundWidth, bckGroundHeight, true, true);
        BackgroundImage idk = new BackgroundImage(MCbackGround, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(idk));

        updateBoard(true);
        if(thaModel.isFirstMenu()) {
            mainMenu.getMenus().clear();
            startMenu();
        }

        //Definition of elements for right pane, including the logos, labels for time and score, reset button
        //and log for clicks on the main grid

        /*------------------DECLARATION OF LEFT SIDE PANEL----------------------*/
        //Label for the game's score
        scoreLabel = new Label();
        scoreLabel.setText("");
        scoreLabel.setText("\t" + Model.scoreTag + thaModel.getScore());
        scoreLabel.setId("Score");
        scoreLabel.setBackground(new Background(new BackgroundImage(Model.MCLabel, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        scoreLabel.setPrefSize(Model.MCLabel.getWidth(), Model.MCLabel.getHeight());
        //Picross logo label setting
        Label PCLogo = new Label();
        PCLogo.setBackground(new Background(new BackgroundImage(Model.logo, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        PCLogo.setPrefSize(Model.logo.getWidth(), Model.logo.getHeight());
        PCLogo.setAlignment(Pos.BOTTOM_CENTER);
        //Minecraft logo label setting
        Label MCLogo = new Label();
        MCLogo.setBackground(new Background(new BackgroundImage(Model.MINECRAFTLOGO, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        MCLogo.setPrefSize(Model.MINECRAFTLOGO.getWidth(), Model.MINECRAFTLOGO.getHeight());
        //Box for containing both logos, these labels are grouped into a vbox,
        //so they appear together and gluing everything together is easier for me
        VBox logoBox = new VBox(MCLogo, PCLogo);
        logoBox.setPadding(new Insets(5, 0, 0, 5));
        //Rest of elements outside of logos
        //Reset button for the game
        Button resetButton = new Button(Model.resetTag + "\n");
        resetButton.setId("Reset");
        resetButton.addEventHandler(ActionEvent.ACTION, thaController);
        //Setting of the game's log
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefRowCount(thaModel.getDimension());
        logArea.setPrefColumnCount(thaModel.getDimension()*5);
        logArea.setWrapText(true);
        resetButton.setOnAction(e -> thaController.handleReset(butArray, logArea));

        timerLabel = new Label("\t" + Model.timeTag + time);
        timerLabel.setId("Timer");
        timerLabel.setBackground(new Background(new BackgroundImage(Model.MCLabel, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        timerLabel.setPrefSize(Model.MCLabel.getWidth(), Model.MCLabel.getHeight());

        //VBox containing all elements from the right panel, all glued into a single vbox that will be
        //positioned on the right side of the borderPane
        VBox rightContainer = new VBox(logoBox, timerLabel, logArea, scoreLabel, resetButton);
        rightContainer.setSpacing(25.0);
        rightContainer.setPadding(new Insets(0,5,5,10));
        root.setRight(rightContainer);


        //Vbox for joining the menu with everything else, between so may cubes, squares and boxes
        //I think I'm starting to see real life as a minecraft POV
        return new VBox(mainMenu, root);
    }

    /**
     * Start method for FX application and creates the entire interface
     * @param primaryStage main Stage for execution
     */
    @Override
    public void start(Stage primaryStage){
        //Basic setup for main stage components
        primaryStage.setTitle(Model.title);
        //MINECRAFT logo for the window's logo
        primaryStage.getIcons().add(new Image("dirt.png"));


        //Setting of the main scene to set the stage...
        //Am I writing a code or a theater play???
        Scene scene = new Scene(generateRoot());
        primaryStage.setScene(scene);
        //CSS styling for custom MINECRAFT font and adjusting sizes for different components
        scene.getStylesheets().add("fontstyle.css");
        //Final settings for stage and showing
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * @param primaryStage primary stage of fx app
     */
    public void updateStage(Stage primaryStage, boolean newMenu){
        thaModel.setFirstMenu(newMenu);
        start(primaryStage);
    }

    /**
     * Method for easily adding text to the log of main UI, used mainly for server responses in
     * main client UI
     * @param s string to be added to log
     */
    public static void appendText(String s){
        System.out.println("Appending text: " + s);
        log.appendText(s);
    }

    /**
     * Method for showing an alert in case of errors sending game
     */
    public void sendError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Send Error");
        alert.setHeaderText("Error Sending Game");
        alert.setContentText("Not Connected to Server");

        alert.showAndWait();
    }

    /**
     * UNFINISHED METHOD, UNFINISHED DOCS
     * @todo finish haha
     */
    public void portError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Port");
        alert.setHeaderText("Invalid Port Number");
        alert.setContentText("Using default port number instead");

        alert.showAndWait();
    }

    /**
     * Method for showing about window
     */
    public void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Model.aboutTag);
        alert.setHeaderText("PICROSS");
        alert.setContentText(Model.aboutText);

        // Retrieve the button types
        ButtonType okButton = new ButtonType("OK", ButtonType.OK.getButtonData());

        // Set the button types with English labels
        alert.getButtonTypes().setAll(okButton);

        alert.showAndWait();
    }

    /**
     * Method for changing the colors of the cells in the game
     */
    public void colorPick() {
        //Definitions for showing color picker
        Stage colorStage = new Stage();
        TilePane tp = new TilePane();
        ColorPicker cp1 = new ColorPicker();
        cp1.setOnAction(e -> thaController.handleColor(cp1,1));
        ColorPicker cp2 = new ColorPicker();
        cp2.setOnAction(e -> thaController.handleColor(cp2,2));
        ColorPicker cp3 = new ColorPicker();
        cp3.setOnAction(e -> thaController.handleColor(cp3,3));
        Label l1 = new Label(Model.defaultCellTag);
        Label l2 = new Label(Model.correctCellTag);
        Label l3 = new Label(Model.wrongCellTag);
        tp.getChildren().addAll(l1, cp1, l2, cp2, l3, cp3);
        Scene s = new Scene(tp, 160, 160);
        colorStage.setScene(s);
        colorStage.setTitle(Model.colorTitleTag);
        //MINECRAFT logo for the window's logo
        colorStage.getIcons().add(new Image("dirt.png"));
        s.getStylesheets().add("fontstyle.css");
        //Final settings for stage and showing
        colorStage.setResizable(false);
        colorStage.sizeToScene();
        colorStage.show();
        colorStage.setOnHiding(e -> thaController.updateColors());
    }

    /**
     * Method for showing current game's solution
     */
    public void showSolution() {
        if(thaModel.getCurrentSolution()!=null) {
            for (int i = 0; i < thaModel.getDimension(); i++) {
                for (int j = 0; j < thaModel.getDimension(); j++) {
                    if (thaModel.getCurrentSolution()[i][j]) {
                        butArray[i][j].fire();
                    }
                }
            }
        }
        thaModel.resetScore();
        logArea.appendText("\nSolution applied");
    }

}


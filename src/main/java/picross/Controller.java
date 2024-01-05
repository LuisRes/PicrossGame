package picross;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;;

/**
 * Class for game's controller
 * NOTE: This is not a DUAL-SHOCK 4 wireless PS4 controller
 * Just a Picross controller
 * @author Antonio Campos
 */
public class Controller implements EventHandler<ActionEvent> {

    /**
     * Instance of DA VIEW for use in the controller
     */
    private final View daView;

    /**
     * Instance of DA MODEL for use in the controller
     */
    private final Model daModel;

    /**
     * Main stage for updating the UI's stage
     */
    private static final Stage mainStage = new Stage();

    /**
     * Getter for main stage, used in other classes
     * @return main stage
     */
    public static Stage getMainStage(){
        return mainStage;
    }

    /**
     * Constructor for default controller
     * @param tView access to the view
     * @param tModel access to the model
     */
    public Controller(View tView, Model tModel) {
        daView = tView;
        daModel = tModel;
    }

    /**
     * Start method for the app
     */
    public void startApp(){
        daView.start(mainStage);
    }

    /**
     * Main handle method used for menu interactions
     * @param actionEvent source of action event
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent!=null) {
            Object source = actionEvent.getSource();
            if (source instanceof MenuItem menuItem) {
                String id = menuItem.getId();

                switch (id) {
                    case "Spanish":
                        daModel.updateLanguage(2);
                        break;
                    case "English":
                        daModel.updateLanguage(1);
                        break;
                    case "Design":
                        System.out.println("Design");
                        daModel.design();
                        break;
                    case "NewGame":
                        System.out.println("New Game");
                        daModel.newGame(true);
                        daView.updateStage(mainStage, false);
                        break;
                    case "Solution":
                        daView.showSolution();
                        break;
                    case "About":
                        daView.about();
                        break;
                    case "Load":
                        System.out.println("Load Game");
                        daModel.newGame(false);
                        daView.updateStage(mainStage, false);
                        break;
                    case "Client":
                        daView.clientSplash();
                        break;
                    case "Color":
                        daView.colorPick();
                        break;
                    case "SendGame":
                        break;
                    case "SendData":
                        break;
                    case "ReceiveGame":
                        break;
                    case "surprise":
                        daView.surprise();
                        break;
                    case "Close":
                        mainStage.close();
                        System.exit(0);
                        break;
                }
            }
        }
    }

    /**
     * Method for choosing type of new game, random or designed game
     * @param s selected type of game
     */
    public void handleGameMode(String s){
        if(s.equalsIgnoreCase("Random")){
            daModel.updateSolution(true);
            boolean[][] sendGame = daModel.getCurrentSolution();
            transformGame(sendGame);
        }else{
            daView.designSplash();
        }
    }

    /**
     * Method for saving game configuration to client
     * @param design generated game
     * @param dStage stage used for designing
     */
    public void handleSave(boolean[][] design, Stage dStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(dStage);
        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file)) {
                for (boolean[] row : design) {
                    for (boolean cell : row) {
                        writer.print(cell ? "1" : "0");
                    }
                    writer.println();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dStage.hide();
    }

    /**
     * Method for turning boolean array into string saved in client
     * @param boolGame game configuration as 2d bool array
     */
    private void transformGame(boolean[][] boolGame){
        StringBuilder game = new StringBuilder();
        for (boolean[] row : boolGame) {
            for (boolean cell : row) {
                game.append(cell ? "1" : "0");
            }
            game.append(",");
        }
        game.deleteCharAt(game.length()-1);
        String DAGAME = game.toString();
        PicrossClient.setCurrentGame(DAGAME);
    }

    /**
     *Method for launching design mode and closing the splash for dimensions
     * @param splashStage stage used for selecting dimensions
     * @param sDimension selected number of dimensions
     */
    public void handleDesign(Stage splashStage, int sDimension) {
        splashStage.close();
        daModel.setDesignDimension(sDimension);
        System.out.println(sDimension);
        daView.designMode();
    }

    /**
     * Method for resetting the game, resets score, time and status of all buttons
     * As well as the current gam log
     * @param butArray game buttons to be reseted
     * @param logArea log of current game
     */
    public void handleReset(Button[][] butArray, TextArea logArea) {
        daModel.resetGrid(butArray, logArea);
        daModel.resetScore();
        Label update = daView.getScoreLabel();
        update.setText("\t" + Model.scoreTag + daModel.getScore());
        daModel.setRequiresUpdate(false);
        daView.updateStage(mainStage, false);
    }

    /**
     * Method for handling button presses in the main game interface
     * @param button current button being pressed
     * @param row row of button pressed
     * @param col col of button pressed
     * @param cSol current game solution, to be used to determine if correct
     *             button pressed or not
     */
    public void handleButton(Button button, int row, int col, boolean[][]cSol) {
        View.logArea.appendText(Model.pressStatus + (row + 1) + "," + (col + 1) + "]\n");
        button.setGraphic(new ImageView(Model.bdrock));
        boolean isSolution = cSol[row][col];
        if (isSolution) {
            button.setBackground(new Background(new BackgroundFill(daModel.rCell, null, null)));
            daModel.hits++;
        } else {
            button.setBackground(new Background(new BackgroundFill(daModel.wCell, null, null)));
        }
        button.setDisable(true);
        daModel.setScore(isSolution);
        daView.getScoreLabel().setText("\t" + Model.scoreTag + daModel.getScore());
        if(daModel.getScore()==daModel.maxPoints){
            //Splash
        }else if(daModel.hits==daModel.maxPoints){

        }
    }

    /**
     * Method for getting input of client information used for connecting to server
     * @param user username text field
     * @param server server name text field
     * @param port port number text field
     * @param stage stage used for client connection
     */
    public void handleConnect(TextField user, TextField server, TextField port, Stage stage) {
        String clientUser;
        String clientServer;
        int clientPort;
        try{
            clientUser = user.getText();
            clientServer = server.getText();
            clientPort = Integer.parseInt(port.getText());
        }catch (Exception e){
            clientUser = "user";
            clientServer = PicrossClient.HOSTNAME;
            clientPort = PicrossClient.PORT;
        }
        PicrossClient.setUsername(clientUser);
        PicrossClient.setHostName(clientServer);
        PicrossClient.setPort(clientPort);
        PicrossClient.updateConnection(true);
        stage.close();
        daView.updateConnection();
    }

    /**
     * Method for closing connection and disconnecting from server
     */
    public void SHUTEVERYTHINGDOWNANDFLEETHECOUNTRY(){
        PicrossClient.sendProtocol(0);
        PicrossClient.disconnect();
    }

    public void loadGame() {
        PicrossClient.setCurrentGame(PicrossServer.getCurrentGame());
        System.out.println(PicrossClient.getCurrentGame());
    }

    public void sendData() {
        PicrossClient.setScore(daModel.getScore());
        PicrossClient.setTime(0);
        PicrossClient.sendProtocol(3);
    }



    public void sendGame() {
        if(!PicrossClient.getConnectionStatus()){
            daView.sendError();
        }else{
            PicrossClient.sendProtocol(1);
        }
    }

    public void handleColor(ColorPicker cpp, int i) {
        Color currentColor = cpp.getValue();
        switch (i) {
            case 1 -> daModel.cell = currentColor;
            case 2 -> daModel.rCell = currentColor;
            case 3 -> daModel.wCell = currentColor;
        }
    }

    public void updateColors() {
        daModel.setRequiresUpdate(false);
        handleReset(daView.getButArray(), daView.getLogArea());
        daView.updateStage(mainStage,false);
    }
}
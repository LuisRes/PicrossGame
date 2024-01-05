package picross;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Class for picross server, contains connection methods and simple view with some actions
 * All is contained within the class
 * @author some guy named Luis Resendiz
 */
public class PicrossServer extends Application implements Runnable{

    /**
     * Nested class for user scores for score table of server
     * maybe an enum or something would have been more effective
     * but IDK how to use enums so... yeah
     * @author Luis Resendiz?
     */
    public static class UserScore{
        /**
         * User name, duh
         */
        public String userName;
        /**
         * Score...
         */
        public int score;
        /**
         * Time taken to complete puzzle
         * This one is not that self-explanatory
         * so more comments, yei
         */
        public int time;

        /**
         * Constructor for each user score and stats
         * @param n name
         * @param s score
         * @param t time
         */
        public UserScore(String n, int s, int t){
            userName = n;
            score = s;
            time = t;
        }
    }

    /**
     * Array list of user scores, used for the score table
     */
    static ArrayList<UserScore> userScores = new ArrayList<UserScore>();

    /**
     * Image for server window
     */
    private static final Image serverImage = new Image("gameserver.png");

    /**
     * Image for server window icon
     */
    private static final Image serverIcon = new Image("serverIcon.png");

    /**
     * Socket variable.
     */
    Socket sock;

    /**
     * Variables for number clients.
     */
    static int nclient = 0, nclients = 0;

    /**
     * Server socket.
     */
    static ServerSocket servsock;

    /**
     * Default port.
     */
    static int PORT = 3000;

    /**
     * Number of port.
     */
    static int portNumber = 0;

    /**
     * Log in server UI, used for notifications of server status and connections
     */
    static TextArea log = new TextArea();

    /**
     * String for current game stored in server
     */
    private static String currentGame = "";

    /**
     * Getter for current game... I'll prolly remove this
     * @return current game stored in server
     */
    public static String getCurrentGame(){
        return currentGame;
    }

    /**
     * Method for adding to local list of scores
     * @param u username... again
     * @param s score... again
     * @param t time... time and time again*music*
     */
    public static void buildList(String u, int s, int t){
        userScores.add(new UserScore(u, s, t));
    }

    /**
     * Default constructor for server
     */
    public PicrossServer(){

    }

    /**
     * Start method of server UI
     * @param stage main stage for server
     */
    @Override
    public void start(Stage stage){
        ImageView serverView = new ImageView(serverImage);
        //ImageView serverIc = new ImageView(serverIcon);
        stage.getIcons().add(serverIcon);
        HBox elements = new HBox();
        Label portLabel = new Label("Port");
        TextField portInput = new TextField();
        Button execute = new Button("Execute");
        Button results = new Button("Results");
        CheckBox finalize = new CheckBox();
        Label finalizeLabel = new Label("Finalize");
        Button FLEE = new Button("CLOSE AND FLEE");
        elements.getChildren().addAll(portLabel, portInput, execute, results, finalize, finalizeLabel, FLEE);
        elements.setSpacing(8.0);
        elements.setPadding(new Insets(10, 5, 5, 5));

        log.setEditable(false);
        int colSize = (int) serverView.getFitWidth();
        log.setPrefRowCount(5);
        log.setPrefColumnCount(colSize);
        log.setWrapText(true);

        VBox root = new VBox();
        root.getChildren().addAll(serverView, elements,log);
        Image MCbackGround = new Image("stone.png", root.getWidth(), root.getHeight(), true, true);
        BackgroundImage idk = new BackgroundImage(MCbackGround, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(idk));
        root.setPadding(new Insets(5, 5, 5, 5));

        execute.setOnAction(e->{
            try {
                portNumber = Integer.parseInt(portInput.getText());
            }catch (Exception ee){
                portNumber=PORT;
            }
            if(portNumber<1000||portNumber>65000){
                log.appendText("Invalid port number!" +
                        "\nUsing default port 3000");
            }
            log.appendText("\nStarting Server Thread on port " + portNumber);
            try {
                servsock = new ServerSocket(portNumber);
                Thread servDaemon = new Thread(new PicrossServer());
                servDaemon.start();
                log.appendText("\nServer running on " + InetAddress.getLocalHost() + " at port " + portNumber + "!");
            } catch (Exception ex) {
                log.appendText("\nError: " + ex);
            }
        });

        FLEE.setOnAction(e->{
            try {
                servsock.close();
            } catch (IOException ex) {
                log.appendText("Error closing server\nShutting down program execution");
            }finally {
                Platform.exit();
                System.exit(0);
            }
        });

        Scene scene = new Scene(root);
        scene.getStylesheets().add("fontstyle.css");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Main method of FX app
     * @param args command line args
     */
    public static void main(String[] args){
        launch(args);
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        for(;;) {
            try {
                sock = servsock.accept();
                nclient += 1;
                nclients += 1;
                log.appendText("\nConnecting " + sock.getInetAddress() + " at port " + sock.getPort() + ".");
            } catch (IOException ioe) {
                log.appendText(ioe.getMessage());
            }
            Worked w = new Worked(sock, nclient);
            w.start();
        }
    }

    static class Worked extends Thread {

        /**
         * Socket variable.
         */
        Socket sock;

        /**
         * Integers for client and positions.
         */
        int clientid;

        /**
         * Default constructor.
         * @param s Socket
         * @param nclient Number of client.
         */
        public Worked(Socket s, int nclient) {
            sock = s;
            clientid = nclient;
        }

        /**
         * Run method.
         */
        public void run() {
            try {
                String data;
                boolean end;
                PrintStream out = new PrintStream(sock.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                System.out.println("Server runs?");
                out.println(clientid);
                data = in.readLine();
                System.out.println(data);
                // split the message into protocol and data
                String[] parts = data.split(Config.PROTOCOL_SEPARATOR);
                String id = parts[0];
                String protocol = parts[1];

                // process the message based on the protocol
                switch (protocol) {
                    case Config.PROTOCOL_END:
                        // handle the end protocol
                        break;
                    case Config.PROTOCOL_SENDGAME:
                        // extract the game ID from the data
                        String createdGame = parts[2];
                        System.out.println(createdGame);
                        // handle the send game protocol
                        break;
                    case Config.PROTOCOL_RECVGAME:
                        // handle the receive game protocol
                        String revievedGame = parts[2];
                        System.out.println(revievedGame);
                        break;
                    case Config.PROTOCOL_DATA:
                        String userName = parts[2];
                        int score = Integer.parseInt(parts[3]);
                        int time = Integer.parseInt(parts[4]);
                        PicrossServer.buildList(userName, score, time);
                        // handle the data protocol
                        break;
                    default:
                        // handle unknown protocol
                        break;
                }
                end = Integer.parseInt(String.valueOf(protocol.charAt(protocol.length()-1))) == 0;
                while(!end){
                    data = in.readLine();
                    // read the incoming message from the client

                    // split the message into protocol and data
                    parts = data.split(Config.PROTOCOL_SEPARATOR);
                    id = parts[0];
                    protocol = parts[1];

                    // process the message based on the protocol
                    switch (protocol) {
                        case Config.PROTOCOL_END:
                            break;
                        case Config.PROTOCOL_SENDGAME:
                            // extract the game ID from the data
                            currentGame = parts[2];
                            // handle the send game protocol
                            break;
                        case Config.PROTOCOL_RECVGAME:

                            // handle the receive game protocol
                            break;
                        case Config.PROTOCOL_DATA:
                            String userName = parts[2];
                            int score = Integer.parseInt(parts[3]);
                            int time = Integer.parseInt(parts[4]);
                            PicrossServer.buildList(userName, score, time);
                            // handle the data protocol
                            break;
                        default:
                            PicrossServer.log.appendText("\nUnknown protocol...");
                            break;
                    }
                    end = Integer.parseInt(protocol) == 0;
                }
                PicrossServer.log.appendText("\nDisconnecting " + sock.getInetAddress() + "!");
                nclients -= 1;
                PicrossServer.log.appendText("\nCurrent client number: " + nclients);
                if (nclients == 0) {
                    PicrossServer.log.appendText("\nEnding server...");
                    sock.close();
                    System.exit(0);
                }
            } catch (IOException ioe) {
                PicrossServer.log.appendText(String.valueOf(ioe));
            }
        }
    }
}

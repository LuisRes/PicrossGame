package picross;

import javafx.application.Platform;
import java.net.*;
import java.io.*;

/**
 * Class for client, includes full MVC and connects to server
 * @author Maybe it was Luis Resendiz
 */
public class PicrossClient {


    /**
     * Default port.
     */
    protected static int PORT = 3000;

    /**
     * Number of port.
     */
    private static int portNumber = 0;

    /**
     * Default hostname.
     */
    protected static String HOSTNAME = "localhost";

    /**
     * Variable for hostname.
     */
    private static String hostName = "";

    /**
     * Boolean for checking status of the client connection
     */
    private static boolean connectionStatus;

    /**
     * Method for getting the connection status, used in other classes
     * @return connection Status
     */
    public static boolean getConnectionStatus() {
        return connectionStatus;
    }

    /**
     * Method for setting(updating) the connection status, used for updating the client connection using model
     * @param status new status
     */
    public static void updateConnection(boolean status) {
        connectionStatus = status;
    }

    /**
     * Current user name
     */
    private static String username;

    /**
     * Method for setting the username of the current client
     * @param un new username
     */
    public static void setUsername(String un) {
        username = un;
    }

    /**
     * Method for setting the port of the current client
     * @param p port number
     */
    public static void setPort(int p) {
        portNumber = p;
    }

    /**
     * Method for setting the hostname of the current client connection
     * @param n host name
     */
    public static void setHostName(String n) {
        hostName = n;
    }

    /*
     * This score and time are used for sending only, the actual values are updated and used in the model
     * These are just copies for sending
     */

    /**
     * Score of the current client, to be sent to server
     */
    private static int score = 0;

    /**
     * Time of the current client, to be sent to the server
     */
    private static int time = 0;

    private static String currentGame = "";

    /**
     * Method for getting the stored game of current client
     * @return currently stored game
     */
    public static String getCurrentGame() {
        return currentGame;
    }

    /**
     * Protocol for client, updated as changes are made elsewhere in the program
     */
    private static int protocol = -1;

    public static void setProtocol(int p) {
        protocol = p;
        System.out.println("Current protocol: " + protocol);
    }

    public static void setScore(int s) {
        score = s;
    }

    public static void setTime(int t) {
        time = t;
    }

    public static int getScore() {
        return score;
    }

    public static int getTime() {
        return time;
    }

    /**
     * Client socket used for connecting to server
     */
    static Socket sock;

    /**
     * Main method for client class
     * @param args command line args
     */
    public static void main(String[] args) {
        Platform.startup(() -> {
            Model DAMODEL = new Model();
            View DAVIEW = new View(DAMODEL);
            Controller DACONTROLLER = new Controller(DAVIEW, DAMODEL);
            DACONTROLLER.startApp();
        });
    }

    /**
     * Method for setting the game stored in client
     * @param s string of game config
     */
    public static void setCurrentGame(String s) {
        currentGame = s;
        View.log.appendText("\nCurrent game in client: " + currentGame);
    }

    /**
     * Method for connecting to the server
     */
    public static void connect() {
        View.appendText("Connecting with server on " + hostName + " at port " + portNumber);
        View.appendText("\nStarting Server thread on port " + portNumber);
        try {
            sock = new Socket(hostName, portNumber);
        } catch(java.net.UnknownHostException uhe){
            View.appendText("\nError: Unknown Host.\n");
        } catch(SocketTimeoutException ste){
            View.appendText("\nError: Connection refused due to timeout.\n");
        } catch(EOFException eof){
            View.appendText("\nError: Server streams are not available.\n");
        } catch(java.net.ConnectException ce){
            View.appendText("\nError: Connection refused.  Server is not available. Check port or restart server.\n");
        } catch(Exception e){
            e.printStackTrace();
            View.appendText("\nCLIENT>ERROR: Client socket exception.\n");
        }
    }

    public static void disconnect(){
        try{
            sock.close();
        }catch (IOException ignored){

        }
    }

    public static void sendProtocol ( int i){
        try {
            OutputStream outputStream = sock.getOutputStream();
            BufferedReader dis = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String id = "";
            String formattedMessage = "";
            String response = "";
            id = dis.readLine();
            formattedMessage += id + Config.PROTOCOL_SEPARATOR;
            switch (i) {
                case 0 -> {
                    formattedMessage += Config.PROTCOL_CONNECT;
                    View.appendText("\nDisconnecting from server");
                    sock.close();
                }
                case 1 -> formattedMessage += Config.PROTOCOL_SENDGAME + Config.PROTOCOL_SEPARATOR
                        + PicrossClient.currentGame;
                case 3 ->
                        formattedMessage += Config.PROTOCOL_SEPARATOR + PicrossClient.getScore() + Config.PROTOCOL_SEPARATOR
                                + PicrossClient.getTime();
                default -> View.appendText("\nUnknown protocol");
            }
            System.out.println("Before sending?");
            outputStream.write(formattedMessage.getBytes());
            System.out.println(formattedMessage + "Message sent");
            //response = dis.readLine();
            //View.appendText(response);
        } catch (IOException e) {
            View.appendText(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

}

import java.net.*;
import java.util.*;
import java.io.*;

public class IterativeSocketServer {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");

        /****************************************
         * HOW TO CREATE A SOCKET SERVER
         * *************************************
         */
        System.out.println("************************");
        System.out.println("WELCOME TO THE SERVER");
        System.out.println("************************");
        System.out.println("What port would you like to use?");
        Scanner scnr = new Scanner(System.in);
        int portNum = scnr.nextInt();
        try {
            ServerSocket serverSocket = new ServerSocket(portNum);// Create a server socket
            System.out.println("Directed to port " + portNum + " .....");
            boolean notDone = true;
            while (notDone) {

                Socket socket = serverSocket.accept();// listen for connection
                System.out.println("New client connected....");

                // READ THE COMMANDS FROM CLIENT
                // System.out.println("What command do you have for the server?");
                InputStream input = socket.getInputStream();// read command from client
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String command = reader.readLine(); // reads a line of text

                // ***WHAT WE USE THE COMMANDS FOR(PROCESSES)*/
                String s = null;
                Process p = Runtime.getRuntime().exec(command);

                BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));// contains the
                                                                                                        // normal output
                                                                                                        // after command

                BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));// contains the
                                                                                                        // error output
                                                                                                        // after command

                // read the output from the command
                System.out.println("Here is the standard output of the command:\n");
                while ((s = stdInput.readLine()) != null) {
                    System.out.println(s);
                }

                // read any errors from the attempted command
                System.out.println("Here is the standard error of the command (if any):\n");
                while ((s = stdError.readLine()) != null) {
                    System.out.println(s);
                }
                // ************End of processes */

                // after actions are done these lines send info back to the client
                OutputStream output = socket.getOutputStream();// Send data to client
                PrintWriter writer = new PrintWriter(output, true);
                writer.println("This is a message");
                socket.close();// close the client connection
                serverSocket.close();// terminate server
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }

    }
}

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
                InputStream input = socket.getInputStream();// read data from client
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line = reader.readLine(); // reads a line of text
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

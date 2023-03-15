import java.net.*;
import java.io.*;

public class IterativeSocketServer {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");

        /****************************************
         * HOW TO CREATE A SOCKET SERVER
         * *************************************
         */
        ServerSocket serverSocket = new ServerSocket(22);// Create a server socket
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
}

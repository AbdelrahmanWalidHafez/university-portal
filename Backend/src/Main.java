import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Main {
    public static void main(String[] args) {//making a multithreaded server that accepts the request only and all the processing will be done by the threads to balance the load on the server and let it handles more than one client
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("Server listening on port " +8000);
            while (true) {//to let the server on and accept all the requests
                Socket clientSocket = serverSocket.accept();//accepting the request
                ClientHandler clientHandler = new ClientHandler(clientSocket);//creating a new client handler object initialized by the client socket
                Thread clientThread = new Thread(clientHandler);//making a thread
                String clientIP = clientSocket.getInetAddress().getHostAddress();//getting the ip address of the connected client
                System.out.println("A client with IP address " + clientIP + " has connected to the server.");//printing the ip connected on the server terminal
                clientThread.start();//starting the client thread
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());//printing a message if an error occurred
        }
    }
}

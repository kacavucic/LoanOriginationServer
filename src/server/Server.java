package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import threads.ProcessRequests;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private int port;
    List<ProcessRequests> users;

    public Server(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        this.users = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                handleClient(socket);
            }
        } catch (Exception ex) {
            System.out.println("Server has been stopped!");
            ex.printStackTrace();
        }
    }

    private void handleClient(Socket socket) throws Exception {
        ProcessRequests processRequests = new ProcessRequests(socket);
        users.add(processRequests);
        processRequests.start();
    }

    public void stopServer() throws IOException {
        serverSocket.close();
        for (ProcessRequests user : users) {
            user.getSocket().close();
        }
    }
}

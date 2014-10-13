package net.teslaraptor.maintenanceserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server implements Runnable {
    
    public static boolean isRunning = false;
    
    private final ServerSocket serverSocket;
    private Socket connection;
    
    public Server() throws IOException {
        serverSocket = new ServerSocket(Config.port);
        
        MaintenanceServer.gui.println("Server initialized on port " + Config.port + ".");
    }
    
    public void readConnection() throws IOException {
        connection = serverSocket.accept();
        
        Thread thread = new Thread(new Connection(connection));
        thread.start();
    }
    
    public void shutdown() throws IOException {
        serverSocket.close();
    }
    
    @Override
    public void run() {
        while (Server.isRunning) {
            try {
                readConnection();
            } catch (SocketException e) {
                if (e.getMessage().equals("socket closed")) {
                    MaintenanceServer.gui.println("Socket forcefully closed.");
                } else {
                    MaintenanceServer.gui.printException(e);
                }
            } catch (Exception e) {
                MaintenanceServer.gui.printException(e);
            }
        }
        
        try {
            if (!serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (Exception e) {
            MaintenanceServer.gui.printException(e);
        }
        
        MaintenanceServer.gui.println("Server has been shutdown succesfully!");
    }
}

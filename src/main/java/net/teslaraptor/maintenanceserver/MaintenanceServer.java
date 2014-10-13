package net.teslaraptor.maintenanceserver;

public class MaintenanceServer {
 
    public static ServerGUI gui;
    public static boolean isGUI = true;
    
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("nogui")) {
            isGUI = false;
            
            Config.load();
            Server.isRunning = true;
            
            try {
                Server server = new Server();
                Thread serverThread = new Thread(server);
                serverThread.start();
            } catch (Exception e) {
                printException(e);
            }
        } else {
            MaintenanceServer.gui = new ServerGUI();
            MaintenanceServer.gui.setVisible(true);
        }
    }
    
    public static void printException(Exception e) {
        if (isGUI) {
            gui.printException(e);
        } else {
            e.printStackTrace();
        }
    }

    public static void print(String string) {
        if (isGUI) {
            gui.print(string);
        } else {
            System.out.print(string);
        }
    }

    public static void println(String string) {
        if (isGUI) {
            gui.println(string);
        } else {
            System.out.println(string);
        }
    }
}
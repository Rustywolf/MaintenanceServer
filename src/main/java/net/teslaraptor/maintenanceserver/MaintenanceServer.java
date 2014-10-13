package net.teslaraptor.maintenanceserver;

public class MaintenanceServer {
 
    public static ServerGUI gui;
    
    public static void main(String[] args) {
        MaintenanceServer.gui = new ServerGUI();
        MaintenanceServer.gui.setVisible(true);
    }
    
}
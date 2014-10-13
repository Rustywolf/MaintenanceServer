package net.teslaraptor.maintenanceserver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.regex.Pattern;
import net.teslaraptor.maintenanceserver.util.TextUtil;

public class Config {
    
    public static int port = 25565;
    
    public static String disconnectMessage = TextUtil.toChatChar("&4The server is in maintainence mode&8!");
    
    public static String versionName = "1.7.4 - 1.8";
    public static int versionProtocol = 0;
    public static int playersMax = 20;
    public static String description = TextUtil.toChatChar("&4The server is down for maintainence&8.");

    private static Pattern loadPattern = Pattern.compile("=");
    
    public static void load() {
        File file = new File("./config.txt");
        if (!file.exists()) {
            save();
        } else {
            try {
                Scanner scanner = new Scanner(new FileInputStream(file));
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] args = loadPattern.split(line, 2);
                    
                    if (args.length > 1) {
                        switch (args[0]) {
                            case "port":
                                port = Integer.valueOf(args[1]);
                                break; 
                                
                            case "disconnect-message":
                                disconnectMessage = TextUtil.toChatChar(args[1]);
                                break;
                                
                            case "version-name":
                                versionName = TextUtil.toChatChar(args[1]);
                                break;
                                
                            case "version-protocol":
                                versionProtocol = Integer.valueOf(args[1]);
                                break;
                                
                            case "max-players":
                                playersMax = Integer.valueOf(args[1]);
                                break;
                                
                            case "description":
                                description = TextUtil.toChatChar(args[1]);
                                break;
                        }
                    }
                }
                
                scanner.close();
            } catch (Exception e) {
                MaintenanceServer.gui.printException(e);
            }
        }
    }
    
    public static void save() {
        File file = new File("./config.txt");
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    MaintenanceServer.gui.println("There was an error creating the file.");
                    return;
                }
            }
            
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write("port=" + port);
            writer.newLine();
            writer.newLine();
            writer.write("disconnect-message=" + disconnectMessage.replace('§', '&'));
            writer.newLine();
            writer.newLine();
            writer.write("version-name=" + versionName.replace('§', '&'));
            writer.newLine();
            writer.write("version-protocol=" + versionProtocol);
            writer.newLine();
            writer.write("max-players=" + playersMax);
            writer.newLine();
            writer.write("description=" + description.replace('§', '&'));
            writer.flush();

            writer.close();
        } catch (Exception e) {
            MaintenanceServer.gui.printException(e);
        }
    }
    
}

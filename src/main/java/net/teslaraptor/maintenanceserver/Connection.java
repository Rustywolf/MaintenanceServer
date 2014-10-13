package net.teslaraptor.maintenanceserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import net.teslaraptor.maintenanceserver.packets.PacketInHandshake;
import net.teslaraptor.maintenanceserver.packets.PacketInPing;
import net.teslaraptor.maintenanceserver.packets.PacketInRequest;
import net.teslaraptor.maintenanceserver.packets.PacketOutDisconnect;
import net.teslaraptor.maintenanceserver.packets.PacketOutPing;
import net.teslaraptor.maintenanceserver.packets.PacketOutResponse;

public class Connection implements Runnable {

    private Socket connection;

    public Connection(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(connection.getInputStream());
            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());

            PacketInHandshake handshake = PacketInHandshake.read(dis);
            if (handshake == null) {
                return;
            }

            if (handshake.getState() == 1) {
                PacketInRequest request = PacketInRequest.read(dis);
                if (!request.getSuccess()) {
                    return;
                }

                PacketOutResponse response = new PacketOutResponse(Config.versionName, Config.versionProtocol, Config.playersMax, Config.description);
                PacketOutResponse.write(dos, response);

                PacketInPing ping = PacketInPing.read(dis);

                PacketOutPing pingOut = new PacketOutPing(ping.getPing());
                PacketOutPing.write(dos, pingOut);

                MaintenanceServer.gui.println("Ping complete to " + connection.getInetAddress().getHostAddress() + ":" + connection.getPort() + "!");
            } else if (handshake.getState() == 2) {
                
                PacketOutDisconnect disconnect = new PacketOutDisconnect(Config.disconnectMessage);
                PacketOutDisconnect.write(dos, disconnect);
                MaintenanceServer.gui.println("Client attempted to connect from " + connection.getInetAddress().getHostAddress() + ":" + connection.getPort() + "." + System.lineSeparator() + "Sent disconnect message.");
            } else {
                
                MaintenanceServer.gui.println("There was an unknown handshake form " + connection.getInetAddress().getHostAddress() + ":" + connection.getPort() + " (State " + handshake.getState() + ") ?!");
            }
        } catch (Exception e) {
            MaintenanceServer.gui.printException(e);
            MaintenanceServer.gui.println("There was an error! Oh no!");
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                MaintenanceServer.gui.printException(e);
            }
        }
    }

}

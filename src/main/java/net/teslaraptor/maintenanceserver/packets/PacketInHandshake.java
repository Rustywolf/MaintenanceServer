package net.teslaraptor.maintenanceserver.packets;

import java.io.DataInputStream;
import java.io.IOException;
import net.teslaraptor.maintenanceserver.MaintenanceServer;
import net.teslaraptor.maintenanceserver.util.ByteUtil;

public class PacketInHandshake {
    
    private int protocolVersion;
    private String serverAddress;
    private int serverPort;
    private int state;

    private PacketInHandshake(int protocolVersion, String serverAddress, int serverPort, int state) {
        this.protocolVersion = protocolVersion;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.state = state;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }

    public int getState() {
        return state;
    }
    
    public static PacketInHandshake read(DataInputStream dis) throws IOException {
        int packetLength = ByteUtil.readVarInt(dis);
        int packetId = ByteUtil.readVarInt(dis);
        
        if (packetId == 0) {
            int protocolVersion = ByteUtil.readVarInt(dis);
            String serverAddress = ByteUtil.readVarString(dis);
            int serverPort = dis.readUnsignedShort();
            int state = ByteUtil.readVarInt(dis);
            
            return new PacketInHandshake(protocolVersion, serverAddress, serverPort, state);
        } else {
            MaintenanceServer.println("Expected 0x00 Packet, received: " + packetId);
        }
        
        return null;
    }
    
}

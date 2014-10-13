package net.teslaraptor.maintenanceserver.packets;

import java.io.DataInputStream;
import java.io.IOException;
import net.teslaraptor.maintenanceserver.MaintenanceServer;
import net.teslaraptor.maintenanceserver.util.ByteUtil;

public class PacketInRequest {
    
    private boolean success;
    
    private PacketInRequest(boolean success) {
        this.success = success;
    }
    
    public boolean getSuccess() {
        return this.success;
    }
    
    public static PacketInRequest read(DataInputStream dis) throws IOException {
        int packetLength = ByteUtil.readVarInt(dis);
        int packetId = ByteUtil.readVarInt(dis);
        
        if (packetId == 0) {
            return new PacketInRequest(true);
        } else {
            MaintenanceServer.gui.println("Expected 0x00 Packet, received: " + packetId);
        }
        
        return new PacketInRequest(false);
    }
}

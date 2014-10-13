package net.teslaraptor.maintenanceserver.packets;

import java.io.DataInputStream;
import java.io.IOException;
import net.teslaraptor.maintenanceserver.MaintenanceServer;
import net.teslaraptor.maintenanceserver.util.ByteUtil;

public class PacketInPing {
    private long ping;
    
    private PacketInPing(long ping) {
        this.ping = ping;
    }
    
    public long getPing() {
        return this.ping;
    }
    
    public static PacketInPing read(DataInputStream dis) throws IOException {
        int packetLength = ByteUtil.readVarInt(dis);
        int packetId = ByteUtil.readVarInt(dis);
        
        if (packetId == 1) {
            long ping = dis.readLong();
            return new PacketInPing(ping);
        } else {
            MaintenanceServer.println("Expected 0x01 Packet, received: " + Integer.toHexString(packetId));
        }
        
        return null;
    }
}

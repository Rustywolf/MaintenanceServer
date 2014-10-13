package net.teslaraptor.maintenanceserver.packets;

import java.io.DataOutputStream;
import java.io.IOException;
import net.teslaraptor.maintenanceserver.util.ByteUtil;

public class PacketOutPing {
    
    private long ping;
    
    public PacketOutPing(long ping) {
        this.ping = ping;
    }
    
    public long getPing() {
        return this.ping;
    }
    
    public static void write(DataOutputStream dos, PacketOutPing packet) throws IOException {
        dos.write(ByteUtil.writeVarInt(9));
        dos.write(ByteUtil.writeVarInt(0x01));
        dos.writeLong(packet.getPing());
    }
    
}

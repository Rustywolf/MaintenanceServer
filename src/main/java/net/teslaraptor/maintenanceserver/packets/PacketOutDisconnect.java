package net.teslaraptor.maintenanceserver.packets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.DataOutputStream;
import java.io.IOException;
import net.teslaraptor.maintenanceserver.util.ByteUtil;

public class PacketOutDisconnect {
    
    private String message;
    
    public PacketOutDisconnect(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public static void write(DataOutputStream dos, PacketOutDisconnect packet) throws IOException {
        JsonObject obj = new JsonObject();
        obj.addProperty("text", packet.getMessage());
        
        dos.flush();
        byte[] data = ByteUtil.writeVarString(obj.toString());
        dos.write(ByteUtil.writeVarInt(data.length + 1));
        dos.write(ByteUtil.writeVarInt(0x00));
        dos.write(data);
        dos.flush();
    }
}

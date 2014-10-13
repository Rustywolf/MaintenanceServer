package net.teslaraptor.maintenanceserver.packets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.DataOutputStream;
import java.io.IOException;
import net.teslaraptor.maintenanceserver.util.ByteUtil;

public class PacketOutResponse {
    
    private String versionName;
    private int versionProtocol;
    private int playersMax;
    private String description;
    
    public PacketOutResponse(String versionName, int versionProtocol, int playersMax, String description) {
        this.versionName = versionName;
        this.versionProtocol = versionProtocol;
        this.playersMax = playersMax;
        this.description = description;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVersionProtocol() {
        return versionProtocol;
    }

    public int getPlayersMax() {
        return playersMax;
    }

    public String getDescription() {
        return description;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setVersionProtocol(int versionProtocol) {
        this.versionProtocol = versionProtocol;
    }

    public void setPlayersMax(int playersMax) {
        this.playersMax = playersMax;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public static void write(DataOutputStream dos, PacketOutResponse packet) throws IOException {
        JsonObject obj = new JsonObject();
        
        JsonObject versionObj = new JsonObject();
        versionObj.addProperty("name", packet.getVersionName());
        versionObj.addProperty("protocol", packet.getVersionProtocol());
        obj.add("version", versionObj);
        
        JsonObject playersObj = new JsonObject();
        playersObj.addProperty("max", packet.getPlayersMax());
        playersObj.addProperty("online", 0);
        playersObj.add("sample", new JsonArray());
        obj.add("players", playersObj);
        
        JsonObject descObj = new JsonObject();
        descObj.addProperty("text", packet.getDescription());
        obj.add("description", descObj);
        
        dos.flush();
        byte[] data = ByteUtil.writeVarString(obj.toString());
        dos.write(ByteUtil.writeVarInt(data.length + 1));
        dos.write(ByteUtil.writeVarInt(0x00));
        dos.write(data);
        dos.flush();
    }
}

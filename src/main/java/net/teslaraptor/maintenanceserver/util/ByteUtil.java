package net.teslaraptor.maintenanceserver.util;

import java.io.DataInputStream;
import java.io.IOException;

public class ByteUtil {

    public static int readVarInt(DataInputStream dis) throws IOException {
        int val = 0;

        boolean lastByte = false;
        int count = 0;

        do {
            byte nextByte = (byte) dis.read();
            lastByte = (0x80 & nextByte) == 0;

            val += ((0x7F & nextByte) << 7 * count);

            count++;
        } while (!lastByte);

        return val;
    }
    
    public static byte[] writeVarInt(int num) throws IOException {
        int count = 0;
        while (num >> count*7 > 0) {
            count++;
        }
        
        if (count == 0) {
            return new byte[] {0};
        }
        
        byte[] bytes = new byte[count];
        for (int i = 0; i < count; i++) {
            bytes[i] = ((byte) (((num >> i*7) & 0x7F) | (i != count - 1 ? 0x80 : 0x00)));
        }
        
        return  bytes;
    }

    public static String readVarString(DataInputStream dis) throws IOException {
        int stringLength = readVarInt(dis);
        byte[] stringBytes = new byte[stringLength];

        for (int i = 0; i < stringLength; i++) {
            stringBytes[i] = (byte) dis.read();
        }

        return new String(stringBytes, "ISO-8859-1");
    }
    
    public static byte[] writeVarString(String string) throws IOException {
        byte[] intBytes = writeVarInt(string.getBytes("UTF8").length);
        byte[] stringBytes = string.getBytes("UTF8");
        
        int aLen = intBytes.length;
        int bLen = stringBytes.length;
        byte[] ret = new byte[aLen+bLen];
        System.arraycopy(intBytes, 0, ret, 0, aLen);
        System.arraycopy(stringBytes, 0, ret, aLen, bLen);
        return ret;
    }

}

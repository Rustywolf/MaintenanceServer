package net.teslaraptor.maintenanceserver.util;

import java.util.regex.Pattern;

public class TextUtil {
    
    private static final Pattern finder = Pattern.compile("&(?=[a-fA-F0-9lmnokr])");
    
    public static String toChatChar(String message) {
        return finder.matcher(message).replaceAll("§");
    }
    
}

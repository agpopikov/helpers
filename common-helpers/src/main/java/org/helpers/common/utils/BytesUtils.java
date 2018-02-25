package org.helpers.common.utils;

import javax.xml.bind.DatatypeConverter;

public class BytesUtils {

    public static byte[] hexToBytes(String value) {
        return DatatypeConverter.parseHexBinary(value.toUpperCase().replaceAll(" ", ""));
    }

    public static byte[] hexToBytes(String value, Object... args) {
        return DatatypeConverter.parseHexBinary(String.format(value, args).replaceAll(" ", ""));
    }

    public static String bytesToHex(byte... value) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[value.length * 2];
        for (int j = 0; j < value.length; j++) {
            int v = value[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String bytesToHexWithFormat(byte... value) {
        String hex = bytesToHex(value);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < hex.length(); i++) {
            builder.append(hex.charAt(i));
            if (Math.floorMod(i, 2) == 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}

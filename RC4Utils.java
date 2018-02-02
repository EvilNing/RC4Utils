package com.example.ning.demo;

import android.text.TextUtils;


public class RC4Utils {
    public RC4Utils() {
        super();
    }

    private static String RC4(String arg8, String arg9) {
        int v5 = 256;
        int[] v3 = new int[v5];
        byte[] v4 = new byte[v5];
        int v1;
        for(v1 = 0; v1 < v5; ++v1) {
            v3[v1] = v1;
        }

        for (v1 = 0; v1 < v5;  v1++){
            v4[v1] = ((byte)arg8.charAt(v1 % arg8.length()));
        }


        v1 = 0;
        int v2 = 0;
        while(v1 < 255) {
            v2 = (v2 + v3[v1] + v4[v1]) % 256;
            v5 = v3[v1];
            v3[v1] = v3[v2];
            v3[v2] = v5;
            ++v1;
        }

        char[] v4_1 = arg9.toCharArray();
        char[] v5_1 = new char[v4_1.length];
        v1 = 0;
        v2 = 0;
        for(short x = 0;x < v4_1.length;x++) {
            v1 = (v1 + 1) % 256;
            v2 = (v2 + v3[v1]) % 256;
            int v6 = v3[v1];
            v3[v1] = v3[v2];
            v3[v2] = v6;
            v5_1[x] = ((char)((((char)v3[(v3[v1] + v3[v2] % 256) % 256])) ^ v4_1[x]));
        }

        return new String(v5_1);
    }

    private static String bytesToHexString(byte[] arg7) {
        String v0_1;
        StringBuilder v2 = new StringBuilder("");
        if(arg7 == null || arg7.length <= 0) {
            v0_1 = null;
        }
        else {
            int v3 = arg7.length;
            int v0;
            for(v0 = 0; v0 < v3; ++v0) {
                String v4 = Integer.toHexString(arg7[v0] & 255);
                if(v4.length() < 2) {
                    v2.append(0);
                }

                v2.append(v4);
            }

            v0_1 = v2.toString();
        }

        return v0_1;
    }

    private static byte charToByte(char arg1) {
        return ((byte)"0123456789ABCDEF".indexOf(arg1));
    }

    public static String deCode(String arg2, String arg3) {
        String v0 = new String(RC4Utils.hexStringToBytes(arg3));
        if(!TextUtils.isEmpty(((CharSequence)arg3))) {
            arg3 = RC4Utils.RC4(arg2, v0);
        }

        return arg3;
    }

    public static String enCode(String arg1, String arg2) {
        return RC4Utils.bytesToHexString(RC4Utils.RC4(arg1, arg2).getBytes());
    }

    private static byte[] hexStringToBytes(String arg6) {
        byte[] v0_1;
        if(arg6 == null || (arg6.equals(""))) {
            v0_1 = null;
        }
        else {
            String v0 = arg6.toUpperCase();
            int v2 = v0.length() / 2;
            char[] v3 = v0.toCharArray();
            v0_1 = new byte[v2];
            int v1;
            for(v1 = 0; v1 < v2; ++v1) {
                int v4 = v1 * 2;
                v0_1[v1] = ((byte)(RC4Utils.charToByte(v3[v4 + 1]) | RC4Utils.charToByte(v3[v4]) << 4));
            }
        }
        return v0_1;
    }
}

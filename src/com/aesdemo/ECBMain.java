package com.aesdemo;


public class ECBMain {
    public static void main(String[] args) {
        String plainStr = "";
        byte[] encBytes1 = AesEcbUtils.encrypt(plainStr);
        System.out.println("encByte1 length: " + encBytes1.length);
        int len0 = encBytes1.length;//flag长度在[00,15]之间
        int flaglen = len0 - 16;
        int FLAG = 0;
        //获取flag长度
        for (int i = 1; i <= 16; ++i) {
            String tmpStr = getStringByCount(i);
            byte[] tmp = AesEcbUtils.encrypt(tmpStr);
            if (tmp.length != len0) {
                FLAG = (16 - i) + flaglen;
                System.out.println("flag len： " + FLAG);
                break;
            }
        }
        StringBuilder sbFlag = new StringBuilder();
        for (int i = 1; i <= FLAG; ++i) {
            System.out.println("start: " + i);
            String str1 = repeat("a", 16 - i);
            String str2 = str1 + sbFlag;
            byte[] byte1 = AesEcbUtils.encrypt(str1);
            for (int j = 0; j <= 128; ++j) {
                char c = (char) ('\0' + j);
                String str1Temp = str2 + (c);
                byte[] byte1Tmp = AesEcbUtils.encrypt(str1Temp);
                if (bytesArrayEqual(byte1, byte1Tmp, 16)) {
                    System.out.println("第 " + i + " 位：" + (char) ('\0' + j));
                    sbFlag.append((char) ('\0' + j));
                    break;
                }
            }
        }

        System.out.println("flag: " + sbFlag);

    }

    public static String getStringByCount(int n) {
        if (n < 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        return sb.toString();
    }

    public static boolean bytesArrayEqual(byte[] byteS, byte[] bytesD, int n) {
        if ((null == byteS) || (null == bytesD)) {
            return false;
        }
        int len1 = byteS.length;
        int len2 = bytesD.length;
        if (len1 < n || len2 < n) {
            return false;
        }
        boolean res = true;
        for (int i = 0; i < n; ++i) {
            if (byteS[i] != bytesD[i]) {
                res = false;
                break;
            }
        }
        return res;
    }

    public static String repeat(String str, int n) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if (n <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(str.length() * n);
        for (int i = 0; i < n; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}

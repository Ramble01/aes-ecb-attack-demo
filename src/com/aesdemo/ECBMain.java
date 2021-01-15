package com.aesdemo;


public class ECBMain {
    public static void main(String[] args) {
        String empStr = "";
        byte[] encBytes1 = AesEcbUtils.encrypt(empStr);
        System.out.println("encByte1 length: " + encBytes1.length);
        int len0 = encBytes1.length;//flag长度在[00,15]之间
        //获取flag长度
        for (int i = 1; i <= 16; ++i) {
            String tmpStr = getStringByCount(i);
            byte[] tmp = AesEcbUtils.encrypt(tmpStr);
            if (tmp.length != len0) {
                System.out.println("flag len： " + (16 - i));
                break;
            }
        }
        //爆破flag第1位
        String str1 = "aaaaaaaaaaaaaaa";
        byte[] byte1 = AesEcbUtils.encrypt(str1);
        for (int i = 0; i <= 128; ++i) {
            char c = (char) ('\0' + i);
            String str1Temp = str1 + (c);
            byte[] byte1Tmp = AesEcbUtils.encrypt(str1Temp);
            if (bytesArrayEqual(byte1, byte1Tmp, 16)) {
                System.out.println("A: " + (char) ('\0' + i));
            }
        }

        //得到A=h后爆破第二位
        String str2 = "aaaaaaaaaaaaaa";
        byte[] byte2 = AesEcbUtils.encrypt(str2);
        for (int i = 0; i <= 128; ++i) {
            char c = (char) ('\0' + i);
//            System.out.println(c);
            String str1Temp = str2 + 'h' + (c);
            byte[] byte1Tmp = AesEcbUtils.encrypt(str1Temp);
            if (bytesArrayEqual(byte2, byte1Tmp, 16)) {
                System.out.println("B: " + (char) ('\0' + i));
            }
        }
        //得到B=e后爆破第三位
        String str3 = "aaaaaaaaaaaaa";
        byte[] byte3 = AesEcbUtils.encrypt(str3);
        for (int i = 0; i <= 128; ++i) {
            char c = (char) ('\0' + i);
//            System.out.println(c);
            String str1Temp = str3 + "he" + (c);
            byte[] byte1Tmp = AesEcbUtils.encrypt(str1Temp);
            if (bytesArrayEqual(byte3, byte1Tmp, 16)) {
                System.out.println("C: " + (char) ('\0' + i));
            }
        }
        //得到C=l后爆破第4位
        String str4 = "aaaaaaaaaaaa";
        byte[] byte4 = AesEcbUtils.encrypt(str4);
        for (int i = 0; i <= 128; ++i) {
            char c = (char) ('\0' + i);
//            System.out.println(c);
            String str1Temp = str4 + "hel" + (c);
            byte[] byte1Tmp = AesEcbUtils.encrypt(str1Temp);
            if (bytesArrayEqual(byte4, byte1Tmp, 16)) {
                System.out.println("D: " + (char) ('\0' + i));
            }
        }
        //得到D=l后爆破第5位
        String str5 = "aaaaaaaaaaa";
        byte[] byte5 = AesEcbUtils.encrypt(str5);
        for (int i = 0; i <= 128; ++i) {
            char c = (char) ('\0' + i);
//            System.out.println(c);
            String str1Temp = str5 + "hel" + (c);
            byte[] byte1Tmp = AesEcbUtils.encrypt(str1Temp);
            if (bytesArrayEqual(byte5, byte1Tmp, 16)) {
                System.out.println("E: " + (char) ('\0' + i));
            }
        }
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
}

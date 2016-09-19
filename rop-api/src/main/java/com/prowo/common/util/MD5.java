package com.prowo.common.util;

import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5 {
    private static final String ENCODE = "GBK";  //UTF-8
    public static final String KEY_EBK_USER_PASSWORD = "KEY_EBK_USER_PASSWORD";    //EBK_USER加码key

    public static final String KEY_TNT_USER_PASSWORD = "KEY_TNT_USER_PASSWORD";

    public static String md5(String str) {
        try {
            return new MD5().code(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String transStringMD5(String str, Integer beginIndex, Integer endIndex) {
        try {
            String passMD5 = new MD5().code(str);
            String rst = passMD5.substring(beginIndex, endIndex);
            return rst;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str;
        }
    }

    public String code(String str) throws NoSuchAlgorithmException {
        MessageDigest alga;
        String myinfo = str;
        alga = MessageDigest.getInstance("MD5");
        alga.update(myinfo.getBytes());
        byte[] digesta = alga.digest();
        String hs = "";
        String stmp = "";
        for (int n = 0; n < digesta.length; n++) {
            stmp = (Integer.toHexString(digesta[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    public static String code(String aValue, String aKey) {
        byte k_ipad[] = new byte[64];
        byte k_opad[] = new byte[64];
        byte keyb[];
        byte value[];
        try {
            keyb = aKey.getBytes(ENCODE);
            value = aValue.getBytes(ENCODE);
        } catch (UnsupportedEncodingException e) {
            keyb = aKey.getBytes();
            value = aValue.getBytes();
        }
        Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
        Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
        for (int i = 0; i < keyb.length; i++) {
            k_ipad[i] = (byte) (keyb[i] ^ 0x36);
            k_opad[i] = (byte) (keyb[i] ^ 0x5c);
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        md.update(k_ipad);
        md.update(value);
        byte dg[] = md.digest();
        md.reset();
        md.update(k_opad);
        md.update(dg, 0, 16);
        dg = md.digest();
        if (dg == null) {
            return null;
        }
        StringBuffer output = new StringBuffer(dg.length * 2);
        for (int i = 0; i < dg.length; i++) {
            int current = dg[i] & 0xff;
            if (current < 16) {
                output.append("0");
            }
            output.append(Integer.toString(current, 16));
        }
        return output.toString();
    }


    /**
     * MD5的算法在RFC1321 中定义
     * 在RFC 1321中，给出了Test suite用来检验你的实现是否正确：
     * MD5 ("") = d41d8cd98f00b204e9800998ecf8427e
     * MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661
     * MD5 ("abc") = 900150983cd24fb0d6963f7d28e17f72
     * MD5 ("message digest") = f96b697d7cb7938d525a2f31aaf161d0
     * MD5 ("abcdefghijklmnopqrstuvwxyz") = c3fcd3d76192e4007dfb496cca67e13b
     *
     * @author qiuguobin
     */
    public static String encode(String str) throws NoSuchAlgorithmException {
        MessageDigest alga;
        String myinfo = str;
        alga = MessageDigest.getInstance("MD5");
        try {
            alga.update(myinfo.getBytes(HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] digesta = alga.digest();
        String hs = "";
        String stmp = "";
        for (int n = 0; n < digesta.length; n++) {
            stmp = (Integer.toHexString(digesta[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs;
    }

    /**
     * 获取byte
     *
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getByte(String str) throws NoSuchAlgorithmException {
        MessageDigest alga;
        String myinfo = str;
        alga = MessageDigest.getInstance("MD5");
        try {
            alga.update(myinfo.getBytes(HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return alga.digest();
    }


    public static String encode16(String str) throws NoSuchAlgorithmException {
        return encode(str).substring(8, 24);
    }

    public static String encode32(String str) throws NoSuchAlgorithmException {
        return encode(str);
    }
}

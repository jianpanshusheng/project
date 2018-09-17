package demo.checkdemo.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * 各种类型转换函数整理
 *
 * @author Damin
 */
public class TransformUtils {

    /**
     * 16进制数字字符集
     * java.lang.Integer.toHexString() 方法返回为无符号整数基数为16的整数参数的字符串表示形式。
     * 以下字符作为十六进制数字：0123456789ABCDEF。
     */
    private static String hexString = "0123456789ABCDEF";


    // 转化十六进制编码为字符串
    public static String toStringHex2(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * 工具类方法
     */
    public static String to16(String onOff, int lines) {
        String sceneLines = Integer.toHexString(lines);
        String line = "";
        if (sceneLines.length() < 2) {
            line = "0" + sceneLines;
        } else {
            line = sceneLines;
        }
        String command = "AABB" + onOff + line;
        String check = MathUtil.getCheck(command);
        String finalCommand = command + check;
        return finalCommand;
    }

    /**
     * 将字符串编码成16进制数字,适用于所有字符（包括中文）
     *
     * @param str
     * @return String
     */
    public static String encode(String str) {
        // 根据默认编码获取字节数组
        byte[] bytes = null;
        try {
            bytes = str.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }

    /**
     * 将16进制数字解码成字符串,适用于所有字符（包括中文）
     *
     * @param bytes
     * @return String
     */
    public static String decode(String bytes) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(
                bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
                    .indexOf(bytes.charAt(i + 1))));

        return new String(baos.toByteArray(), "GB2312");

    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase(Locale.getDefault()));
        }
        return sb.toString();
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param src
     * @return String
     */
    public static String bytesToHexString(byte[] src, int size) {
        StringBuffer ret = new StringBuffer();
        if (src == null || size <= 0) {
            return null;
        }
        for (int i = 0; i < size; i++) {
            String hex = Integer.toHexString(src[i] & 0xFF);
            // String hex = String.format("%02x", src[i] & 0xFF);
            if (hex.length() < 2) {
                hex = "0" + hex;
            }
//            hex += "";
            ret.append(hex);
        }
        return ret.toString().toLowerCase(Locale.getDefault());
    }

    /**
     * 把字节数组转换成16进制字符串 0x0A格式
     *
     * @param src
     * @param length
     * @return String
     */
    public static String bytesToHexStringWithOx(byte[] src, int length) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || length <= 0) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            int v = src[i] & 0xFF;
            String hv = " 0x" + Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 把16进制字符串转换成字节数组
     *
     * @param hex
     * @return
     */
    public static byte[] hexStringToBytes(String hex) {
        while (hex.length() < 2)
            return null;
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (charToByte(achar[pos]) << 4 | charToByte(achar[pos + 1]));
        }
        return result;
    }

    public static byte charToByte(char c) {
        int index = "0123456789ABCDEF".indexOf(c);
        if (index == -1) {
            index = "0123456789abcdef".indexOf(c);
        }
        byte b = (byte) index;
        return b;
    }

    public static byte intToByte(int input) {
        byte output1;
        output1 = (byte) (input & 0xff);
        return output1;
    }

    /**
     * byte to int
     *
     * @param b
     * @return
     */
    public static int byteToInt(byte b) {
        return b < 0 ? b & 0x7F + 128 : b;
    }

    /**
     * 数字字符串转ASCII码字符串
     *
     * @return ASCII字符串
     */
    public static String StringToAsciiString(String content) {
        String result = "";
        int max = content.length();
        for (int i = 0; i < max; i++) {
            char c = content.charAt(i);
            String b = Integer.toHexString(c);
            result = result + b;
        }
        return result;
    }

    /**
     * 十六进制字符串装十进制
     *
     * @param hex 十六进制字符串
     * @return 十进制数值
     */
    public static int hexStringToAlgorism(String hex) {
        hex = hex.toUpperCase();
        int max = hex.length();
        int result = 0;
        for (int i = max; i > 0; i--) {
            char c = hex.charAt(i - 1);
            int algorism = 0;
            if (c >= '0' && c <= '9') {
                algorism = c - '0';
            } else {
                algorism = c - 55;
            }
            result += Math.pow(16, max - i) * algorism;
        }
        return result;
    }

    /**
     * 校验和 Checksum: (Start+Command+Length+Data)&0xFF
     *
     * @param Len
     * @return byte[]
     */
    public static byte[] Check_Sum(byte[] Data, int Len) {
        byte[] Sum = new byte[1];

        byte CheckSum = 0;
        for (int i = 0; i < Len; i++)
            CheckSum += Data[i];
        Sum[0] = (byte) (CheckSum & 0xFF);

        return Sum;
    }


    /**
     * 16进制转化为字符串 无乱码问题
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }
}

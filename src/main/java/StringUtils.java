import kt.Lichun;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class StringUtils {

    public static String base64Encode(String s) {
        Base64 base64 = new Base64();
        final byte[] textByte;
        try {
            textByte = s.getBytes("UTF-8");
            return base64.encodeToString(textByte);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return s;
        }
    }

    public static String base64Decode(String s) {
        Base64 base64 = new Base64();
        try {
            return new String(base64.decode(s), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return s;
        }
    }

    public static String encodePwd(String pwd) {
        String[] split = pwd.split("");
        String sl = "::";
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            sb.append(sl);
            sb.append(SALT);
            sb.append(s);
        }
        return base64Encode(sb.toString());
    }

    public static String decodePwd(String pwd) {
        String s1 = base64Decode(pwd);
        String sp = "::";
        return s1.replace(sp + SALT, "");
    }

    private static String SALT = "#$%^&uiop";

    public static void main(String[] args) {
        System.out.println(encodePwd("kl_zhpt_ah"));
//        System.out.println(base64Encode("皖GC6198"));
//        System.out.println(base64Encode("533746"));
    }
}

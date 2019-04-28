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
//        System.out.println(encodePwd("hfzd_kjk_5797"));
//        System.out.println(base64Encode("皖GC6198"));
        try {
            byte[] bytes = RSAUtil.decryptByPrivateKey(Base64.decodeBase64("Vm/zje/IrIuVgJDi2g8luQu0DDGYBqKE1s0xlFnimimIqtIhx9ALBGljzF+IW9g7fmfnoBNBo9mDrqslgE5Lp+jobEQbNDMgzCp9ffFiaN5/5vW5bQsgqegDQnbMlZLRV8jMflbFSz+HLSyYxzJh+ID+N6Pfe4DIge1dBHXW1xU="), "-----BEGIN RSA PRIVATE KEY-----\n" +
                    "MIICXQIBAAKBgQCzh1YvA22DJq9NxqLuSEHYUbOhY5nkcFZz8veGvmQCJmwfAOFD\n" +
                    "E88syXCiir1LVYoeaIUy+9nuCrih4Ba4CzwqykWHzfKfgPuBTuun/ZdZzyJ3BsMu\n" +
                    "KKkQdrSH0ZUROI9tPOuVCl8mArGjFdSoWJXLxjgx4i4pQ+ZxqoVWYRVIJQIDAQAB\n" +
                    "AoGBAIgHHpUmur6p/CApRuDXXRTD6yB48Gz3dXoLeePdtLcwV7IyH3yMsOiVhUOT\n" +
                    "bhR7rtiVh3929sx30SfOPEidMqtQhNNfmYB+4x6XHW4e91PrPR2TlPBXN+p2jz7q\n" +
                    "/d9XS0SSuvhjHy7QrM8apcoGqlVo97ZyYGu3H87ZjDwSH3htAkEA2i0gS36BTJxD\n" +
                    "+XxWvzWkhYtC9NiC3cIYHZMtuzMmfmMV6nLqJ7MZAZmw/g4cwAH6pana8FCQUyjb\n" +
                    "n5nEqGKZuwJBANKm/fOG4LwygQcSux4M0Ln/gKeNvSe120jwU7rJxnK01dMUV+9k\n" +
                    "f/L0TXgpLJ3JoiUPqOt37uH3vnGEReuYF58CQAEkUVHz1ItpGfX5T57qkfTc+Lm9\n" +
                    "VFoCxQuKlKexKddpf1jdxxn0FwJqIoIB4uaiXAR38hxl34t/4D5imKPk+DcCQQCb\n" +
                    "hyXqnoPhD5zKHx8+QZH+MdHIY9Lrtc5QnPgE6iL13+Qbc2K1YktBkvR7qlLBsPRh\n" +
                    "Tp2WNLeP6P2YDGQfXOzbAkBXq+S8hML/elnNlB2JrEExFwhbYqjvUBPdWfZPCVLl\n" +
                    "+mjk/wF/fPs8gu5LC+gGUFD8JbIh5WHd2KU8EOZjBdZB\n" +
                    "-----END RSA PRIVATE KEY-----");
            System.out.println(new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(base64Encode("533746"));
    }
}

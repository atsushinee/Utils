package test;

import java.util.HashMap;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("1", "2");
        System.out.println("1".hashCode());
        System.out.println("2".hashCode() ^ "1".hashCode() >>> 16);
    }

    public static String a() {
        String f = "32131.2018.3123123.7.131234.1";
        StringBuilder b = new StringBuilder();
        String[] split = f.split("[.]");
        for (String s : split) {
            b.append(Integer.toBinaryString(Integer.parseInt(s)));
            b.append(".");
        }
        b.deleteCharAt(b.length() - 1);
        return b.toString();
    }
}

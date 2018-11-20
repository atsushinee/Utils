package jni;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class TestJni {

    public interface nativeDLL extends Library {

        ///当前路径是在项目下，而不是bin输出目录下。
        nativeDLL INSTANCE = (nativeDLL) Native
                .loadLibrary(
                        "E:\\projects\\Utils\\src\\main\\java\\jni\\mr.dll",
                        nativeDLL.class);

        String Reply(String s);
        String GetMrKm3Data(String url,String sfzmhm,String ksrq);
    }

    public static void main(String[] args) {
//        String s = "123";
//        Pointer p = new Memory(s.length());
        String reply = nativeDLL.INSTANCE.Reply("123");
        System.out.println(reply);
        System.out.println(nativeDLL.INSTANCE.GetMrKm3Data("http://192.168.23.132:8484/vehcheckweb/services/InService?wsdl","21","123"));
//        nativeDLL.INSTANCE.Return("1231");

    }
}

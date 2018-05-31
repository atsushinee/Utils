package hotpublish;

import java.io.*;

// 自定义java类加载器  实现java类热加载
public class MyClassLoader extends ClassLoader {

    // 要加载的java类的classpath
    private String classpath;

    protected MyClassLoader(String classpath) {
        super(ClassLoader.getSystemClassLoader());
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        return super.findClass(name); // 取消父类的方法
        byte[] data = this.loadClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }

    // 加载class文件中的内容
    private byte[] loadClassData(String name) {
        // 包名改为路径
        System.out.println(name);
        name = name.replace(".", "//");
        try {
            FileInputStream is = new FileInputStream(new File(classpath + name + ".class"));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int b;
            while ((b = is.read()) != -1) {
                outputStream.write(b);
            }
            is.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

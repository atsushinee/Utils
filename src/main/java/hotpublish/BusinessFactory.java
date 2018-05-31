package hotpublish;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class BusinessFactory {
    private static final Map<String, LoadInfo> loadTimeMap = new HashMap<>();
    public static final String CLASS_PATH = "E:/projects/Utils/target/classes/";
    public static final String WHO_TO_HOT_PUBLISH = "hotpublish.MyBusiness";

    public static DynamicBusiness getBusiness(String className) {
        File loadFile = new File(CLASS_PATH + className.replaceAll("[.]", "/") + ".class");
        long lastModified = loadFile.lastModified();
        if (loadTimeMap.get(className) == null) {
            // 重新加载
            load(className, lastModified);
        } else if (loadTimeMap.get(className).getLoadTime() != lastModified) {
            load(className, lastModified);
        }
        return loadTimeMap.get(className).getDynamicBusiness();
    }

    private static void load(String className, long lastModified) {
        MyClassLoader myClassLoader = new MyClassLoader(CLASS_PATH);
        Class<?> loadClass = null;
        try {
            loadClass = myClassLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DynamicBusiness dynamicBusiness = getInstance(loadClass);
        LoadInfo loadInfo = new LoadInfo(myClassLoader, lastModified);
        loadInfo.setDynamicBusiness(dynamicBusiness);
        loadTimeMap.put(className, loadInfo);
    }

    private static DynamicBusiness getInstance(Class<?> loadClass) {
        try {
            return (DynamicBusiness) loadClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}

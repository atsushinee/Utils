package hotpublish;

// 封装加载类的信息
@SuppressWarnings("unused")
public class LoadInfo {

    private MyClassLoader myClassLoader;
    private long loadTime;
    private DynamicBusiness dynamicBusiness;

    public LoadInfo(MyClassLoader myClassLoader, long loadTime) {
        this.myClassLoader = myClassLoader;
        this.loadTime = loadTime;
    }

    public MyClassLoader getMyClassLoader() {
        return myClassLoader;
    }

    public void setMyClassLoader(MyClassLoader myClassLoader) {
        this.myClassLoader = myClassLoader;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public DynamicBusiness getDynamicBusiness() {
        return dynamicBusiness;
    }

    public void setDynamicBusiness(DynamicBusiness dynamicBusiness) {
        this.dynamicBusiness = dynamicBusiness;
    }
}

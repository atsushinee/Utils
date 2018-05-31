package hotpublish;

@SuppressWarnings("all")
public class RefreshToHotPublish implements Runnable {
    @Override
    public void run() {
        while (true) {
            DynamicBusiness business = BusinessFactory.getBusiness(BusinessFactory.WHO_TO_HOT_PUBLISH);
            business.doSomeThing();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

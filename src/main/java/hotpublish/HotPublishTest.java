package hotpublish;

public class HotPublishTest {

    public static void main(String[] args) {
        // F9
        new Thread(new RefreshToHotPublish()).start();
    }
}

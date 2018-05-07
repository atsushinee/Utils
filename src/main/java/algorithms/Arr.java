package algorithms;

public class Arr {
    public static double findMaxElement(double[] arr) {
        double max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static double average(double[] arr) {
        double sum = 0.0;
        for (double n : arr) {
            sum += n;
        }
        return sum / arr.length;
    }
}

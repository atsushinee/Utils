package algorithms;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Algo {

    public static void main(String[] args) {
//        program_113();
//        program_115();
        String s = "";
        int N = 20;
        for (int n = N; n > 0; n /= 2)
            s = (n % 2) + s;
        System.out.println(s);

    }

    /*
     * 编写一个程序，从命令行得到三个整数参数。
     * 如果它们都相等则打印equals 否则 打印 not equals
     *
     */
    private static void program_113() {
        int a, b, c;
        Scanner scanner = new Scanner(System.in);
        try {
            a = scanner.nextInt();
            b = scanner.nextInt();
            c = scanner.nextInt();
            if (a == b && b == c) {
                System.out.println("equals");
            } else {
                System.out.println("not equals");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input value is not integer!");
        }
        scanner.close();
    }

    /*
     * 编写一段程序，如果double类型的变量x和y都严格位于
     * 0 和 1 之间 则打印true 否则打印false
     *
     */

    private static void program_115() {
        double x, y;
        Scanner scanner = new Scanner(System.in);
        try {
            x = scanner.nextDouble();
            y = scanner.nextDouble();
            System.out.println(x > 0 && x < 1 && y > 0 && y < 0);
        } catch (InputMismatchException e) {
            System.out.println("Input value is not double!");
        }
        scanner.close();
    }
}
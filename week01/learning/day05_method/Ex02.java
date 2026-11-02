// 练习 3：计算器 —— 加减乘除四个方法，divide 要处理除数为 0
public class Ex02 {

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("  [warn] divisor must not be 0");
            return 0;
        }
        return a / b;
    }

    public static void main(String[] args) {
        System.out.println("1 + 2       = " + add(1, 2));
        System.out.println("1 - 2       = " + subtract(1, 2));
        System.out.println("1 * 2       = " + multiply(1, 2));
        System.out.println("1.0 / 2.0   = " + divide(1.0, 2.0));
        System.out.println("1.0 / 0.0   = " + divide(1.0, 0.0));

        // 注意：整数除法陷阱。add 收的是 int，所以要小数必须传 double 版本的方法
        System.out.println("5 / 2       = " + (5 / 2));          // 2
        System.out.println("5.0 / 2     = " + (5.0 / 2));        // 2.5
    }
}

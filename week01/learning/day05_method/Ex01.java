// 练习 1：方法重载 —— 三个 max，分别接 2 个 int、3 个 int、1 个 int[]
public class Ex01 {

    // 两个 int
    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    // 三个 int（参数个数不同 -> 合法重载），直接复用上面的 max，别写嵌套三元
    public static int max(int a, int b, int c) {
        return max(max(a, b), c);
    }

    // 一个 int 数组（参数类型不同 -> 合法重载）
    public static int max(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("array must not be empty");
        }
        int result = arr[0];
        for (int n : arr) {
            if (n > result) {
                result = n;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        System.out.println("max(1, 2)    = " + max(1, 2));
        System.out.println("max(1, 2, 3) = " + max(1, 2, 3));
        System.out.println("max(arr)     = " + max(arr));

        // 观察：同名方法，编译器按参数自动选版本（编译期绑定）
        // 注意：max(3.5, 2.1) 无法通过编译 —— 这里只定义了 int 和 int[] 版本
        // 想支持小数，再写一个 max(double, double) 即可，这也是重载的用途
    }
}

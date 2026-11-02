import java.util.Arrays;

// 练习 5：数组方法（补第 4 天的缺口）
// 重点看 reverse：方法里改的是数组内容，所以调用方看得到变化 —— 这正是值传递的体现
public class Ex05 {

    public static int maxOf(int[] arr) {
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

    public static int minOf(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("array must not be empty");
        }
        int result = arr[0];
        for (int n : arr) {
            if (n < result) {
                result = n;
            }
        }
        return result;
    }

    public static double averageOf(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        double total = 0;                  // 用 double，否则整数除法会把小数丢掉
        for (int n : arr) {
            total += n;
        }
        return total / arr.length;
    }

    public static int sumOf(int[] arr) {
        int total = 0;
        for (int n : arr) {
            total += n;
        }
        return total;
    }

    // 原地反转：双指针，从两头往中间换
    // 注意：方法内部修改的是同一个数组对象，所以外面能看到结果
    public static void reverse(int[] arr) {
        if (arr == null) {
            return;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] scores = {78, 92, 65, 88, 100};

        System.out.println("scores        = " + Arrays.toString(scores));
        System.out.println("sum           = " + sumOf(scores));
        System.out.println("max           = " + maxOf(scores));
        System.out.println("min           = " + minOf(scores));
        System.out.println("average       = " + averageOf(scores));

        reverse(scores);
        System.out.println("after reverse = " + Arrays.toString(scores) + "   <- changed: same array object");

        // 对照实验：如果 reverse 里改成 arr = new int[]{...}，外面就不会变（见 Ex06）
    }
}

import java.util.Arrays;

// 练习 6：值传递实验（面试最高频）
// 结论：Java 只有值传递。基本类型传数值副本，引用类型传"地址"的副本。
//   - 通过副本改对象内容  -> 外面看得到
//   - 让副本指向新对象    -> 外面看不到
// 说明：输出用英文，避免 Windows 控制台 GBK 编码导致中文乱码
public class Ex06 {

    // 实验 1：基本类型 —— 改的是副本
    public static void swap(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
        System.out.println("  inside swap:     a = " + a + ", b = " + b);
    }

    // 实验 2：通过引用副本修改对象内容 —— 有效
    public static void changeFirst(int[] arr, int value) {
        if (arr != null && arr.length > 0) {
            arr[0] = value;
        }
    }

    // 实验 3：让引用副本指向新数组 —— 无效
    public static void reassign(int[] arr) {
        arr = new int[]{999, 999, 999};
        System.out.println("  inside reassign: " + Arrays.toString(arr));
    }

    // 实验 4：正确交换数组两个元素的方式（传数组 + 两个下标）
    public static void swapInArray(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println("== test 1: primitive arguments ==");
        int x = 1;
        int y = 2;
        swap(x, y);
        System.out.println("  outside:         x = " + x + ", y = " + y + "   -> unchanged");
        System.out.println();

        System.out.println("== test 2: modify the object ==");
        int[] a = {1, 2, 3};
        changeFirst(a, 100);
        System.out.println("  outside:         " + Arrays.toString(a) + "   -> changed");
        System.out.println();

        System.out.println("== test 3: reassign the reference ==");
        reassign(a);
        System.out.println("  outside:         " + Arrays.toString(a) + "   -> unchanged");
        System.out.println();

        System.out.println("== test 4: swap two elements ==");
        int[] b = {10, 20, 30};
        System.out.println("  before:          " + Arrays.toString(b));
        swapInArray(b, 0, 2);
        System.out.println("  after:           " + Arrays.toString(b) + "   -> changed");
    }
}

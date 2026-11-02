// 练习 4：递归三连（阶乘 / 求和 / 斐波那契）+ 汉诺塔
// 递归三要素：1) 终止条件  2) 每次递归规模变小  3) 结果由小规模推出
// 说明：输出用英文，避免 Windows 控制台 GBK 编码导致中文乱码
public class Ex04 {

    // 阶乘：5! = 5 * 4 * 3 * 2 * 1
    // 注意用 long：13! 就超过 int 上限了
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        if (n <= 1) {
            return 1;                      // 终止条件
        }
        return n * factorial(n - 1);       // 规模从 n 变成 n-1
    }

    // 1 + 2 + ... + n
    public static int sum(int n) {
        if (n <= 0) {
            return 0;                      // 终止条件
        }
        return n + sum(n - 1);
    }

    // 斐波那契（递归版）：fib(n) = fib(n-1) + fib(n-2)
    // 缺点：重复计算极多，时间复杂度 O(2^n)，n=40 就会卡住
    public static int fib(int n) {
        if (n <= 1) {
            return n;                      // 终止条件：fib(0)=0, fib(1)=1
        }
        return fib(n - 1) + fib(n - 2);
    }

    // 斐波那契（循环版）：只保存前两个值，O(n)
    public static int fibLoop(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int next = a + b;
            a = b;
            b = next;
        }
        return b;
    }

    // 汉诺塔：把 n 个盘子从 from 借助 via 移到 to
    public static void hanoi(int n, char from, char to, char via) {
        if (n == 1) {
            System.out.println("  " + from + " -> " + to);
            return;
        }
        hanoi(n - 1, from, via, to);        // 1. 先把上面 n-1 个挪到辅助柱
        System.out.println("  " + from + " -> " + to);   // 2. 最大的那个挪到目标柱
        hanoi(n - 1, via, to, from);        // 3. 再把 n-1 个从辅助柱挪到目标柱
    }

    public static void main(String[] args) {
        System.out.println("== factorial ==");
        System.out.println("5!  = " + factorial(5));
        System.out.println("10! = " + factorial(10));

        System.out.println();
        System.out.println("== sum ==");
        System.out.println("sum(100) = " + sum(100));

        System.out.println();
        System.out.println("== fibonacci ==");
        for (int i = 0; i <= 10; i++) {
            System.out.print(fib(i) + " ");
        }
        System.out.println();
        System.out.println("fib(10)     = " + fib(10));
        System.out.println("fibLoop(10) = " + fibLoop(10));

        System.out.println();
        System.out.println("== recursion vs loop: fib(35) ==");
        long start = System.currentTimeMillis();
        int r1 = fib(35);
        long cost1 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        int r2 = fibLoop(35);
        long cost2 = System.currentTimeMillis() - start;

        System.out.println("recursive: " + r1 + "  cost " + cost1 + " ms");
        System.out.println("loop     : " + r2 + "  cost " + cost2 + " ms");

        System.out.println();
        System.out.println("== hanoi n=3 ==");
        hanoi(3, 'A', 'C', 'B');
    }
}

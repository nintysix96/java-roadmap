// 练习 2：判断质数
public class Ex03 {

    /**
     * 质数：大于 1，且只能被 1 和自身整除。
     * 优化：只需试除到 sqrt(n)。因为如果 n = a * b 且 a <= b，那么 a 一定 <= sqrt(n)。
     * 用 i * i <= n 代替 Math.sqrt(n)，避免浮点误差，也不用每次开方。
     */
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;               // 1 和负数都不是质数
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;           // 找到因子，直接返回（提前结束方法）
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.print("primes in 1..30: ");
        for (int i = 1; i <= 30; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        System.out.println("isPrime(17) = " + isPrime(17));
        System.out.println("isPrime(1)  = " + isPrime(1));
        System.out.println("isPrime(9)  = " + isPrime(9));
        System.out.println("isPrime(97) = " + isPrime(97));
    }
}

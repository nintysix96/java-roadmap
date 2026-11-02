package day05_method;

public class Ex01 {
    public static int max(int a, int b){
        return a > b ? a : b ;
    }

    public static int max(int a,int b,int c){
        return (a>b) ? ((a>c) ? a : c) : ((b>c) ? b : c);
    }

    public static int max(int[] arr){
        int max = arr[0];
        for(int i : arr) if (i > max) max = i;
        return max;
    }

    static void main() {
        int[] arr = {1,2,3,4,5};
        System.out.println(max(1,2));
        System.out.println(max(1,2,3));
        System.out.println(max(arr));
    }
}

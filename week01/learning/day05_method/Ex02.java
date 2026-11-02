package day05_method;

public class Ex02 {
    public int add(int a,int b){
        return a+b;
    }
    public int subtract(int a,int b){
        return a-b;
    }
    public int multiply(int a,int b){
        return a*b;
    }
    public double divide(double a,double b){
        if(b == 0) return b;
        return a/b;
    }

    static void main() {
        Ex02 e = new Ex02();
        System.out.println(e.add(1,2));
        System.out.println(e.subtract(1,2));
        System.out.println(e.multiply(1,2));
        System.out.println(e.divide(1.0,2.0));
    }
}

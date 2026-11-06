// 【修正 1】原文件第一行是 `package day08_oop_basic;`，已删除（原因见 Student.java 顶部）。
// 同一个目录（默认包）下的类可以直接互相引用，不需要 import。
public class StudentTest {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Alice";
        s1.score = 85;

        Student s2 = new Student();
        s2.name = "Bob";
        s2.score = 55;

        Student s3 = new Student();
        s3.name = "Carol";
        s3.score = 92;

        Student[] students = {s1, s2, s3};

        // 【修正 2】原来这里有一句 `System.out.println(students[0].name);`
        // 它是验证数组有没有装对用的调试代码，会把 "Alice" 打在输出最前面，
        // 导致实际输出比验收格式多一行。调试代码验证完就删，别留在提交里。

        // 第一部分：自我介绍
        for (int i = 0; i < students.length; i++) {
            students[i].introduce();
        }

        // 第二部分：一行一个，带及格状态
        for (int i = 0; i < students.length; i++) {
            String status = students[i].isPass() ? "PASS" : "FAIL";
            System.out.println(students[i].getInfo() + " " + status);
        }

        // 第三部分：平均分（注意 (double) 转换，否则整数除法会把小数砍掉）
        int sum = 0;
        for (int i = 0; i < students.length; i++) {
            sum += students[i].score;
        }
        double avg = (double) sum / students.length;
        System.out.printf("Average: %.1f%n", avg);
    }
}

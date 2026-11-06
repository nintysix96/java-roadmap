package day08_oop_basic;

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

        Student[] students = {s1,s2,s3};
        System.out.println(students[0].name);

        // 第一部分：自我介绍
        for (int i = 0; i < students.length; i++) {
            students[i].introduce();
        }

// 第二部分：一行一个，带及格状态
        for (int i = 0; i < students.length; i++) {
            String status = students[i].isPass() ? "PASS" : "FAIL";
            System.out.println(students[i].getInfo() + " " + status);
        }

        int sum = 0;
        for (int i = 0; i < students.length; i++) {
            sum += students[i].score;
        }
        double avg = (double) sum / students.length;
        System.out.printf("Average: %.1f%n", avg);
    }
}

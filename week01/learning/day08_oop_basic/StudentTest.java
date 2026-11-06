// 【修正 1】原文件第一行是 `package day08_oop_basic;`，已删除（原因见 Student.java 顶部）。
// 同一个目录（默认包）下的类可以直接互相引用，不需要 import。
public class StudentTest {
    public static void main(String[] args) {
        Student s1 = new Student("Alice", 85);
        Student s2 = new Student("Bob", 55);
        Student s3 = new Student("Carol", 92);

        // 额外验证两个构造器
        Student empty = new Student();              // 无参
        System.out.println("[" + empty.name + "," + empty.score + "]");   // [null,0]

        Student onlyName = new Student("Dave");     // 单参
        System.out.println("[" + onlyName.name + "," + onlyName.score + "]");  // [Dave,0]

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

        // ---- 练习 3：findTop / average ----
        // 【修正 3】这里原来多了一行孤立的 `java`（应该是复制粘贴时带进来的），
        // 编译器把它当成一句语句，后面缺分号，报 "需要';'"。
        // 教训：报错行号（46）往往不是出错的那一行，真正的问题在第 42 行。
        // 正常情况
        Student top = findTop(students);
        if (top != null) {                     // 必须判空，否则 NPE
            System.out.println("Top: " + top.getInfo());
        } else {
            System.out.println("No students");
        }
        System.out.printf("Average(check): %.1f%n", average(students));

// 边界情况（这四条必须全部跑一遍）
        System.out.println("empty  findTop: " + findTop(new Student[0]));   // null
        System.out.println("null   findTop: " + findTop(null));             // null
        System.out.println("empty  average: " + average(new Student[0]));   // 0.0
        System.out.println("null   average: " + average(null));

        Student[] one = {s1};                    // 只有一个人
        System.out.println(findTop(one).getInfo());   // 应该就是他

        Student[] sameScore = new Student[2];    // 分数相同
        sameScore[0] = new Student(); sameScore[0].name = "A"; sameScore[0].score = 80;
        sameScore[1] = new Student(); sameScore[1].name = "B"; sameScore[1].score = 80;
        System.out.println(findTop(sameScore).name);  // A 或 B 都算对

        Student[] zeros = new Student[1];        // 分数是 0
        zeros[0] = new Student(); zeros[0].name = "Z"; zeros[0].score = 0;
        System.out.println(findTop(zeros).name);      // Z（不能返回 null！）
    }

    static Student findTop(Student[] arr) {
        // ① 守卫：null 或 空数组，直接返回 null
        if (arr == null || arr.length == 0) {
            return null;
        }

        // ② 初值取第一个元素，不是 null、也不是 0
        Student top = arr[0];

        // ③ 从 1 开始比较（自己跟自己比没意义）
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].score > top.score) {
                top = arr[i];          // 记住"整个对象"，不是只记分数
            }
        }
        return top;
    }

    static double average(Student[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;                      // 返回 0（会隐式转成 0.0）
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i].score;           // 总分用 int 累加
        }

        return (double) sum / arr.length;  // 除法前转 double
    }
}

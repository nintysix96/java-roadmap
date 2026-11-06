// 【修正 1】原文件第一行是 `package day08_oop_basic;`，已删除。
// 原因：写了包名后，类的全名变成 day08_oop_basic.Student，运行时必须
//   退到上级目录执行：java -cp . day08_oop_basic.StudentTest
// 而直接 `java StudentTest` 会报：
//   NoClassDefFoundError: StudentTest (wrong name: day08_oop_basic/StudentTest)
// 第一周统一不用 package，第 9 天正式学"包"的时候再加回来。
public class Student {

    // 现在字段用 public 是为了方便直接赋值；
    // 第四段学"封装"时会改成 private + getter/setter + 校验。
    public String name;
    public int score;

    public void introduce() {
        // this.name 在这里可以不写：没有同名局部变量时，name 自动指向成员变量。
        // 写出来没错，而且等后面有 setName(String name) 时，this 就变成必须的了。
        System.out.println("I am " + this.name + ", score = " + this.score);
    }

    public boolean isPass() {
        return score >= 60;
    }

    public String getInfo() {
        return this.name + "(" + this.score + ")";
    }
}

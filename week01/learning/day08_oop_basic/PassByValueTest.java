/*
 * ===== 参数绑定实验总结（Java 只有值传递）=====
 *
 * 1. 为什么实验 2 外面看得到变化，而实验 3、4 看不到？
 *    → 实验 2 里 arr[0] = "Bart" 改的是"数组对象里的内容"：外面的 names 和形参 arr
 *      指向同一个数组对象，所以这次修改双方都看得到。
 *      实验 3 的 arr = new String[]{...} 只是让"形参这个变量"换指向了一个新数组，
 *      外面的 names2 仍然指着原来的数组，完全不知道发生了这件事。
 *      实验 4 的 s = "Alice" 同理（换指向）；而且 String 本身不可变，
 *      即使你想"改内容"也没有任何方法可用，这条路被彻底堵死。
 *
 * 2. 一句话概括：方法能改到外面的，只有"对象本身的内容"；
 *    方法永远改不了"调用方变量手里那张写着地址的纸条"。
 *
 * 判断口诀：赋值语句左边是【变量名】        → 改指针 → 外面看不到
 *           赋值语句左边是【变量[下标]】或【变量.字段】→ 改内容 → 外面看得到
 *
 * 注意：本文件不要写 package（第一周统一用默认包）。
 * 写了 package 后 java PassByValueTest 会报
 * NoClassDefFoundError (wrong name: day08_oop_basic/PassByValueTest)。
 */

public class PassByValueTest {

    // 实验 1：基本类型参数 —— 传的是数值副本，改副本不影响外面
    static void changeInt(int n) {
        n = 100;
    }

    // 实验 2：改"对象内容" —— 左边是 arr[下标]，改的是数组里的一个元素
    static void changeArray(String[] arr) {
        arr[0] = "Bart";
    }

    // 实验 3：改"引用指向" —— 左边是变量名 arr，只是让形参换指向一个新数组
    static void reassignArray(String[] arr) {
        arr = new String[]{"Bart", "Simpson"};
    }

    // 实验 4：String 换指向 —— 左边是变量名 s，且 String 不可变，改内容这条路也不存在
    static void changeString(String s) {
        s = "Alice";
    }

    // 实验 5（加分）：改对象字段 —— 左边是 s.字段，改的是对象内容，外面看得到
    static void changeScore(Student s) {
        s.score = 100;
    }

    // 实验 6（加分）：让对象引用换指向 —— 左边是变量名 s，外面看不到
    static void replaceStudent(Student s) {
        s = new Student();   // 只改了形参的指向
        s.name = "Tom";
        s.score = 0;
    }

    public static void main(String[] args) {
        // 实验 1：基本类型
        int n = 15;
        changeInt(n);
        System.out.println("[1] n = " + n + "          (expect 15)");

        // 实验 2：改内容 —— 有效
        String[] names = {"Homer", "Simpson"};
        changeArray(names);
        System.out.println("[2] names[0] = " + names[0] + "     (expect Bart)");

        // 实验 3：改指向 —— 无效
        String[] names2 = {"Homer", "Simpson"};
        reassignArray(names2);
        System.out.println("[3] names2[0] = " + names2[0] + "    (expect Homer)");

        // 实验 4：String 换指向 —— 无效
        String bob = "Bob";
        changeString(bob);
        System.out.println("[4] bob = " + bob + "            (expect Bob)");

        // 实验 5：改对象字段 —— 有效
        Student st = new Student();
        st.name = "Alice";
        st.score = 60;
        changeScore(st);
        System.out.println("[5] st.score = " + st.score + "     (expect 100)");

        // 实验 6：对象换指向 —— 无效
        replaceStudent(st);
        System.out.println("[6] st.name = " + st.name + "      (expect Alice)");
    }
}

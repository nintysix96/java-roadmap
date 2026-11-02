# 第 1 周学习笔记

> 记录人：nintysix96 ｜ 更新时间：2026-11-02
> 配套代码：`week01/learning/day05_method/`

## 进度看板

| 天 | 内容 | 状态 |
|---|---|---|
| Day 1 | 环境搭建 + Git + HelloWorld | ✅ 完成 |
| Day 2 | 变量 / 数据类型 / 运算符 / Scanner | ✅ 自学完成 |
| Day 3 | 流程控制 if/for/while/switch | ✅ 自学完成 |
| Day 4 | 数组 | ⏭ 跳过（见下方速查，代码里已验证） |
| Day 5 | 方法 / 重载 / 递归 / 值传递 | ✅ 完成 |
| Day 6 | 综合项目：学生成绩管理 | ⬜ 待做 |
| Day 7 | 第一周复盘 + 自测 | ⬜ 待做 |

---

## Day 1：环境与 Git

### 已跑通的链路

```
写代码 → javac 编译 → java 运行 → git add → git commit → git push → GitHub
```

### 常用命令速查

| 目的 | 命令 |
|---|---|
| 初始化仓库 | `git init -b main` |
| 暂存全部改动 | `git add .` |
| 提交 | `git commit -m "说明"` |
| 关联远端 | `git remote add origin https://github.com/用户名/仓库.git` |
| 首次推送并建立跟踪 | `git push -u origin main` |
| 之后推送 | `git push` |
| 看状态 | `git status` / `git log --oneline` |
| 看当前路径 | `Get-Location`（PowerShell）/ `cd`（cmd） |

### 踩过的坑（重点）

1. **不要在 `C:\Users\<你>` 或 `D:\` 根目录执行 `git init`**
   - 一开始在用户主目录误 init，整个用户文件夹变成仓库，`git status` 会扫全盘
   - 处理：`Remove-Item C:\Users\HP\.git -Recurse -Force`（里面没有任何提交，删了无损）
   - 铁律：`git init` 前先 `Get-Location` 看清路径

2. **`javac` 对空文件不报错**
   - `HelloWorld.java` 是 0 字节时，`javac` 退出码 0、不生成 `.class`，看起来像"命令无效"
   - 排查：`dir` 看文件大小、看有没有生成 `.class`

3. **`git add` 后面必须跟路径**
   - `git add` 单独执行 = "Nothing specified, nothing added"
   - 正确：`git add .`（当前目录全部）或 `git add 文件名`

4. **cmd 与 PowerShell 命令不通用**
   - `Get-ChildItem` 在 cmd 里不存在；cmd 用 `dir /a`
   - cmd 的 `cd` 不能跨盘符，要 `cd /d D:\...`
   - 建议统一用 PowerShell

### 环境信息

| 项 | 值 |
|---|---|
| JDK | 机器上装了 21.0.12 / 25.0.4 / 26.0.2，当前命令行默认 26.0.2 |
| 计划 | 第 3 周上 Spring 之前切到 **JDK 21 LTS**（`C:\Program Files\Java\jdk-21.0.12`） |
| JAVA_HOME | **未设置**，第 2 周用 Maven 前必须补上 |
| Git | 已配置 user.name / user.email / credential.helper = manager |

> 提醒：JDK 25+ 支持 `void main()` 这种无参入口，但**标准写法永远是**
> `public static void main(String[] args)`。切到 JDK 21 后无参写法直接报
> `Main method not found`。

---

## Day 4：数组速查（跳过，但必须会用）

```java
int[] a = new int[5];        // 默认值：int→0，double→0.0，boolean→false，引用→null
int[] b = {1, 2, 3};
b.length                     // 是属性，不带括号
b[0]                         // 索引从 0 开始，越界抛 ArrayIndexOutOfBoundsException
```

| 要点 | 说明 |
|---|---|
| 定长 | `new` 时长度定死，要变长用 `ArrayList`（第 2 周） |
| 引用类型 | `int[] c = b;` 是同一块内存；复制用 `Arrays.copyOf(b, b.length)` |
| 遍历 | 普通 `for` 能改元素；for-each 只读、拿不到索引 |
| 工具类 | `Arrays.toString / sort / copyOf / fill / equals` |
| 二维 | `int[][] m = new int[3][4];` 嵌套遍历 |

**手写要会的两个**：冒泡排序（双层循环 + 相邻交换）、二分查找（有序数组，`left/right/mid`，注意 `left <= right`）。

---

## Day 5：方法 / 重载 / 递归 / 值传递

### 5.1 方法定义

```java
public static int add(int a, int b) {
    return a + b;
}
```

- 方法必须在类中，Java 没有全局函数
- `return` 两个作用：**交回结果** + **立即结束方法**
- `void` 方法不能 `return 值;`，但可以 `return;` 提前结束
- 参数是局部变量，只在方法内有效

### 5.2 重载（Overload）

同名字、**参数列表不同**，编译期按参数类型自动选版本。

| 能重载 ✅ | 不能重载 ❌ |
|---|---|
| 参数**个数**不同 | 只有**返回值**不同 |
| 参数**类型**不同 | 只有**参数名**不同 |
| 参数**顺序**不同 | 只有**修饰符**不同 |

```java
max(int, int)         // 2 个 int
max(int, int, int)    // 3 个 int，内部复用 max(max(a,b), c)
max(int[])            // 数组入参
```

> 嵌套三元 `(a>b) ? ((a>c) ? a : c) : ((b>c) ? b : c)` 能写对，但可读性差，
> 重载就该互相复用。

### 5.3 值传递（今天最重要，面试必问）

**结论：Java 只有值传递。**

> 基本类型传的是**数值副本**；引用类型传的是**引用的副本（地址的副本）**。
> 所以：通过副本**修改对象内容**有效；让副本**指向新对象**无效。

实验代码见 `Ex06.java`，运行结果：

| 实验 | 方法内操作 | 外部结果 | 原因 |
|---|---|---|---|
| 1 | `swap(int a, int b)` 交换 | `x=1, y=2` **没变** | a、b 是数值副本 |
| 2 | `changeFirst(arr, 100)` 改 `arr[0]` | `[100, 2, 3]` **变了** | 副本地址指向同一数组 |
| 3 | `reassign(arr)` 里 `arr = new int[]{...}` | `[100, 2, 3]` **没变** | 只改了副本里的地址 |
| 4 | `swapInArray(arr, 0, 2)` 换下标 | `[30, 20, 10]` **变了** | 改的是同一数组的内容 |

**一句话背法**：
> 改内容有效，改指向无效。

**为什么"交换两个数"要用数组/对象包一层？**
因为基本类型传的是副本，方法里换不了外面的值；只能把值放进数组或对象里，通过"改内容"的方式换。

### 5.4 递归

**三要素**：① 终止条件 ② 每次递归规模变小 ③ 结果由小规模推出。

```java
// 阶乘
public static long factorial(int n) {
    if (n <= 1) return 1;              // 终止条件
    return n * factorial(n - 1);       // 规模 n → n-1
}
```

- **没有终止条件 → `StackOverflowError`**（栈溢出）
- 阶乘用 `long`：13! 就超过 `int` 上限
- 斐波那契递归版是 O(2^n)，大量重复计算；**循环版 O(n)**

实测（`Ex04.java`）：`fib(35)`

| 实现 | 结果 | 耗时 |
|---|---|---|
| 递归 | 9227465 | 约 30 ms |
| 循环 | 9227465 | 0 ms |

> 递归适合树形结构、分治（归并/快排）；简单递推关系优先用循环。

### 5.5 常见坑

| 坑 | 现象 | 正确做法 |
|---|---|---|
| `main` 签名不完整 | 换 JDK 后 `java Xxx` 报 Main method not found | `public static void main(String[] args)` |
| 第一周写 `package` | 命令行 `javac` 生成到子目录，`java` 找不到类 | 第一周全部默认包，第 9 天再学包 |
| `int[] arr` 空数组取 `arr[0]` | `ArrayIndexOutOfBoundsException` | 先判 `arr == null \|\| arr.length == 0` |
| 整数除法 | `5 / 2` = `2` | 要小数用 `5.0 / 2` 或 `double` 变量 |
| 控制台中文乱码 | `== �׳� ==` | 输出用英文，或 `chcp 65001`；源码编译加 `-encoding UTF-8` |

### 5.6 面试问答（今天要能答的）

1. **Java 是值传递还是引用传递？**
   只有值传递。基本类型传数值副本，引用类型传地址副本；能改对象内容，不能改引用指向。

2. **重载和重写的区别？**（重写下周学）
   重载：同类中同名不同参，编译期决定。重写：子类覆盖父类方法，运行期多态。

3. **只有返回值不同能构成重载吗？**
   不能，编译报错。调用时编译器无法只凭返回值区分。

4. **递归的缺点？**
   栈空间开销（可能 StackOverflowError）、重复计算（如朴素斐波那契 O(2^n)）、调试困难。

5. **`static` 方法里能直接用实例变量吗？**
   不能。`static` 属于类，实例变量属于对象，此时还没有对象。（第 9 天细讲）

### 5.7 代码清单与运行方式

| 文件 | 内容 |
|---|---|
| `Ex01.java` | 重载求最值：2 个 int / 3 个 int / int[] |
| `Ex02.java` | 计算器：加/减/乘/除，除零处理，整数除法陷阱 |
| `Ex03.java` | `isPrime` 质数判断（试除到 √n） |
| `Ex04.java` | 递归：阶乘 / 求和 / 斐波那契（递归 vs 循环）/ 汉诺塔 |
| `Ex05.java` | 数组方法：`maxOf` `minOf` `sumOf` `averageOf` `reverse` |
| `Ex06.java` | 值传递四组实验 |

```powershell
cd D:\L\java-roadmap\week01\learning\day05_method
javac -encoding UTF-8 *.java
java Ex06
```

> `-encoding UTF-8` 是为了让 javac 正确读取中文注释；IDEA 默认 UTF-8，不用加。

---

## 自测（明天做，不许翻笔记）

1. `int[] a = {1,2,3}; int[] b = a; b[0] = 9;` 之后 `a[0]` 是多少？为什么？
2. 写一个方法，交换数组里下标 i 和 j 的两个元素。
3. `public static void swap(int a, int b)` 能不能交换两个 int 变量的值？为什么？怎么才能做到？
4. 下面哪些是合法重载：`int f(int)` / `double f(int)` / `int f(int,int)` / `int f(int a)`？
5. 递归求 `1+2+...+n`，终止条件写什么？
6. `fib(10)` 的递归调用次数大概是多少？为什么循环版快那么多？
7. `main` 方法的完整签名是什么？缺了 `public` 会怎样？
8. `javac` 一个空文件会发生什么？

---

## 下一步

- Day 6：学生成绩管理（数组 + 方法 + 菜单循环 + 输入校验），**这个不跳过**
- Day 7：本周复盘 + 上面 8 道自测
- 第 2 周：面向对象（类与对象、构造器、封装、继承、多态、接口）

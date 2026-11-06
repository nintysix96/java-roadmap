# 第 1 周学习笔记

> 记录人：nintysix96 ｜ 更新时间：2026-11-06
> 配套代码：`week01/learning/day05_method/`、`week01/learning/day06_score_manager/`

## 进度看板

| 天 | 内容 | 状态 |
|---|---|---|
| Day 1 | 环境搭建 + Git + HelloWorld | ✅ 完成 |
| Day 2 | 变量 / 数据类型 / 运算符 / Scanner | ✅ 自学完成 |
| Day 3 | 流程控制 if/for/while/switch | ✅ 自学完成 |
| Day 4 | 数组 | ⏭ 跳过（见下方速查，代码里已验证） |
| Day 5 | 方法 / 重载 / 递归 / 值传递 | ✅ 完成 |
| Day 6 | 综合项目：学生成绩管理（118 行，6 条路径回归通过） | ✅ 完成 |
| Day 7 | 第一周复盘 + 自测 + v2（删除/排序/及格率，222 行，9 条回归通过） | ✅ 完成 |
| Day 8 | 面向对象（一）：类与对象 + 参数绑定三例 | 🔄 理论已学，练习待交 |

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

## Day 6：学生成绩管理（综合项目）

> 代码：`week01/learning/day06_score_manager/ScoreManager.java`（118 行）
> 交付标准：6 条路径全部回归通过——录入 / 判重 / 列表 / 统计 / 查询 / 退出

### 6.1 程序结构

```
ScoreManager
├── 数据（static 字段）
│   ├── MAX = 10                   容量
│   ├── String[] names             姓名（与 scores 平行数组）
│   ├── int[] scores               成绩
│   ├── int count                  已录入人数（= 已用长度，不是 MAX）
│   └── Scanner sc                 全局唯一，不要 new 多个
├── main()            菜单循环 + switch 分发，case 5 用 return 退出
├── addStudent()      满员 → 姓名校验 → 判重 → 读成绩 → 入库 → count++
├── readScore()       while(true) + hasNextInt，校验 0~100
├── exists(name)      遍历 0..count-1，用 equals 比对
├── printAll()        空表判断 → 表头 → 循环 printOne(i)
├── printOne(i)       单行输出，被 printAll / findByName 复用
├── statistics()      一次遍历求 total / max / min
└── findByName()      读姓名 → 遍历 equals → 命中打印 / 未命中 Not found
```

四条贯穿全项目的规则：

1. **所有循环用 `i < count`**，不是 `i < MAX`（否则打印 9 个 `null 0`，统计也错）
2. **`max` / `min` 初值取 `scores[0]`**，不能取 `0`（两人都是 80 时 min 会错成 0）
3. **平均分必须 `(double) total / count`**，否则整数除法把小数砍掉
4. **输出格式串只在 `printOne` 里维护一份**，改对齐只改一处

### 6.2 今天踩的 8 个坑（现象 → 原因 → 解法）

| # | 现象 | 原因 | 解法 |
|---|---|---|---|
| 1 | `NoClassDefFoundError: ScoreManager (wrong name: day06_score_manager/ScoreManager)` | 写了 `package`，类全名变成 `包名.类名` | 第一周删掉 package；或用 `java -cp 目录 全限定名` |
| 2 | `Added:Alice5`，成绩 85 凭空消失 | `main` 里已经消费过换行，`addStudent` 又补一句 `nextLine()`，把成绩行吃掉了 | 换行只在 `nextInt()` 之后、`nextLine()` 之前**消费一次**，下游不要再补 |
| 3 | 选 5 退不出程序，继续刷菜单 | `switch` 里的 `break` 只跳出 switch，`while` 照转 | `case 5: return;`（或布尔标志位 / 标签 break） |
| 4 | 菜单只在启动时出现一次 | 打印语句写在了 `while` 外面 | 挪进循环体首行 |
| 5 | `UnknownFormatConversionException: Conversion = '-'` | `%-4` 缺类型字符；`-4d` 缺 `%` | `%-4s`、`%-4d` |
| 6 | 统计 `Total: 0`、`Min: 0`，录满 10 人还越界 | 循环体里写的是 `scores[count]`，`count` 在循环里不变，永远读空位 0 | 改用循环变量 `scores[i]` |
| 7 | `错误: 找不到符号 result` | `result` 是 `printAll` 的局部变量，别的方法看不到 | 补一行定义，或抽 `printOne(i)` 复用 |
| 8 | 菜单输 `abc` 抛 `InputMismatchException`，程序直接退出 | 菜单层 `sc.nextInt()` 没有防护（`readScore` 里有，菜单没有） | `if (!sc.hasNextInt()) { println("Invalid choice"); sc.next(); continue; }` |

> 第 6 条的排查诀窍：**累加量恒为 0 / 恒等于初值时，先怀疑循环体里数组下标是不是没跟着 `i` 变**。

### 6.3 知识点补充

#### （1）`printf` 格式符结构

```
%   [标志][宽度][.精度]  类型字符
│    │      │              └─ 必填：s 字符串 / d 整数 / f 小数 / n 换行
│    │      └─ 占多少列
│    └─ - 表示左对齐（不写默认右对齐）
└─ 必填
```

| 写法 | 含义 | 示例（值 85，名 Alice） |
|---|---|---|
| `%d` | 原样输出整数 | `85` |
| `%5d` | 宽 5 右对齐 | `   85` |
| `%-5d` | 宽 5 左对齐 | `85   ` |
| `%-10s` | 宽 10 左对齐 | `Alice     ` |
| `%.1f` | 小数保留 1 位（自动四舍五入） | `88.5` |
| `%n` | 换行（跨平台，优于 `\n`） | — |

⚠️ 格式符和参数**个数、类型都要对上**：少参数抛 `MissingFormatArgumentException`，类型不符抛 `IllegalFormatConversionException`。

#### （2）`equals` vs `==`（操作文本时必用 `equals`）

```java
String a = "Alice";
String b = new String("Alice");
a == b          // false —— 比的是引用（地址）
a.equals(b)     // true  —— 比的是内容
```

项目里 `names[i].equals(name)`、`exists()` 判重全靠它。**用 `==` 会出现"明明存在却查不到"的 bug**，而且不报错。

`equalsIgnoreCase` 可忽略大小写。

#### （3）Scanner 的三条时序规律

| 操作 | 是否消费 | 后果 |
|---|---|---|
| `hasNextInt()` | ❌ 只看不取 | 不消费非法输入 → 死循环，必须配 `sc.next()` |
| `nextInt()` | ✅ 取走数字，**停在 `\n` 前** | 后面接 `nextLine()` 会读到空字符串 |
| `nextLine()` | ✅ 读到行尾，**连 `\n` 一起吃掉** | 它之后不存在残留换行 |

记法：**`nextInt()` 之后、`nextLine()` 之前，补一次 `nextLine()`；补过就不要再补。**

#### （4）局部变量的作用域

变量只在**它所在的那对 `{}`** 内有效，方法之间不共享。`printAll` 里的 `String result` 在 `findByName` 里不存在——这就是第 7 条坑的来源。

判断技巧：**看到"找不到符号"，先问"这个变量是在哪个 `{}` 里定义的"**。

#### （5）整数除法

`5 / 2 == 2`。要小数必须有一个操作数是浮点：`5.0 / 2` 或 `(double) total / count`。

### 6.4 面试问答（今天新增）

1. **`==` 和 `equals` 的区别？**
   `==` 比引用（基本类型比数值），`equals` 默认也是比引用，但 `String`、`Integer` 等重写过，比的是内容。

2. **为什么 `nextInt()` 之后直接 `nextLine()` 会读到空？**
   `nextInt` 只消费数字，换行符留在缓冲区；`nextLine` 从当前位置读到行尾，遇到残留换行就立刻返回空串。

3. **`switch` 里的 `break` 能跳出 `while` 吗？**
   不能，`break` 只作用于最近的一层 `switch` 或循环。要退出外层循环用 `return`、标志位或标签 `break outer;`。

4. **数组的 `length` 和集合的 `size()` 区别？**
   `length` 是数组的**属性**（不带括号），`size()` 是集合的**方法**（带括号）。字符串用 `length()`。

### 6.5 回归验收记录（2026-11-05）

| 路径 | 实测输出 | 结果 |
|---|---|---|
| 空表选 2 | `No record` | ✅ |
| 录入 Alice 85 / Bob 92 | `Added: Alice (85)`、`Added: Bob (92)` | ✅ |
| 重复录入 Alice | `Student already exists`，未索要成绩 | ✅ |
| 列表 | 2 行，列对齐，PASS 正确 | ✅ |
| 统计 | `Total: 177`、`Average: 88.5`、`Max: 92`、`Min: 85` | ✅ |
| 两人都是 80 | `Average: 80.0`、`Max: 80`、`Min: 80` | ✅ |
| 录满 10 人后统计 | `Total: 550`、`Average: 55.0`，不越界 | ✅ |
| 查 Alice / 查 Dave | 打印整行 / `Not found` | ✅ |
| 选 99 | `Invalid choice` | ✅ |
| 菜单输 `abc` | ❌ `InputMismatchException`（待修） | ⬜ |

### 6.6 Day 6 遗留 TODO（已在 Day 7 全部清掉）

- [x] 菜单层加 `hasNextInt()` 防护（坑 8）
- [x] `"No record"` → `"No records"`
- [x] `readScore()` 的提示语从 `while` 外挪到循环内
- [x] Day 7 三个加练功能：删除学生、按成绩排序、显示及格率

---

## Day 7：第一周复盘（闭卷自测 + v2 三个新功能）

> 代码：`week01/learning/day06_score_manager/ScoreManager.java`（118 → 222 行，8 项菜单）

### 7.1 闭卷自测批改（13 题，实际作答 8 题）

| 题 | 考点 | 我的答案 | 判定 |
|---|---|---|---|
| 1 | `int[] b = a` 后改 `b[0]` | `a[0] = 9` | ✅（理由：复制的是地址，两个引用指向同一数组） |
| 2 | 交换数组两个下标 | `tmp → arr[i]=arr[j] → arr[j]=tmp` | ✅ |
| 3 | 基本类型能否被方法交换 | 不能，值传递；传数组可以 | ✅（补充：也可 `return new int[]{b,a}`） |
| 4 | 哪些是合法重载 | "除了第二个之外" | ❌ **第 4 个也不合法** |
| 5 | 递归求和终止条件 | `n == 1` 时 `return 1` | ✅（更稳：`if (n <= 0) return 0;`） |
| 6 | `fib(10)` 调用次数 | 2 的 10 次方，循环次数少 | ✅（精确约 177 次；循环版 10 次） |
| 7 | `main` 完整签名 | 缺 `public`，低版本报错 | ⚠️ 半对（还漏 `String[] args`，且与版本无关） |
| 8 | `javac` 空文件 | 生成一个空 class | ❌ **不生成任何 class** |
| 9–13 | Day 6 相关 | 跳过未答 | ⏭（答案速览见笔记前文） |

**两个真漏洞（面试高频，务必记住）**

**（1）参数名不参与方法签名**

```java
int f(int a) { return a; }
int f(int b) { return b; }   // ❌ 编译错误：method f(int) is already defined
```

签名 = **方法名 + 参数的类型/顺序**。参数名只是形式，`f(int a)` 和 `f(int)` 在编译器眼里是同一个方法。

所以合法重载只有：`int f(int)`、`int f(int,int)`；`double f(int)`（只有返回值不同）和 `int f(int a)`（签名重复）都不合法。

**（2）空文件不生成 class**

0 字节的 `.java` 文件 → `javac` **退出码 0、不报错、不产出任何字节码**。因为 `.class` 是为"类"生成的，空文件里没有类声明。

判据永远是：**编译后看目录里有没有 `.class`**，而不是看有没有报错。

**（3）`main` 的四要素**

```java
public static void main(String[] args)
└─┬──┘ └─┬──┘ └┬─┘ └──────┬──────┘
public  static  void    String[] args
```

缺 `public` / `static` / `String[] args` 任一个 → `Main method not found in class X`，与 JDK 版本无关。
（JDK 25+ 支持无参 main 是特例，第 3 周切 JDK 21 后必须用标准签名。我已在项目里**三次**漏写 `public`。）

### 7.2 删除元素的三步模式（平行数组）

```java
int index = indexOf(name);
if (index == -1) {                       // ① 先判断存在（-1 表示未找到）
    System.out.println("Student not found");
    return;
}

for (int i = index; i < count - 1; i++) { // ② 后面的元素整体前移一格
    names[i] = names[i + 1];
    scores[i] = scores[i + 1];
}

names[count - 1] = null;                 // ③ 清理尾部残留
scores[count - 1] = 0;
count--;                                 // ④ 人数同步减一（最容易忘）
```

三个关键点：

| 点 | 说明 |
|---|---|
| 边界 `i < count - 1` | 写 `i < count` 会读 `arr[count]` 越界 |
| 尾部清理 | 不清会打印出 `null 0 FAIL` 的"幽灵行" |
| `count--` | `count` 是**影子数据**，任何增删都要手动同步，编译器不会提醒你 |

**通用规律**：用"数组 + count"这种手工管理容量的结构时，**每次增删都要同时维护 count**。这也是第 2 周 `ArrayList` 存在的意义——它替你维护。

### 7.3 平行数组排序要"双份交换"

```java
if (scores[j] < scores[j + 1]) {
    int t = scores[j]; scores[j] = scores[j+1]; scores[j+1] = t;      // 换成绩
    String s = names[j]; names[j] = names[j+1]; names[j+1] = s;       // 同时换姓名
}
```

只换一边 → **姓名和成绩错位**，而且程序不报错、不抛异常，静默出错。这是平行数组最危险的地方。

> 更彻底的办法是把 `name + score` 封成一个对象（第 8 天学完类之后就能做），从根上消除平行数组。这就是面向对象要解决的问题之一。

### 7.4 `printf` 打印百分号，以及及格率的算法

```java
int passed = 0;
for (int i = 0; i < count; i++) {
    if (scores[i] >= 60) passed++;
}
double rate = passed * 100.0 / count;        // 必须乘 100 才是百分数
System.out.printf("Pass rate: %.1f%%%n", rate);   // %% → 一个字符 %
```

- `passed / count` 得到的是 **0~1 的比例**，直接用会打印成 `0.7%`
- `%%` 是 printf 里转义百分号的写法：`%.1f` 输出数字，`%%` 输出 `%`
- 最后是 `66.7%`（1 位小数四舍五入）

### 7.5 "返回 -1" 的约定

```java
static int indexOf(String name) {
    for (int i = 0; i < count; i++) {
        if (names[i].equals(name)) return i;
    }
    return -1;      // 未找到
}
```

**不要用 `return 0` 表示"没找到"**——0 是合法下标（第一个人），调用方无法区分"找到了第一个"和"没找到"。

`-1` 是 Java 标准库的通用约定：`String.indexOf()`、`List.indexOf()`、`Arrays.binarySearch()` 找不到都返回负数。养成这个习惯，你和别人写的代码才能对接。

### 7.6 今天又踩的 3 个坑

| 现象 | 原因 | 解法 |
|---|---|---|
| `Pass rate: 0.7%` | `passed / count` 没乘 100 | `passed * 100.0 / count` |
| 删除后列表出现 `null 0 FAIL` | 忘了 `count--` | 增删都要同步 count |
| JDK 21 起动不了 | `main` 第 3 次缺 `public` | 固定写 `public static void main(String[] args)` |

### 7.7 v2 回归验收（9 条全过）

| # | 用例 | 实测 |
|---|---|---|
| 1 | 录 3 人 → 列表 | 3 行，PASS/PASS/FAIL 正确 |
| 2 | 统计 | `Total: 232`、`Average: 77.3`、`Max: 92`、`Min: 55` |
| 3 | 及格率 | `Pass rate: 66.7%` |
| 4 | 排序 | `92 Bob / 85 Alice / 55 Carol`，姓名跟随 |
| 5 | 删 Bob | `Deleted: Bob`，剩 2 行，无 null 幽灵行 |
| 6 | 删不存在的人 | `Student not found`，列表不变 |
| 7 | 删除后及格率 | `Pass rate: 50.0%`（说明 `count--` 生效） |
| 8 | 空表 | `No records`（列表/统计/及格率/排序/删除统一） |
| 9 | 菜单输 `abc` | `Invalid input`，不崩、回到菜单 |

### 7.8 第一周收官

**代码成果**：1 个命令行程序，118 → 222 行，8 项功能，11 个方法。

**真正掌握的**：

- 数组的引用语义、遍历、越界边界
- 方法拆分、重载、值传递（能讲清 3 种情形）
- 递归的终止条件与性能代价
- Scanner 的完整时序（`hasNextInt` / `nextInt` / `nextLine` 三者关系）
- 平行数组的增删改、双份交换、`count` 影子数据维护
- `printf` 格式化、`equals` vs `==`、`indexOf` 返回 -1 的约定
- Git 全链路：init → add → commit → push → 冲突排查

**还欠的**：

- 面向对象（第 8 天开始，整个 Java 的地基）
- 集合框架（第 2 周）：有了 `ArrayList` 和 `HashMap`，这个项目能砍掉一半代码
- 异常处理：现在全靠返回值表达错误，正式项目会用异常

---

## Day 8（一）：类与对象 + 参数绑定三例

> 参考资料：廖雪峰 [3.1 面向对象基础](https://liaoxuefeng.com/books/java/oop/basic/index.html) · [3.1.1 方法](https://liaoxuefeng.com/books/java/oop/basic/method/index.html)
> 代码目录（练习）：`week01/learning/day08_oop_basic/`

### 8.1 为什么需要类：从平行数组的痛讲起

```java
String[] names = new String[10];
int[] scores = new int[10];
```

| 问题 | 表现 |
|---|---|
| 加字段 | 想加"年龄"→ 再加一个 `int[] ages`，三处都要同步 |
| 排序 | 交换成绩**必须**同时交换姓名，漏一次就错位，**且不报错** |
| 删除 | 三个数组都要前移，漏一个就乱 |

类解决的正是这件事：**把"一个人的数据 + 操作这些数据的方法"绑成一个整体**。

```java
Student[] students = new Student[10];   // 一个人 = 一个对象
```

### 8.2 定义类与 `new` 的内存模型

```java
public class Student {
    String name;      // 成员变量（字段）
    int score;

    void introduce() {
        System.out.println("I am " + name + ", score = " + score);
    }

    boolean isPass() {
        return score >= 60;
    }
}
```

三条硬规则：

1. **文件名必须与 `public class` 同名**（`Student.java` ↔ `public class Student`）
2. **一个文件只能有一个 `public` 类**
3. 成员方法里可以直接用成员变量，不用当参数传

`new Student()` 做三件事：① 堆里分配内存 ② 字段填默认值 ③ 返回地址给变量。

```
栈（main 栈帧）                    堆
┌──────────────┐
│ s1 = 0x1a2b ─┼──────────────► ┌───────────────────┐
└──────────────┘                │ name="Alice"      │
                                │ score=85          │
                                └───────────────────┘
```

**`s1` 里存的是地址，不是数据本身**——和数组完全同机制（Day 5 的引用副本）。

### 8.3 成员变量有默认值，局部变量没有

| 类型 | 成员变量 | 局部变量 |
|---|---|---|
| `int` / `long` | `0` | **无**，直接用 → 编译错误 |
| `double` | `0.0` | 同上 |
| `boolean` | `false` | 同上 |
| 引用类型 | `null` | 同上 |

```java
public class Demo {
    int count;                  // 成员变量，默认 0
    public static void main(String[] args) {
        int x;
        System.out.println(x);  // ❌ 编译错误：可能尚未初始化变量 x
    }
}
```

> 面试常问："Java 变量不初始化能用吗？"——成员变量能，局部变量不能。

### 8.4 `null` 与 NPE

```java
Student s = null;
System.out.println(s.name);   // NullPointerException
```

看到 NPE 的固定动作：**找哪个引用是 `null`**。

### 8.5 对象数组的两个坑（最要命，直接关系项目重构）

**坑 1：`new Student[3]` 只创建了"装引用的格子"，里面全是 `null`**

```java
Student[] students = new Student[3];   // 三个格子都是 null
students[0].introduce();               // ❌ NPE

students[0] = new Student();           // ✅ 必须逐个 new
```

**坑 2：数组里存的是引用，不是对象副本**

```java
arr[0] = s1;
arr[0].score = 100;
System.out.println(s1.score);   // 100 —— 同一个对象
```

### 8.6 参数绑定三例（廖雪峰 3.1.1，★★ 核心）

**先说结论**：三个例子传参机制**完全相同**（都是值传递，传的是副本），差别只在**赋值语句的左边**。

#### 例 1：基本类型 —— 传数值副本

```java
int n = 15;
p.setAge(n);        // 复制 15 传给 age
n = 20;             // 改 main 里的 n
p.getAge();         // 仍是 15
```

结论：基本类型传**数值副本**，两边互不影响。

#### 例 2：引用类型 —— 改对象内容 → 外面看得到

```java
String[] fullname = {"Homer", "Simpson"};
p.setName(fullname);         // 传地址的副本
fullname[0] = "Bart";        // 改数组内容
p.getName();                 // "Bart Simpson" ← 变了
```

结论：副本和原变量**指向同一个对象**，改内容双方都看得到。

#### 例 3：引用类型 —— 改指向 → 外面看不到（两次都是 Bob）

```java
String bob = "Bob";
p.setName(bob);              // 传地址的副本（指向 "Bob"）
bob = "Alice";               // 让 bob 换指向
p.getName();                 // 仍是 "Bob"
```

**为什么是 Bob**：

1. `bob = "Alice"` 改的是**变量的指向**，不是 "Bob" 这个对象的内容
2. `String` **不可变**，连"改内容"这条路都堵死了

#### 决定性逻辑：这叫不叫"引用传递"？

若真是引用传递（形参=实参同一变量），例 3 应该输出 `Alice`；实际输出 `Bob` → **形参只是副本** → Java 只有值传递。

#### 左边写什么，决定改谁（最关键的一张表）

| 赋值左边 | 改的是 | 外面看得到 |
|---|---|---|
| `bob = "Alice"` | 变量换指向 | ❌ |
| `fullname[0] = "Bart"` | 对象里的元素 | ✅ |
| `p.name = "X"` | 对象里的字段 | ✅ |
| `fullname = new String[]{...}` | 变量换指向 | ❌ |

> **口诀：左边是变量名 → 改指针；左边是 `变量[...]` 或 `变量.字段` → 改内容。**

#### 三层模型：例 2 到底动了哪一层

```
第一层：变量 fullname  ──►  数组对象
第二层：数组对象        ──►  2 个格子
第三层：格子[0]、[1]    ──►  各自的 String
```

- `fullname[0] = "Bart"` → 动**第三层**，`p.name` 顺着同一地址看过去，看得到
- `fullname = new String[]{...}` → 动**第一层**，`p.name` 手里地址没变，看不到

#### 想让例 3 达到例 2 的效果（四方案）

| 方案 | 写法 | 改 `Person` 类 | 实际项目 |
|---|---|---|---|
| **0. 先改再传** | `bob = "Alice"; p.setName(bob);` | 否 | ✅ 最常用（String 的惯例：`s = s.trim()`） |
| **1. 直接调 setter** | `p.setName("Alice");` | 否 | ✅ 业务代码 99% 这么写 |
| **2. 换 `StringBuilder`** | `p.setName(sb); sb.replace(0, sb.length(), "Alice");` | 是 | 偶尔（需频繁修改时） |
| **3. `String[]` 盒子** | `holder[0] = "Alice";` | 是 | ✗ 只为理解机制，和例 2 完全同构 |

**永远做不到的事**：让"`bob` 换指向"自动反映到 `p.name`——机制层面不可能（`p.name` 存的是当时那份地址副本）。

#### 延伸自测（面试问法）

```java
StringBuilder sb = new StringBuilder("Bob");
p.setName(sb);
System.out.println(p.getName());   // ① Bob
sb.append("!");                    // 改内容
System.out.println(p.getName());   // ② Bob!
sb = new StringBuilder("Alice");   // 改指向
System.out.println(p.getName());   // ③ Bob!
```

#### 与后续章节的关联

| 后面会用到 | 怎么用 |
|---|---|
| Day 9 封装 | setter 检查参数，因为传进来的值会直接存进字段 |
| Day 10 多态 | 父类引用指向子类对象，靠的就是"引用存地址" |
| Day 11 接口 | 接口变量指向任意实现类对象 |
| 第 6 周 Spring | 依赖注入 = 把对象引用塞进字段，同一套模型 |

### 8.7 本段练习（待交）

`Student.java`：字段 `name`/`score`；`introduce()`、`isPass()`、`getInfo()`
`StudentTest.java`：3 个对象放 `Student[]`，遍历打印 + 平均分；另做一次 `Student s = null; s.name` 的 NPE 实验并抄下报错。

### 8.8 本段必踩的坑

| 坑 | 现象 | 原因 |
|---|---|---|
| 文件名 ≠ 类名 | `class X is public, should be declared in a file named X.java` | 硬性规定 |
| 忘了 `new` 就访问 | `NullPointerException` | 引用是 null |
| 对象数组没逐个 new | 同上 | `new Student[3]` 只建了数组 |
| `main` 里写 `this` | 编译错误 | `static` 方法没有当前对象 |
| 一个文件两个 `public class` | 编译错误 | 只能有一个 public 类 |

### 8.9 面试问答

1. **类和对象的关系？** 类是模板/类型，对象是它的一个实例；`new` 在堆上分配并返回引用。
2. **成员变量和局部变量的默认值？** 成员变量有默认值（0/false/null），局部变量必须先赋值。
3. **Java 是值传递还是引用传递？** 只有值传递；引用类型传地址副本，能改对象内容，不能改引用指向。
4. **`String` 传参为什么改不动？** 不可变（改不了内容）+ 传的是地址副本（改指向没用）。

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

**Day 6 追加：**

9. `printAll` 为什么用 `i < count` 而不是 `i < MAX`？
10. 为什么 `max` / `min` 的初值不能写 `0`？用什么当初值？
11. 比较两个 `String` 的内容为什么必须用 `equals`？用 `==` 会怎样？
12. 菜单里输入 `abc` 为什么会崩？怎么修？
13. `nextInt()` 之后直接 `nextLine()` 读到空串，根因是什么？

---

## 下一步

- **Day 8 剩余**：构造器（默认构造器会消失）、`this`、封装（`private` + getter/setter + 校验）
- **Day 9**：继承、方法重写、`super`、`final`、包与访问修饰符
- **Day 10–11**：多态、转型、抽象类、接口
- **第 3 周**：集合框架（`ArrayList`/`HashMap`）+ 异常 + IO
- **第 3 周前**：把默认 JDK 从 26.0.2 切到 21 LTS，并设置 `JAVA_HOME`

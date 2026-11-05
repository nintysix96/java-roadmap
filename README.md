# Java 后端学习路线（6 个月）

目标：6 个月内成为能独立开发的 Java 后端工程师。
每周有明确主题、产出和验收标准，代码与笔记全部提交在这里。

## 进度看板

| 周 | 主题 | 状态 |
|---|---|---|
| 第 1 周 | Java 基础：环境 / 语法 / 数组 / 方法 | 🔄 进行中 |
| 第 2 周 | 面向对象：类 / 封装 / 继承 / 多态 / 接口 | ⬜ |
| 第 3 周 | 集合框架 + 异常 + IO | ⬜ |
| 第 4–5 周 | MySQL + JDBC / MyBatis | ⬜ |
| 第 6–8 周 | Spring / Spring Boot / 项目一 | ⬜ |
| 第 9–12 周 | Redis / 消息队列 / Docker / 项目二 | ⬜ |
| 第 13 周起 | 并发、JVM、分布式、面试冲刺 | ⬜ |

> 详细分日计划见 `notes/week01.md` 里的进度看板。

## 目录结构

```
java-roadmap/
├── README.md
├── notes/                     # 学习笔记
│   └── week01.md
└── week01/
    └── learning/
        ├── day05_method/      # 方法 / 重载 / 递归 / 值传递
        │   ├── Ex01.java      # 重载求最值
        │   ├── Ex02.java      # 计算器
        │   ├── Ex03.java      # 质数判断
        │   ├── Ex04.java      # 递归：阶乘/求和/斐波那契/汉诺塔
        │   ├── Ex05.java      # 数组方法：最值/平均/反转
        │   └── Ex06.java      # 值传递四组实验
        └── day06_score_manager/
            └── ScoreManager.java   # 综合项目：学生成绩管理
```

## 怎么编译运行

```powershell
# 第 5 天的练习
cd week01/learning/day05_method
javac -encoding UTF-8 *.java
java Ex06

# 第 6 天的项目
cd week01/learning/day06_score_manager
javac -encoding UTF-8 ScoreManager.java
java ScoreManager
```

- `-encoding UTF-8` 让 javac 正确读取中文注释（IDEA 里默认 UTF-8，不用加）
- 编译产物 `*.class`、IDEA 的 `.idea/`、`out/` 已被 `.gitignore` 排除，不会进仓库

## 提交规范

```
week01 day05: 方法/重载/递归/值传递
```

格式：`第几周 第几天: 当天内容摘要`

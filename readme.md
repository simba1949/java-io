# 常见IO工具包

1. Java 原生的 java.io 和 java.nio 包下；

2. Google 的 Guava；

3. Apache 下的 commmon-io 包；

4. Hutool 包下 的 io 工具；

# 绝对路径和相对路径说明

Windows

* 绝对路径：（这里是两个\，源文件使用四个\，用于兼容 md 文档显示）

  * D:\\\\Program Files\\\\file.txt

  * D:/Program Files/file.txt

* 相对路径：

  * ./../file.txt

  * .\\..\\file.txt

Linux

* 绝对路径：/data/file.txt

* 相对路径：./../file.text

相对路径说明

> * 如果是在 main 包下相对路径是基于整个项目下的路径；
> * 如果是在 test 包下相对路径是基于该模块下的路径

# IO 流操作步骤

1. 选择数据源
2. 选择流
3. 对流进行操作，读或者写
4. 对流进行关闭，先开的流后关闭，后开的流先关闭

# 字符流和字节流的使用

字符流不能操作音频、视频、图片，字符流一般仅当读取一行文本或者写入一行文本时使用；

常见的文本文件有如下的格式：.txt、.java、.c、.cpp、.py 等；

注意：.doc、.xls、.ppt 这些都不是文本文件；

# 核心类

在整个 java.io 包中最重要的就是 5个类和3个接口；

| 类/接口         | 说明    |
| ------------ | ----- |
| File         | 文件类   |
| InputStream  | 字节输入流 |
| OutputStream | 字节输出流 |
| Reader       | 字符输入流 |
| Writer       | 字符输出流 |
| Closeable    | 关闭流接口 |
| Flushable    | 刷新流接口 |
| Serializable | 序列号接口 |

# 四大抽象类

| 抽象类          | 说明               | 常用方法                                          |
| ------------ | ---------------- | --------------------------------------------- |
| InputStream  | 字节输入流的父类，数据单位为字节 | int read(); void close()                      |
| OutputStream | 字节输出流的父类，数据单位为字节 | int write(int); void flush(); void close()    |
| Reader       | 字符输入流的父类，数据单位字符  | int read(); void close()                      |
| Writer       | 字符输出流的父类，数据单位字符  | int write(String); void flush(); void close() |

# Tips

## 缓冲流的基本原理：

> 在创建流对象时，内部会创建一个缓冲区数组（缺省使用 8192个字节(8Kb)的缓冲区），通过缓冲区读写，减少系统 IO 次数，从而提高读写的效率。

## 关于 flush

* flush() ：刷新缓冲区，流对象可以继续使用；

* close()：先刷新缓冲区，然后通知系统释放资源。流对象不可以再被使用了；

# Java IO

## 绝对路径和相对路径说明

Windows
> * 绝对路径：（这里是两个\，源文件使用四个\，用于兼容 md 文档显示）
>   * D:\\\\Program Files\\\\file.txt 
>   * D:/Program Files/file.txt
> * 相对路径：
>   * ./../file.txt
>   * .\..\file.txt

Linux
> * 绝对路径：/data/file.txt
> * 相对路径：./../file.text

相对路径说明
> * 如果是在 main 包下相对路径是基于整个项目下的路径；
> * 如果是在 test 包下相对路径是基于该模块下的路径
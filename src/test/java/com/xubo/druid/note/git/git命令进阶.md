## git进阶指南
**参考文章**
https://blog.csdn.net/qq_34609889/article/details/88733153
### merge 与 pull 的区别
有这个疑问的还是对git的原理理解不深。当时工作直接界面化操作，也就没有细想这两个命令的区别。git对每次提交commit都会生成一个一个commit id。我们的工作区切换
分支其实就是修改HEAD指针指向的commit id。eg: 我们本地有四个分支（dev test prod master）那么在.git\refs\heads这个文件夹里面就有四个文件，这些文件里面就
记录了当前分支的commit id。
    


### 
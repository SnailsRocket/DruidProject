## git进阶指南
**参考文章**
https://blog.csdn.net/qq_34609889/article/details/88733153
### merge 与 pull 的区别
有这个疑问的还是对git的原理理解不深。当时工作直接界面化操作，也就没有细想这两个命令的区别。git对每次提交commit都会生成一个一个commit id。我们的工作区切换
分支其实就是修改HEAD指针指向的commit id。eg: 我们本地有四个分支（dev test prod master）那么在.git\refs\heads这个文件夹里面就有四个文件，这些文件里面就
记录了当前分支的commit id。
当所有分支代码都一致的时候，refs\remotes\origin 和 \refs\heads 文件夹下面文件中的commit是一致的。
fetch eg:修改了本地分支上的代码，然后提交了，生成新的commit id。这个时候heads文件夹中dev里面的commit id信息已经换成最新的了。push到线上去的时候origin文件夹中的dev
文件修改成最新的commit id。然后切换到master分支，将dev分支merge到master分支，这个时候heads中master文件的内容也修改成最新的commit id。push到线上去的时候origin
文件夹中的master文件夹也修改成最新的commit id。
pull eg: 我们在master上修改了代码。然后commit push到线上分支。这个时候heads和origin文件夹中的master已经更新成最新的commit id。，然后我们将master分支的代码pull到
dev后，origin和heads中的commit id都更新成最新的id。
fetch + merge = pull
直接pull会导致很多问题,所以一般不要直接pull。    


### 
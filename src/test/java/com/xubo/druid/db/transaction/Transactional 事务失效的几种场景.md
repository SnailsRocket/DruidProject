### @Transactional注解 事务失效的几种场景
参考 https://www.cnblogs.com/xhq1024/p/13085280.html


#### 修饰 非public方法
AbstractFallbackTransactionAttributeSource.computeTransactionAttribute 这个方法会去校验被Transactional 注解修饰的方法的作用域是否是public


#### 一个没有@Transactional 的方法 调用  用 @Transactional 修饰的方法
由于使用Spring AOP代理造成的，因为只有当前事务方法被当前类以外的代码调用时，才回由Spring生成的代理对象来管理

#### 还有一种情况 A被注释 B也被注释
```java
@Transactional
public void a() {
    try {
        this.b();
    }catch(Exception e) {
        e.printStack();
    }
}
```
 

### MybatisPlus
saveBatch or saveOrUpdateBatch 限制单次执行的数量 batchSize = 1000，如果批量新增的数量大于1000，则会多次执行新增。
每当数量=1000时，就会刷新会话 sqlSession.flushStatements()

IService<T> 类中 saveBatch 方法   然后executeBatch方法中又刷新sqlSession的逻辑。

```json
if (i % batchSize == 0 || i == size) {
    sqlSession.flushStatements();
}
```



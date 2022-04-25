## SpringBoot启动MySQL连接失败

### 事故背景：
   开发的项目进入到测试阶段，需要将项目配置文件切换到测试环境。然后启动的时候数据库连接失败，并且一直在重连。

#### 异常信息
DruidAbstractDataSource.createPhysicalConnection   

java.sql.SQLException: connect error, url jdbc:mysql://xx.xx.xx.xxx:3306/abc?useUnicode=true&characterEncoding=UTF-8&serverTimeZone=GMT, driverClass com.p6spy.engine.spy.P6SpyDriver
    at com.alibaba.druid.pool.DruidAbstractDataSource.createPhysicalConnection(DruidAbstractDataSource.java:1579)
    at com.alibaba.druid.pool.DruidDataSource$CreateConnectionThread.run(DruidDataSource.java:2450)


#### 定位错误
Druid 源码

createPhysicalConnection conn == null 

```text
public DruidAbstractDataSource.PhysicalConnectionInfo createPhysicalConnection() throws SQLException {
        String url = this.getUrl();
        Properties connectProperties = this.getConnectProperties();
        String user;
        if (this.getUserCallback() != null) {
            user = this.getUserCallback().getName();
        } else {
            user = this.getUsername();
        }

        String password = this.getPassword();
        PasswordCallback passwordCallback = this.getPasswordCallback();
        if (passwordCallback != null) {
            if (passwordCallback instanceof DruidPasswordCallback) {
                DruidPasswordCallback druidPasswordCallback = (DruidPasswordCallback)passwordCallback;
                druidPasswordCallback.setUrl(url);
                druidPasswordCallback.setProperties(connectProperties);
            }

            char[] chars = passwordCallback.getPassword();
            if (chars != null) {
                password = new String(chars);
            }
        }

        Properties physicalConnectProperties = new Properties();
        if (connectProperties != null) {
            physicalConnectProperties.putAll(connectProperties);
        }

        if (user != null && user.length() != 0) {
            physicalConnectProperties.put("user", user);
        }

        if (password != null && password.length() != 0) {
            physicalConnectProperties.put("password", password);
        }

        Connection conn = null;
        long connectStartNanos = System.nanoTime();
        Map<String, Object> variables = this.initVariants ? new HashMap() : null;
        Map<String, Object> globalVariables = this.initGlobalVariants ? new HashMap() : null;
        createStartNanosUpdater.set(this, connectStartNanos);
        creatingCountUpdater.incrementAndGet(this);
        boolean var27 = false;

        long connectedNanos;
        long initedNanos;
        long validatedNanos;
        try {
            var27 = true;
            conn = this.createPhysicalConnection(url, physicalConnectProperties);
            connectedNanos = System.nanoTime();
            if (conn == null) {
                throw new SQLException("connect error, url " + url + ", driverClass " + this.driverClass);
            }

            this.initPhysicalConnection(conn, variables, globalVariables);
            initedNanos = System.nanoTime();
            this.validateConnection(conn);
            validatedNanos = System.nanoTime();
            this.setFailContinuous(false);
            this.setCreateError((Throwable)null);
            var27 = false;
        } catch (SQLException var28) {
            this.setCreateError(var28);
            JdbcUtils.close(conn);
            throw var28;
        } catch (RuntimeException var29) {
            this.setCreateError(var29);
            JdbcUtils.close(conn);
            throw var29;
        } catch (Error var30) {
            createErrorCountUpdater.incrementAndGet(this);
            this.setCreateError(var30);
            JdbcUtils.close(conn);
            throw var30;
        } finally {
            if (var27) {
                long nano = System.nanoTime() - connectStartNanos;
                this.createTimespan += nano;
                creatingCountUpdater.decrementAndGet(this);
            }
        }

        long nano = System.nanoTime() - connectStartNanos;
        this.createTimespan += nano;
        creatingCountUpdater.decrementAndGet(this);
        return new DruidAbstractDataSource.PhysicalConnectionInfo(conn, connectStartNanos, connectedNanos, initedNanos, validatedNanos, variables, globalVariables);
    }
```

这个时候去检查 配置文件  
driver-class-name:
druid.db-type:
url:
最后发现url 中缺少 p6spy
网上很多朋友是没有指定 db-type: mysql 导致 conn == null


### 正确的积分配置

```text
  datasource:
    driver-class-name: com.p6spy.engine.spy.P6SpyDriver
    url: jdbc:p6spy:mysql://127.0.0.1:3306/xxxxx?useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true&serverTimezone=GMT%2B8
    username: root
    password: xxxxx
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      db-type: mysql
```




### 参考博客
https://www.cnblogs.com/wandoupeas/p/15016744.html
https://blog.csdn.net/liuming690452074/article/details/118090291

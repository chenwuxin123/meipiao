# 实践是检验真理的唯一标准
 
## 开发环境
>
 * JDK11
 * Maven 3.5 +
 * IntelliJ IDEA ULTIMATE 2018.2 +（同时保证安装 ```lombok``` 插件）
 * Mysql 5.7 +

## 配置多数据源注意事项
>
---
```
 org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'xxxxController'
```
 造成的原因：`MAPPER_LOCATION` 未扫描到正确的mapper中的xml文件

---
开启对应数据源事务： `@Transactional(transactionManager = "mysqlTransactionManager")`

---
```
Mapped Statements collection already contains value for
```
使用注解sql时，xml中不要有对应的id

---
如再配置mysql添加config文件配置连接信息，而连接其他数据时，则需额导入相应的jar包


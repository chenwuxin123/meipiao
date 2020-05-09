# CWX Study Manual 
> (CWX的学习菜单)↩

### Brief

 ⌦ *The amount of experience gained from work is also my first MD*

### Guides

* [Elasticsearch--java操作之QueryBuilders构建搜索Query](https://www.cnblogs.com/pypua/articles/9459941.html)
* [支付服务：支付宝，微信，银联详细代码案例；支付API文档、持续更新中](https://gitee.com/52itstyle/spring-boot-pay)
* [FastJson的使用详解](https://www.jianshu.com/p/f20ffefeec4d)

⌘ps:其中Elasticsearch使用~~JestClient~~，推荐使用<font color="warning">Spring Data Elasticsearch</font>

### Related Configuration(Config)
*swagger2*: ```.apis(RequestHandlerSelectors.basePackage("com.meipiao.statistics.controller"))```修改此处对应接口

*RestTemplateConfig*: RestTemplate相关配置

*CorsConfig*: 跨域配置

*redis*: RedisConfig+RedisUtil--修改RedisTemplate默认序列化+Redis工具类

*Pagehelper*: 分页插件的使用

*elasticsearchUtil*: Elasticsearch使用jestClient多条件的查询 

*chat:* 企业微信

*result:* 统一返回结果

*RabbitMQ*: [此demo单独开启，内附双数据源配置，点击跳转](https://github.com/chenwuxin123/listener)

### About pom.xml files
<font color="warning">使用私人工具包，请添加镜像:</font>

 `<mirror>
 		<id>anyline</id>   
 		<mirrorOf>*</mirrorOf>   
 		<url>http://maven.anyline.org/repository/maven-public</url>   
 	</mirror> 
`
---
 <font color="warning">请添加pom依赖:</font>
---
 `
         <dependency>
             <groupId>org.anyline</groupId>
             <artifactId>anyline-core</artifactId>
             <version>8.3.7-SNAPSHOT</version>
         </dependency>
         <dependency>
             <groupId>org.anyline</groupId>
             <artifactId>anyline-net</artifactId>
             <version>8.3.7-SNAPSHOT</version>
         </dependency>
 `
 ---
 :o代码已迁至https://github.com/anylineorg/anyline

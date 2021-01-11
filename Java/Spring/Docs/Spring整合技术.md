# springBoot整合技术

## 整合JDBC

在resources-application.properties 里面配置连接

```
#mysql config
spring.datasource.url=jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

在pom xml里面配置依赖

```
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.22</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-jdbc -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
    <version>2.4.1</version>
</dependency>
```

在ApplicationTest里面测试查询

```
@Test
public void contextLoad(){
     List<Map<String,Object>> res =jdbcTemplate.queryForList("select * from myTest");
     System.out.println("rest "+res.size());
}
@Test
public void updateTest(){
    jdbcTemplate.execute("update myTest set Name='gg' where Id=1");
    System.out.println("rest success");
}
```
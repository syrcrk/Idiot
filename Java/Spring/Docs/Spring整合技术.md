# springBoot整合技术

## navicat连接问题

mysql8 之前的版本中加密规则是mysql_native_password,而在mysql8之后,加密规则是caching_sha2_password, 

把mysql用户登录密码加密规则还原成mysql_native_password. 

方法：

进入MySQL Server的bin目录，然后输入mysql -u root -p，输入密码

ALTER USER 'root'@'localhost' IDENTIFIED BY 'password' PASSWORD EXPIRE NEVER; #修改加密规则

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password'; #更新一下用户的密码

FLUSH PRIVILEGES; #刷新权限

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



## 整合Mybatis

### xml版

依赖插件 mybatis-generator

pom文件

```
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
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
        <!-- https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-spring-boot-starter -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.4</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-joda</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.module</groupId>
            <artifactId>jackson-module-parameter-names</artifactId>
        </dependency>
        <!-- 分页插件 -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.3</version>
        </dependency>
        
```

generator 插件

```
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
        <!-- mybatis generator 自动生成代码插件  -->
        <plugin>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-maven-plugin</artifactId>
            <version>1.3.2</version>
            <configuration>
                <configurationFile>${basedir}/src/main/resources/generator/generatorConfig.xml</configurationFile>
                <overwrite>true</overwrite>
                <verbose>true</verbose>
            </configuration>
        </plugin>
    </plugins>
</build>
```

数据库建表

```
CREATE TABLE `messages` (
  `id` varchar(16) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `senddate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```



Generator 配置文件位置新建文件 填入参数

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <!-- 数据库驱动:选择你的本地硬盘上面的数据库驱动包-->
    <classPathEntry  location="C:\Program Files\MySQL\Connector Java 8\mysql-connector-java-8.0.22.jar"/>
    <context id="DB2Tables"  targetRuntime="MyBatis3">
        <commentGenerator>
            <property name="suppressDate" value="true"/>
            <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>
        <!--数据库链接URL，用户名、密码 -->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://localhost:3306/test?serverTimezone=UTC" userId="root" password="123456" >
        </jdbcConnection>
        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>
        <!-- 生成模型的包名和位置-->
        <javaModelGenerator targetPackage="com.mybatis.mybatis.model" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        <!-- 生成映射文件的包名和位置-->
        <sqlMapGenerator targetPackage="mappers" targetProject="src/main/resources">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>
        <!-- 生成DAO的包名和位置-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.mybatis.mybatis.mapper" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>
        <!-- 要生成的表 tableName是数据库中的表名或视图名 domainObjectName是实体类名-->
        <table tableName="messages" domainObjectName="AppMessage" enableCountByExample="false" enableUpdateByExample="false" enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false"></table>
    </context>
</generatorConfiguration>
```

注意如果 **http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd** 这个报错 要手动添加dtd配置文件。

在Settings/Schemas and DTDs 里面添加外部dtd，添加这个连接，并设置本地路径为

**D:/maven-local-repository/org/mybatis/generator/mybatis-generator-core/1.3.2/mybatis-generator-core-1.3.2.jar!/org/mybatis/generator/config/xml/mybatis-generator-config_1_0.dtd**

在 Run /RunConfigurations 里面，添加Maven配置， 

```
mybatis-generator:generate -e
```

添加好后 运行一次，会生成， AppMessage.java   AppMessageMapper.java  ,  AppMessageMapper.xml

```
@SpringBootApplication
@MapperScan("com.mybatis.mybatis.mapper")
public class BeginerApplication
```

在Application 那里添加MapperScan， 并设在映射文件的包名， 剩下的就是常规操作，可以看Beginer 工程



### anotation版

配置application.yml

```
server:
  port: 8080
spring:
  datasource:
      name: test
      url: jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
      username: root
      password: 123456
      driver-class-name: com.mysql.jdbc.Driver
      filters: stat
      maxActive: 20
      initialSize: 1
      maxWait: 60000
      minIdle: 1
      timeBetweenEvictionRunsMillis: 60000
      minEvictableIdleTimeMillis: 300000
      validationQuery: select 'x'
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
      poolPreparedStatements: true
      maxOpenPreparedStatements: 20
```

手动添加模型文件  AppMessage.java

添加Mapper文件

```
@Mapper
public interface AppMessageMapper {
    @Insert("INSERT INTO messages(id, message, senddate) VALUES(#{id}, #{message}, #{senddate})")
    int insert(@Param("id") String id, @Param("message")String message, @Param("senddate")Date senddate);

    @Select("SELECT * FROM messages WHERE id = #{id}")
    AppMessage selectByPrimaryKey(@Param("id")String id);

}
```

新建测试

```
@Autowired
AppMessageMapper mapper;

@Test
void testAdd(){
    mapper.insert("456","wmd",new Date());
}
@Test
void testFind(){
    AppMessage message = mapper.selectByPrimaryKey("crk");
    System.out.println(message.getMessage()+" "+message.getSenddate());
}
```


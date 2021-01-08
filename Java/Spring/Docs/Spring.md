# Spring

## 标注

### RequestMapping

Annotation for mapping web requests onto methods in request-handling classes with flexible method signatures.



### Component

Indicates that an annotated class is a "Repository", originally defined by Domain-Driven Design (Evans, 2003) as "a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects".



### Controller

Indicates that an annotated class is a "Controller" (e.g. a web controller).
This annotation serves as a specialization of @Component, allowing for implementation classes to be autodetected through classpath scanning. It is typically used in combination with annotated handler methods based on the org.springframework.web.bind.annotation.RequestMapping annotation.

### RestController

A convenience annotation that is itself annotated with @Controller and @ResponseBody.
Types that carry this annotation are treated as controllers where @RequestMapping methods assume @ResponseBody semantics by default.



### PostMapping

Annotation for mapping HTTP POST requests onto specific handler methods.
Specifically, @PostMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.POST).



### GetMapping

### DeleteMapping

### PutMapping



### RequestBody

Annotation indicating a method parameter should be bound to the body of the web request. The body of the request is passed through an HttpMessageConverter to resolve the method argument depending on the content type of the request. Optionally, automatic validation can be applied by annotating the argument with @Valid.

### Repository

Indicates that an annotated class is a "Repository", originally defined by Domain-Driven Design (Evans, 2003) as "a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects

The value may indicate a suggestion for a logical component name, to be turned into a Spring bean in case of an autodetected component.
Returns:
the suggested component name, if any (or empty String otherwise)



### NotBlank

The annotated element must not be null and must contain at least one non-whitespace character. Accepts CharSequence.



### JsonProperty

Marker annotation that can be used to define a non-static method as a "setter" or "getter" for a logical property (depending on its signature), or non-static object field to be used (serialized, deserialized) as a logical property.
Default value ("") indicates that the field name is used as the property name without any modifications, but it can be specified to non-empty value to specify different name. Property name refers to name used externally, as the field name in JSON objects.



### Service

Indicates that an annotated class is a "Service", originally defined by Domain-Driven Design (Evans, 2003) as "an operation offered as an interface that stands alone in the model, with no encapsulated state."
May also indicate that a class is a "Business Service Facade" (in the Core J2EE patterns sense), or something similar. This annotation is a general-purpose stereotype and individual teams may narrow their semantics and use as appropriate.
This annotation serves as a specialization of @Component, allowing for implementation classes to be autodetected through classpath scanning.



### Qualifier

This annotation may be used on a field or parameter as a qualifier for candidate beans when autowiring. It may also be used to annotate other custom annotations that can then in turn be used as qualifiers.



### Autowired

Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities. This is an alternative to the JSR-330 javax.inject.Inject annotation, adding required-vs-optional semantics.

### EnableAutoConfiguration

Enable auto-configuration of the Spring Application Context, attempting to guess and configure beans that you are likely to need. Auto-configuration classes are usually applied based on your classpath and what beans you have defined. For example, if you have tomcat-embedded.jar on your classpath you are likely to want a TomcatServletWebServerFactory (unless you have defined your own ServletWebServerFactory bean).



### ComponentScan

Configures component scanning directives for use with @Configuration classes. Provides support parallel with Spring XML's <context:component-scan> element.
Either basePackageClasses or basePackages (or its alias value) may be specified to define specific packages to scan. If specific packages are not defined, scanning will occur from the package of the class that declares this annotation.



### SpringBootApplication

Indicates a configuration class that declares one or more @Bean methods and also triggers auto-configuration and component scanning. This is a convenience annotation that is equivalent to declaring @Configuration, @EnableAutoConfiguration and @ComponentScan.



## 概念比较

### 比较Component 和 Autowired

@Component的作用：
表示这个 Class 是一个自动扫描组件，当组件不好归类的时候，我们可以使用这个注解进行标注，相当于配置文件中的<bean id="" class=""/>

@Autowired的作用：
它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 通过 @Autowired的使用来消除 set ，get方法。

如果一个类是component了 ，那么他的构造函数默认就是要 自动装配的。可以不用写autowire

### @Autowired 用在构造函数和成员变量上的区别

因为Java类会先执行构造方法，然后再给注解了@Autowired 的user注入值，所以在执行构造方法的时候， autoWired的值是空的。可以看出，使用构造器注入的方法，**可以明确成员变量的加载顺序**。

## 工程理解

比如我要搭建一个网站，首先 我需要一个  Controller 类 来处理 put get delete update 等等。 要被spring 识别就需要 标记为@Controller， 但是还是不能处理路由功能，  需要跟一个@requestMapping， 表示路由。 需要做的就是增删改查吗， 先做增， 要向服务器加个张三，

post  localhost:8080/api/v1/person  

 {
	"name":"chen rong kai"
}

先让他能接受Post 请求，添加一个 addPerson函数，标记为 PostMapping， 如果传的是string，就太low了， 我们传个对象， 用spring的自动装配来把这个json string 转为 Person 对象，

定义一个Person 类，在他的构造函数上面上加上@JsonProperty 这个是 FIELD  METHOD  PARAMETER 都可以标记的

```
public Person(@JsonProperty("id") UUID id,@JsonProperty("name") String name)
```

来表示json到这个构造函数的映射关系。

回到addPerson函数

```
public void addPerson(Person person)
```

我们如何知道 Person的原始值是哪里来的呢， 就要， 加上标记，@RequestBody ，表示， 同时要验证下是否有效 @Valid，@NotNull

在addPerson里面 就要用 service 类来处理添加逻辑， service的赋值可以放到 Controller的构造函数里面， **这里Controller 是个Component，说明他可以让spring 来初始化，他的构造函数也是spring 来处理参数依赖**。 可以不用写autoWire

Service的构造函数要传Dao层对象，这边的DAO 层如果有多个实现，构造函数里面就可以写个 接口参赛，然后修饰上 @Qualifier("postgres") 用里面的参数选择具体的实现。这里的service 层都是调用dao的函数，就不讲了，

DAO层 添加类FakePersonDataAccessService， 加上注解 @Repository("postgres"), 表示用这个bean的名字。这里add就结束了。



现在要加个获取全部person的接口。这是个Get请求 要用 @GetMapping，然后使用service发挥列表

现在用加个获取某个id的person接口，这个Get请求后面要跟随path="{id}" ，函数的参赛用  @PathVariable("id") UUID id，表示从 id 映射到 UUID，

加个更新某个id的person数据接口，url用id， body用 person，这是个Put请求，用@PutMapping ， @PathVariable("id")，@Valid @NotNull @RequestBody Person person，

删掉某个person， 用@DeleteMapping 接口


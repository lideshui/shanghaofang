# shanghaofang
尚好房，基于Java语言、SSM框架、RPC框架的房源交易平台项目



# 1 搭建环境

## 1.1创建项目结构

- 创建父工程shf_parent，类型为project，删除src目录。
- 创建工具类子模块common_util，类型为model，创建时选择父项目为shf_parent。
- 创建实体类子模块model，类型为Maven的model，创建时选择父项目为shf_parent。
- 创建项目子模块web_admin，类型为Maven的model，创建时选择父项目为shf_parent。



## 1.2配置依赖关系

### 1.2.1父工程shf_parent

pom.xml文件内容：

- 打包方式设置为pom（idea自动完成）
- 聚合三个子模块（idea自动完成）
- 添加依赖
- 设置Maven编译版本

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.atguigu</groupId>
    <artifactId>shf_parent</artifactId>
    <!--打包方式设置为父工程，idea自动设置-->
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>

    <!--idea自动生成的聚合-->
    <modules>
        <module>common_util</module>
        <module>model</module>
        <module>web_admin</module>
    </modules>

    <!--jar包的版本管理-->
    <properties>
        <java.version>1.8</java.version>
        <spring.version>5.2.20.RELEASE</spring.version>
        <thymeleaf.version>3.0.11.RELEASE</thymeleaf.version>
        <pagehelper.version>4.1.6</pagehelper.version>
        <servlet-api.version>4.0.1</servlet-api.version>
        <fastjson.version>1.2.70</fastjson.version>
        <mybatis.version>3.5.6</mybatis.version>
        <mybatis.spring.version>2.0.6</mybatis.spring.version>
        <mysql.version>8.0.25</mysql.version>
        <druid.version>1.1.12</druid.version>
        <commons-fileupload.version>1.3.1</commons-fileupload.version>
        <slf4j-version>1.7.30</slf4j-version>
        <logback-version>1.2.3</logback-version>
    </properties>

    <!--依赖管理，子类可按需引用继承-->
    <dependencyManagement>
        <dependencies>
            <!-- SpringMVC相关 -->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webmvc</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <!--spring封装的jdbc数据库访问-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jdbc</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-tx</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <!--Spring提供的对AspectJ框架的整合-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-aspects</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <!--用于spring测试(没啥用，就是为了单元测试)-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-test</artifactId>
                <version>${spring.version}</version>
            </dependency>

            <!--用于springMVC模板-->
            <dependency>
                <groupId>org.thymeleaf</groupId>
                <artifactId>thymeleaf-spring5</artifactId>
                <version>${thymeleaf.version}</version>
            </dependency>

            <!--mybatis的分页插件-->
            <dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper</artifactId>
                <version>${pagehelper.version}</version>
            </dependency>
            <!-- Mybatis -->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis</artifactId>
                <version>${mybatis.version}</version>
            </dependency>
            <!-- Mybatis与Spring整合所需要的jar包 -->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis-spring</artifactId>
                <version>${mybatis.spring.version}</version>
            </dependency>
            <!-- MySql -->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>
            <!-- 连接池 -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>
            <!-- 文件上传组件 -->
            <dependency>
                <groupId>commons-fileupload</groupId>
                <artifactId>commons-fileupload</artifactId>
                <version>${commons-fileupload.version}</version>
            </dependency>

            <!-- fastjson json转换的-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>${fastjson.version}</version>
            </dependency>

            <!-- 日志 -->
            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-api</artifactId>
                <version>${slf4j-version}</version>
            </dependency>
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-classic</artifactId>
                <version>${logback-version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <!--子类强制继承的依赖-->
    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>${servlet-api.version}</version>
            <!--Servlet-API  不会部署到服务器上-->
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- 控制Maven的编译版本：java编译插件，设置编译版本1.8-->
            <!-- 其实之前在maven的配置文件中设置过，不加也行⚠️ -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.2</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



### 1.2.2子模块common_util

pom.xml文件内容：

- 继承父工程（idea自动完成）
- 选择性继承父工程内管理的依赖
- 添加依赖
- 设置Maven编译版本

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>shf_parent</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <!--version、groupId与父模块相同，可省略只写artifactId-->
    <artifactId>common_util</artifactId>

    <!--将父工程内管理的jar包都继承下来，可以不用加version-->
    <dependencies>
        <!-- SpringMVC相关 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
        <!--spring封装的jdbc数据库访问-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
        </dependency>
        <!--Spring提供的对AspectJ框架的整合-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
        </dependency>
        <!--用于spring测试-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
        </dependency>

        <!--用于springMVC模板-->
        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf-spring5</artifactId>
        </dependency>

        <!--mybatis的分页插件-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
        </dependency>
        <!-- Mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
        </dependency>
        <!-- Mybatis与Spring整合所需要的jar包 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
        </dependency>
        <!-- MySql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!-- 连接池 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
        </dependency>
        <!-- 文件上传组件 -->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
        </dependency>

        <!-- fastjson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
        </dependency>

        <!-- 日志 -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
        </dependency>
    </dependencies>
</project>
```



### 1.2.3子模块model

pom.xml文件内容：

- 继承父工程（idea自动完成）
- model是实体类子模块，只提供bean类，几乎不需要引入父工程管理的依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>shf_parent</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <!--version、groupId与父模块相同，可省略只写artifactId-->
    <artifactId>model</artifactId>

</project>
```



### 1.2.4子模块web_admin

pom.xml文件内容：

- 继承父工程（idea自动完成）
- 选择性继承父工程内管理的依赖
- 添加兄弟模块common_util和model为依赖，根据依赖传递使用他们compile的依赖
- 设置设置服务器jetty的Maven的插件形式

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>shf_parent</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <!--version、groupId与父模块相同，可省略只写artifactId-->
    <artifactId>web_admin</artifactId>

    <!--设置打包方式为war包-->
    <packaging>war</packaging>

    <!--直接依赖兄弟模块common_util和model，根据依赖传递的特性可使用他们compile的jar包-->
    <dependencies>
        <!--使用兄弟模块common_util的依赖-->
        <dependency>
            <groupId>com.atguigu</groupId>
            <artifactId>common_util</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <!--使用兄弟模块model的依赖-->
        <dependency>
            <groupId>com.atguigu</groupId>
            <artifactId>model</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <!--设置服务器jetty的参数，以Maven的插件形式存在-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>9.4.15.v20190215</version>
                <configuration>
                    <!-- 如果检测到项目有更改则自动热部署，每隔n秒扫描一次。默认为0，即不扫描-->
                    <scanIntervalSeconds>10</scanIntervalSeconds>
                    <webAppConfig>
                        <!--指定web项目的根路径，默认为/    设置上下文路径-->
                        <contextPath>/</contextPath>
                    </webAppConfig>
                    <httpConnector>
                        <!--设置端口号，默认 8080-->
                        <port>8000</port>
                    </httpConnector>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



## 1.3配置SSM环境

### 1.3.1common_util

该模块负责依赖与常用工具类管理

- 依赖管理的实现：
  - 直接继承父工程的依赖，再成为其他模块的依赖
- 工具类管理的实现：
  - 在main/java下创建不同的包，对异步请求结果处理类、枚举处理类、类型转换类、MD5加密类等工具类进行管理

模块结构：

- common_util
  - src/main/java/com.atguigu
    - result (存放工具类)
    - util (存放工具类，QiniuUtil.java文件先不加入)



### 1.3.2model

该模块主要存放数据表对应的pojo类：

- 在main/java下创建每张表对应的pojo类

模块结构：

- model
  - src/main/java/com.atguigu
    - entity (存放实体类)
    - vo (存放实体类)



### 1.3.3web_admin

该模块是ssm模块，实现MVC功能，请创建Web模块、日志模块、mvc目录结构、ssm目录结构

#### 1.3.3.1添加web模块

- 将项目的打包方式设置为war包
- 在模块结构中添加web.xml，模块名后添加路径src/main/webapp/



#### 1.3.3.2添加日志模块

在resources目录添加logback.xml文件，该文件名固定

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration debug="false">

    <!--定义日志文件的存储地址 logs为当前项目的logs目录 还可以设置为../logs -->
    <property name="LOG_HOME" value="logs" />

    <!--控制台日志， 控制台输出 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度,%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- 日志输出级别 -->
    <root level="DEBUG">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```



#### 1.3.3.3创建mvc目录结构

mvc结构：

- web_admin/src/main

  - java/com.atguigu

    - controller (控制层)
      - IndexController(首页访问控制类)
      - RoleController(用户角色管理控制类)
    - dao (持久层)
      - RoleDao (映射接口)
    - service (业务层)
      - RoleService (业务接口)
      - impl
        - RoleServiceImpl (业务实现类)

  - resources (资源目录)

    - mapper

      - RoleDao.xml (接口映射文件)

      

#### 1.3.3.4创建ssm分层

ssm分层：

- web_admin/src/main
  - resources (资源目录)
    - mapper
      - RoleDao.xml (接口映射文件)
    - spring
      - spring-dao.xml (数据源+mybatis)⚠️
      - spring-mvc.xml (视图解析器+fastjson处理器+注解扫描器+mvc常规配置)⚠️
      - spring-service.xml (注解扫描器+事务管理器)⚠️
    - mybatis-config.xml (mybatis主配置文件：配置别名+驼峰映射+缓存+分页插件+数据厂商标识等)⚠️
    - db.properties (数据源信息)
    - logback.xml (日志文件)



#### 1.3.3.5创建web资源

web资源：

- web-admin/src/main
  - webapp (web目录)
    - WEB-INF
      - pages (必须和视图解析器前缀一致)
        - frame
          - index.html
        - role
          - index.html
        - web.xml (字符编码过滤器+DispatcherServlet+监听服务启动加载resources/spring下的三个spring容器)⚠️



#### 1.3.3.6properties.xml 

数据源配置文件

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/db_hose
jdbc.username=root
jdbc.password=12345678
```



#### 1.3.3.7spring-dao.xml

持久层dao配置文件：数据源+mybatis

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring.xsd">

    <!--引入db.properties-->
    <context:property-placeholder location="classpath:db.properties"/>
    <!--创建数据源：数据库连接池-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <!--进行Spring和MyBatis的整合-->
    <bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!--需要数据源-->
        <property name="dataSource" ref="dataSource"/>
        <!--需要加载mybatis的核心配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <!--需要加载mybatis的所有映射文件-->
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
    </bean>

    <!--扫描所有的dao接口-->
    <mybatis-spring:scan base-package="com.atguigu.dao"/>

</beans>
```



#### 1.3.3.8spring-mvc.xml

控制层mvc配置文件：视图解析器+fastjson处理器+注解扫描器+mvc常规配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--controller包的注解扫描-->
    <context:component-scan base-package="com.atguigu.controller"/>

    <!--配置视图解析器 ：Thymeleaf  SpringBoot之后是不需要自己配置-->
    <bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver" id="viewResolver">
        <!--配置字符集属性-->
        <property name="characterEncoding" value="UTF-8"></property>
        <!--配置模板引擎属性-->
        <property name="templateEngine">
            <!--配置内部bean-->
            <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
                <!--配置模块的解析器属性-->
                <property name="templateResolver">
                    <!--配置内部bean-->
                    <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
                        <!--配置前缀  ★-->
                        <property name="prefix" value="/WEB-INF/pages/"></property>
                        <!--配置后缀  ★-->
                        <property name="suffix" value=".html"></property>
                        <!--配置字符集-->
                        <property name="characterEncoding" value="UTF-8"></property>
                    </bean>
                </property>
            </bean>
        </property>
    </bean>

    <!--mvc的驱动设置，fastjson转换器的添加-->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <!-- 配置Fastjson支持 -->
            <bean class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=UTF-8</value>
                        <value>application/json</value>
                    </list>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>

    <!--静态资源访问-->
    <mvc:default-servlet-handler/>

    <!--配置首页访问-->
    <mvc:view-controller path="index.html" view-name="frame/index"/>

</beans>
```



#### 1.3.3.9spring-service.xml

业务层service配置文件：注解扫描器+事务管理器

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!--设置一个注解的扫描包-->
    <context:component-scan base-package="com.atguigu.service"/>

    <!--事务-->
    <!--配置事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--需要数据源-->
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--开启事务的注解支持-->
    <tx:annotation-driven transaction-manager="transactionManager"/>

</beans>
```



#### 1.3.3.10mybatis-config.xml

mybatis主配置文件：配置别名+驼峰映射+缓存+分页插件+数据厂商标识等

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>
        <!--自动驼峰-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
        <!--延迟加载-->
        <!--缓存-->
        <!--自动映射：默认是开的，一般不关-->
    </settings>
    <!--起别名-->
    <typeAliases>
        <package name="com.atguigu"/>
    </typeAliases>
    <!--分页插件：用到的时候在配置-->

    <!--数据库厂商的标识：看需求-->

    <!--其他的都交给Spring了-->
</configuration>
```



#### 1.3.3.11web.xml 

监听服务启动初始化spring容器，字符编码过滤器+前端控制器

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!-- 解决post乱码 添加字符编码过滤器 -->
    <filter>
        <filter-name>encode</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceRequestEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encode</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!-- 配置SpringMVC框架前端控制器 -->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>

    <!-- 加载spring容器 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <!-- 使用通配符，一次性加载spring目录下的三个spring容器 -->
        <param-value>classpath:spring/spring-*.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
  
</web-app>
```



## 1.4测试SSM环境

### 1.4.1完善dao层

dao/RoleDao接口

```java
package com.atguigu.dao;

import com.atguigu.entity.Role;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RoleDao
 */
public interface RoleDao {
    List<Role> findAll();
}
```

resoueces/mapper/RoleDao.xml映射文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成RoleMapper接口的全类名-->
<mapper namespace="com.atguigu.dao.RoleDao">

    <!-- 用于select查询公用抽取的列 -->
    <sql id="columns">
        select id,role_name,role_code,description,create_time,update_time,is_deleted from acl_role
    </sql>

    <!--查询所有-->
    <select id="findAll" resultType="role">
        <include refid="columns"></include>
        where is_deleted = 0
    </select>
</mapper>
```



### 1.4.2完善service层

RoleService接口

```java
package com.atguigu.service;

import com.atguigu.entity.Role;
import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface RoleService {
    List<Role> findAll();
}
```

RoleServiceImpl类

```java
package com.atguigu.service.impl;

import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
```



### 1.4.3完善controller层

IndexController首页访问控制类

```java
package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.IndexController
 */
@Controller
public class IndexController {

    private final static String PAGE_INDEX = "frame/index";

    @RequestMapping("/")
    public String index() {
        return PAGE_INDEX;
    }

}
```

RoleController用户角色管理控制类

```java
package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private final static String PAGE_INDEX = "role/index";

    @Autowired
    private RoleService roleService;

    @RequestMapping
    public String findAll(Map map){
        //1. 调用业务层处理业务
        List<Role> list = roleService.findAll();
        //2. 将数据共享到请求域
        map.put("list",list);
        //3. 设置逻辑视图
        return PAGE_INDEX;
    }

}
```



### 1.4.4完善web

frame/index.html页面

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <!--base标签我们一直在用，咱们项目阶段先不用base标签-->
    <!--    <base th:href="@{/}">-->
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>首页</h1>
<a th:href="@{/role}">查询所有的角色</a>
</body>
</html>
```

role/index.html页面

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>role的所有数据</h1>
<table border="1" width="800px">
    <tr>
        <th>序号</th>
        <th>名称</th>
        <th>编码</th>
        <th>描述</th>
        <th>创建时间</th>
    </tr>
    <tr th:each="role:${list}">
        <td th:text="${role.id}"></td>
        <td th:text="${role.roleName}"></td>
        <td th:text="${role.roleCode}"></td>
        <td th:text="${role.description}"></td>
        <td th:text="${#dates.format(role.createTime,'yyyy-MM-dd HH:mm:ss')}"></td>
    </tr>
</table>
</body>
</html>
```



### 1.4.5开启jetty服务测试

项目打包：

Maven → shf-parent (root)  → Lifecycle → install

项目运行：

Maven → web_admin → Plugins → jetty → jetty:run

访问：http://localhost:8000/（8000是web_admin.pom文件中设置的jetty服务的端口）





# 2 用户角色管理

## 2.1集成后台前端框架

后台前端框架模板：Hplus

下载地址：https://gitee.com/hplus_admin/hplus

### 2.1.1添加框架静态资源

下载框架，在web_admin模块webapp下创建static目录存放从Hplus模版中拷贝的静态资源：

webapp/static目录下的资源（必须叫static，开发的规范）：

- js目录
- css目录
- img目录
- fonts目录



### 2.1.2完善框架主页面

修改pages/frame/index.html页面，修改内容和路径

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="renderer" content="webkit" />

    <title>用户管理</title>

    <meta name="keywords" content="用户管理后台" />
    <meta name="description" content="用户管理后台" />

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" th:href="@{/static/favicon.ico}" />
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet" />
    <link th:href="@{/static/css/font-awesome.min.css?v=4.4.0}" rel="stylesheet" />
    <link th:href="@{/static/css/animate.css}" rel="stylesheet" />
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet" />
    <link th:href="@{/static/css/jquery.contextMenu.min.css}" rel="stylesheet"/>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden;">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i></div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <span><img alt="image" class="img-circle" th:src="@{/static/img/profile_small.jpg}" /></span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
									<span class="clear">
										<span class="block m-t-xs"><strong class="font-bold">Beaut-zihan</strong></span>
										<span class="text-muted text-xs block">超级管理员<b class="caret"></b></span>
									</span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a class="J_menuItem" href="form_avatar.html">修改头像</a></li>
                            <li><a class="J_menuItem" href="profile.html">个人资料</a></li>
                            <li><a class="J_menuItem" href="contacts.html">联系我们</a></li>
                            <li><a class="J_menuItem" href="mailbox.html">信箱</a></li>
                            <li class="divider"></li>
                            <li><a href="login.html">安全退出</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">H+</div>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-home"></i>
                        <span class="nav-label">权限管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" th:href="@{/role}" href="index_v1.html" data-index="0">用户管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" th:href="@{/role}">角色管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" th:href="@{/role}">菜单管理</a>
                        </li>
                    </ul>
                </li>


            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0;">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary" href="#"><i class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                        <div class="form-group">
                            <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search" />
                        </div>
                    </form>
                </div>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i></button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i></button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown" data-toggle="dropdown">页签操作<span class="caret"></span></button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="tabCloseCurrent"><a>关闭当前</a></li>
                    <li class="J_tabCloseOther"><a>关闭其他</a></li>
                    <li class="J_tabCloseAll"><a>全部关闭</a></li>
                </ul>
            </div>
            <a href="#" class="roll-nav roll-right tabReload"><i class="fa fa-refresh"></i> 刷新</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <!--在当前网页内恰套其他html页面，src就是路径-->
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" th:src="@{/main}"  frameborder="0" data-id="index_v1.html" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">&copy; 2014-2015 <a href="http://www.zi-han.net/" target="_blank">zihan's blog</a></div>
        </div>
    </div>

</div>


<!-- 全局js -->
<script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
<script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>
<script th:src="@{/static/js/plugins/metisMenu/jquery.metisMenu.js}"></script>
<script th:src="@{/static/js/plugins/slimscroll/jquery.slimscroll.min.js}"></script>
<script th:src="@{/static/js/plugins/contextMenu/jquery.contextMenu.min.js}"></script>
<script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>

<!-- 自定义js -->
<script th:src="@{/static/js/hplus.js?v=4.1.0}"></script>
<script type="text/javascript" th:src="@{/static/js/contabs.js}"></script>
</body>
</html>
```



### 2.1.3添加欢迎登录页面

添加pages/frame/main.html页面

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="renderer" content="webkit" />
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
</head>
<body style="position: relative;">
<div style="text-align:center;margin-top: 100px;font-size: 20px;">
    <strong>欢迎登录尚好房平台管理系统</strong>
</div>
</body>
</html>
```



### 2.1.4添加欢迎页面控制器

方式一：修改IndexController（推荐）

```java
package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.IndexController
 */
@Controller
public class IndexController {

    private final static String PAGE_INDEX = "frame/index";
    private final static String PAGE_MAIN = "frame/main";

    @RequestMapping("/")
    public String index() {
        return PAGE_INDEX;
    }

    @RequestMapping("/main")
    public String main() {
        return PAGE_MAIN;
    }

}

```

方式二：在spring-mvc.xml中添加

```xml
<!--配置iframe欢迎页面访问-->
<mvc:view-controller path="main" view-name="frame/main"/>
```



## 2.2角色管理

### 2.2.1弹出层封装

在webapp/static/js目录下新建文件myLayer.js，对弹出层进行二开发，封装五种样式的弹出层：

```js
var opt = {
    alert : function(msg){
        layer.alert(msg);
    },
    //加载中
    load : function () {
        layer.load(1, {
            shade: [0.5,'#fff'] //0.1透明度的白色背景
        });
    },
    //确认框(确认和取消按钮，一般用于删除等危险操作)
    confirm : function(url, msg) {
        var msg = msg ? msg : "确定该操作吗？";
        layer.confirm(msg,function(index){
            opt.load();
            window.location = url;
        });
    },
    //提示框(有正确或错误的图标)
    dialog : function(message, messageType) {
        if(message != '' && message != null) {
            if(messageType == '1') {
                layer.msg(message, {icon: 1});
            } else {
                layer.alert(message, {icon: 2});
            }
        }
    },
    //打开一个窗口(弹出层，url就是弹出层内显示的frame窗体的路径)
    openWin : function(url,title, width,height) {
        var title = title ? title : false;
        layer.open({
            type: 2,
            title: title,
            zIndex:10000,
            anim: -1,
            maxmin: true,
            aini:2,
            shadeClose: false, //点击遮罩关闭层
            area: [width+"px", height+"px"],
            content: url
        });
    },
    //关闭窗口
    closeWin : function(refresh,call) {
        var index = parent.layer.getFrameIndex(window.name);
        if(refresh) {
            parent.location.reload();
        }
        if(call) {
            parent.init();
        }
        parent.layer.close(index); //执行关闭
    }
}
```



### 2.2.2添加角色CRUD页面

角色CRUD的web页面，都在`webapp/WEB-INF/pages/role`目录下

#### 2.2.2.1角色展示页面

角色展示页面：`pages/role/index.html`文件

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
    <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

    <!-- Data Tables -->
    <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

    <link th:href="@{/static/css/animate.css}" rel="stylesheet">
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

    <!-- 全局js -->
    <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
    <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>
    <script th:src="@{/static/js/plugins/jeditable/jquery.jeditable.js}"></script>
    <!-- Data Tables -->
    <script th:src="@{/static/js/plugins/dataTables/jquery.dataTables.js}"></script>
    <script th:src="@{/static/js/plugins/dataTables/dataTables.bootstrap.js}"></script>

    <!-- 弹出层js -->
    <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
    <script th:src="@{/static/js/myLayer.js}"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <!--弹出层按钮-->
                    <button type="button" class="btn btn-sm btn-primary create">新增</button>
                    <table class="table table-striped table-bordered table-hover dataTables-example">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>角色名称</th>
                            <th>角色编码</th>
                            <th>描述</th>
                            <th>创建时间</th>
                            <th>操作 </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="gradeX" th:each="item,it : ${list}">
                            <td class="text-center" th:text="${it.count}">11</td>
                            <td th:text="${item.roleName}">22</td>
                            <td th:text="${item.roleCode}">33</td>
                            <td th:text="${item.description}">33</td>
                            <td th:text="${#dates.format(item.createTime,'yyyy-MM-dd HH:mm:ss')}" >33</td>
                            <td class="text-center">
                                <a class="edit" th:attr="data-id=${item.id}">修改</a>
                                <a class="delete" th:attr="data-id=${item.id}">删除</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!--在使用thymeleaf时，前端页面如要在javascript中获取后端传入的数据，需要使用<script th:inline="javascript">-->
<script th:inline="javascript">
    <!--弹出层事件-->
    $(function(){
        <!--新增-->
        $(".create").on("click",function () {
            opt.openWin("/role/create","新增",580,430);
        });
        <!--修改-->
        $(".edit").on("click",function () {
            var id = $(this).attr("data-id");
            opt.openWin('/role/edit/' + id,'修改',580,430);
        });
        <!--删除-->
        $(".delete").on("click",function(){
            var id = $(this).attr("data-id");
            opt.confirm('/role/delete/'+id);
        });
    });
</script>
</body>
</html>
```



#### 2.2.2.2角色新增页面

角色新增页面：`pages/role/create.html`文件

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>新增</title>

    <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
    <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

    <!-- Data Tables -->
    <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

    <link th:href="@{/static/css/animate.css}" rel="stylesheet">
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

    <!-- 全局js -->
    <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
    <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>

    <!-- 弹出层js -->
    <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
    <script th:src="@{/static/js/myLayer.js}"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/role/save}" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">角色名称：</label>
                    <div class="col-sm-10">
                        <input type="text" name="roleName" id="roleName" value="" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">角色编码：</label>
                    <div class="col-sm-10">
                        <input type="text" name="roleCode" id="roleCode" value="" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">描述：</label>
                    <div class="col-sm-10">
                        <textarea name="description" id="description" class="form-control" style="width:100%;height: 50px;" ></textarea>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group posf">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" value="取消" onclick="opt.closeWin()">取消</button></div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```



#### 2.2.2.3角色修改页面

角色修改页面：`pages/role/edit.html`文件

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>修改</title>

  <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
  <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
  <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

  <!-- Data Tables -->
  <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

  <link th:href="@{/static/css/animate.css}" rel="stylesheet">
  <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

  <!-- 全局js -->
  <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
  <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>

  <!-- 弹出层js -->
  <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
  <script th:src="@{/static/js/myLayer.js}"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
  <div class="ibox float-e-margins">
    <div class="ibox-content" style="width: 98%;">
      <form id="ec" th:action="@{/role/update}" method="post" class="form-horizontal" >
        <input type="hidden" name="id" th:value="${role.id}">
        <div class="form-group">
          <label class="col-sm-2 control-label">角色：</label>

          <div class="col-sm-10">
            <input type="text" name="roleName" id="roleName" th:value="${role.roleName}" class="form-control"/>
          </div>
        </div>
        <div class="hr-line-dashed"></div>
        <div class="form-group">
          <label class="col-sm-2 control-label">角色编码：</label>
          <div class="col-sm-10">
            <input type="text" name="roleCode" id="roleCode" th:value="${role.roleCode}" class="form-control"/>
          </div>
        </div>
        <div class="hr-line-dashed"></div>
        <div class="form-group">
          <label class="col-sm-2 control-label">描述：</label>
          <div class="col-sm-10">
            <textarea name="description" id="description" class="form-control" style="width:100%;height: 50px;" th:text="${role.description}" ></textarea>
          </div>
        </div>
        <div class="hr-line-dashed"></div>
        <div class="form-group posf">
          <div class="col-sm-4 col-sm-offset-2 text-right">
            <button class="btn btn-primary" type="submit">确定</button>
            <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button></div>
        </div>
      </form>
    </div>
  </div>
</div>
</div>
</body>
</html>
```



#### 2.2.2.4创建修改成功页面

在WEB-INF/pages/common目录下创建success.html文件

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>成功提示页</title>

    <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
    <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

    <!-- Data Tables -->
    <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

    <link th:href="@{/static/css/animate.css}" rel="stylesheet">
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

    <!-- 全局js -->
    <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
    <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>

    <!-- 弹出层js -->
    <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
    <script th:src="@{/static/js/myLayer.js}"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content">
            <div class="form-group">
                <div class="col-sm-10">操作成功</div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group posf">
                <div class="col-sm-4 col-sm-offset-2">
                    <!--传递true，让父级刷新-->
                    <button class="btn btn-primary" type="button" onclick="opt.closeWin(true);">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
```





### 2.2.3角色CRUD持久层

dao层接口文件：`dao/RoleDao`

```java
package com.atguigu.dao;

import com.atguigu.entity.Role;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RoleDao
 */
public interface RoleDao {

    /**
     * @Description: 查询所有
     */
    List<Role> findAll();

    /**
     * @Description: 插入一条数据
     */
    Integer insert(Role role);

    /**
     * @Description: 通过id获取
     */
    Role getById(Long id);

    /**
     * @Description: 修改数据
     */
    Integer update(Role role);

    /**
     * @Description: 删除数据
     */
    void delete(Long id);

}
```

dao层映射文件：`resources/mapper/RoleDao.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.RoleDao">

    <!-- 用于select查询公用抽取的列 -->
    <sql id="columns">
        select id,role_name,role_code,description,create_time,update_time,is_deleted from acl_role
    </sql>

    <!--查询所有-->
    <select id="findAll" resultType="role">
        <include refid="columns"></include>
        where is_deleted = 0
    </select>

    <!--查询单个-->
    <select id="getById" resultType="role">
        <include refid="columns"/>
        where
        id = #{id}
    </select>

    <!--新增-->
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        insert into acl_role (
        id ,
        role_name ,
        role_code ,
        description
        ) values (
        #{id} ,
        #{roleName} ,
        #{roleCode} ,
        #{description}
        )
    </insert>

    <!--修改方式一：可赋值为null
    <update id="update">
        update acl_role set
        role_name=#{roleName},role_code=#{roleCode},description=#{description}
        where id=#{id}
    </update>
    -->

    <!--修改方式二：使用set标签，赋为null或空串时不修改原来的数据-->
    <update id="update">
        update acl_role
        <set>
            <if test="roleName!=null and roleName!=''">
                role_name=#{roleName},
            </if>
            <if test="roleCode!=null and roleCode!=''">
                role_code=#{roleCode},
            </if>
            <if test="description!=null and description!=''">
                description=#{description},
            </if>
        </set>
        where id=#{id}
    </update>


    <!--逻辑删除-->
    <update id="delete">
        update acl_role set
        is_deleted = 1
        where
        id = #{id}
    </update>

</mapper>
```



### 2.2.4角色CRUD业务层

service层接口文件：`service/RoleService`

```java
package com.atguigu.service;

import com.atguigu.entity.Role;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface RoleService {

    /**
     * @Description: 角色列表
     */
    List<Role> findAll();

    /**
     * @Description: 角色新增
     */
    Integer insert(Role role);

    /**
     * @Description: 修改回显
     */
    Role getById(Long id);

    /**
     * @Description: 修改操作
     */
    Integer update(Role role);

    /**
     * @Description: 删除操作
     */
    void delete(Long id);
}
```

service层接口实现类文件：`service/impl/RoleServiceImpl`

```java
package com.atguigu.service.impl;

import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * @Description: 展示所有角色
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /**
     * @Description: 新增角色
     */
    @Override
    public Integer insert(Role role) {
        return roleDao.insert(role);
    }

    /**
     * @Description: 修改角色回显数据
     */
    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    /**
     * @Description: 角色更新操作
     */
    @Override
    public Integer update(Role role) {
        return roleDao.update(role);
    }

    /**
     * @Description: 角色删除操作
     */
    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }
}
```



### 2.2.5角色CRUD控制层

controller层类文件：`controller/RoleController`

```java
package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/role";

    @Autowired
    private RoleService roleService;

    /**
     * @Description: 处理/role请求，查询所有角色
     */
    @RequestMapping
    public String findAll(Map map) {
        List<Role> list = roleService.findAll();
        map.put("list", list);
        return PAGE_INDEX;
    }

    /**
     * @Description: 处理/create请求，跳转到添加角色页面
     */
    @RequestMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * @Description: 处理/save请求，执行添加角色操作
     */
    @RequestMapping("/save")
    public String save(Role role) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/edit/id请求，跳转到修改角色页面
     */
    @RequestMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Map map
    ) {
        Role role = roleService.getById(id);
        map.put("role",role);
        return PAGE_EDIT;
    }

    /**
     * @Description: 处理/update请求，执行角色修改操作
     */
    @RequestMapping(value="/update")
    public String update(Role role) {
        roleService.update(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/delete/id请求，执行角色删除操作
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        //不是在iframe窗体内执行操作，直接重定向即可
        return LIST_ACTION;
    }

}
```



### 2.2.6角色模糊查询实现

第一步：修改`pages/role/index.html`文件，添加查询的表单和查询条件输入框及查询按钮。

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
    <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

    <!-- Data Tables -->
    <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

    <link th:href="@{/static/css/animate.css}" rel="stylesheet">
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

    <!-- 全局js -->
    <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
    <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>
    <script th:src="@{/static/js/plugins/jeditable/jquery.jeditable.js}"></script>
    <!-- Data Tables -->
    <script th:src="@{/static/js/plugins/dataTables/jquery.dataTables.js}"></script>
    <script th:src="@{/static/js/plugins/dataTables/dataTables.bootstrap.js}"></script>

    <!-- 弹出层js -->
    <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
    <script th:src="@{/static/js/myLayer.js}"></script>
</head>
<body class="gray-bg">
<!--查询角色的表单-->
<form id="ec" th:action="@{/role}" method="post">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">

                        <!--高级搜索内容开始-->
                        <table class="table form-table margin-bottom10">
                            <tr>
                                <td>
                                    <!--搜索内容回显-->
                                    <input type="text" name="roleName"
                                           th:value="${#maps.containsKey(filters, 'roleName')} ? ${filters.roleName} : ''"
                                           placeholder="角色名称" class="input-sm form-control"/>
                                </td>
                            </tr>
                        </table>
                        <div>
                            <!--点击搜索按钮提交当前表单-->
                            <button type="button" class="btn btn-sm btn-primary" onclick="document.forms.ec.submit();">
                                搜索
                            </button>
                            <button type="button" class="btn btn-sm btn-primary create">新增</button>
                            <button type="button" id="loading-example-btn"
                                    onclick="javascript:window.location.reload();" class="btn btn-white btn-sm">刷新
                            </button>
                        </div>
                        <!--高级搜索内容结束-->

                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>角色名称</th>
                                <th>角色编码</th>
                                <th>描述</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="gradeX" th:each="item,it : ${list}">
                                <td class="text-center" th:text="${it.count}">11</td>
                                <td th:text="${item.roleName}">22</td>
                                <td th:text="${item.roleCode}">33</td>
                                <td th:text="${item.description}">33</td>
                                <td th:text="${#dates.format(item.createTime,'yyyy-MM-dd HH:mm:ss')}">33</td>
                                <td class="text-center">
                                    <a class="edit" th:attr="data-id=${item.id}">修改</a>
                                    <a class="delete" th:attr="data-id=${item.id}">删除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!--在使用thymeleaf时，前端页面如要在javascript中获取后端传入的数据，需要使用<script th:inline="javascript">-->
<script th:inline="javascript">
<script th:inline="javascript">
    //弹出层事件
    $(function () {
        //新增
        $(".create").on("click", function () {
            opt.openWin("/role/create", "新增", 580, 430);
        });
        //修改
        $(".edit").on("click", function () {
            var id = $(this).attr("data-id");
            opt.openWin('/role/edit/' + id, '修改', 580, 430);
        });
        //删除
        $(".delete").on("click", function () {
            var id = $(this).attr("data-id");
            opt.confirm('/role/delete/' + id);
        });
    });
</script>
</script>
</body>
</html>
```

第二步：修改持久层文件，findRole方法替换掉findAll方法

dao层接口文件

```java
    /**
     * @Description: 查询所有
     */
//    List<Role> findAll();

    /**
     * @Description: index页搜索条件，可替代查询所有
     */
    List<Role> findRole(Map<String,Object> filters);
```

dao层映射文件

```xml
<!--查询所有-->
<!--    <select id="findAll" resultType="role">-->
<!--        <include refid="columns"></include>-->
<!--        from acl_role-->
<!--        where is_deleted = 0-->
<!--    </select>-->

<!--按条件模糊搜索-->
<select id="findRole" resultType="role">
  <include refid="columns"></include>
  <where>
    <!--判断有无搜索条件-->
    <if test="roleName != null and roleName != ''">
      role_name like concat('%',#{roleName},'%')
      <!--精准搜索：and role_name = #{roleName}-->
    </if>
    and is_deleted = 0
  </where>
</select>
```

第三步：修改业务层，findRole方法替换掉findAll方法

service层接口

```java
    /**
     * @Description: 角色列表
     */
//		List<Role> findAll();

    /**
     * @Description: index页搜索条件，可替代查询所有
     */
    List<Role> findRole(Map<String,Object> filters);
```

service层接口实现类

```java
    /**
     * @Description: 展示所有角色
     */
//    @Override
//    public List<Role> findAll() {
//        return roleDao.findAll();
//    }

    /**
     * @Description: 条件搜索，可代替查询所有角色
     */
    @Override
    public List<Role> findRole(Map<String, Object> filters) {
        return  roleDao.findRole(filters);
    }
```

第四步：修改控制层

controller层：

```java
	  /**
     * @Description: 处理/role请求，查询所有角色
     */
//    @RequestMapping
//    public String findAll(Map map) {
//        List<Role> list = roleService.findAll();
//        map.put("list", list);
//        return PAGE_INDEX;
//    }

    /**
     * @Description: 处理/role请求，根据条件搜索，代替findAll
     */
    @RequestMapping
    public String findRole(
            Map map,
            HttpServletRequest request
    ) {
        Map<String, Object> filters =  getFilters(request);
        List<Role> list = roleService.findRole(filters);
        map.put("list", list);
        return PAGE_INDEX;
    }

		/**
     * @Description: 将请求中的搜索条件分装成map
     */
    private Map<String, Object> getFilters(HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, Object> filters = new TreeMap();
        while(paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            if (values != null && values.length != 0) {
                if (values.length > 1) {
                    filters.put(paramName, values);
                } else {
                    //若只有一个value，只向Map集合中放value本身
                    filters.put(paramName, values[0]);
                }
            }
        }
        return filters;
    }
```



## 2.3分页处理实现

### 2.3.1分页之dao层

首先在mybatis-config.xml添加分页插件PageHelper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <plugins>
        <!-- com.github.pagehelper 为 PageHelper 类所在包名 -->
        <plugin interceptor="com.github.pagehelper.PageHelper">
            <!-- 设置数据库类型 Oracle,Mysql,MariaDB,SQLite,Hsqldb,PostgreSQL 六种数据库-->
            <property name="dialect" value="mysql"/>
        </plugin>
    </plugins>
</configuration>
```



#### 2.3.1.1dao层接口文件

```java
package com.atguigu.dao;

import com.atguigu.entity.Role;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RoleDao
 */
public interface RoleDao {

    /**
     * @Description: 搜索结果分页显示
     */
    List<Role> findPage(Map<String, Object> filters);

    /**
     * @Description: 插入一条数据
     */
    Integer insert(Role role);

    /**
     * @Description: 通过id获取
     */
    Role getById(Long id);

    /**
     * @Description: 修改数据
     */
    Integer update(Role role);

    /**
     * @Description: 删除数据
     */
    void delete(Long id);

}
```



#### 2.3.1.2dao层映射文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.RoleDao">

    <!-- 用于select查询公用抽取的列 -->
    <sql id="columns">
        select id,role_name,role_code,description,create_time,update_time,is_deleted from acl_role
    </sql>

    <!--搜索结果分页显示-->
    <select id="findPage" resultType="role">
        <include refid="columns"></include>
        <where>
            <!--判断有无搜索条件-->
            <if test="roleName != null and roleName != ''">
                role_name like concat('%',#{roleName},'%')
                <!--精准搜索：and role_name = #{roleName}-->
            </if>
            and is_deleted = 0
            order by id desc
        </where>
    </select>

    <!--查询单个-->
    <select id="getById" resultType="role">
        <include refid="columns"/>
        where
        id = #{id}
    </select>

    <!--新增-->
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        insert into acl_role (
        id ,
        role_name ,
        role_code ,
        description
        ) values (
        #{id} ,
        #{roleName} ,
        #{roleCode} ,
        #{description}
        )
    </insert>

    <!--修改方式一：可赋值为null
    <update id="update">
        update acl_role set
        role_name=#{roleName},role_code=#{roleCode},description=#{description}
        where id=#{id}
    </update>
    -->

    <!--修改方式二：使用set标签，赋为null或空串时不修改原来的数据-->
    <update id="update">
        update acl_role
        <set>
            <if test="roleName!=null and roleName!=''">
                role_name=#{roleName},
            </if>
            <if test="roleCode!=null and roleCode!=''">
                role_code=#{roleCode},
            </if>
            <if test="description!=null and description!=''">
                description=#{description},
            </if>
        </set>
        where id=#{id}
    </update>


    <!--删除-->
    <update id="delete">
        update acl_role set
        is_deleted = 1
        where
        id = #{id}
    </update>

</mapper>
```



### 2.3.2分页之service层

#### 2.3.2.1service层接口

```java
package com.atguigu.service;

import com.atguigu.entity.Role;
import com.github.pagehelper.PageInfo;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface RoleService {

    /**
     * @Description: 搜索结果分页显示
     */
    PageInfo<Role> findPage(Map<String, Object> filters);


    /**
     * @Description: 角色新增
     */
    Integer insert(Role role);

    /**
     * @Description: 修改回显
     */
    Role getById(Long id);

    /**
     * @Description: 修改操作
     */
    Integer update(Role role);

    /**
     * @Description: 删除操作
     */
    void delete(Long id);
}
```



#### 2.3.2.2service层接口实现类

```java
package com.atguigu.service.impl;

import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.atguigu.util.CastUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * @Description: 搜索结果分页显示
     */
    @Override
    public PageInfo<Role> findPage(Map<String, Object> filters) {
        //当前页数，common-util引入工具类：CastUtil
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 3);

        //分页插件
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleDao.findPage(filters);
        //构造方法中传入list集合和每页显示条数
        //通过pageInfo.getList()可获取list集合的数据，将pageInfo对象放到请求域就可以共享到前端
        PageInfo pageInfo = new PageInfo<Role>(list, 3);
        return pageInfo;
    }

    /**
     * @Description: 新增角色
     */
    @Override
    public Integer insert(Role role) {
        return roleDao.insert(role);
    }

    /**
     * @Description: 修改角色回显数据
     */
    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    /**
     * @Description: 角色更新操作
     */
    @Override
    public Integer update(Role role) {
        return roleDao.update(role);
    }

    /**
     * @Description: 角色删除操作
     */
    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }
}
```



### 2.3.3分页之controller层

```java
package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/role";

    @Autowired
    private RoleService roleService;

    /**
     * @Description: 处理/role请求，搜索处理、分页处理
     */
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        //处理请求参数
        Map<String,Object> filters = getFilters(request);
        //传递参数到service层，拿到查询结果并构建分页对象
        PageInfo<Role> page = roleService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索内容的回显
        map.put("filters", filters);

        return PAGE_INDEX;
    }

    /**
     * @Description: 处理/create请求，跳转到添加角色页面
     */
    @RequestMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * @Description: 处理/save请求，执行添加角色操作
     */
    @RequestMapping("/save")
    public String save(Role role) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/edit/id请求，跳转到修改角色页面
     */
    @RequestMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Map map
    ) {
        Role role = roleService.getById(id);
        map.put("role",role);
        return PAGE_EDIT;
    }

    /**
     * @Description: 处理/update请求，执行角色修改操作
     */
    @RequestMapping(value="/update")
    public String update(Role role) {
        roleService.update(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/delete/id请求，执行角色删除操作
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        //不是在iframe窗体内执行操作，直接重定向即可
        return LIST_ACTION;
    }

    /**
     * 封装页面提交的分页参数及搜索条件
     */
    private Map<String, Object> getFilters(HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, Object> filters = new TreeMap();
        while(paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            if (values != null && values.length != 0) {
                if (values.length > 1) {
                    filters.put(paramName, values);
                } else {
                    //若只有一个value，只向Map集合中放value本身
                    filters.put(paramName, values[0]);
                }
            }
        }
      
      	//设置默认页数和分页显示数据的数量
        if(!filters.containsKey("pageNum")) {
            filters.put("pageNum", 1);
        }
        if(!filters.containsKey("pageSize")) {
            filters.put("pageSize", 3);
        }

        return filters;
    }


}
```



### 2.3.4分页之前端HTML页面

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
    <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

    <!-- Data Tables -->
    <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

    <link th:href="@{/static/css/animate.css}" rel="stylesheet">
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

    <!-- 全局js -->
    <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
    <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>
    <script th:src="@{/static/js/plugins/jeditable/jquery.jeditable.js}"></script>
    <!-- Data Tables -->
    <script th:src="@{/static/js/plugins/dataTables/jquery.dataTables.js}"></script>
    <script th:src="@{/static/js/plugins/dataTables/dataTables.bootstrap.js}"></script>

    <!-- 弹出层js -->
    <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
    <script th:src="@{/static/js/myLayer.js}"></script>
</head>
<body class="gray-bg">
<!--查询角色的表单-->
<form id="ec" th:action="@{/role}" method="post">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">

                        <!--搜索内容开始-->
                        <table class="table form-table margin-bottom10">
                            <tr>
                                <td>
                                    <!--搜索内容回显-->
                                    <input type="text" name="roleName"
                                           th:value="${#maps.containsKey(filters, 'roleName')} ? ${filters.roleName} : ''"
                                           placeholder="角色名称" class="input-sm form-control"/>
                                </td>
                            </tr>
                        </table>
                        <div>
                            <!--点击搜索按钮提交表单，并为表单项的页码参数pageNum赋值1-->
                            <button type="button" class="btn btn-sm btn-primary"
                                    onclick="javascript:document.forms.ec.pageNum.value=1;document.forms.ec.submit();">
                                搜索
                            </button>
                            <button type="button" class="btn btn-sm btn-primary create">新增</button>
                            <button type="button" id="loading-example-btn"
                                    onclick="javascript:window.location.reload();" class="btn btn-white btn-sm">刷新
                            </button>
                        </div>
                        <!--搜索内容结束-->

                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>角色名称</th>
                                <th>角色编码</th>
                                <th>描述</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="gradeX" th:each="item,it : ${page.list}">
                                <td class="text-center" th:text="${it.count}">11</td>
                                <td th:text="${item.roleName}">22</td>
                                <td th:text="${item.roleCode}">33</td>
                                <td th:text="${item.description}">33</td>
                                <td th:text="${#dates.format(item.createTime,'yyyy-MM-dd HH:mm:ss')}">33</td>
                                <td class="text-center">
                                    <a class="edit" th:attr="data-id=${item.id}">修改</a>
                                    <a class="delete" th:attr="data-id=${item.id}">删除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <!--分页插件页码开始-->
                        <!--将当前页码添加到表单项中，随着点击页码按钮而作为请求参数提交-->
                        <input type="hidden" name="pageNum" id="pageNum" th:value="${page.pageNum}"/>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="dataTables_info" id="DataTables_Table_0_info" role="alert"
                                     aria-live="polite" aria-relevant="all">显示
                                    <span th:text="${page.startRow}"></span> 到 <span th:text="${page.endRow}"></span> 项，
                                    共 <span th:text="${page.total}"></span> 项
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
                                    <ul class="pagination">

                                        <!--页码开始-->
                                        <!--首页，两种样式，根据是否为首页进行切换⚠️-->
                                        <li th:if="${page.isFirstPage}" class="paginate_button previous disabled" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页是首页，则无法点击首页跳转-->
                                            <a href="javascript:;">首页</a>
                                        </li>
                                        <li th:if="${!page.isFirstPage}" class="paginate_button previous" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页不是首页，修改当前页pageNum的value值为1，点击即可提交表单跳转到首页-->
                                            <a th:href="'javascript:document.forms.ec.pageNum.value=1;document.forms.ec.submit();'">首页</a>
                                        </li>

                                        <!--上一页，两种样式，根据是否为首页进行切换⚠️-->
                                        <li th:if="${page.isFirstPage}" class="paginate_button previous disabled" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页是首页，则无法点击上一页跳转-->
                                            <a href="javascript:;">上一页</a>
                                        </li>
                                        <li th:if="${!page.isFirstPage}" class="paginate_button previous" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页不是首页，修改当前页pageNum的value值为上一页prePage属性的值，点击即可提交表单跳转到首页-->
                                            <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.prePage}+';document.forms.ec.submit();'">上一页</a>
                                        </li>

                                        <!--页码，循环所有导航页号navigatepageNums，并判断是否为当前页，设置active样式-->
                                        <li th:class="${i==page.pageNum?'paginate_button active':'paginate_button'}" aria-controls="DataTables_Table_0" tabindex="0" th:each="i:${page.navigatepageNums}">
                                            <!--为每个导航页码都赋值上跳转到自己页面的超链接-->
                                            <a th:href="'javascript:document.forms.ec.pageNum.value='+${i}+';document.forms.ec.submit();'" th:text="${i}"></a>
                                        </li>

                                        <!--下一页，两种样式，根据是否为尾页进行切换⚠️-->
                                        <li th:if="${page.isLastPage}" class="paginate_button next disabled" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页是尾页，则无法点击下一页跳转-->
                                            <a href="javascript:;">下一页</a>
                                        </li>
                                        <li th:if="${!page.isLastPage}" class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页不是尾页，修改当前页pageNum的value值为下一页nextPage属性的值，点击即可提交表单跳转到首页-->
                                            <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.nextPage}+';document.forms.ec.submit();'">下一页</a>
                                        </li>

                                        <!--尾页，两种样式，根据是否为尾页进行切换⚠️-->
                                        <li th:if="${page.isLastPage}" class="paginate_button next disabled" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页是尾页，则无法点击尾页跳转-->
                                            <a href="javascript:;">尾页</a>
                                        </li>
                                        <li th:if="${!page.isLastPage}" class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页不是尾页，修改当前页pageNum的value值为总页数pages属性的值，点击即可提交表单跳转到尾页-->
                                            <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.pages}+';document.forms.ec.submit();'">尾页</a>
                                        </li>
                                        <!--页码结束-->

                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!--分页插件页码结束-->

                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!--在使用thymeleaf时，前端页面如要在javascript中获取后端传入的数据，需要使用<script th:inline="javascript">-->
<script th:inline="javascript">
    //弹出层事件
    $(function () {
        //新增
        $(".create").on("click", function () {
            opt.openWin("/role/create", "新增", 580, 430);
        });
        //修改
        $(".edit").on("click", function () {
            var id = $(this).attr("data-id");
            opt.openWin('/role/edit/' + id, '修改', 580, 430);
        });
        //删除
        $(".delete").on("click", function () {
            var id = $(this).attr("data-id");
            opt.confirm('/role/delete/' + id);
        });
    });

</script>
</body>
</html>
```



## 2.4管理代码封装

### 2.4.1封装dao层

在common_util模块下进行封装

#### 2.4.1.1封装BaseDao

```java
package com.atguigu.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.BaseDao
 */
public interface BaseDao<T> {

    /**
     * 获取index页面的列表实体数据，以及查询结果的分页展示
     */
    List<T> findPage(Map<String,Object> filters);


    /**
     * 插入一个实体
     */
    void insert(T t);


    /**
     * 根据ID删除实体，参数ID可以是字符串型也可以是整型
     */
    void delete(Serializable id);


    /**
     * 更新一个实体
     */
    void update(T t);


    /**
     * 根据ID获取实体，参数ID可以是字符串型也可以是整型
     */
    T getById(Serializable id);

}
```



#### 2.4.1.2改造RoleDao

```java
package com.atguigu.dao;

import com.atguigu.entity.Role;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RoleDao
 */
public interface RoleDao extends BaseDao<Role> {

}
```



### 2.4.2封装service层

在common_util模块下进行封装

#### 2.4.2.1封装BaseService

```java
package com.atguigu.service;

import com.github.pagehelper.PageInfo;
import java.io.Serializable;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.BaseService
 */
public interface BaseService<T> {

    /**
     * 获取实体数据，包装到PageInfo分页对象中返回
     */
    PageInfo<T> findPage(Map<String,Object> filters);


    /**
     * 插入一个实体
     */
    void insert(T t);


    /**
     * 根据ID删除实体，参数ID可以是字符串型也可以是整型
     */
    void delete(Serializable id);


    /**
     * 更新一个实体
     */
    void update(T t);


    /**
     * 根据ID获取实体，参数ID可以是字符串型也可以是整型
     */
    T getById(Serializable id);

}
```



#### 2.4.2.2改造RoleService

```java
package com.atguigu.service;

import com.atguigu.entity.Role;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface RoleService extends BaseService<Role> {

}
```



#### 2.4.2.3封装BaseServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.service.BaseService;
import com.atguigu.util.CastUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * TODD
 * @AllClassName: com.atguigu.service.impl.BaseServiceImpl
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {


    //模版方法设计模式，通过该抽象方法获取实际的dao层对象
    //service层实现类重写该方法返回dao层对象，就可以用该方法返回值调用其他方法⚠️
    public abstract BaseDao<T> getEntityDao();

    /**
     * 搜索结果分页显示
     */
    @Override
    public PageInfo<T> findPage(Map<String, Object> filters) {
        //当前页数，common-util引入工具类：CastUtil
        //先获取请求参数处理方法getFilters中设置的初始化值，未设置时使用默认值⚠️
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 3);

        //分页插件，必须设置在查询之前⚠️
        PageHelper.startPage(pageNum, pageSize);
        //使用方法返回的dao层对象调用
        List<T> list = getEntityDao().findPage(filters);
        //构造方法中传入list集合和每页显示条数
        //通过pageInfo.getList()可获取list集合的数据，将pageInfo对象放到请求域就可以共享到前端
        return new PageInfo<T>(list, 3);
    }


    /**
     * 新增一个实体
     */
    @Override
    public void insert(T t) {
        getEntityDao().insert(t);
    }


    /**
     * 根据ID获取实体，参数ID可以是字符串型也可以是整型
     */
    @Override
    public T getById(Serializable id) {
        return getEntityDao().getById(id);
    }


    /**
     * 更新一个实体
     */
    @Override
    public void update(T t) {
        getEntityDao().update(t);
    }


    /**
     * 根据ID删除实体，参数ID可以是字符串型也可以是整型
     */
    @Override
    public void delete(Serializable id) {
        getEntityDao().delete(id);
    }

}
```



#### 2.4.2.4改造RoleServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public BaseDao<Role> getEntityDao() {
        return roleDao;
    }

}
```



### 2.4.3封装Controller层

在common_util模块下进行封装

#### 2.4.3.1封装BaseController

```java
package com.atguigu.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.BaseController
 */
public class BaseController {

    /**
     * 封装页面提交的分页参数及搜索条件
     * @param request
     * @return
     */
    protected Map<String, Object> getFilters(HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, Object> filters = new TreeMap();
        while(paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            if (values != null && values.length != 0) {
                if (values.length > 1) {
                    filters.put(paramName, values);
                } else {
                    //若只有一个value，只向Map集合中放value本身
                    filters.put(paramName, values[0]);
                }
            }
        }
        //判断请求参数内没有pageNum和pageSize的话，初始值设置为1和3
        if(!filters.containsKey("pageNum")) {
            filters.put("pageNum", 1);
        }
        if(!filters.containsKey("pageSize")) {
            filters.put("pageSize", 3);
        }

        return filters;
    }
}
```



#### 2.4.3.2改造RoleController

```java
package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/role";

    @Autowired
    private RoleService roleService;

    /**
     * @Description: 处理/role请求，搜索处理、分页处理
     */
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        //处理请求参数
        Map<String,Object> filters = getFilters(request);
        //传递参数到service层，拿到查询结果并构建分页对象
        PageInfo<Role> page = roleService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索内容的回显
        map.put("filters", filters);

        return PAGE_INDEX;
    }

    /**
     * @Description: 处理/create请求，跳转到添加角色页面
     */
    @RequestMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * @Description: 处理/save请求，执行添加角色操作
     */
    @RequestMapping("/save")
    public String save(Role role) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/edit/id请求，跳转到修改角色页面
     */
    @RequestMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Map map
    ) {
        Role role = roleService.getById(id);
        map.put("role",role);
        return PAGE_EDIT;
    }

    /**
     * @Description: 处理/update请求，执行角色修改操作
     */
    @RequestMapping(value="/update")
    public String update(Role role) {
        roleService.update(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/delete/id请求，执行角色删除操作
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        //不是在iframe窗体内执行操作，直接重定向即可
        return LIST_ACTION;
    }

}
```



### 2.4.4封装Thymeleaf模板

在wb_admin模块下进行封装

#### 2.4.4.1封装头部资源引用

在common下新建：head.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>

<div th:fragment="head">
    <title>权限管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge,chrome=1"/>
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="shortcut icon" th:href="@{favicon.ico}"/>
    <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
    <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

    <!-- Data Tables -->
    <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

    <link th:href="@{/static/css/animate.css}" rel="stylesheet">
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

    <!-- 全局js -->
    <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
    <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>
    <script th:src="@{/static/js/plugins/jeditable/jquery.jeditable.js}"></script>
    <!-- Data Tables -->
    <script th:src="@{/static/js/plugins/dataTables/jquery.dataTables.js}"></script>
    <script th:src="@{/static/js/plugins/dataTables/dataTables.bootstrap.js}"></script>

    <!-- 弹出层js -->
    <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
    <script th:src="@{/static/js/myLayer.js}"></script>
</div>
</body>
</html>
```



#### 2.4.4.2引用头部封装模版

增改查页面head部分全部替换：

```html
<head th:include="common/head :: head"></head>
```



#### 2.4.4.3封装分页插件样式

在common下新建：pagination.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Title</title>
</head>
<body>

<div class="row" th:fragment="pagination">
    <!--将每页的数量作为请求参数提交，可修改每页最多显示的条数-->
    <input type="hidden" name="pageSize" id="pageSize" th:value="${page.pageSize}"/>
    <!--将当前页码作为请求参数提交，切换不同页码的页面-->
    <input type="hidden" name="pageNum" id="pageNum" th:value="${page.pageNum}"/>
    <div class="col-sm-6">
        <div class="dataTables_info" id="DataTables_Table_0_info" role="alert"
             aria-live="polite" aria-relevant="all">显示
            <span th:text="${page.startRow}"></span> 到 <span th:text="${page.endRow}"></span> 项，
            共 <span th:text="${page.total}"></span> 项
        </div>
    </div>
    <div class="col-sm-6">
        <div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
            <ul class="pagination">

                <!--页码开始-->
                <!--首页，两种样式，根据是否为首页进行切换⚠️-->
                <li th:if="${page.isFirstPage}" class="paginate_button previous disabled" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页是首页，则无法点击首页跳转-->
                    <a href="javascript:;">首页</a>
                </li>
                <li th:if="${!page.isFirstPage}" class="paginate_button previous" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页不是首页，修改当前页pageNum的value值为1，点击即可提交表单跳转到首页-->
                    <a th:href="'javascript:document.forms.ec.pageNum.value=1;document.forms.ec.submit();'">首页</a>
                </li>

                <!--上一页，两种样式，根据是否为首页进行切换⚠️-->
                <li th:if="${page.isFirstPage}" class="paginate_button previous disabled" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页是首页，则无法点击上一页跳转-->
                    <a href="javascript:;">上一页</a>
                </li>
                <li th:if="${!page.isFirstPage}" class="paginate_button previous" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页不是首页，修改当前页pageNum的value值为上一页prePage属性的值，点击即可提交表单跳转到首页-->
                    <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.prePage}+';document.forms.ec.submit();'">上一页</a>
                </li>

                <!--页码，循环所有导航页号navigatepageNums，并判断是否为当前页，设置active样式-->
                <li th:class="${i==page.pageNum?'paginate_button active':'paginate_button'}" aria-controls="DataTables_Table_0" tabindex="0" th:each="i:${page.navigatepageNums}">
                    <!--为每个导航页码都赋值上跳转到自己页面的超链接-->
                    <a th:href="'javascript:document.forms.ec.pageNum.value='+${i}+';document.forms.ec.submit();'" th:text="${i}"></a>
                </li>

                <!--下一页，两种样式，根据是否为尾页进行切换⚠️-->
                <li th:if="${page.isLastPage}" class="paginate_button next disabled" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页是尾页，则无法点击下一页跳转-->
                    <a href="javascript:;">下一页</a>
                </li>
                <li th:if="${!page.isLastPage}" class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页不是尾页，修改当前页pageNum的value值为下一页nextPage属性的值，点击即可提交表单跳转到首页-->
                    <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.nextPage}+';document.forms.ec.submit();'">下一页</a>
                </li>

                <!--尾页，两种样式，根据是否为尾页进行切换⚠️-->
                <li th:if="${page.isLastPage}" class="paginate_button next disabled" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页是尾页，则无法点击尾页跳转-->
                    <a href="javascript:;">尾页</a>
                </li>
                <li th:if="${!page.isLastPage}" class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页不是尾页，修改当前页pageNum的value值为总页数pages属性的值，点击即可提交表单跳转到尾页-->
                    <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.pages}+';document.forms.ec.submit();'">尾页</a>
                </li>
                <!--页码结束-->

            </ul>
        </div>
    </div>
</div>
</body>
</html>
```



#### 2.4.4.4引用分页封装模版

```html
<div class="row" th:include="common/pagination :: pagination"></div>
```



#### 2.4.4.5完整封装页面展示

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<body class="gray-bg">
<!--查询角色的表单-->
<form id="ec" th:action="@{/role}" method="post">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">

                        <!--搜索内容开始-->
                        <table class="table form-table margin-bottom10">
                            <tr>
                                <td>
                                    <!--搜索内容回显-->
                                    <input type="text" name="roleName"
                                           th:value="${#maps.containsKey(filters, 'roleName')} ? ${filters.roleName} : ''"
                                           placeholder="角色名称" class="input-sm form-control"/>
                                </td>
                            </tr>
                        </table>
                        <div>
                            <!--点击搜索按钮提交表单，并为表单项的页码参数pageNum赋值1-->
                            <button type="button" class="btn btn-sm btn-primary"
                                    onclick="javascript:document.forms.ec.pageNum.value=1;document.forms.ec.submit();">
                                搜索
                            </button>
                            <button type="button" class="btn btn-sm btn-primary create">新增</button>
                            <button type="button" id="loading-example-btn"
                                    onclick="javascript:window.location.reload();" class="btn btn-white btn-sm">刷新
                            </button>
                        </div>
                        <!--搜索内容结束-->

                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>角色名称</th>
                                <th>角色编码</th>
                                <th>描述</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="gradeX" th:each="item,it : ${page.list}">
                                <td class="text-center" th:text="${it.count}">11</td>
                                <td th:text="${item.roleName}">22</td>
                                <td th:text="${item.roleCode}">33</td>
                                <td th:text="${item.description}">33</td>
                                <td th:text="${#dates.format(item.createTime,'yyyy-MM-dd HH:mm:ss')}">33</td>
                                <td class="text-center">
                                    <a class="edit" th:attr="data-id=${item.id}">修改</a>
                                    <a class="delete" th:attr="data-id=${item.id}">删除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <!--分页插件页码开始-->
                        <div class="row" th:include="common/pagination :: pagination"></div>
                        <!--分页插件页码结束-->

                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!--在使用thymeleaf时，前端页面如要在javascript中获取后端传入的数据，需要使用<script th:inline="javascript">-->
<script th:inline="javascript">
    //弹出层事件
    $(function () {
        //新增
        $(".create").on("click", function () {
            opt.openWin("/role/create", "新增", 580, 430);
        });
        //修改
        $(".edit").on("click", function () {
            var id = $(this).attr("data-id");
            opt.openWin('/role/edit/' + id, '修改', 580, 430);
        });
        //删除
        $(".delete").on("click", function () {
            var id = $(this).attr("data-id");
            opt.confirm('/role/delete/' + id);
        });
    });

</script>
</body>
</html>
```



## 2.5前端数据校验

前端数据校验使用jQuery Validate 插件

jQuery Validate 插件捆绑了一套有用的验证方法，包括 URL 和电子邮件验证，同时提供了一个用来编写用户自定义方法的 API。

参考文档：https://www.runoob.com/jquery/jquery-plugin-validate.html

### 2.5.1默认校验规则

| 序号 | 规则               | 描述                                                         |
| :--- | :----------------- | :----------------------------------------------------------- |
| 1    | required:true      | 必须输入的字段。                                             |
| 2    | remote:"check.php" | 使用 ajax 方法调用 check.php 验证输入值。                    |
| 3    | email:true         | 必须输入正确格式的电子邮件。                                 |
| 4    | url:true           | 必须输入正确格式的网址。                                     |
| 5    | date:true          | 必须输入正确格式的日期。日期校验 ie6 出错，慎用。            |
| 6    | dateISO:true       | 必须输入正确格式的日期（ISO），例如：2009-06-23，1998/01/22。只验证格式，不验证有效性。 |
| 7    | number:true        | 必须输入合法的数字（负数，小数）。                           |
| 8    | digits:true        | 必须输入整数。                                               |
| 9    | creditcard:        | 必须输入合法的信用卡号。                                     |
| 10   | equalTo:"#field"   | 输入值必须和 #field 相同。                                   |
| 11   | accept:            | 输入拥有合法后缀名的字符串（上传文件的后缀）。               |
| 12   | maxlength:5        | 输入长度最多是 5 的字符串（汉字算一个字符）。                |
| 13   | minlength:10       | 输入长度最小是 10 的字符串（汉字算一个字符）。               |
| 14   | rangelength:[5,10] | 输入长度必须介于 5 和 10 之间的字符串（汉字算一个字符）。    |
| 15   | range:[5,10]       | 输入值必须介于 5 和 10 之间。                                |
| 16   | max:5              | 输入值不能大于 5。                                           |
| 17   | min:10             | 输入值不能小于 10。                                          |



### 2.5.2使用插件添加校验

#### 2.5.2.1导入插件js资源

head.html文件引入js库

```html
<!--jQuery Validate 插件-->
<script th:src="@{/static/js/plugins/validate/jquery.validate.min.js}" type="text/javascript" ></script>
<script th:src="@{/static/js/plugins/validate/messages_zh.min.js}" type="text/javascript" ></script>
```



#### 2.5.2.2新增操作添加校验

向create.html添加校验代码：

```html
<script type="text/javascript">
    $(function(){//window.onload
        //为id值为ec的表单添加校验
        $('#ec').validate({
            //规则：roleName和description不能为空
            rules:{
                roleName:"required",
                description:"required"
            },
            //错误的提示信息：如果为空则提示信息
            messages:{
                roleName:"角色必须输入",
                description:"描述必须输入"
            },
            //拦截表单的提交，如果有不符合规则的内容，则不会提交，如果提交的话，还会将确定按钮设置为禁用
            submitHandler: function(form) {
                $(form).find(":submit").attr("disabled", true).text("正在提交...");
                form.submit();
            }
        });
    });
</script>
```

create.html完整的校验代码：

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head::head"></head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/role/save}" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">角色名称：</label>
                    <div class="col-sm-10">
                        <input type="text" name="roleName" id="roleName" value="" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">角色编码：</label>
                    <div class="col-sm-10">
                        <input type="text" name="roleCode" id="roleCode" value="" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">描述：</label>
                    <div class="col-sm-10">
                        <textarea name="description" id="description" class="form-control" style="width:100%;height: 50px;" ></textarea>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group posf">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" value="取消" onclick="opt.closeWin()">取消</button></div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function(){//window.onload
        //为id值为ec的表单添加校验
        $('#ec').validate({
            //规则：roleName和description不能为空
            rules:{
                roleName:"required",
                description:"required"
            },
            //错误的提示信息：如果为空则提示信息
            messages:{
                roleName:"角色必须输入",
                description:"描述必须输入"
            },
            //拦截表单的提交，如果有不符合规则的内容，则不会提交，如果提交的话，还会将确定按钮设置为禁用
            submitHandler: function(form) {
                $(form).find(":submit").attr("disabled", true).text("正在提交...");
                form.submit();
            }
        });
    });
</script>
</html>
```



#### 2.5.2.3修改操作添加校验

向edit.html添加校验代码：

```html
<script type="text/javascript">
    $(function(){//window.onload
        //为id值为ec的表单添加校验
        $('#ec').validate({
            //规则：roleName和description不能为空
            rules:{
                roleName:"required",
                description:"required"
            },
            //错误的提示信息：如果为空则提示信息
            messages:{
                roleName:"角色必须输入",
                description:"描述必须输入"
            },
            //拦截表单的提交，如果有不符合规则的内容，则不会提交，如果提交的话，还会将确定按钮设置为禁用
            submitHandler: function(form) {
                $(form).find(":submit").attr("disabled", true).text("正在提交...");
                form.submit();
            }
        });
    });
</script>
```

edit.html完整的校验代码：

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head::head"></head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/role/update}" method="post" class="form-horizontal" >
                <!--将roleId作为隐藏域-->
                <input type="hidden" name="id" th:value="${role.id}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">角色：</label>

                    <div class="col-sm-10">
                        <input type="text" name="roleName" id="roleName" th:value="${role.roleName}" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">角色编码：</label>
                    <div class="col-sm-10">
                        <input type="text" name="roleCode" id="roleCode" th:value="${role.roleCode}" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">描述：</label>
                    <div class="col-sm-10">
                        <textarea name="description" id="description" class="form-control" style="width:100%;height: 50px;" th:text="${role.description}" ></textarea>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group posf">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button></div>
                </div>
            </form>
        </div>
    </div>
</div>
</div>
</body>
<script type="text/javascript">
    $(function(){//window.onload
        //为id值为ec的表单添加校验
        $('#ec').validate({
            //规则：roleName和description不能为空
            rules:{
                roleName:"required",
                description:"required"
            },
            //错误的提示信息：如果为空则提示信息
            messages:{
                roleName:"角色必须输入",
                description:"描述必须输入"
            },
            //拦截表单的提交，如果有不符合规则的内容，则不会提交，如果提交的话，还会将确定按钮设置为禁用
            submitHandler: function(form) {
                $(form).find(":submit").attr("disabled", true).text("正在提交...");
                form.submit();
            }
        });
    });
</script>
</html>
```






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

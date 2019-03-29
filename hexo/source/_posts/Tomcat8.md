---
title: Tomcat8源码导入IDEA
date: 2017-3-28 18:40:05
tags: Java
toc: true 
---
学习Tomcat源码，最好是将其导入IDE，并可以运行调试。Apache的方法是使用ant工具，还要添加各种Jar包依赖，比较繁琐。通过搜索，发现一种通过Maven导入IDE方法，期间也遇到了坑。

### 下载Tomcat源码
由于之前使用的是Tomcat8.5.11版本，通过官方的 [svn地址](http://svn.apache.org/repos/asf/tomcat/tc8.5.x/tags/TOMCAT_8_5_11/)下载Tomcat源码。

### 构建Maven工程
在下载的源码根目录新建pom.xml文件，输入以下内容：

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>Tomcat8.0</artifactId>
    <name>Tomcat8.0</name>
    <version>8.0</version>

    <build>
        <finalName>Tomcat8.0</finalName>
        <sourceDirectory>java</sourceDirectory>
        <resources>
            <resource>
                <directory>java</directory>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.3</version>
                <configuration>
                    <encoding>UTF-8</encoding>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.easymock</groupId>
            <artifactId>easymock</artifactId>
            <version>3.4</version>
        </dependency>
        <dependency>
            <groupId>ant</groupId>
            <artifactId>ant</artifactId>
            <version>1.7.0</version>
        </dependency>
        <dependency>
            <groupId>wsdl4j</groupId>
            <artifactId>wsdl4j</artifactId>
            <version>1.6.2</version>
        </dependency>
        <dependency>
            <groupId>javax.xml</groupId>
            <artifactId>jaxrpc</artifactId>
            <version>1.1</version>
        </dependency>
        <dependency>
            <groupId>org.eclipse.jdt.core.compiler</groupId>
            <artifactId>ecj</artifactId>
            <version>4.5.1</version>
        </dependency>
    </dependencies>
</project>
```
然后在IDEA通过pom.xml导入工程，为了方便，可删除根目录下的test文件夹。

### 运行源代码
Tomcat的运行main方法位于org.apache.catalina.startup包下的BootStrap.java文件中，打开Run菜单下的Edit Configuration选项，新建Application项，Main class设置为org.apache.catalina.startup.Bootstrap，VM options中设置启动参数-Dcatalina.home="xxx"，可为根目录也可自选，此处先设置为源码根目录。
设置完毕后就可以run启动了。
之前查阅的一篇文章到这里就结束了，导致了一个坑的出现。
代码可以run启动，但是会有异常出现，找不到一些目录文件夹。
当打开[http://localhost:8080](http://localhost:8080)的时候会出现以下异常：

```
exception

org.apache.jasper.JasperException: Unable to compile class for JSP
	org.apache.jasper.JspCompilationContext.compile(JspCompilationContext.java:601)
	org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:363)
	org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:396)
	org.apache.jasper.servlet.JspServlet.service(JspServlet.java:340)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:729)
root cause

java.lang.NullPointerException
	org.apache.jasper.compiler.Validator$ValidateVisitor.<init>(Validator.java:516)
	org.apache.jasper.compiler.Validator.validateExDirectives(Validator.java:1853)
	org.apache.jasper.compiler.Compiler.generateJava(Compiler.java:217)
	org.apache.jasper.compiler.Compiler.compile(Compiler.java:356)
	org.apache.jasper.compiler.Compiler.compile(Compiler.java:336)
	org.apache.jasper.compiler.Compiler.compile(Compiler.java:323)
	org.apache.jasper.JspCompilationContext.compile(JspCompilationContext.java:585)
	org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:363)
	org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:396)
	org.apache.jasper.servlet.JspServlet.service(JspServlet.java:340)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:729)
```
此时浏览器返回500的错误。

搜索错误查阅了好久，并没有这些异常的说明，后来才发现这是由于缺少了lib文件夹下的某些包的缘故，而源码中是不带lib的。
原来源代码中并不包含这些依赖包，还是要到官网下载对应Tomcat版本的软件包，解压之后将以下文件夹复制到之前设置的Dcatalina.home目录中：

```
bin
conf
lib
logs
temp
webapps
work
```
然后run就可以正常启动，打断点调试了。

如果闲麻烦，配置好的项目托管在[Github仓库中](https://github.com/quekx/tomcat8)，可自行下载导入IDEA。

参考：[https://yq.aliyun.com/articles/61765](https://yq.aliyun.com/articles/61765)
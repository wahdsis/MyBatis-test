##配置pom.xml文件
```
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
```
> 设置源代码的编码机制为UTF-8

```
<build>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
> 设置编译器版本为jdk1.8


```
    <dependencies>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.3.0</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.29</version>
        </dependency>
    </dependencies>
```
>导入必要的依赖：org.mybatis组下的mybatis，junit组下的junit，mysql组下的mysql-connector-java项目，需要严格与机器上mysql版本对应

偶尔会遇到Maven显示要导入的依赖不存在的问题，可以直接从maven代理仓库网址[Maven Repository: Search/Browse/Explore (mvnrepository.com)](https://mvnrepository.com/)中搜索出想要的依赖，然后通过cmd指令：
>mvn dependency:get -DremoteRepositories=url -DgroupId=groupId -DartifactId=artifactId -Dversion=version
url:所需依赖的地址
groupId：依赖所属的群组
artifactId: 依赖的项目名
version:依赖版本

来直接将maven依赖从maven远程仓库导入本地仓库
![install_dependency1.png](https://upload-images.jianshu.io/upload_images/29061688-bcbae7a41c178c2e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![install_dependency2.png](https://upload-images.jianshu.io/upload_images/29061688-b77f7ef2185915a2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![install_dependency3.png](https://upload-images.jianshu.io/upload_images/29061688-00a4eb4c0f8ea099.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




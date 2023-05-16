## 配置pom.xml文件
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
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
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
>导入必要的依赖：mybatis组件：org.mybatis组下的mybatis，测试：junit组下的junit，jdbc驱动器：mysql组下的mysql-connector-java项目，需要严格与机器上mysql版本对应，日志组件：log4j组下的log4j项目，
查询MySQL版本可以使用下面的方式：
![](image\unit1\showsql_version.png)
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

# 3.通过xml文件配置mybatis
## 1.在resource文件夹下构造一个xml文件
![](image/unit1/buildconfigxml.png)
## 2.导入mybatis命名空间：
```<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd"
        >
```

## 3.设置一个包的别名
```
<typeAliases>
        <package name="com.mybatistest.test"></package>
    </typeAliases>
```
在mybatis中使用同一个类需要使用到类的全限定名，所以需要频繁的使用到类的全限定名，定义了typeAliases的包名之后就不再需要写类的全限定名，直接使用类名即可

## 4.数据库配置需要使用一些属性
```
<environments default="development">
        <environment id="development">
            <transactionManager type="JDBC">
                <property name="" value=""/>
            </transactionManager>
            <dataSource type="UNPOOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/sakila"/>
                <property name="username" value="root"/>
                <property name="password" value="qwer2511604899"/>
            </dataSource>
        </environment>
    </environments>
```
environments用于配置数据库连接的环境配置
默认使用id为development的环境配置
数据源dataSource中配置了连接数据库所需的元素
1.驱动器driver：com.mysql.cj.jdbc.Driver 
2.连接数据库的url：jdbc:mysql://localhost:3306/sakila
3.用户名user：root
4.密码password：qwer2511604899


## 创建实体类
作为一个结果映射框架，mybatis对一张表执行INSERT,UPDATE,DELETE操作和简单的SELECT时都需要有一个实体类对应着这张表。
![](image\unit1\model.png)
```
import java.sql.Timestamp;

public class Actor {
    private int actor_id;
    private String last_name;
    private String first_name;
    private java.sql.Timestamp time;

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
```
## 创建Mybatis的sql语句和映射配置文件 *Mapper.xml
### 1.首先还是导入命名空间
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd"
        >
```
### 2.根元素mapper
```
<mapper namespace="mapper.ActorMapper">
    <select id="selectAll" resultType="com.mybatistest.model.Actor">
        SELECT * FROM actor;
    </select>
</mapper>
```
namespace表示命名空间，这里就是当前映射文件的相对目录
select表示定义的一个SELECT查询
id：表示当前select的唯一标识符
resultType：表示查询的返回类型，这里就是表对应的实体类
####！在创建完映射文件之后还需要回到配置文件*config.xml中加上对该文件的引用
```
<mappers>
        <mapper resource="mapper/ActorMapper.xml"></mapper>
    </mappers>
```

## 上述工作做完之后就可以测试mybatis
#### 1.首先需要把mybatis的配置文件作为Reader类读入
```
 Reader reader=Resources.getResourceAsReader("mybatis-config.xml");
```

#### 2.使用配置文件对应的Reader对象创建一个SqlSessionFactory的工厂对象，该工厂对象用于创建代表Sql会话的SqlSession对象
```
SqlSessionFactory fac=new SqlSessionFactoryBuilder().build(reader);
```
#### 3.创建一个会话
```
SqlSession session=fac.openSession();
```
### 4.选择要执行的sql操作
```
List<Actor> list=session.selectList("selectAll");
selectAll为要执行操作的id也就是在Mapper.xml中定义的select元素的id
```
#### ---打印执行结果
```
for(Actor a:list){
                System.out.println(a.getActor_id()+"\t"+a.getFirst_name()+"\t"+a.getLast_name()+"\t"+a.getTime());

            }
```
## 整体代码如下：
```
import com.mybatistest.model.Actor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            Reader reader=Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory fac=new SqlSessionFactoryBuilder().build(reader);
            SqlSession session=fac.openSession();
            List<Actor> list=session.selectList("selectAll");
            for(Actor a:list){
                System.out.println(a.getActor_id()+"\t"+a.getFirst_name()+"\t"+a.getLast_name()+"\t"+a.getTime());

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

```
![](image\unit1\result.png)

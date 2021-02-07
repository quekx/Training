##### JVM 运行时区域

###### 1. 程序计数器

线程私有，当前线程执行的字节码所在行号指示器，每个线程都有一个独立的计数器，私有互不影响

###### 2. Java虚拟机栈

线程私有，存储方法调用信息，每个方法被执行时，会创建一个栈帧，存储局部变量表、操作数栈、动态链接、方法出口等

###### 3. 本地方法栈 

线程私有，服务于 native 方法

###### 4. Java 堆

线程共有，最主要的 Java 对象分配区域

参数 -Xmx -Xms

###### 5. 方法区

线程共有，存储虚拟机加载的 class 类信息、常量、静态变量、<mark>即时编译器编译后的代码缓存数据？</mark>

 ###### 5.1 运行时常量池

常量池是方法区的一部分，虚拟机加载时，class 文件中编译生成的常量表，会放在常量池中

比如：常见的 Integer[-128, 127]，static final 变量

动态放入常量池：String.intern()

###### 6. 直接内存

直接内存（direct memory）不是 JVM 的运行区域。JDK 1.4 新引入 NIO，可以使用 native 方法直接分配堆外内存，然后通过一个堆内的 Java 对象（DirectByteBuffer）作为这块内存的引用进行内存操作





![image-20210112121813796](/Users/kaixin/Library/Application Support/typora-user-images/image-20210112121813796.png)



#### class

class 文件包含 Java 虚拟机指令集、符号表、其他辅助信息

Java 能较好的保持向后兼容性，class 文件结构的稳定性功不可没

任何 class 文件都对应一个类或者接口，但是类和接口不一定必须要 class 文件，有可能动态生成，直接送入类加载器

class 文件以 8 个字节为基础单位存储，各项数据按照顺序紧凑排列，没有分隔符。数据项多于 8 个字节时，按照高位在前的方式进行分割

class 文件只有两种数据结构

* 无符号数：描述数字、引用、数量值、UTF-8 编码的字符串
* 表：多个无符号数和表组成的复合数据结构

![image-20210112201139570](/Users/kaixin/Library/Application Support/typora-user-images/image-20210112201139570.png)

![image-20210112201348987](/Users/kaixin/Library/Application Support/typora-user-images/image-20210112201348987.png)



常量池存放两类

1. 字面量：常量数据
2. 符号引用




| 字节编号 | 含义               |
| ------ | ----- |
| 0~3    | 魔数（0xCAFEBABE） |
| 4~5 | 次版本号 |
| 6~7 | 主版本号，过新的版本号虚拟机拒绝运行 |
| 8~9 | 常量池大小 0xXXXX = n，代表后面 (n - 1) 字节为常量池数据 |
|  |  |



### 类加载

加载时机

1. 加载
2. 验证
3. 准备
4. 解析
5. 初始化
6. 使用
7. 卸载

![image-20210112210748477](/Users/kaixin/Library/Application Support/typora-user-images/image-20210112210748477.png)

必须对类进行初始化

1. 遇到new/getstatic/putstatic/invokestatic
   1. new 实例化对象
   2. 读取静态变量
   3. 设置静态变量
   4. 调用静态方法
2. 反射调用
3. 初始化某个类，该类的父类没初始化，先初始化父类
4. 启动 main 方法所在的主类
5. 动态语音支持？
6. JDK8 新增带默认方法的接口，先初始化接口，类比父类初始化



![image-20210112211550395](/Users/kaixin/Library/Application Support/typora-user-images/image-20210112211550395.png)



#### 1. 加载

* 通过类的全限定名获取类的二进制字节流

* 将字节流代表的静态存储结构转化为运行时数据结构

* 内存中生成一个 Class 对象，作为方法区这个类各种数据的访问入口



![image-20210113104744996](/Users/kaixin/alibaba/personal/quekx.github.io/image-20210113104744996.png)



不懂？数组类型？

![image-20210113105134906](/Users/kaixin/Library/Application Support/typora-user-images/image-20210113105134906.png)



#### 2. 验证

确保 class 文件的数据符合规范，不会危害虚拟机；class 文件可以任意生成，不一定由 Java 编译由来

* 文件格式验证

![image-20210113105527982](/Users/kaixin/Library/Application Support/typora-user-images/image-20210113105527982.png)

* 元数据验证

![image-20210113105624656](/Users/kaixin/Library/Application Support/typora-user-images/image-20210113105624656.png)



* 字节码验证

![image-20210113105745569](/Users/kaixin/Library/Application Support/typora-user-images/image-20210113105745569.png)

* 符合引用验证

![image-20210113110016558](/Users/kaixin/Library/Application Support/typora-user-images/image-20210113110016558.png)

1、校验使用的类

2、校验对应类中的字段、方法

3、校验类、字段、方法的访问属性



#### 3. 准备

为类 static 变量分配内存区域，并设置初始值

![image-20210113111005954](/Users/kaixin/Library/Application Support/typora-user-images/image-20210113111005954.png)



#### 4. 解析

将常量池内的符号引用转化的直接引用

![image-20210113111917669](/Users/kaixin/Library/Application Support/typora-user-images/image-20210113111917669.png)



#### 5. 初始化

类变量设置为代码中设置的初始值

初始化是执行类构造器<cinit>() 方法的过程

由类变量初始化语句和静态初始化方法（static 代码块）合并组成



### 类加载器

对于任意一个类，必须由加载它的类加载器和这个类本身一起共同在虚拟机内唯一确定



虚拟机角度看，只有两类类加载器

* 启动类加载器：Bootstrap ClassLoader，由 C++ 实现，是虚拟机一部分
* 其他类加载器，由 Java 实现，在虚拟机外部，继承自基类 ClassLoader



开发角度看，类加载器分类更细致

##### 1. 启动类加载器

Bootstrap Class Loader，虚拟机集成。负责加载 ${JAVA_HOME}/lib 目录下的类库，同时按指定文件名识别，如：rt.jar，tools.jar... 名称不符合不会被加载

？？？

![image-20210113142401211](/Users/kaixin/Library/Application Support/typora-user-images/image-20210113142401211.png)



##### 2. 扩展类加载器

Extension Class Loader，实现类 sun.misc.Launcher$ExtClassLoader。负责加载 ${JAVA_HOME}/lib/ext 目录

##### 3. 应用类加载器

Application Class Loader，实现类 sun.misc.Launcher$AppClassLoader。是 ClassLoader#getSystemClassLoader 的返回值，又称“系统类加载器”。负责加载用户类路径上的类库，即用户 ClassPath。一般情况下默认的类加载器。



##### 双亲委派模型

<img src="/Users/kaixin/Library/Application Support/typora-user-images/image-20210113143315113.png" alt="image-20210113143315113" style="zoom:30%;" />



双亲委派过程：类加载器收到加载类的请求，先把类加载的请求委托给父类加载器去完成，每一层的加载都如此，因为加载委托会提交到最顶层的启动类加载器。当父类加载器无法加载此类（在搜索范围内没有找到需要的类），子加载器才会尝试自己加载。

双亲委派能保证 Java 基础类库的稳定性，因为所有类首先会尝试通过启动类加载器进行加载，这样保证所有用到的基础类库，例如 java.lang.Object（来自 rt.jar），都是由启动类加载器加载进来的。

<img src="/Users/kaixin/Library/Application Support/typora-user-images/image-20210113145122843.png" alt="image-20210113145122843" style="zoom:33%;" />



<img src="/Users/kaixin/Library/Application Support/typora-user-images/image-20210113153543933.png" alt="image-20210113153543933" style="zoom:30%;" />

线程上下文加载器，通常默认是 AppClassLoader





<img src="/Users/kaixin/Library/Application Support/typora-user-images/image-20210113153656601.png" alt="image-20210113153656601" style="zoom:33%;" />



### 字节码执行

虚拟机以方法作为最基本的执行单元

栈帧是用于方法调用和执行的数据结构，是 Java 虚拟机栈的元素

<img src="/Users/kaixin/Library/Application Support/typora-user-images/image-20210113160245003.png" alt="image-20210113160245003" style="zoom:33%;" />

栈帧元素

* 局部变量表
* 操作数栈
* 动态链接
* 方法返回地址
* 额外附加信息

方法开始调用到结束返回结果，对应一个栈帧入栈到出栈的过程



#### 局部变量表

一组变量存储空间，用于存储方法参数和方法内部定义的局部变量

<img src="/Users/kaixin/Library/Application Support/typora-user-images/image-20210113162248553.png" alt="image-20210113162248553" style="zoom:33%;" />



#### 操作数栈

存放运行字节码指令期间需要的数据

* 算数运算

* 调用其他方法传递参数



#### 动态链接

？？？

<img src="/Users/kaixin/Library/Application Support/typora-user-images/image-20210113163106063.png" alt="image-20210113163106063" style="zoom:33%;" />



#### 方法返回地址





### 方法调用

解析

编译期操作，对应方法重载，根据参数类型来确认调用哪个方法



分派

运行期操作，对应方法重写，根据对象实际类型来确定方法版本（父类还是子类）










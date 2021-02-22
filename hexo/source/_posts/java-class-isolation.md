---
title: 学习笔记(7):Java 类加载隔离
date: 2021-02-07 16:04:50
tags:
---

> Java 依赖类冲突是比较常见的问题，简单介绍使用 Java 类隔离机制解决类冲突问题



### 类冲突问题

类冲突本质上是由于相同名称的类，同时存在多个版本，并且多个版本之间并不兼容

由于 JVM 的类加载特性，即使依赖的 Jar 里存在多个版本，一个类只会会被加载一次，运行使用的类和预期类不一致时候可能出现问题。比如：

1. A 组件使用了某个只有高版本类中存在的方法，实际加载类为 B 组件引入的低版本，在调用方法时，会抛出 NoSuchMethodError
2. A 组件某个类和 B 组件某个类重名，两个类没有任何关系，实际加载类为 B 组件中的类。第二种这是少数情况

PS：JVM 加载类的顺序由对应的 ClassLoader 决定，一般默认的系统类加载器 AppClassLoader 就按照 -classpath 下的文件列表顺序进行加载。当有类冲突发生时，类版本来自于先加载的 Jar 包

<!-- more -->



### 类加载隔离

JVM 的类加载机制不再详述

由 JVM 机制可得具体类由加载该类的 ClassLoader + 类名进行确定，要隔离同名类的方法显而易见：**使用不同的 ClassLoader 来加载不同的组件** 



### 场景示例

#### 1. 一般场景

一般使用场景，当运行以下 main 方法时，类如何加载的？

```
public class Player {
    public void sayHello() {
        System.out.println("player say hello");
    }
}
```

```
public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        player.sayHello();
    }
}    
```
* 首先，编译后的 class 文件或者 Jar 包，启动时会通过 -classpath 参数传入，并传入启动主类 Main。-classpath 路径下的类文件由系统类加载器 AppClassLoader 进行加载，主类由 AppClassLoader 加载

* Player 类如何加载？以上的调用中，Player 类是通过隐式加载的。在某个类中 new 对象，会默认使用该类（示例中为主类 Main）的类加载器进行加载。因此 Player 也是由 AppClassLoader 加载的。

  使用该隐式默认加载行为还包括以下等操作，没有显式指定 ClassLoader：

  ```
  // 获取 class 对象，获取前会触发加载
  Class<?> clazz = Player.class;
  // 显式加载, 等值于 Main.class.getClassLoader.loadClass("Player");
  Class.forName("Player");
  ```



#### 2. 类冲突场景

假设存在以下类冲突场景

{% asset_img class-conlict.jpg 类冲突 %}

定义两个模块含有同名冲突类：Player；两者同名，但是方法存在冲突

```
// module player1
public class Player {
    public void sayHello() {
        System.out.println("player1 say hello");
    }

    public void swim() {
        System.out.println("player1 swim");
    }
}
```
```
// module player2
public class Player {
    public void sayHello() {
        System.out.println("player2 say hello");
    }

    public void run() {
        System.out.println("player2 run");
    }
}
```



a 模块 和 b 模块分别依赖 player1 和 player2

```
public class A {
    public void doSport() {
        Player player = new Player();
        player.sayHello();
        player.swim();
    }
}
```

```
public class B {
    public void doSport() {
        Player player = new Player();
        player.sayHello();
        player.run();
    }
}
```



主模块方法调用

```
public class Main {
    public static void main(String[] args) {
        A a = new A();
        a.doSport();
        
        B b = new B();
        b.doSport();
    }
}
```



模块结构

```
.
|____a
| |____A.java
|____b
| |____B.java
|____main
| |____Main.java
|____player1
| |____Player.java
|____player2
| |____Player.java
```



main 方法运行结果

```
player1 say hello
player1 swim
player1 say hello
Exception in thread "main" java.lang.NoSuchMethodError: Player.run()V
	at B.doSport(B.java:21)
	at Main.main(Main.java:11)
```

由于底层类 Player 存在两个同名不兼容版本，直接运行必定出错：

1. b 中的 sayHello 方法调到了 a 依赖版本 player1
2. b 在对应 Player 类中找不到 run 方法

这是由于各部分都是由同一个 AppClassLoader 加载导致，因为 player1 先加载，只会存在一个 player1 版本中的 Player.java



#### 3. 类加载隔离冲突

通过不同的类加载器，分别加载组件 a 和组件 b，达到类隔离的目的

```
public class Main {
    public static void main(String[] args) throws Exception {
        // a class 文件和依赖的 player1 class 文件路径
        URL urlA1 = new URL("file:a/target/classes/");
        URL urlA2 = new URL("file:player1/target/classes/");
        URL[] urlA = new URL[]{urlA1, urlA2};
        // parent 不能指定为 AppClassLoader，不然会优先委托父类加载
        ClassLoader classLoaderA = new URLClassLoader(urlA, null);
        Class<?> classA = classLoaderA.loadClass("A");
        Object a = classA.newInstance();
        Method methodA = classA.getMethod("doSport");
        methodA.invoke(a);

        // b class 文件和依赖的 player2 class 文件路径
        URL urlB1 = new URL("file:b/target/classes/");
        URL urlB2 = new URL("file:player2/target/classes/");
        URL[] urlB = new URL[]{urlB1, urlB2};
        ClassLoader classLoaderB = new URLClassLoader(urlB, null);
        Class<?> classB = classLoaderB.loadClass("B");
        Object b = classB.newInstance();
        Method methodB = classB.getMethod("doSport");
        methodB.invoke(b);
    }
}
```

运行结果

```
player1 say hello
player1 swim
player2 say hello
player2 run
```



#### 4. 类隔离容器

类隔离容器底层原理本质上也是利用不同 ClassLoader 进行类隔离，对组件类加载进行一层封装，可以不侵入主体代码：

1. 在入口处，提前使用不同的 ClassLoader 进行组件类的加载，放入缓存中。此时缓存中的类是隔离的
2. 自定义 ClassLoader，类加载时优先从缓存类中获取，获取不到在进行加载
3. 使用自定义 ClassLoader 重新加载主类运行主方法

```
public class MyClassLoader extends URLClassLoader {

    private final Map<String, Class<?>> classCache;

    public MyClassLoader(URL[] urls, ClassLoader parent, Map<String, Class<?>> classCache) {
        super(urls, parent);
        this.classCache = classCache;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (classCache != null && classCache.containsKey(name)) {
            return classCache.get(name);
        }
        return super.loadClass(name);
    }
}
```



```
public class Main {

    private static Boolean isInit = null;

    public static void main(String[] args) throws Exception {
        // 运行主方法前检查
        preRun(args);

        // 主体方法
        A a = new A();
        a.doSport();

        B b = new B();
        b.doSport();
    }

    private static void preRun(String[] args) throws Exception {
        if (isInit != null) {
            return;
        }

        // 加载类并缓存
        Map<String, Class<?>> classCache = initClass();
        // 自定义 ClassLoader，父类不能指定为 AppClassLoader
        // 否则由于双亲委派机制，类又会委托给 AppClassLoader 进行加载，GG
        // 可以指定为 null 或者 extClassLoader (和 null 没区别反正加载不到用户 class 文件)
        ClassLoader appClassLoader = Main.class.getClassLoader();
        URL[] urls = ((URLClassLoader) appClassLoader).getURLs();
        MyClassLoader myClassLoader = new MyClassLoader(urls, appClassLoader.getParent(), classCache);

        // 使用自定义 ClassLoader 重新加载主类
        // 重启主方法，这样才会走自定义的类加载逻辑
        Class<?> mainClass = myClassLoader.loadClass("Main");
        // 标志位 isInit，只在第一次进入此方法进行类加载
        Field field = mainClass.getDeclaredField("isInit");
        field.setAccessible(true);
        field.set(null, true);
        Method mainMethod = mainClass.getDeclaredMethod("main", String[].class);
        mainMethod.invoke(null, new Object[]{args});

        // 此时主方法运行结束，结束进程
        System.exit(0);
    }

    public static Map<String, Class<?>> initClass() throws Exception {
        Map<String, Class<?>> classCache = new HashMap<>();
        URL urlA1 = new URL("file:a/target/classes/");
        URL urlA2 = new URL("file:player1/target/classes/");
        URL[] urlA = new URL[]{urlA1, urlA2};
        // parent 不能指定为 AppClassLoader，不然会优先委托父类加载
        ClassLoader classLoaderA = new URLClassLoader(urlA, null);
        Class<?> classA = classLoaderA.loadClass("A");
        classCache.put("A", classA);

        URL urlB1 = new URL("file:b/target/classes/");
        URL urlB2 = new URL("file:player2/target/classes/");
        URL[] urlB = new URL[]{urlB1, urlB2};
        ClassLoader classLoaderB = new URLClassLoader(urlB, null);
        Class<?> classB = classLoaderB.loadClass("B");
        classCache.put("B", classB);
        return classCache;
    }
}
```

运行结果

```
player1 say hello
player1 swim
player2 say hello
player2 run
```




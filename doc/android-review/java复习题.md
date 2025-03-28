Java 基础
1、面向对象编程有哪些特征？
- 封装、继承、多态 

# 2、JDK 与 JRE 的区别是什么？

# 3、Java 有哪几种基本数据类型？
- byte、short、int、long、float、double、char、boolean

# 4、== 和 equals 比较有什么区别？

5、public,private,protected,默认的区别？

6、this 和 super 有什么区别？

7、short s1 = 1; s1 += 1;有错吗？
- 没有错，s1 += 1; 相当于 s1 = (short)(s1 + 1);

8、short s1 = 1; s1 = s1 + 1;有错吗？

9、float n = 1.8 有错吗？

10、i++ 和 ++i 的区别？
 -   i++ 先赋值后加一，++i 先加一后赋值

11、while 和 do while 有啥区别？

12、如何跳出 Java 中的循环？

13、如何跳出 Java 中的多层嵌套循环？

14、& 和 && 的区别？

15、2 * 8 最有效率的计算方法是什么？

16、数组有没有 length 方法？String 呢？

# 17、怎么理解值传递和引用传递？
- 值传递：传递的是值的副本，不会改变原来的值
- 引用传递：传递的是引用的地址，会改变原来的值

18、Java 到底是值传递还是引用传递？
- Java 是值传递，传递的是值的副本

19、一个 ".java" 源文件的类有什么限制？

20、Java 中的注释有哪些写法？

21、static 关键字有什么用？

22、static 变量和普通变量的区别？

23、static 可以修饰局部变量么？

24、final 关键字有哪些用法？

25、final、finally、finalize 有什么区别？
- final 修饰类，方法，变量
- finally 用在 try-catch 语句中，无论是否发生异常都会执行
- finalize 是 Object 类的一个方法，用来在垃圾回收前释放资源

26、void 和 Void 有什么区别？

27、为什么 byte 取值范围为 -128～127？

28、char 类型可以存储中文汉字吗？
- 可以，char 类型是 16 位无符号整数，可以存储一个中文汉字

29、重载和重写有什么区别？

30、构造器可以被重写和重载吗？

31、main 方法可以被重写和重载吗？

32、私有方法能被重载或者重写吗？

33、Java 中的断言（assert）是什么？

34、Java 异常有哪些分类？

35、Error 和 Exception 有什么区别？

36、Java 中常见的异常有哪些？

37、Java 中常见的运行时异常有哪些？

38、运行时异常与受检查异常有什么区别？

39、什么时候会发生空指针异常？

40、你知道有哪些避免空指针的方法？

41、throw 和 throws 的区别？

42、try-catch-finally 中哪个部分可以省略？

43、try 里面 return，finally还会执行吗？

44、int 和 Integer 有什么区别？

45、什么是包装类型？有什么用？

46、什么是自动装厢、拆厢？

47、你怎么理解 Java 中的强制类型转换？

48、你怎么理解 Java 中的自动类型转换？

49、你怎么理解 Java 中的类型提升？

50、switch 是否能用在 long 上？

51、switch 是否能用在 String 上？

52、switch case 支持哪几种数据类型？

## 53、String 属于基础的数据类型吗？
- String 属于引用数据类型

## 54、String 类的常用方法都有那些？

55、String 的底层实现是怎样的？

56、String 是可变的吗？为什么？

57、String 类可以被继承吗？

58、String 真的是不可变的吗？

59、String 字符串如何进行反转？

60、String 字符串如何实现编码转换？

61、String 有没有长度限制？是多少？

## 62、为什么不能用 + 拼接字符串？
- String定义为不可变的，每次拼接都会生成一个新的String对象，效率低下

## 63、StringBuffer 和 StringBuilder 的区别？
- StringBuffer 线程安全，StringBuilder 线程不安全

64、StringJoiner 有什么用？

65、Java 所有类的祖先类是哪个？

66、Object 类有哪些常用的方法？

## 67、普通类和抽象类有什么区别？
- 普通类可以实例化，抽象类不能实例化

68、静态内部类和普通内部类有什么区别？
- 静态内部类不依赖外部类，普通内部类依赖外部类

69、静态方法可以直接调用非静态方法吗？
- 不可以

70、静态变量和实例变量有什么区别？

71、内部类可以访问其外部类的成员吗？

72、接口和抽象类有什么区别？

73、接口是否可以继承接口？

74、接口里面可以写方法实现吗？

75、抽象类必须要有抽象方法吗？

76、抽象类能使用 final 修饰吗？

77、抽象类是否可以继承具体类？

78、抽象类是否可以实现接口？

79、怎么查看一个 Java 类的字节码？

80、Java 中的 UUID 是什么?

81、Java 类初始化顺序是怎样的？

82、为什么成员变量命名不建议用 isXXX？

83、hashCode 有什么用？

84、hashCode 和 identityHashCode 的区别？

85、什么是 hash 冲突？

86、equals 和 hashCode 的区别和联系？

87、两个对象 equals 相等， hashCode 也相等么？

88、两个对象 hashCode 相等，equals 也相等么？

89、为什么重写 equals 就要重写 hashCode 方法？

90、Java 常用的元注解有哪些？

91、Java 泛型中的 T、R、K、V、E 分别指什么？

92、Java 金额计算怎么避免精通丢失？

93、Java 语法糖是什么意思？

94、transient 关键字有什么用？

95、如何实现对象克隆？

96、对象克隆浅拷贝和深拷贝的区别？

97、Java 反射机制有什么用？

98、Java 反射机制的优缺点？

99、Java 反射机制 Class 类有哪些常用方法？

100、Java 反射可以访问私有方法吗？

101、Java 反射可以访问私有变量吗？

102、Class.forName 和 ClassLoader 的区别？

103、什么是宏变量和宏替换？

104、什么是逃逸分析？

105、什么是伪共享？有什么解决方案？

106、Java 有没有 goto 关键字？

107、Java 中有没有指针的概念？

108、Java 中的 classpath 环境变量作用？

109、Math.round(1.5) 等于多少？

110、Math.round(-1.5) 等于多少？

111、Java 8 都新增了哪些新特性？

112、Java 8 中的 Lambda 表达式有啥用？

113、Java 8 中的 Optional 类有什么用？

114、Java 8 中的 Stream 有啥用？

115、Java 8 中的@Repeatable 注解有什么用？

116、Java 8 中的方法引用是指什么？

117、Java 8 中的函数式编程怎么用？

118、怎么创建一个 Stream 流？

119、Oracle JDK 和 OpenJDK 有啥区别？

Java 集合
## 1、说说常见的集合有哪些？
- List、Set、Map

2、哪些集合类可对元素的随机访问？
- List

3、Comparable 和 Comparator 接口的区别？

4、Collection 和 Collections 的区别？

5、Enumeration 和 Iterator 接口的区别？

6、集合使用泛型有什么优点？

## 7、List、Set、Map 之间的区别是什么？
- List 有序，可重复 Set 无序，不可重复 Map 无序，key 不可重复，value 可重复

## 8、为什么 Map 接口不继承 Collection 接口？
- Map 是键值对，Collection 是集合

## 9、常用的线程安全的 Map 有哪些？
- ConcurrentHashMap、HashTable

## 10、HashMap 与 Hashtable 的区别？
- HashMap 线程不安全，HashTable 线程安全

11、HashMap 和 TreeMap 怎么选？

12、HashMap 的数据结构是什么？

13、HashMap 在 JDK 8 中有哪些改变？

14、HashMap 的 put 方法逻辑？

15、HashMap 的 get 方法逻辑？

16、HashMap 是线程安全的吗？

17、HashMap 是怎么解决 hash 冲突的？

18、HashMap 是怎么扩容的？

19、HashMap 如何实现同步?

20、HashMap 中的负载因子是什么？

21、Hashtable 为什么不叫 HashTable？

22、ConcurrentHashMap 的数据结构？

23、ArrayList 是线程安全的么？

24、常用的线程安全的 List 集合有哪些？

25、循环删除 List 集合可能会发生什么异常？

26、ArrayList 和 LinkedList 的区别？

27、ArrayList 和 Vector 的区别？

28、什么是 CopyOnWriteArrayList？

29、什么是 fail-safe？

30、什么是 fail-fast？

31、fail-fast 与 fail-safe 有什么区别？

32、HashSet 的底层实现原理是什么？

33、怎么确保一个集合不能被修改？

JVM
1、Java 为什么能一次编写，处处运行？

2、JVM 是什么？

3、HotSpot 是什么？

4、JVM 内存区域分类哪些？

5、堆和栈区别是什么？

6、JVM 哪块内存区别不会发生内存溢出？

7、什么情况下会发生栈内存溢出？

8、对象都是在堆上分配的吗？

9、你怎么理解强、软、弱、虚引用？

10、常用的 JVM 参数有哪些？

11、Java 8 中的内存结构有什么变化？

12、Java 8 中的永久代为什么被移除了？

13、什么是类加载器？

14、类加载器的分类及作用？

15、什么是双亲委派模型？

16、为什么要打破双亲委派模型？

17、可以自定义一个 java.lang.String 吗？

18、什么是 JVM 内存模型？

19、JVM 内存模型和 JVM 内存结构的区别？

20、什么是指令重排序？

21、内存屏障是什么？

22、什么是 Happens-Before 原则？

23、GC 是什么？为什么需要 GC？

24、什么是 MinorGC 和 FullGC？

25、一次完整的 GC 流程是怎样的？

26、JVM 如何判断一个对象可被回收？

27、常用的垃圾收集器有哪些？

28、常用的垃圾回收算法有哪些？

29、什么是内存泄漏？

30、为什么会发生内存泄漏？

31、如何防止内存泄漏？

32、什么是直接内存？

33、直接内存有什么用？

34、怎样访问直接内存？

35、常用的 JVM 调优命令有哪些？

36、常用的 JVM 问题定位工具有哪些？

37、常用的主流 JVM 虚拟机都有哪些？

多线程（并发编程）
1、进程和线程的区别？

- 进程是操作系统资源分配的最小单位，线程是 CPU 调度的最小单位

2、什么是原子性、可见性、有序性？

    - 原子性：一个操作或者多个操作要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行
    - 可见性：一个线程对共享变量的修改可以及时的被其他线程看到
    - 有序性：程序执行的顺序按照代码的先后顺序执行

3、为什么要使用多线程？

4、创建线程有哪几种方式？

5、什么是守护线程？

6、线程的状态有哪几种？怎么流转的？

7、线程的优先级有什么用？

8、我们常说的 JUC 是指什么？

9、i++ 是线程安全的吗？
- 不是线程安全的，i++ 是三个操作，读取、+1、写入，如果两个线程同时读取到了同一个值，然后+1，然后写入，那么就会出现线程安全问题

10、join 方法有什么用？什么原理？

11、如何让一个线程休眠？

12、启动一个线程是用 start 还是 run 方法？


13、start 和 run 方法有什么区别？

# 14、sleep 和 wait 方法有什么区别？
- sleep 是Thread的静态方法 wait是Object的方法, sleep不会释放锁，wait会释放锁

15、Thread.yield 方法有什么用？

16、yield 和 sleep 有什么区别？

17、怎么理解 Java 中的线程中断？

18、你怎么理解多线程分组？

19、你怎么理解 wait、notify、notifyAll？

20、同步和异步的区别？

21、什么是死锁？

22、怎么避免死锁？

23、什么是活锁？

24、什么是无锁？

25、什么是线程饥饿？

26、什么是 CAS？

27、阻塞和非阻塞的区别？

28、并发和并行的区别？

29、为什么不推荐使用 stop 停止线程？

30、如何优雅地终止一个线程？

31、Synchronized 同步锁有哪几种用法？

32、什么是重入锁（ReentrantLock）？

33、Synchronized 与 ReentrantLock 的区别？

34、synchronized 锁的是什么?

35、什么是读写锁？

36、公平锁和非公平锁的区别？

37、有哪些锁优化的方式？

38、什么是偏向锁？

39、什么是轻量级锁？

40、什么是自旋锁？

41、什么是锁消除？

42、什么是锁粗化？

43、什么是重量级锁？

44、什么是线程池？

45、使用线程池有什么好处？

46、创建一个线程池有哪些核心参数？

47、线程池的工作流程是怎样的？

48、Java 里面有哪些内置的线程池？

49、为什么阿里不让用 Executors 创建线程池？

50、线程池的拒绝策略有哪几种？

51、如何提交一个线程到线程池？

52、线程池 submit 和 execute 有什么区别？

53、如何查看线程池的运行状态？

54、如何设置线程池的大小？

55、如何关闭线程池？

56、AQS 是什么？

57、AQS 的底层原理是什么？

58、Java 中的 Fork Join 框架有什么用？

59、ThreadLocal 有什么用？

60、ThreadLocal 有什么副作用？

61、volatile 关键字有什么用？

62、volatile 有哪些应用场景？

63、CyclicBarrier 有什么用？

64、CountDownLatch 有什么用？

65、CountDownLatch 与 CyclicBarrier 的区别？

66、Semaphore 有什么用？

67、Exchanger 有什么用？

68、LockSupport 有什么用？

69、Java 中原子操作的类有哪些？

70、什么是 ABA 问题？怎么解决？

71、Java 并发容器，你知道几个？

72、什么是阻塞队列？

73、阻塞队列有哪些常用的应用场景？

74、Java 中的阻塞的队列有哪些？

75、什么是幂等性？

IO（网络编程）
1、什么是 IO？

2、常用的 IO 类有哪些？

3、你怎么理解 IO、BIO、NIO、AIO？

4、什么是比特(Bit)、字节(Byte)、字符(Char)？

5、Java 有哪几种类型的流？

6、字节流和字符流的区别？

7、Java 序列化是什么？

8、怎么序列化一个对象？

9、Java 有哪两种序列化方式？

10、怎么控制类中的某些变量不被序列化？

11、静态变量能不能被序列化？

12、OSI 的七层模型都有哪些？

13、tcp 和 udp 协议的区别？

# 14、tcp 为什么要三次握手，两次不行吗？

- 第一次client 发送SYN包到server 客户端进入SYN_SEND状态
- 第二次server 收到SYN包后，回复一个ACK包，同时发送一个SYN包到client，服务端进入SYN_RECV状态
- 第三次client 收到server的SYN包后，回复一个ACK包，客户端进入ESTABLISHED状态，服务端收到ACK包后也进入ESTABLISHED状态
-
如果只有两次握手，那么client发送的SYN包丢失，server收不到，client会认为server没有收到，会再次发送SYN包，server收到后会再次进入SYN_RECV状态，这样就会导致资源浪费，所以需要三次握手
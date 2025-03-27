# 1. JAVA 基础题

* ## 抽象类与接口的区别？
- JAVA中的类只能单继承几个抽象类，但是可以实现多个接口
- 抽象类是一种 is-a的关系，比如鸟类是抽象类，麻雀继承自鸟类 ，接口表示一种 has-a 关系，比如麻雀实现 flyable接口，包含一个 fly()方法
* ## 分别讲讲 final，static，synchronized关键字可以修饰什么，以及修饰后的作用？
- final 修饰的关键字表示不可修改，final修饰的变量不可修改，final修饰的方法不可被子类重写，final修饰的类不可北继承
- static 可以修饰变量，静态方法不依赖类的实例对象调用，不可使用this(),super(),只能调用静态成员，静态代码块在类加载时只执行一次，静态内部类不需要通过外层实例调用，可直接通过类名访问
- synchronized关键字 1.修饰方法可以为方法加锁，保证多线程访问时同步

* ## 静态内部类和非静态内部类的区别
- 静态内部类只能访问外部类的静态属性和方法
- 调用静态内部类可以直接通过类名访问
- 静态内部类不持有外部类的引用，内存更独立不易发生内存泄露 非静态内部类持有外部类的实例引用，有内存泄露的风险
- 静态内部类可以做工具类比如 builder模式时候， 非静态内部类与外部类紧密联系，可以做事件监听器
* ## java传参时候是值传递还是引用传递
- 基本数据类型为值传递，传递的事数值的副本，不影响原变量
- 引用数据类型传递的事对象引用地址的副本，修改不影响原有对象，只改变引用指向对象
* ## JAVA 存在隐式转换
- char -> int  int->long long -> float float -> double

* ## Java 中深拷贝与浅拷贝的区别？
- 仅复制对象本身和其中的基本数据类型字段，对引用类型字段仅复制引用地址。
- 不仅复制对象本身，还递归复制其中的引用类型字段，生成新的对象副本。

* ## 什么是反射机制？反射机制的应用场景有哪些？
* - 反射机制是Java提供的一种运行时动态获取类信息并操作的机制，
* - Java序列化是一种反射，Android 中view 绑定，插件化

* ## 使用equals和==进行比较的区别，为什么要重写 equals()方法，为什么还要重写hashCode()？
- 基本数据类型 ==比较值 引用数据类型 ==比较内存地址
- equals() 默认比较对象地址 如果重写 equals() 可以实现内容比较
- 配合如 hashMap HashSet等集合类，如果不重写 equals() 和 hashcode()方法 会出现 内容相同,被认为是不同的对象的情况，保证一致性

* ## finally中的代码一定会执行吗？try里有return，finally还执行么
- 不一定总会执行，在程序死循环，crash后，或者System.exit() 就不会执行到这里
- try里有 return任然会执行 finally()

* ## Java异常机制中，异常Exception与错误Error区别
- 都是继承自Throwable类
- Exception表示程序运行中可以捕获处理的异常，error表示程序无法处理的异常
- 异常抛出后JVM可以继续运行 Error发生后 JVM崩溃

* ## 序列Parcelable,Serializable的区别？
- 序列化是将对象转换为字节楼的过程，以便于存储到文件或数据库，传输到网络或其他进程，恢复成对象的操作
- Parcelable是Android特有的序列化接口， 高效用于Android内的数据的传递，但是需要手动实现序列化和反序列化
- Serializable 性能较低，内存开销大，适合小数据持久化存储

* ## 为什么Intent传递对象为什么需要序列化？
- 转换为字节流以便于binder传输
- 内存地址无效，

* ## Synchronized修饰普通方法和静态方法的区别？什么是可见性?
* - Sync修饰的普通方法锁住的是对象实例，不同实例互不影响
* - 修饰静态方法锁住的是类的 Class对象 所有实例会共用同一把锁
* - 因为JMM中有缓存一致性机制原因，需要保证共享变量的修改能被其他线程立即察觉， volatile 和 sync 可以保证可见性，但是 volatile 不能保证原子性
* ## Synchronized在JDK1.6之后做了哪些优化
* - 偏向锁，无竞争的情况下，避免CAS操作，直接记录现成ID提升性能
* - 轻量级锁 多线程下尝试加锁，使用CAS竞争，失败则自旋
* - 竞争激烈时候使用OS级别的 mutex互斥锁，线程进入阻塞
* ## CAS无锁编程的原理，如何解决ABA 问题
* - 比如 AtomicInteger 内部先获取当前值，然后通过循环CAS将当前值与预期值比较，如果相等就更新，否则就继续自旋，如果更新后就返回最终更新的值
* - 基于 Compare and swap 比较并交换 基于底层硬件指令
* - 通过 AtomicStampedReference 增加版本号，记录修改，可以根据版本号防止误判
* ## AQS原理
* - AQS支持两种工作模式 一种是独占模式 一种是共享模式
* ## ReentrantLock的实现原理
* - 通过AQS实现的一种，支持可重入，支持公平锁，非公平锁的一种锁
* - 加锁过程是通过CAS竞争state 如果失败进入等待队列
* - 解锁过程通过 state--释放锁，唤醒队列
* - 默认非公平锁，提高性能，支持 公平锁、可中断、条件变量
* ## Synchronized的原理以及与ReentrantLock的区别。
* -synchronized 是依赖JVM级别的锁机制，基于对象头+监视器锁实现
* ## volatile关键字干了什么？什么叫指令重排，什么叫内存屏障
* ## volatile 能否保证线程安全？在DCL上的作用是什么？
* ## volatile和synchronize有什么区别？

* ## 请说一下HashMap 原理
- HashMap的底层数据结构包括 数组 链表 红黑树 当链表结构超过8时候自动转化为红黑树，提升查找效率
- HashMap的存储方式包括hash值得计算，确定数组的索引，检查是否有hash冲突，
- HashMap

* ## 请说一下HashMap与HashTable的区别
* - HashMap是非线程安全的，效率较高  HashTable是线程安全的，使用了 synchronized关键字修饰方法，效率较低
* - HashMap 基于数组+链表+红黑树的结构实现， HashTable基于 数组+链表
* - HashMap允许空键空值 HashTable不允许空键空值
* - hashMap 扩容位翻倍  hashTable位翻倍+1
* ## 谈一谈ArrayList的扩容机制？
- ArrayList 初始默认值10 当添加的元素超过数组容量时，触发扩容机制，将旧数据 ArrayCopy()复制到新数组中
- 新扩容的区域相当于原来的区域的1.5倍 扩容所需要的时间复杂度为  O(n)，会有GC压力
- 如何避免扩容带来的性能损耗， 预设容量，避免频繁调用 add() 如果涉及到频繁的增删，可以使用 LinkedList
* ## 请简述 LinkedHashMap 的工作原理和使用方式？
* ## 介绍一下 ConcurrentHashMap
* ## Java 中使用多线程的方式有哪些？说一下线程的几种状态？
* ## 谈谈线程死锁，如何有效的避免线程死锁？
* ## 什么是线程池？如何创建一个线程池？

* ## 谈一谈JAVA垃圾回收机制？
* ## 详细介绍一下 JAVA的GC root
* ## 详细介绍一下JAVA的类加载器
* ## 详细介绍一下JAVA的垃圾回收机制
* ## JVM、Dalvik、ART三者的原理和区别？
* ## 描述JVM类加载过程
* ## 请描述new一个对象的流程
* ## Java对象会不会分配到栈中？
* ## GC的流程是怎么样的？介绍下GC回收机制与分代回收策略
* ## Java中对象如何晋升到老年代？
* ## 判断对象是否被回收，有哪些GC算法，虚拟机使用最多的是什么算法？
* ## Class会不会回收？用不到的Class怎么回收？
* ## Java中有几种引用关系，它们的区别是什么？
* ## 描述JVM内存模型
* ## StackOverFlow与OOM的区别？分别发生在什么时候，JVM栈中存储的是什么，堆存储的是什么？
* ## PathClassLoader与DexClassLoader的区别是什么？
* ## 什么是双亲委托机制，为什么需要双亲委托机制？
* ## Android中加载类的方法有哪些？有什么区别？
* ## ClassNotFound的有可能的原因是什么？
* ## odex了解吗？解释型和编译型有什么区别？
* ## 说说反射的应用场景，哪些框架？
* ## 反射为什么慢？
* ## 动态代理是什么？如何实现？
* ## 动态代理的方法怎么初始化的？
* ## CGLIB动态代理


# 2. Android基础题

* ## Activity 与 Fragment 之间常见的几种通信方式？
* ## Activity生命周期？

- onCreate() -> onStart() -> onResume() -> onPause() -> onStop() -> onDetroy()

* ## Service生命周期？

- service 启动方式有两种，一种是通过startService()方式进行启动，另一种是通过bindService()
  方式进行启动。不同的启动方式他们的生命周期是不一样.

- 通过startService()这种方式启动的service，生命周期是这样：调用startService() --> onCreate()–>
  onStartConmon()–> onDestroy()。这种方式启动的话，需要注意一下几个问题，
- 第一：当我们通过startService被调用以后，多次在调用startService(),onCreate()
  方法也只会被调用一次，而onStartConmon()会被多次调用当我们调用stopService()的时候，onDestroy()
  就会被调用，从而销毁服务。
- 第二：当我们通过startService启动时候，通过intent传值，在onStartConmon()方法中获取值的时候，一定要先判断intent是否为null。

- 通过bindService()方式进行绑定，这种方式绑定service，生命周期走法：bindService–>onCreate()–>onBind()–>
  unBind()–>onDestroy()  bingservice 这种方式进行启动service好处是更加便利activity中操作service，比如加入service中有几个方法，a,b
  ，如果要在activity中调用，在需要在activity获取ServiceConnection对象，通过ServiceConnection来获取service中内部类的类对象，然后通过这个类对象就可以调用类中的方法，当然这个类需要继承Binder对象

* ## Activity的启动过程（不要回答生命周期）

- app启动的过程有两种情况，第一种是从桌面launcher上点击相应的应用图标，第二种是在activity中通过调用startActivity来启动一个新的activity。

-
我们创建一个新的项目，默认的根activity都是MainActivity，而所有的activity都是保存在堆栈中的，我们启动一个新的activity就会放在上一个activity上面，而我们从桌面点击应用图标的时候，由于launcher本身也是一个应用，当我们点击图标的时候，系统就会调用startActivitySately()
-
,一般情况下，我们所启动的activity的相关信息都会保存在intent中，比如action，category等等。我们在安装这个应用的时候，系统也会启动一个PackaManagerService的管理服务，这个管理服务会对AndroidManifest.xml文件进行解析，从而得到应用程序中的相关信息，比如service，activity，Broadcast等等，然后获得相关组件的信息。

- 当我们点击应用图标的时候，就会调用startActivitySately()方法，而这个方法内部则是调用startActivty()
- ,而startActivity()方法最终还是会调用startActivityForResult()这个方法。而在startActivityForResult()
- 这个方法。因为startActivityForResult()方法是有返回结果的，所以系统就直接给一个-1，就表示不需要结果返回了。

- 而startActivityForResult()这个方法实际是通过Instrumentation类中的execStartActivity()
-
方法来启动activity，Instrumentation这个类主要作用就是监控程序和系统之间的交互。而在这个execStartActivity()
-
方法中会获取ActivityManagerService的代理对象，通过这个代理对象进行启动activity。启动会就会调用一个checkStartActivityResult()
-
方法，如果说没有在配置清单中配置有这个组件，就会在这个方法中抛出异常了。当然最后是调用的是Application.scheduleLaunchActivity()
-
进行启动activity，而这个方法中通过获取得到一个ActivityClientRecord对象，而这个ActivityClientRecord通过handler来进行消息的发送，系统内部会将每一个activity组件使用ActivityClientRecord对象来进行描述，而ActivityClientRecord对象中保存有一个LoaderApk对象，通过这个对象调用handleLaunchActivity来启动activity组件，而页面的生命周期方法也就是在这个方法中进行调用。

* ## Broadcast注册方式与区别


* ## 谈一谈startService和bindService的区别，生命周期以及使用场景？
* ## 简单介绍下ContentProvider是如何实现数据共享的？
* ## App的启动流程
* - 用户点击图标，Launcher 调用AMS 启动Activity
* - 如果App进城存在，则直接启动对应Activity,如果应用进城不存在，则通过binder调用Zygote fork新进程
* - 新进程启动ActivityThread 通过attach()注册自己，然后AMS调用scheduleLauncherActivity()启动Activity
* - AMS通过 Instrumentation创建activity 并调用 onCrate()
* - Activity OnCreate()执行，OnResume()进入前台，ViewRootImpl通过 Choreogrother触发UI渲染
* - 最后呈现应用界面
* ## 说下切换横竖屏时Activity的生命周期?
* ## 说说Activity加载的流程？
* - 
* ## HandlerThread 的使用场景和用法？
* ## 谈谈 Handler 机制和原理？
* ## 试从源码角度分析Handler的post和sendMessage方法的区别和应用场景？
* - post方法实际上调用的事 sendMessage 方法，通过getPostMessage()存入callback中 最后存储消息队列中
* - 适合在线程切换中，执行异步任务
* - sendMessage() 直接把 message存储到MessageQueue中 用于消息分发，线程通信

* ## 详细介绍一下 Handler 消息屏障
- Handler 发送的消息分为两类 ，一类是同步消息 如SendMessage() 一类是异步消息 sendMessageAtFontOfQueue() 和Handler.setAsynchronous(true)
- 消息屏障是在消息队列中插入一个屏障，阻止同步消息的处理，确保异步消息的执行，
- 一般在Android 中，Choreographer
* ## 谈谈Android的事件分发机制？
* ## 谈谈自定义View的流程？
* ## 谈谈你对Android性能优化方面的了解？
* ## 自定义 Handler 时如何有效地避免内存泄漏问题？
* ## 谈谈Android中内存优化的方式？
* ## 谈谈你是如何优化App启动过程的？
* ## 请回答一下Android进程间的通信方式？
* ## 请简要谈谈Android系统的架构组成？
* ## 请简述一下 Android 7.0 的新特性？
* ## HttpClient与HttpUrlConnection的区别

- 此处延伸：Volley里用的哪种请求方式（2.3前HttpClient，2.3后HttpUrlConnection）
- 首先HttpClient和HttpUrlConnection
  这两种方式都支持Https协议，都是以流的形式进行上传或者下载数据，也可以说是以流的形式进行数据的传输，还有ipv6,以及连接池等功能。HttpClient这个拥有非常多的API，所以如果想要进行扩展的话，并且不破坏它的兼容性的话，很难进行扩展，也就是这个原因，Google在Android6.0的时候，直接就弃用了这个HttpClient.
-

而HttpUrlConnection相对来说就是比较轻量级了，API比较少，容易扩展，并且能够满足Android大部分的数据传输。比较经典的一个框架volley，在2.3版本以前都是使用HttpClient,在2.3以后就使用了HttpUrlConnection。

* ## java虚拟机和Dalvik虚拟机的区别

- Java虚拟机：

- java虚拟机基于栈。 基于栈的机器必须使用指令来载入和操作栈上数据，所需指令更多更多。
- java虚拟机运行的是java字节码。（java类会被编译成一个或多个字节码.class文件）
- Dalvik虚拟机：

- dalvik虚拟机是基于寄存器的
-

Dalvik运行的是自定义的.dex字节码格式。（java类被编译成.class文件后，会通过一个dx工具将所有的.class文件转换成一个.dex文件，然后dalvik虚拟机会从其中读取指令和数据

- 常量池已被修改为只使用32位的索引，以 简化解释器。
- 一个应用，一个虚拟机实例，一个进程（所有android应用的线程都是对应一个linux线程，都运行在自己的沙盒中，不同的应用在不同的进程中运行。每个android
  dalvik应用程序都被赋予了一个独立的linux PID(app_*)）

* ## 进程保活（不死进程）

- 此处延伸：进程的优先级是什么

* ## 讲解一下Context

-

Context是一个抽象基类。在翻译为上下文，也可以理解为环境，是提供一些程序的运行环境基础信息。Context下有两个子类，ContextWrapper是上下文功能的封装类，而ContextImpl则是上下文功能的实现类。

- 而ContextWrapper又有三个直接的子类，
  ContextThemeWrapper、Service和Application。其中，ContextThemeWrapper是一个带主题的封装类，而它有一个直接子类就是Activity，所以Activity和Service以及Application的Context是不一样的，只有Activity需要主题，Service不需要主题。Context一共有三种类型，分别是Application、Activity和Service。这三个类虽然分别各种承担着不同的作用，但它们都属于Context的一种，而它们具体Context的功能则是由ContextImpl类去实现的，因此在绝大多数场景下，Activity、Service和Application这三种类型的Context都是可以通用的。不过有几种场景比较特殊，比如启动Activity，还有弹出Dialog。
-

出于安全原因的考虑，Android是不允许Activity或Dialog凭空出现的，一个Activity的启动必须要建立在另一个Activity的基础之上，也就是以此形成的返回栈。而Dialog则必须在一个Activity上面弹出（除非是System
Alert类型的Dialog），因此在这种场景下，我们只能使用Activity类型的Context，否则将会出错。

- getApplicationContext()和getApplication()方法得到的对象都是同一个application对象，只是对象的类型不一样。
- Context数量 = Activity数量 + Service数量 + 1 （1为Application）


* ## 什么是冒泡排序？如何优化？

* ## 理解Activity，View,Window三者关系

-

这个问题真的很不好回答。所以这里先来个算是比较恰当的比喻来形容下它们的关系吧。Activity像一个工匠（控制单元），Window像窗户（承载模型），View像窗花（显示视图）LayoutInflater像剪刀，Xml配置像窗花图纸。

- > 1 Activity构造的时候会初始化一个Window，准确的说是PhoneWindow。
- > 2 这个PhoneWindow有一个“ViewRoot”，这个“ViewRoot”是一个View或者说ViewGroup，是最初始的根视图。
- > 3 “ViewRoot”通过addView方法来一个个的添加View。比如TextView，Button等
- > 4 这些View的事件监听，是由WindowManagerService来接受消息，并且回调Activity函数。比如onClickListener，onKeyDown等。

# 3. Kotlin

* ## 请简述一下什么是 Kotlin？它有哪些特性？
* ## Kotlin中实现单例的几种常见方式？
* ## Kotlin内置标准函数let的原理是什么？
* -inline实现的内联函数
* ## Kotlin inline crossinline noinline
* ## Kotlin高阶函数
* ## kotlin中 线程和协程有什么区别
* ## Kotlin中 作用域函数有哪些
* ## 在Kotlin中，什么是内联函数？有什么作用？
* ## 请谈谈Kotlin中的Coroutines，它与线程有什么区别？有哪些优点？
* ## 说说Kotlin中的Any与Java中的Object 有何异同？
* ## Kotlin中的数据类型有隐式转换吗？为什么？
* ## Kotlin中集合遍历有哪几种方式

# 4. 音视频

* ## 怎么做到直播秒开优化？
* ## 数字图像滤波有哪些方法？
* ## 图像可以提取的特征有哪些？
* ## FFMPEG:图片如何合成视频
* ## 常见的音视频格式有哪些？
* ## 请叙述MPEG视频基本码流结构？
* ## 说一说FFMPEG的数据结构？
* ## 如何降低延迟？如何保证流畅性？如何解决卡顿？解决网络抖动？
* ## 平时说的软解和硬解，具体是什么？

# 5.Flutter方面

* ## Dart 语言的特性？
* ## Dart 多任务如何并行的？
* ## dart是值传递还是引用传递？
* ## Flutter 特性有哪些？
* ## Widget 和 element 和 RenderObject 之间的关系？
* ## 使用mixins的条件是什么？
* ## Stream 两种订阅模式？
* ## Flutter中的Widget、State、Context 的核心概念？是为了解决什么问题？
* ## 说一下Hot Reload，Hot Restart，热更新三者的区别和原理
* ## Flutter 如何与 Android iOS 通信？
* ## 说一下什么是状态管理，为什么需要它？


# 7. Android Framework

 ---

* ## 你了解Android系统启动流程吗？
* - 按下电源键，电源管理芯片组给设备供电
* - 启动bootloader,在Uboot中初始化硬件资源，GPIO
* - 
* ## system_server 为什么要在 Zygote 中启动，而不是由 init 直接启动呢？
* - 
* ## Zygote 为什么不采用 Binder 机制进行 IPC 通信？
* - Zygote 是在 Binder 
* ## Binder有什么优势
* - 相对于传统的二次拷贝，一次拷贝提升了效率，并且减少了cpu和内存的消耗，适合移动端
* - 权限校验 保证只有合法的进程才能访问共享内存
* - 内存映射保证数据对齐，Parcel序列化，防止数据被篡改
* - binder采用引用技术，防止内存泄露
* ## 常规IPC的过程
* - 常规IPC通信中，如socket 一般通过 第一次拷贝需要从 用户态到内核态，也就是将用户数据拷贝到内核空间中
* - 第二次拷贝在接受进程， 从内核态到用户态
* - 大量数据传输时，性能明显下降
* ## Binder是如何做到一次拷贝的
* - binder驱动筒骨 mmap在内核空间创建内存共享区域，用户空间与内存空间共享同一块物理内存，实现零拷贝数据传输
* 
* ## MMAP的内存映射原理了解吗？
* - MMAP是Linux中用于内存文件映射或者设备映射，它将文件或设备映射到虚拟内存空间中，通过内存映射机制，可以在访问内存时候直接操作文件和设备，无需通过传统的 read() write()系统调用进行拷贝
* - MMAP核心字段  addr 映射的起始地址，传入NULL将由内核自动分配
* - length 4096
* ## 说说四大组件的通信机制
* - 可以通过Intent 跳转传递数据
* - 使用Bundle 传递数据
* - Messenger
* - EventBus
* ## 简述下 Handler 机制的总体原理？
* - Looper消息循环器 MessageQueue 消息队列存储等待处理的消息队列 Message 需要处理的消息  Handler 负责向 MessageQueue中发送消息并处理分发的消息
* - 
* ## Handler 或者说 Looper 如何切换线程？
* ## Handler、Message 和 Runnable 的关系如何理解？
* ## Handler 为什么可能导致内存泄露？如何避免？
* ## Handler在系统当中的应用
* ## ActivityManagerService是什么？什么时候初始化的？有什么作用？
* ## Instrumentation是什么？和ActivityThread是什么关系？
* ## ActivityManagerService和zygote进程通信是如何实现的。
* ## Android 应用的启动流程详解
- 1. 点击应用图标 AMS 检查应用进程是否存在，如果已经存在，启动对应Activity,如果不存在创建应用进程
- 2. Zygote  fork出应用进程 新进程会预加载Android核心类 比如 android.app* android.view.*下的 提高启动效率,
- 3. Zygote调用 RuntimeInit.main() ,初始化 ActivityThread



* #  8.网络方面
* ## 1. 浏览器输入地址到显示页面，经历了哪些过程

* # 9.第三方框架
* ## Eventbus 原理
* - EventBus 采用注解+反射的方式，实现一种发布订阅者的机制。内部通过HashMap维护事件表，


* ## 如何实现快排

（双指针划分）
i 从 左往右 找 大于 pivot 的元素。
j 从 右往左 找 小于 pivot 的元素。
交换 i 和 j 直到 i ≥ j，最后交换 pivot。
交换 i 和 j，保证 pivot 左侧全是小值，右侧全是大值。
适用于 大量重复数据，降低不必要的交换次数。

* ## 如何实现二分查找
二分查找是一种高效的查找算法，适用于有序数组，时间复杂度 O(log n)。
基本原理：
选取中间元素 作为基准 (mid)。
比较目标值 (target)：
target == mid，返回索引。
target < mid，缩小到左半部分。
target > mid，缩小到右半部分。
重复步骤 1-2，直到找到 target 或区间无效。


# 6. 算法方面

* ## 如何运⽤⼆分查找算法
* ## 如何去除有序数组的重复元素
* ## 如何在无线序列中随机抽取元素
* ## 如何判定括号合法性
* ## 如何寻找缺失和重复的元素

### **一、基础算法题**

**1. 数组与字符串**

- **两数之和**：给定一个数组和目标值，返回和为目标值的两个数的下标。（LeetCode 1）
- **反转字符串**：不使用额外空间，反转字符串中的所有字符。
- **移动零**：将数组中的所有 `0` 移到末尾，保持其他数字顺序不变。（LeetCode 283）
- **有效的括号**：判断括号字符串是否有效，包含 `()`、`{}`、`[]`。（LeetCode 20）
- **合并两个有序数组**：将两个有序数组合并为一个有序数组。（LeetCode 88）

**2. 链表**

- **反转链表**：反转单链表。（LeetCode 206）
- **合并两个有序链表**：将两个有序链表合并成一个新的有序链表。（LeetCode 21）
- **删除链表的倒数第 N 个节点**：单链表中删除倒数第 `N` 个节点。（LeetCode 19）
- **链表中环的检测**：判断链表中是否存在环。（LeetCode 141）

**3. 排序与查找**

- **二分查找**：在有序数组中查找目标值，返回索引。
- **冒泡排序**：实现冒泡排序算法。
- **快速排序**：实现快速排序算法。
- **合并区间**：将重叠区间合并，返回合并后的区间。（LeetCode 56）

---

### **二、中级算法题**

**1. 动态规划**

- **爬楼梯问题**：每次可以爬 1 或 2 阶，求到达第 `n` 阶的方法数。（LeetCode 70）
- **最大子数组和**：求数组中连续子数组的最大和。（LeetCode 53）
- **不同路径**：机器人从左上角到右下角的不同路径数量。（LeetCode 62）
- **零钱兑换**：给定硬币面值数组，求最少硬币数量凑成目标值。（LeetCode 322）

**2. 树与二叉树**

- **二叉树的最大深度**：求二叉树的最大深度。（LeetCode 104）
- **对称二叉树**：判断二叉树是否对称。（LeetCode 101）
- **二叉树的层序遍历**：按层级遍历二叉树。（LeetCode 102）
- **二叉树的最近公共祖先**：寻找两个节点的最近公共祖先。（LeetCode 236）

**3. 贪心算法**

- **分发糖果**：按照规则给小朋友分糖果，保证每个小朋友至少有一颗且分数高的孩子糖果更多。（LeetCode 135）
- **跳跃游戏**：判断是否能跳到数组最后一个位置。（LeetCode 55）
- **区间调度问题**：给定若干区间，选择最多不重叠的区间数量。

---

### 🚀 **三、高级算法题**

**1. 回溯法**

- **N 皇后问题**：在 `N × N` 棋盘上放置 `N` 个皇后，要求任何两个皇后不同行、列、斜线。（LeetCode 51）
- **电话号码的字母组合**：数字键盘对应字母的组合。（LeetCode 17）
- **全排列**：求数组的所有全排列。（LeetCode 46）
- **组合总和**：求和为目标值的所有组合。（LeetCode 39）

**2. 图算法**

- **岛屿数量**：计算二维网格中岛屿的数量。（LeetCode 200）
- **最短路径（Dijkstra 算法）**：计算图中从起点到其他点的最短路径。
- **拓扑排序**：给定有向图，求拓扑排序序列。
- **网络延迟时间**：求信号传播到所有节点的最短时间。（LeetCode 743）

**3. 动态规划进阶**

- **最长回文子串**：求字符串中最长的回文子串。（LeetCode 5）
- **编辑距离**：求将一个字符串转换成另一个字符串的最小操作次数。（LeetCode 72）
- **戳气球问题**：给定一排气球，每次戳破一个气球，求最大分数。（LeetCode 312）
- **打家劫舍 II**：房屋首尾相连，不能偷相邻房屋，求最大金额。（LeetCode 213）

---

### 🛠️ **附加题型**

- **位运算**
  - **只出现一次的数字**：数组中只有一个数字出现一次，其余数字出现两次，找到出现一次的数字。（LeetCode
    136）
  - **汉明距离**：两个整数的二进制表示不同位的数量。（LeetCode 461）

- **数学题**
  - **FizzBuzz**：打印 `1` 到 `n`，`3` 的倍数打印 `Fizz`，`5` 的倍数打印 `Buzz`，同时是 `3 和 5`
    的倍数打印 `FizzBuzz`。（LeetCode 412）
  - **整数反转**：将整数反转输出。（LeetCode 7）
  - **回文数**：判断整数是否为回文数。（LeetCode 9）

---












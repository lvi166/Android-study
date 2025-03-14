
# 1.JAVA的基本数据类型有哪些
 - JAVA的基本数据类型包括  byte(1字节 8位) shot(2字节 16位)  int(4字节 32位) long(8字节 64位) 
 - float(4字节 32位) double(8字节 64位)  
 - char(2字节 16位) boolean

# String StringBuilder StringBuffer
- String是 final修士的不可变的 一旦创建不可修改，一单进行拼接修改操作，会生成一个新的String对象，占用新内存
- StringBuffer 可变字符串，但是由于使用了 synchronized关键字进行同步操作，所以是线程安全的，但是新能略低与StringBuilder
- StringBuilder 是可变字符串，修改字符串不会创建对象线程不安全

# java的深拷贝和浅拷贝有什么区别
- 浅拷贝针对基本数据类型进行值传递，引用类型进行引用传递拷贝 使用对象的clone方法
- 深拷贝针对基本数据类型进行值传递，引用数据类型创建一个新的对象，并复制其内容，可以重写 clone方法，或者使用序列化

# Error和Exception的区别
- Error不可预见，会导致系统崩溃
- Exception 是可以预料的，通常可以用 try catch捕获 

# 介绍一下 HashMap
- HashMap的底层数据结构包括 数组 链表 红黑树 当链表结构超过8时候自动转化为红黑树，提升查找效率
- HashMap的存储方式包括hash值得计算，确定数组的索引，检查是否有hash冲突，
- 

# 介绍一下 ConcurrentHashMap


# 说一说 ArrayList的扩容机制
- ArrayList 初始默认值10 当添加的元素超过数组容量时，触发扩容机制，将旧数据 ArrayCopy()复制到新数组中
- 新扩容的区域相当于原来的区域的1.5倍 罗荣所需要的时间复杂度为  O(n)，会有GC压力
- 如何避免扩容带来的性能损耗， 预设容量，避免频繁调用 add() 如果涉及到频繁的增删，可以使用 LinkedList


# 介绍一下 GC ROOT


# 1.什么是Java的虚拟机
 - Java虚拟机包括 类加载器，

# 2.垃圾回收机制
 - Java虚拟机中垃圾回收机制通过算法和回收策略配合完成，Java虚拟机把内存区域分为新生代和老年代，新生代分为 Eden,Survivor From,Survivor to 三款

# 3.



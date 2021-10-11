
https://www.jianshu.com/p/fbe2320e57fa
* ## 1. Java中 == .equals() hashcode() 的区别
> == 对象对比两个对象在内存中的地址

> 默认的 .euqls() 是实现 == 。String类重写了 equals()方法先对比对象 然后再对比内容

> hashcode()方法返回对象的Hash码

* ## 2. int chat long 等等各占多少字节
- int 4个
- char 占 2个字节
- short 占两个字节
- float 4个字节
- long 占 8个字节
- double 8字节

* ## 3. int Integer 的区别
> int 是基本数据类型 Integer是int 的包装类
* ## 4. String StringBuilder StringBuffer 区别
- String 是final修饰的类是不可变的对象每次变化都要新建一个对象。字符串变化比较大不推荐使用。
- StringBuffer是线程安全的但是效率低
- StringBuilder 是线程不安全的但是效率高

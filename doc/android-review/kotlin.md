
# kotlin的基本数据类型有哪些
- byte short int long float double char boolean string

# kotlin val var 有什么区别
- val 申明的变量可读，不可修改
- var 申明的变量 可读可写

# kotlin处理空
- 安全调用运算符 ?.
- Elvis 运算符
- 非空断言

# kotlin中的 Elvis运算符是什么
- 可以提供默认值，在处理空时候非常有用

# kotlin 类型智能转换
- is 进行类型监察，

# kotlin 中列表和数组有什么区别
- 列表是一个有序集合，可以存储任何元素

# kotlin中高阶函数是什么
- 高阶函数可以接受其他函数作为参数 有的也可以把函数作为返回借过，他们将函数视为一等公民

# kotlin 协程和线程的区别
- 协程是轻量级的，它们在同一个线程中运行，可以在不阻塞的情况下挂起和恢复
- 协程不需要操作系统线程的上下文切换，更高效
- 线程相对协程是重量级的，

# kotlin中的密封类是什么，如何使用密封类
- 密封类是一个特殊的类，



# 1.什么是kotlin的作用域函数
- kotlin的作用域函数有五个，分别是 let run with apply also

# 2.什么是kotlin的冷流和热流
- kotlin 中 Flow
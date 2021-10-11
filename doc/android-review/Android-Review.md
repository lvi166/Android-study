## 复习题

### Android 基础

* 1 Activity的启动模式

Activity 有四种启动模式分别是

> standend
默认的启动方式 每次启动一个activity时候就会创造一个新的Activity，不管当前activity实例是否已经存在

> singleTop
当Activity设置成SingleTop 如果当前Activity实例已经存在于栈顶，则不会重新创建实例，同时调用activity的onNewIntent方法，如果当前Activity实例存在于非栈顶，则重新创建Activity实例放入栈顶

> singleTask
Activity设置为SingleTask之后 如果当前Activity实例位于栈顶，则类似SingleTop，不会创建实例，并调用onNewIntent()方法，如果当前Activity不是位于栈顶，则清空其上的所有activity实例，并重新回到栈顶

> singleInstance
属于SingleTask的加强型，设置singleInstance之后 activity实例只能单独运行在一个任务栈中，这个任务栈只有当前activity实例存在

* 2 Android中触发ANR的原因
> 1.在主线程做耗时操作，

> 2.应用未响应用户输入事件

> 3.BroadcastReceiver未在10秒内完成相关的处理

> 4.Service在特定的时间内无法处理完成 20秒

* 3.Android 内存泄漏
> 内存泄漏指的是一个无用对象长期占有内存得不到释放会造成内存泄漏 常见的内存泄漏有
> 1.单利类持有生命周期端的Context 

> 2.资源未释放 比如I/O流未关闭 数据库Cursor未关闭，比如动态注册的广播未释放等等

> 3.非静态内部类，比如Handler 造成的内存泄漏 Handler在Activity等销毁后可以调用removeCallbackAndMessage()方法清空消息队列。

* 4.Android 处理显示大图
> Android 图片处理一般是通过Bitmap 来处理的








~~## 复习题

### Android 基础

* ## Andrid四大组件

- Activity 生命周期 onCreate() onDestroy() onStart() onStop() onPase() onResume() onRestart()
- Service
- ContentProvider
- BroadCastReceiver

* ## Activity之间传值

> 通过Intent传值 大小限制在1M之内最好不要打过 512K
> 可以通过 EventBus

* ## Activity横竖屏切换

> 如果Activity需要配置 configChanges 配置完毕只走 onConfigChange();

* ## Activity上有Dialog的时候按Home键时的生命周期

> 按下Home键后 走哦onPause() onStop()

* ## 两个Activity 之间跳转时必然会执行的是哪几个方法？

> activity onstop onstart()

* ## Activity状态保存于恢复

- onSaveInstanceState() 用来保存Activity销毁前的数据

- onRestoreInstanceState() 用来恢复之前保存的值


* ## 1 Activity的启动模式

Activity 有四种启动模式分别是

> standend
> 默认的启动方式 每次启动一个activity时候就会创造一个新的Activity，不管当前activity实例是否已经存在

> singleTop
> 当Activity设置成SingleTop
>
如果当前Activity实例已经存在于栈顶，则不会重新创建实例，同时调用activity的onNewIntent方法，如果当前Activity实例存在于非栈顶，则重新创建Activity实例放入栈顶

> singleTask
> Activity设置为SingleTask之后
> 如果当前Activity实例位于栈顶，则类似SingleTop，不会创建实例，并调用onNewIntent()
> 方法，如果当前Activity不是位于栈顶，则清空其上的所有activity实例，并重新回到栈顶

> singleInstance
> 属于SingleTask的加强型，设置singleInstance之后 activity实例只能单独运行在一个任务栈中，这个任务栈只有当前activity实例存在

* ## 2 Android中触发ANR的原因

> 1.在主线程做耗时操作，

> 2.应用未响应用户输入事件

> 3.BroadcastReceiver未在10秒内完成相关的处理

> 4.Service在特定的时间内无法处理完成 20秒

* ## 3.Android 内存泄漏

> 内存泄漏指的是一个无用对象长期占有内存得不到释放会造成内存泄漏 常见的内存泄漏有
> 1.单利类持有生命周期端的Context

> 2.资源未释放 比如I/O流未关闭 数据库Cursor未关闭，比如动态注册的广播未释放等等

> 3.非静态内部类，比如Handler 造成的内存泄漏
> Handler在Activity等销毁后可以调用removeCallbackAndMessage()方法清空消息队列。

* ## 4.Android 处理显示大图

> Android 图片处理一般是通过Bitmap 来处理的比如加载一张50M的图如果允许压缩不考虑画质的预览图，可以先配置Bitmap.options
> ，计算出合适的 inSampleSize.缩放显示

> 如果图片不可缩放 可以使用 BitmapRegionDecoder 局部加载显示

* ## 5.Android 动画有哪些

> 帧动画 通过AnimationDrawable 实现，但是容易出现OOM的情况

> View动画 可以通过XML也可以通过代码实现一些 旋转 平移 放大缩小等

> 属性动画 相当于View动画的扩展实现更多的效果，包括ObjectAnimator,ValuetAnimator,AnimatorSet
> ,可以作用域任何对象，

> 属性动画中有时间插值器 就是根据时间流逝来改变属性变化的百分比，比如加速 减速 匀速插值器

> 属性动画类型估值器 是根据属性改变的百分比来计算之后变化的属性值 如整形 浮点 色值 估值器

* ## 6.Android 中有多少个Context

> 每一个Activity 和Service 就是一个Context 加上一个 Application Activity和Service继承自
> ContextThemeWapper, Application继承自ContextWapper

* ## 7 Android每一次版本更新的变化

> Android 6.0 新特性

- 动态权限管理，

> Android 7.0 新特性

- 应用间共享文件被限制 需要通过FileProvider进行授权
- 删除了几个系统广播 如网络变化
- 多窗口支持
- v2签名
- 夜间模式

> Android 8.0 新特性

- 优化通知 通知需要绑定Channel
- 后台优化
- 后台service 需要绑定通知 还需要在Androidmanifest中添加 ForgService 权限

> Android 9.0 新特性

- 刘海屏
- 对明文网络限制 需要配置 networkSecurityConfig

* ## 8. Android  Handler机制

> Android Handler 机制主要包括几个角色

- message 消息的载体
- MessageQuene 单链表的消息队列负责消息的管理
- Looper 消息循环器 负责从 messageQuene中获取消息，并发送到对应的接受者
- handler 消息的处理器 负责接受和发送消息

> Looper 位置一个无限for循环，不断的从messageQuene中去取message 一旦messageQuene为空则阻塞

* ## 9.Android 自定义View流程

- 自定义View 首先继承自View 重写构造方法，如果有自定义属性
- 通过onMeasure测量View大小 涉及到MeasureSpec类，通过 MeasureSpac类获取 SpecMode SpecSize
  MeasureSpac是一个32位的int 值 高两位是 SpacMode 低30位是SpacSize
- SpecMode 中 AT_MOST相当于 warp_content EXACTLY 相当于 match_parent UNSPECIFIED 就是要多大给多大不受父容器控制
- onSizeChanged 确定View的大小 一半情况下 onMeasure()能确定view的大小 但是view收到父控件的影响，我们还需要重写
  onSizeChanged();
-


* ## JNI 静态注册和动态注册的区别

* ## JNI 的数据类型有哪些

* ## 11. 请描述一次完整的Binder通信过程，

- client端向serviceManager查询服务，找到目标服务的binder代理对象
- client获取 binder 代理对象
- client 调用binder.transact()方法，将数据传递给service`

* ## 12. 选择binder的原因

- 安全性 binder基于 身份认证机制，双方通过binder 驱动验证，放置进程劫持
- 高效性 binder采用单次拷贝 内存映射的方式，提高了数据传输的小吕，也兼顾了移动端设备的性能
- 简洁性，binder通过 servicemanager 进行全局管理，只需要通过AIDL接口调用，系统自动处理底层通信逻辑

* ## 12. Android 事件分发机制

- Activity 接受到事件，并调用到dispatchTouchEvent()方法 一般情况下，Activity会将事件传递给Window,Window传给DecorView
- DecorView 会将事件传递给ViewGroup
    - viewGroup 调用onInterceptTouchEvent()方法判断是否拦截事件，如果拦截则调用自己的onTouchEvent()
      方法，如果不拦截则调用子View的dispatchTouchEvent()方法
    - 子View 会调用自己的onTouchEvent()方法，如果返回false则会调用父View的onTouchEvent()方法
    - 子View 会调用自己的onTouchEvent()方法，如果返回true处理 ACTION_DOWN ACTION_MOVE ACTION_UP 事件

* ## 13. Android 事件分发机制
    * ## 14. Android 事件分发机制
      *~~ 








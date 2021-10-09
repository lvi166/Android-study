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



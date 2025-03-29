
## Android进阶

- ### 什么是NDK
NDK 是 Native Development Kit 是Native开发工具包，用于快速开发 C C++的动态库，
- ### 什么是JNI 
Java Native Interferce 是Java和 C C++代码交流的接口，
- ### Java 如何调用 C C++ 语言
  - 静态方法注册 JNI函数，新建Java类 声明Native方法，变异成 .class文件，然后通过 Javah命令生成头文件，然后创建相应的源文件，缺点是函数名比较长，不灵活
  - 动态注册 通过JNINativeMethod保存对应关系，最后通过JNi_Onload函数动态注册，动态注册灵活便于修改包名 类名
- ### JNI 签名
  - 类型签名
  - 方法签名 Java支持函数重载，所以引用了方法签名，利用方法签名和方法名，来唯一确定一个 JNI函数的调用
- ### Android之间进程通信
    - 文件（FileLock）
    - AIDL 基于Binder
    - Messenger 基于Binder
    - ContentProvider （基于Binder）
    - SOcket 
- ### Binder机制
Binder 是Android提供的一种 IPC框架，用来多进程之间发送消息，同步和共享内存。
Android Binder机制是一种 client Server 结构，由 client Server ServiceManager Binder驱动构成
- 第一步获取服务，cliend 需要请求服务，通过ServiceManager 从 svc_list中查抄相关服务
- 第二步 注册服务，service 通过ServiceManagerNative


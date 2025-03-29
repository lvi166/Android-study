#设置 cmake 最低版本和项目名称
cmake_minimum_required(VERSION 3.10.2)
project("native-lib")

# 指定生成的 so 库的名称

file(GLOB_RECURSE stu src/main/jni/stu/*.c)

add_library(
        native-lib  # 库名，对应 System.loadLibrary("native-lib") 中的名字
        SHARED # 库的类型，SHARED 表示动态库，STATIC 表示静态库
        native-lib.cpp # 源文件
        ${stu} # 引用的其他源文件
)

# 查找系统库 log，并赋值给变量 log-lib
find_library(
        log-lib
        log
)

# 链接库
target_link_libraries(
        native-lib
        ${log-lib}
)
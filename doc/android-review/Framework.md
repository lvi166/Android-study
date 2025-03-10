# Android 设备的启动流程是什么
 
 - Android设备按下电源键之后 电源管理芯片为主板的CPU,内存等核心硬件供电，触发硬件自检
 - SOC内置的 BootRoom代码启动，初始化时钟，GPIO等底层硬件，并加载bootloader到内存中
 - Bootloader 第一阶段检测 RAM可用性，初始化存储控制器，为加载SPL做准备
 - Bootloader SPL阶段，加载linux内核镜像到内存中，初始化显示器，文件系统，虚拟内存
 - Linux内核启动
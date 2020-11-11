package com.springboot.utils;

public class SystemUtil {

	public static void main(String[] args) {
		// 1、获取系统当前毫秒值
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		System.out.printf("程序运行时间为[%d]毫秒！", (end - start));

		// 2、结束正在运行的Java程序
		/*int counts = 0;
		while (true) {
			System.out.println("yinzhengjie");
			if (counts == 5) {
				System.exit(0);
			}
			counts++;
		}*/
		
		// 3、垃圾回收器 清除垃圾时，会默认调用被清空对象的finalize方法。
		System.gc();
		
		// 4、确定当前的系统属性
		// java.version Java 运行时环境版本
		// java.vendor Java 运行时环境供应商
		// java.vendor.url Java 供应商的 URL
		// java.home Java 安装目录
		// java.vm.specification.version Java 虚拟机规范版本
		// java.vm.specification.vendor Java 虚拟机规范供应商
		// java.vm.specification.name Java 虚拟机规范名称
		// java.vm.version Java 虚拟机实现版本
		// java.vm.vendor Java 虚拟机实现供应商
		// java.vm.name Java 虚拟机实现名称
		// java.specification.version Java 运行时环境规范版本
		// java.specification.vendor Java 运行时环境规范供应商
		// java.specification.name Java 运行时环境规范名称
		// java.class.version Java 类格式版本号
		// java.class.path Java 类路径
		// java.library.path 加载库时搜索的路径列表
		// java.io.tmpdir 默认的临时文件路径
		// java.compiler 要使用的 JIT 编译器的名称
		// java.ext.dirs 一个或多个扩展目录的路径

		// os.name 操作系统的名称
		// os.arch 操作系统的架构
		// os.version 操作系统的版本

		// file.separator 文件分隔符（在 UNIX 系统中是“/”）
		// path.separator 路径分隔符（在 UNIX 系统中是“:”）
		// line.separator 行分隔符（在 UNIX 系统中是“/n”）
		// user.name 用户的账户名称
		// user.home 用户的主目录
		// user.dir 用户的当前工作目录

		System.out.println(System.getProperties());
		//5、System类方法复制数组
		/**
		 * 用来实现将源数组部分元素复制到目标数组的指定位置。各个参数功能如下： 
		 * Object src：要复制的原数组； 
		 * Int srcPos：数组源的起始索引； 
		 * Object dest：复制后的目标数组； 
		 * int destPos：目标数组起始索引； 
		 * int length，指定复制的长度；
		 */
		 int[] src = {1,22,333,4444,5555,666666,7777777};
        int[] dest = {10,20,30};
        System.arraycopy(src, 2, dest, 0, 2);

        for(int i=0;i<dest.length;i++) {
            System.out.println(dest[i]);
        }
		

	}

}

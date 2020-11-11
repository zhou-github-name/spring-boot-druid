package com.springboot.test;

/**
 * java中的按值调用
 * @author zejian
 */
public class CallByValueTest {

    private static int x=10;

    public static void updateValue(int value){
        value = 3 * value;
    }

    public static void main(String[] args) {
		/**
		 * 分析：
		 * 1）value被初始化为x值的一个拷贝（也就是10）
		 * 2）value被乘以3后等于30，但注意此时x的值仍为10！
		 * 3）这个方法结束后，参数变量value不再使用，被回收。
		 */
        System.out.println("调用前x的值："+x);
        updateValue(x);
        System.out.println("调用后x的值："+x);
    }

}


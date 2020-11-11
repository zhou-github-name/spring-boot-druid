package com.springboot.jdk.v8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * Java 8系列之Stream中万能的reduce
 * 
 * @author E102535
 *
 */
public class ReduceMain {
	/**
	 * reduce 操作可以实现从Stream中生成一个值，其生成的值不是随意的，而是根据指定的计算模型。比如，之前提到count、min和max方法，
	 * 因为常用而被纳入标准库中。事实上，这些方法都是reduce操作。
	 * reduce方法有三个override的方法：
	 * 	Optional<T> reduce(BinaryOperator<T> accumulator); T reduce(T identity,
	 * 	BinaryOperator<T> accumulator); <U> U reduce(U identity,BiFunction<U, ?
	 * 	super T, U> accumulator,BinaryOperator<U> combiner);
	 */
	
	public static void main(String[] args) {
		//变形1，未定义初始值，从而第一次执行的时候第一个参数的值是Stream的第一个元素，第二个参数是Stream的第二个元素
		Optional<Integer> accResult1 = Stream.of(1, 2, 3, 4).reduce((acc, item) -> {
			System.out.println("acc : " + acc);
			System.out.println("item: " + item);
			acc += item;
			System.out.println("acc+ : " + acc);
			System.out.println("--------");
			return acc;
		});
		System.out.println("accResult1: " + accResult1.get());
		System.out.println("--------");
		
		//变形2，定义了初始值，从而第一次执行的时候第一个参数的值是初始值，第二个参数是Stream的第一个元素
		int accResult2 = Stream.of(1, 2, 3, 4).reduce(2, (acc, item) -> {
			System.out.println("acc : " + acc);
			System.out.println("item: " + item);
			acc += item;
			System.out.println("acc+ : " + acc);
			System.out.println("--------");
			return acc;
		});
		System.out.println("accResult2: " + accResult2);
		System.out.println("--------");
		
		//变形3
		List<Integer> accResult3 = Stream.of(1, 2, 3, 4)
		        .reduce(new ArrayList<Integer>(),
		                new BiFunction<List<Integer>, Integer, List<Integer>>() {
		                    @Override
		                    public List<Integer> apply(List<Integer> acc, Integer item) {
		                        acc.add(item);
		                        System.out.println("item: " + item);
		                        System.out.println("acc+ : " + acc);
		                        System.out.println("BiFunction");
		                        return acc;
		                    }
		                }, new BinaryOperator<List<Integer>>() {
		                    @Override
		                    public List<Integer> apply(List<Integer> acc, List<Integer> item) {
		                       /* System.out.println("BinaryOperator");
		                        acc.addAll(item);
		                        System.out.println("item: " + item);
		                        System.out.println("acc+ : " + acc);
		                        System.out.println("--------");*/
		                        return acc;
		                    }
		                });
		System.out.println("accResult3: " + accResult3);
	}

}

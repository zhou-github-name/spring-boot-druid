package com.springboot.jdk.v8;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import com.springboot.jdk.assist.IntegerSum;

/**
 * Java 8系列之重构和定制收集器
 * @author E102535
 *
 */
public class CustomizeCollector {
	
	public static void main(String[] args) {
		Integer integerSum = Stream.of(1, 2, 3, 4)
		        .collect(new Collector<Integer, IntegerSum, Integer>() {
		            @Override
		            public Supplier<IntegerSum> supplier() {
		                return () -> new IntegerSum(2);
		            }

		            //将元素添加到结果容器
		            @Override
		            public BiConsumer<IntegerSum, Integer> accumulator() {
		                return IntegerSum::doSum;
		            }

		            //将两个结果容器合并为一个结果容器
		            @Override
		            public BinaryOperator<IntegerSum> combiner() {
		                return IntegerSum::doCombine;
		            }

		            //对结果容器作相应的变换
		            @Override
		            public Function<IntegerSum, Integer> finisher() {
		                return IntegerSum::toValue;
		            }

		            @Override
		            public Set<Characteristics> characteristics() {
		                Set<Collector.Characteristics> CH_NOID = Collections.emptySet();
		                return CH_NOID;
		            }
		        });
		System.out.println("integerSum: " + integerSum); // 打印结果：integerSum: 18
	}

}

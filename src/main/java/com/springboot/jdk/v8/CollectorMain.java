package com.springboot.jdk.v8;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * 收集器
 * @author E102535
 *
 */
public class CollectorMain {
	
	/**
	 * Collector是Stream的可变减少操作接口，可变减少操作包括：将元素累积到集合中，使用StringBuilder连接字符串;计算元素相关的统计信息，
	 * 例如sum，min，max或average等。Collectors(类收集器)提供了许多常见的可变减少操作的实现。
	 * 
	 * Collector<T, A, R>接受三个泛型参数，对可变减少操作的数据类型作相应限制：
	 * T：输入元素类型;
	 * A：缩减操作的可变累积类型（通常隐藏为实现细节）;
	 * R：可变减少操作的结果类型;
	 * 
	 * Collector接口声明了4个函数，这四个函数一起协调执行以将元素目累积到可变结果容器中，并且可以选择地对结果进行最终的变换.
	 * Supplier<A> supplier(): 创建新的结果结;
	 * BiConsumer<A, T> accumulator(): 将元素添加到结果容器;
	 * BinaryOperator<A> combiner(): 将两个结果容器合并为一个结果容器;
	 * Function<A, R> finisher(): 对结果容器作相应的变换;
	 * 
	 * 在Collector接口的characteristics方法内，可以对Collector声明相关约束
	 * Set<Characteristics> characteristics():
	 * 
	 * 而Characteristics是Collector内的一个枚举类，声明了CONCURRENT、UNORDERED、IDENTITY_FINISH等三个属性，用来约束Collector的属性。
	 * CONCURRENT：表示此收集器支持并发，意味着允许在多个线程中，累加器可以调用结果容器;
	 * UNORDERED：表示收集器并不按照Stream中的元素输入顺序执行;
	 * IDENTITY_FINISH：表示finisher实现的是识别功能，可忽略。
	 * 
	 * 注：如果一个容器仅声明CONCURRENT属性，而不是UNORDERED属性，那么该容器仅仅支持无序的Stream在多线程中执行。
	 * 
	 */
	
	/**
	 * 基于Collector工具库
	 *在Collector工具库中，声明了许多常用的收集器,以供我们快速创建一个收集器。前面我们已经了解到，收集器函数必须满足身份约束和相关项约束。
	 *而基于Collector实现简化的库（如Stream.collect（Collector））创建收集器时，必须遵守以下约束：
	 *第一个参数传递给accumulator()函数，两个参数都传递给combiner()函数，传递给finisher()函数的参数必须是上一次调用supplier()，accumulator()或combiner()函数的结果。
	 *实现不应该对任何accumulator()，combiner()或finisher()函数的结果做任何事情，除非收集器将返回的结果返回给调用者。
	 *如果结果传递到combiner()或finisher()函数，而且返回对象与传入的不相同，则不会再将对象传递给accumulator()函数调用。
	 *一旦结果传递到combiner()或finisher()函数，它就不会再次传递到accumulator()函数。
	 *对于串行收集器，supplier()，accumulator()或combiner()函数返回的任何结果必须是限制串行的。这使得收集器可以并行进行，而收集器不需要执行任何额外的同步。reduce操作实现必须管理Stream的元素被正确区别并分别处理，并且仅在累积完成之后，对累加器中的数据合并。
	 *对于并发收集器，实现可以自由地（但不是必须）同时实现reduce操作。accumulator()可以在多个线程同时调用，而不是在累积期间保持结果的独立性。仅当收集器具有Collector.Characteristics.UNORDERED特性或者原始数据是无序的时才应用并发还原。
	 */

	
	public static void main(String[] args) {
		//1、toList
		List<Integer> collectList = Stream.of(1, 2, 3, 4)
		        .collect(Collectors.toList());
		System.out.println("collectList: " + collectList);
		// 打印结果
		// collectList: [1, 2, 3, 4]
		
		//2、toSet
		Set<Integer> collectSet = Stream.of(1, 2, 3, 4)
		        .collect(Collectors.toSet());
		System.out.println("collectSet: " + collectSet);
		// 打印结果
		// collectSet: [1, 2, 3, 4]
		
		//3、toCollection
		
		//4、toMap
		
		//5、转成值
		/**
		 * 使用collect可以将Stream转换成值。maxBy和minBy允许用户按照某个特定的顺序生成一个值。
		 * averagingDouble:求平均值，Stream的元素类型为double
		 * averagingInt:求平均值，Stream的元素类型为int
		 * averagingLong:求平均值，Stream的元素类型为long
		 * counting:Stream的元素个数
		 * maxBy:在指定条件下的，Stream的最大元素
		 * minBy:在指定条件下的，Stream的最小元素
		 * reducing: reduce操作
		 * summarizingDouble:统计Stream的数据(double)状态，其中包括count，min，max，sum和平均。
		 * summarizingInt:统计Stream的数据(int)状态，其中包括count，min，max，sum和平均。
		 * summarizingLong:统计Stream的数据(long)状态，其中包括count，min，max，sum和平均。
		 * summingDouble:求和，Stream的元素类型为double
		 * summingInt:求和，Stream的元素类型为int
		 * summingLong:求和，Stream的元素类型为long
		 */
		Optional<Integer> collectMaxBy = Stream.of(1, 2, 3, 4)
	            .collect(Collectors.maxBy(Comparator.comparingInt(o -> o)));
		System.out.println("collectMaxBy:" + collectMaxBy.get());
		// 打印结果
		// collectMaxBy:4
		
		//6、partitioningBy、groupingBy根据条件分割数据块
		Map<Boolean, List<Integer>> collectParti = Stream.of(1, 2, 3, 4)
	            .collect(Collectors.partitioningBy(it -> it % 2 == 0));
		System.out.println("collectParti : " + collectParti);
		// 打印结果
		// collectParti : {false=[1, 3], true=[2, 4]}
		
		Map<Object, List<Integer>> collectGroup= Stream.of(1, 2, 3, 4)
	            .collect(Collectors.groupingBy(it -> it > 3));
		System.out.println("collectGroup : " + collectGroup);
		// 打印结果
		// collectGroup : {false=[1, 2, 3], true=[4]}
		
		//按自定义格式对数据集合List进行分组
		//List.stream().collect(Collectors.groupingBy(对象Vo -> {分组自定义标志字段}))
		/*List<TestRecord> TestRecordsByMonthList = mapper.selectTestRecordByCondition(plantId, startDate, endDate);
		Map<String, List<TestRecord>> mapGroupByMonth = TestRecordsByMonthList .stream().collect(Collectors.groupingBy(testRecord -> {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		    return sdf.format(testRecord.getPlanSamplingTime());
		}));*/

		//常规方式，按数据集合List内元素特定字段进行分组
		//List.stream().collect(Collectors.groupingBy(对象Vo::分组标志字段))
		/*List<Equipment> equipmentList = operateCenterMapper.selectEquipmentFailureGroupByOrgId(plantId,startDate,endDate);
		Map<String, List<Equipment>> mapGroupByOrg = equipmentList.stream().collect(Collectors.groupingBy(Equipment::getOrgId));*/
		
		//7、拼接字符串
		String strJoin = Stream.of("1", "2", "3", "4")
		        .collect(Collectors.joining(",", "[", "]"));
		System.out.println("strJoin: " + strJoin);
		// 打印结果
		// strJoin: [1,2,3,4]
		
		Map<Boolean, Long> partiCount = Stream.of(1, 2, 3, 4)
		        .collect(Collectors.partitioningBy(it -> it.intValue() % 2 == 0,
		                Collectors.counting()));
		System.out.println("partiCount: " + partiCount);
		// 打印结果
		// partiCount: {false=2, true=2}
		
	}
	
}

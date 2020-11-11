package com.springboot.jdk.v8;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.springboot.common.entity.User;

/**
 * JDK8新特性之Optional
 * 
 * @author E102535
 *
 */
public class OptionalMain<T> {

	private final T value = null;

	// Optional常用方法
	/**
	 * of 为value创建一个Optional对象，如果value为空则 会报出NullPointerException异常。
	 */

	/*public static <T> Optional<T> of(T value) {
		return new Optional<>(value);
	}*/

	/**
	 * ofNullable 为value创建一个Optional对象，但可以允许value为null值。
	 */
	/*public static <T> Optional<T> ofNullable(T value) {
		return value == null ? empty() : of(value);
	}*/

	/**
	 * isPresent 判断当前value是否为null,如果不为null则返回true，否则false。
	 */
	public boolean isPresent() {
		return value != null;
	}

	/**
	 * ifPresent 如果不为null值就执行函数式接口的内容。
	 */
	public void ifPresent(Consumer<? super T> consumer) {
		if (value != null)
			consumer.accept(value);
	}

	/**
	 * get 返回当前的值，如果为空则报异常。
	 */
	public T get() {
		if (value == null) {
			throw new NoSuchElementException("No value present");
		}
		return value;
	}

	/**
	 * orElse 返回当前值，如果为null则返回other。
	 */
	public T orElse(T other) {
		return value != null ? value : other;
	}

	/**
	 * orElseGet orElseGet和orElse类似，只是orElseGet支持函数式接口来生成other值。
	 */
	public T orElseGet(Supplier<? extends T> other) {
		return value != null ? value : other.get();
	}

	/**
	 * orElseThrow 如果有值则返回，没有则用函数式接口抛出生成的异常。
	 */
	public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
		if (value != null) {
			return value;
		} else {
			throw exceptionSupplier.get();
		}
	}

	public static void main(String[] args) {
		testOf();
		testNullable();
	}

	private static void testNullable() {
		User user = null;
		User john = new User("john", "18");
		User dick = new User("dick", "12");
		System.out.println(Optional.ofNullable(user).orElse(john));
		System.out.println(Optional.ofNullable(john).get());
		System.out.println(Optional.ofNullable(dick).orElse(john));
		System.out.println(Optional.ofNullable(user).orElseGet(() -> john));
		System.out.println();
	}

	private static void testOf() {
		try {
			User user1 = new User();
			Optional<User> userOptional1 = Optional.of(user1);
			if (userOptional1.isPresent()) {
				System.out.println("user is not null");
			}
			User user2 = null;
			Optional<User> userOptional2 = Optional.of(user2);
			// NullPointerException
			if (userOptional2.isPresent()) {
				System.out.println("user is not null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

}

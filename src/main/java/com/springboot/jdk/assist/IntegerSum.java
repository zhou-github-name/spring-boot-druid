package com.springboot.jdk.assist;

public class IntegerSum {

	private Integer sum;

	public IntegerSum(Integer sum) {
		this.sum = sum;
	}

	public IntegerSum doSum(Integer item) {
		if (item % 2 == 0) {
			this.sum += item * 2;
		} else {
			this.sum += item;
		}
		return this;

	}

	public IntegerSum doCombine(IntegerSum it) {
		this.sum += it.sum;
		return this;
	}

	public Integer toValue() {
		return this.sum;
	}

}

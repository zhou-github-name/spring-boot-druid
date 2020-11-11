package com.springboot.utils;

import java.util.ArrayList;
import java.util.List;

import com.springboot.common.entity.User;

public class UtilityUtils {
	
	/**
	 * 判断一个list里某个字段是否全相等
	 */
	public static String isEqual(List<User> list) {
		if (list != null) {
			List<Integer> listDay = new ArrayList<Integer>();
			for (User user : list) {
				Integer day = user.getId();
				listDay.add(day);
			}
			Integer firstspDay = listDay.get(0);
			for (Integer spDay : listDay) {
				if (!spDay.equals(firstspDay)) {
					System.out.println("有一个不相等，返回-->false");
					return "false";
				}
			}
			return "true"; // 循环完没有找到相等的返回true
		}else{
			return null; // 为空返回false
		}
	}
	
	public static void main(String[] args) {
		List<User> list = new ArrayList<>();
		User attendDay1 = new User();
		attendDay1.setId(28);
		list.add(attendDay1);
		User attendDay2 = new User();
		attendDay2.setId(28);
		list.add(attendDay2);
		User attendDay3 = new User();
		attendDay3.setId(29);
		list.add(attendDay3);
		System.err.println(isEqual(list));
	}

}

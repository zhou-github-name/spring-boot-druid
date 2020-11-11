package com.springboot.utils;



/**
 * @description: 返回的错误码枚举类
 */

public enum ResultEnum {

    SUCCESS("101","成功"),
    FAILURE("102","失败"),
    USER_NEED_AUTHORITIES("201","用户未认证"),
    USER_LOGIN_FAILED("202","用户账号或密码错误"),
    USER_LOGIN_PASSWORD_FAILED("203","用户密码错误"),
    USER_LOGIN_FAILED_DISABLE("2021","账户被禁用，请联系管理员"),
    USER_LOGIN_FAILED_NOT_FOUND_ROLE("2022","用户没有分配权限角色，无法使用系统"),
    USER_LOGIN_FAILED_OTHER("2029","验证失败"),
    USER_LOGIN_SUCCESS("200","用户登录成功"),
    USER_NO_ACCESS("204","权限不足，无法访问该资源"),
    USER_LOGOUT_SUCCESS("205","用户登出成功"),
    TOKEN_IS_BLACKLIST("206","此token为黑名单"),
    LOGIN_IS_OVERDUE("207","登录已失效"),
    TOEKN_IS_OVERDUE("2071","token 已过期"),
    TOEKN_IS_FAILED("2072","token 验证失败"),
    TOEKN_IS_NO_NULL("2072","token 不能为空"),
    URL_IS_NOT_FOUNT("404","访问地址不存在");

	private String code;

	private String message;

	ResultEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * @deprecation:通过code返回枚举
	 */
	public static ResultEnum parse(String code) {
		ResultEnum[] values = values();
		for (ResultEnum value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		throw new RuntimeException("Unknown code of ResultEnum");
	}
}

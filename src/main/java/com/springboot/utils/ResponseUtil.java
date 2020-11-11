package com.springboot.utils;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @Description: 返回结果工具类 用于封装返回结果，返回json
 * @Author: 
 * @Date: 
 */
public class ResponseUtil {
	private final static Logger log = LoggerFactory.getLogger(ResponseUtil.class);

	/**
	 * 使用response输出JSON
	 * 
	 * @param response
	 * @param resultMap
	 */
	public static void out(ServletResponse response, Map<String, Object> resultMap) {

		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			out = response.getWriter();
			out.println(JSON.toJSONString(resultMap));
		} catch (Exception e) {
			log.error("输出JSON出错", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 使用response输出JSON
	 * 
	 * @param response
	 */
	public static void out(ServletResponse response, ResultEnum respCode, String jwtToken) {

		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			out = response.getWriter();
			out.println(JSON.toJSONString(result(respCode, jwtToken)));
		} catch (Exception e) {
			log.error("输出JSON出错", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 使用response输出JSON
	 * 
	 * @param response
	 */
	public static void out(ServletResponse response, ResultEnum respCode) {

		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			out = response.getWriter();
			out.println(JSON.toJSONString(result(respCode)));
		} catch (Exception e) {
			log.error("输出JSON出错", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	public static Map<String, Object> resultMap(Integer code, String msg) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", code);
		resultMap.put("message", msg);
		return resultMap;
	}

	public static Map<String, Object> resultMap(Integer status, String msg, Object data) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", status);
		resultMap.put("message", msg);
		resultMap.put("data", data);
		return resultMap;
	}

	/**
	 * 返回状态吗 成功
	 * 
	 * @param respCode
	 * @param jwtToken
	 * @return
	 */
	public final static GeneralResponse<String> result(ResultEnum respCode, String jwtToken) {
		return GeneralResponse.success(jwtToken);
	}

	/**
	 * 失败
	 * 
	 * @param respCode
	 * @return
	 */
	private static GeneralResponse<String> result(ResultEnum respCode) {
		return GeneralResponse.fail(respCode.getCode(), respCode.getMessage(), respCode.getMessage());
	}

}

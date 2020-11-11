package com.springboot.utils;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 响应报文(统一规范的返回类
 *
 * @param <T>
 */
@JsonPropertyOrder(value = {"code", "msg", "desc", "data"})
public class GeneralResponse<T> implements Serializable {

    private static final long serialVersionUID = 6405629435684529060L;

    // 通用错误码-成功
    public static final String CODE_SUCCESS = "0000";

    private String code = CODE_SUCCESS; // 成功,默认0000 响应状态通用码

    private String msg; // 响应信息

    private String desc; // 响应友好描述信息

    private T data; // 执行成功后返回的结果

    public GeneralResponse() {}

    private GeneralResponse(T data) {
        this.data = data;
    }

    private GeneralResponse(String code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 成功返回
     * 
     * @param data
     * @return
     */
    public static <T> GeneralResponse<T> success(T data) {
        return new GeneralResponse<T>(data);
    }

    /**
     * 失败返回
     * 
     * @param code
     * @param msg
     * @param desc
     * @return
     */
    public static <T> GeneralResponse<T> fail(String code, String msg, String desc) {
        return new GeneralResponse<T>(code, msg, desc);
    }

    /**
     * 失败返回,带参数的错误码
     * 
     * @param code
     * @param msg
     * @param desc
     * @param descArgs
     * @return
     */
    public static <T> GeneralResponse<T> failWithPrams(String code, String msg, String desc, Object... descArgs) {
        return new GeneralResponse<T>(code, msg, String.format(desc, descArgs));
    }

}

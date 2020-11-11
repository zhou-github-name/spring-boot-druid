package com.springboot.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("aia_city")
public class City extends Model<City> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1085448435425389595L;

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 所在城市代码
	 */
	private String cityCode;

	/**
	 * 所在城市
	 */
	private String cityName;

	/**
	 * 所属分公司
	 */
	private String coCode;
	
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date crtDttm = new Date();

	/**
	 * 创建人
	 */
	private String crtUser;

	/**
	 * 数据是否有效
	 */
	private Boolean enableFlag = Boolean.TRUE;

	/**
	 * 最后更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date lastUpdateDttm = new Date();

	/**
	 * 最后更新人员
	 */
	private String lastUpdateUser;
	
	public City() {
		super();
	}

	public City(String cityCode) {
		super();
		this.cityCode = cityCode;
	}

	public City(String cityCode, String cityName, String coCode) {
		super();
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.coCode = coCode;
	}

	@Override
	protected Serializable pkVal() {
		return null;
	}

}

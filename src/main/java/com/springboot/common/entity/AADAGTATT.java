package com.springboot.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "AADAGTATT")
public class AADAGTATT extends Model<AADAGTATT> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5237828632691759577L;
	private String AGTCODE;
	private String ECONTRACT;
	private Date REVIEVE;

	private String FILLER1;
	private String FILLER2;
	private String FILLER3;
	private String FILLER4;
	private String FILLER5;
	private String FILLER6;
	private String FILLER7;
	private String FILLER8;
	private String FILLER9;
	private String FILLER10;

	@Override
	protected Serializable pkVal() {
		return null;
	}

}

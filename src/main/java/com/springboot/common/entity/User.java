package com.springboot.common.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * 后台管理用户表
 *
 * @author 
 * @version 1.0
 * @since 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName(value = "t_user")
public class User extends Model<User> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3687617998589175140L;
	
	/**
     * 主键ID
     */
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**
     * 账号
     */
    private String username;
    /**
     * 名字
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * md5密码盐
     */
    private String salt;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 备注
     */
    private String tips;
    /**
     * 状态 1:正常 2:禁用
     */
    private Integer state;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

	public User(String username, String salt) {
		super();
		this.username = username;
		this.salt = salt;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", password=" + password + ", salt="
				+ salt + ", phone=" + phone + ", tips=" + tips + ", state=" + state + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + "]";
	}
    
}
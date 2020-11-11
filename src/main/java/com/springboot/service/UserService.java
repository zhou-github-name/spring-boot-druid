package com.springboot.service;

import com.springboot.common.entity.User;
import com.springboot.datasource.annotion.DataSourceMore;
import com.springboot.datasource.mutidatesource.DSEnum;

/**
 * 后台用户管理
 */

public interface UserService {

    /**
     * 通过ID查找用户
     * @param id
     * @return
     */
    public User findById(Integer id);

    /**
     * 通过ID查找用户
     * @param id
     * @return
     */
    @DataSourceMore(name = DSEnum.DATA_SOURCE_SCOTT)
    public User findById1(Integer id);

    /**
     * 新增用户
     * @param user
     */
    public void insertUser(User user);

    /**
     * 修改用户
     * @param user
     */
    public void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(Integer id);

}

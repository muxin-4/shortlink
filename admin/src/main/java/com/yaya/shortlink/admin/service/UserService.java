package com.yaya.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yaya.shortlink.admin.dao.entity.UserDO;
import com.yaya.shortlink.admin.dto.resp.UserRespDTO;

/**
 * 用户接口层
 */
public interface UserService extends IService<UserDO> {
    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户返回实体
     */
    UserRespDTO getUserByUserName(String username);

    /**
     * 查询用户名是否存在
     * @param username 用户名
     * @return 用户名存在返回 True，不存在返回 false
     */
    Boolean hasUserName(String username);
}

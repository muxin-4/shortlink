package com.yaya.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaya.shortlink.admin.common.convention.exception.ClientException;
import com.yaya.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.yaya.shortlink.admin.dao.entity.UserDO;
import com.yaya.shortlink.admin.dao.mapper.UserMapper;
import com.yaya.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.yaya.shortlink.admin.dto.resp.UserRespDTO;
import com.yaya.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.yaya.shortlink.admin.common.enums.UserErrorCodeEnum.USER_NAME_EXIST;
import static com.yaya.shortlink.admin.common.enums.UserErrorCodeEnum.USER_SAVE_ERROR;

/**
 * 用户接口实现层
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

    @Override
    public UserRespDTO getUserByUserName(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean hasUserName(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if(!hasUserName(requestParam.getUsername())) {
            throw new ClientException(USER_NAME_EXIST);
        }
        int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
        if(inserted < 1) {
            throw new ClientException(USER_SAVE_ERROR);
        }
        userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
    }
}

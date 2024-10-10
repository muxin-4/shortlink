package com.yaya.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yaya.shortlink.project.dao.entity.ShortLinkDO;
import com.yaya.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.yaya.shortlink.project.dto.res.ShortLinkCreateRespDTO;

/**
 * 短连接接口层
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     *
     * @param requestParams 创建短链接请求参数
     * @return 短链接创建信息
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParams);
}

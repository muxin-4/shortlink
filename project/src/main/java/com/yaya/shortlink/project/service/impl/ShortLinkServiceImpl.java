package com.yaya.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaya.shortlink.project.dao.entity.ShortLinkDO;
import com.yaya.shortlink.project.dao.mapper.ShortLinkMapper;
import com.yaya.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.yaya.shortlink.project.dto.res.ShortLinkCreateRespDTO;
import com.yaya.shortlink.project.service.ShortLinkService;
import com.yaya.shortlink.project.toolkit.HashUtil;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 短连接接口实现层
 */
@Slf4j
@Service
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParams) {
        String shortLinkSuffix = generateSuffix(requestParams);
        ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParams, ShortLinkDO.class);
        shortLinkDO.setFullShortUrl(requestParams.getDomain() + "/" + shortLinkSuffix);
        baseMapper.insert(shortLinkDO);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(requestParams.getOriginUrl())
                .gid(requestParams.getGid())
                .build();
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParams) {
        String originUrl = requestParams.getOriginUrl();
        return HashUtil.hashToBase62(originUrl);
    }
}

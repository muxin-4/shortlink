package com.yaya.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaya.shortlink.project.convention.exception.ServiceException;
import com.yaya.shortlink.project.dao.entity.ShortLinkDO;
import com.yaya.shortlink.project.dao.mapper.ShortLinkMapper;
import com.yaya.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.yaya.shortlink.project.dto.res.ShortLinkCreateRespDTO;
import com.yaya.shortlink.project.service.ShortLinkService;
import com.yaya.shortlink.project.toolkit.HashUtil;
import groovy.util.logging.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * 短连接接口实现层
 */
@Slf4j
@Service
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;

    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParams) {
        String shortLinkSuffix = generateSuffix(requestParams);
        String fullShortUrl = requestParams.getDomain() + "/" + shortLinkSuffix);
        ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParams, ShortLinkDO.class);
        shortLinkDO.setShortUri(shortLinkSuffix);
        shortLinkDO.setEnableStatus(0);
        shortLinkDO.setFullShortUrl(requestParams.getDomain() + "/" + shortLinkSuffix);
        try{
            baseMapper.insert(shortLinkDO);
        }catch (DuplicateKeyException e) {
            log.warn("短链接：{} 重复入库");
        }
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(requestParams.getOriginUrl())
                .gid(requestParams.getGid())
                .build();
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParams) {
        int customGenerateCount = 0;
        String shortUri;
        while (true) {
            if (customGenerateCount > 10) {
                throw new ServiceException("短连接频繁生成，请稍后再试");
            }
            String originUrl = requestParams.getOriginUrl();
            shortUri = HashUtil.hashToBase62(originUrl);
            if (!shortUriCreateCachePenetrationBloomFilter.contains(requestParams.getDomain() + "/" + shortUri)) {
                break;
            }
//            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
//                    .eq(ShortLinkDO::getShortUri, requestParams.getDomain() + "/" + shortUri);
//            ShortLinkDO shortLinkDO = baseMapper.selectOne(queryWrapper);
//            if (shortLinkDO == null) {
//                break;
//            }
            customGenerateCount++;
        }
        return shortUri;
    }
}

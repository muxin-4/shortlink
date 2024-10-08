package com.yaya.shortlink.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaya.shortlink.project.dao.entity.ShortLinkDO;
import com.yaya.shortlink.project.dao.mapper.ShortLinkMapper;
import com.yaya.shortlink.project.service.ShortLinkService;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 短连接接口实现层
 */
@Slf4j
@Service
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {
}

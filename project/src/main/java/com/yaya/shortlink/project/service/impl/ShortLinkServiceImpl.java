package com.yaya.shorlink.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaya.shorlink.project.dao.entity.ShortLinkDO;
import com.yaya.shorlink.project.dao.mapper.ShortLinkMapper;
import com.yaya.shorlink.project.service.ShortLinkService;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 短连接接口实现层
 */
@Slf4j
@Service
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {
}

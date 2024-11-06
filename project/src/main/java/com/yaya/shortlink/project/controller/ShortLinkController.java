package com.yaya.shortlink.project.controller;

import com.yaya.shortlink.project.common.convention.result.Result;
import com.yaya.shortlink.project.common.convention.result.Results;
import com.yaya.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.yaya.shortlink.project.dto.res.ShortLinkCreateRespDTO;
import com.yaya.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短连接控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * 创建短链接
     *
     * @return
     */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParams) {
        return Results.success(shortLinkService.createShortLink(requestParams));
    }
}

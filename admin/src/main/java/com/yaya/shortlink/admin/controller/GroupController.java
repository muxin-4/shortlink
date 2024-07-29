package com.yaya.shortlink.admin.controller;

import com.yaya.shortlink.admin.common.convention.result.Result;
import com.yaya.shortlink.admin.common.convention.result.Results;
import com.yaya.shortlink.admin.dto.req.ShortLInkGroupSaveReqDto;
import com.yaya.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接分组控制层
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/api/short-link/v1/group")
    public Result<Void> save(@RequestBody ShortLInkGroupSaveReqDto requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }
}

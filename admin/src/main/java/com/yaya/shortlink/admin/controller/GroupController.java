package com.yaya.shortlink.admin.controller;

import com.yaya.shortlink.admin.common.convention.result.Result;
import com.yaya.shortlink.admin.common.convention.result.Results;
import com.yaya.shortlink.admin.dto.req.ShortLInkGroupSaveReqDto;
import com.yaya.shortlink.admin.dto.resp.ShortLInkGroupRespDTO;
import com.yaya.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 短链接分组控制层
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 新增短链接分组
     */
    @PostMapping("/api/short-link/v1/group")
    public Result<Void> save(@RequestBody ShortLInkGroupSaveReqDto requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    @GetMapping("/api/short-link/v1/group")
    public Result<List<ShortLInkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }
}

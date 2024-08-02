package com.yaya.shortlink.admin.controller;

import com.yaya.shortlink.admin.common.convention.result.Result;
import com.yaya.shortlink.admin.common.convention.result.Results;
import com.yaya.shortlink.admin.dto.req.ShortLinkGroupSaveReqDto;
import com.yaya.shortlink.admin.dto.req.ShortLinkGroupSortReqDto;
import com.yaya.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDto;
import com.yaya.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.yaya.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDto requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    /**
     * 查询短链接分组集合
     */
    @GetMapping("/api/short-link/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    /**
     * 修改短链接分组名称
     */
    @PutMapping("/api/short-link/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDto requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    /**
     * 删除短链接分组名称
     */
    @DeleteMapping("/api/short-link/v1/group")
    public Result<Void> updateGroup(@RequestParam String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }

    /**
     * 排序短链接分组
     */
    @PostMapping("/api/short-link/v1/group/sort")
    public Result<Void> updateGroup(@RequestBody List<ShortLinkGroupSortReqDto> requestParam) {
        groupService.sortGroup(requestParam);
        return Results.success();
    }
}

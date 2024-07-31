package com.yaya.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yaya.shortlink.admin.dao.entity.GroupDO;
import com.yaya.shortlink.admin.dto.req.ShortLInkGroupUpdateReqDto;
import com.yaya.shortlink.admin.dto.resp.ShortLInkGroupRespDTO;

import java.util.List;

/**
 * 短链接分组接口层
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * 新增短链接分组
     *
     * @param groupName 短链接分组名
     */
    void saveGroup(String groupName);

    /**
     * 查询用户短链接分组集合
     *
     * @return 用户短链接分组集合
     */
    List<ShortLInkGroupRespDTO> listGroup();

    /**
     * 修改短链接分组
     *
     * @param requestParam 短链接分组参数
     */
    void updateGroup(ShortLInkGroupUpdateReqDto requestParam);
}

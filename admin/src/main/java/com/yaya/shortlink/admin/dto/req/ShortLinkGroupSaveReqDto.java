package com.yaya.shortlink.admin.dto.req;

import lombok.Data;

/**
 * 短链接分组创建参数
 */
@Data
public class ShortLinkGroupSaveReqDto {
    /**
     * 分组名
     */
    private String name;

    /**
     * 排序
     */
    private Integer sortOrder;
}

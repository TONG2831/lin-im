package com.xyz.im.domain.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页返回体
 *
 * @author xyz
 * @date 2019-09-26
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBack<T> {

    /**
     * 内容
     */
    private List<T> resultList;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 总记录数
     */
    private Integer total;

}

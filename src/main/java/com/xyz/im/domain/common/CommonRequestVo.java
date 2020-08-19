package com.xyz.im.domain.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xyz
 * @date 2019-09-27
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonRequestVo {

    /**
     * id
     */
    private Integer id;
}

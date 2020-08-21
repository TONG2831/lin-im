package com.xyz.im.service.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 联系人
 *
 * @author xyz
 * @date 2020/8/21
 */
@Data
@ApiModel
public class ContactDto {

    @ApiModelProperty("联系人类型 0:好友 1:群")
    private int type;

    @ApiModelProperty("联系人的id 好友用户id 群id")
    private long contactId;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("好友昵称/群名称")
    private String name;

}

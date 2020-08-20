package com.xyz.im.service.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel
public class LoginResultDto {

    @ApiModelProperty("用户id")
    private long uid;

}

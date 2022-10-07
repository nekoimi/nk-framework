package com.sakuraio.nk.core.protocol;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * <p>BaseRequest</p>
 *
 * @author nekoimi 2022/10/03
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class BaseRequest<T extends Serializable> implements Serializable {
    /**
     * <p>分页参数，页码：默认第一页</p>
     */
    @ApiModelProperty("分页参数，页码：默认第一页")
    private Integer page = 1;

    /**
     * <p>分页参数，每页显示数量：默认显示10条</p>
     */
    @ApiModelProperty("分页参数，每页显示数量：默认显示10条")
    private Integer pageSize = 10;

    /**
     * <p>请求参数</p>
     */
    @ApiModelProperty("请求参数")
    private T param;
}

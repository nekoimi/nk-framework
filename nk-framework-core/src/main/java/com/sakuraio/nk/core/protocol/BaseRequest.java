package com.sakuraio.nk.core.protocol;

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
public class BaseRequest<T> implements Serializable {
    private Integer page;
    private Integer pageSize;
    private T param;
}

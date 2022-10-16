package com.sakuraio.nk.auth.api.vo;

import com.sakuraio.nk.auth.api.contract.JwtSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>AccessResponseVO</p>
 * <p>
 *
 * @author nekoimi 2022/10/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class AccessResponseVO implements Serializable {
    /**
     * <p>是否需要刷新</p>
     */
    private Boolean needRefresh;

    /**
     * <p>accessToken</p>
     */
    private String accessToken;

    /**
     * <p>认证对象</p>
     */
    private JwtSubject subject;
}

package com.sakuraio.nk.auth.client.feign;

import com.sakuraio.nk.auth.api.vo.AccessResponseVO;
import com.sakuraio.nk.feign.AbstractFeignFallback;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>AuthFeignFallback</p>
 *
 * @author nekoimi 2022/10/04
 */
@Slf4j
public class AuthFeignFallback extends AbstractFeignFallback<AccessResponseVO> {

    @Override
    protected AccessResponseVO createFallback() {
        log.error("auth feign fallback...");
        return null;
    }
}

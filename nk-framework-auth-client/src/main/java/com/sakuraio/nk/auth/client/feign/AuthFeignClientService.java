package com.sakuraio.nk.auth.client.feign;

import com.sakuraio.nk.auth.api.service.AuthRemoteService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>AuthFeignClientService</p>
 *
 * @author nekoimi 2022/10/16
 */
@FeignClient(
        name = "${nk.auth.feign.name:nk-auth-provider}",
        fallback = AuthFeignFallback.class
)
public interface AuthFeignClientService extends AuthRemoteService {
}

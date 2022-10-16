package com.sakuraio.nk.auth.api.service;

import com.sakuraio.nk.auth.api.vo.AccessResponseVO;
import com.sakuraio.nk.core.protocol.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>AuthRemoteService</p>
 * <p>
 * 认证服务接口
 * auth-server：本地实现
 * auth-client：rpc远程实现
 *
 * @author nekoimi 2022/10/16
 */
public interface AuthRemoteService {

    /**
     * <p>使用token获取认证信息</p>
     *
     * @param token 请求参数token值
     * @return
     */
    @PostMapping("/api/auth/authorization")
    BaseResponse<AccessResponseVO> authorization(String token);
}

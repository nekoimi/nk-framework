package com.sakuraio.nk.file.client.feign;

import com.sakuraio.nk.file.api.service.FileRemoteService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>FileFeignClientService</p>
 *
 * @author nekoimi 2022/10/04
 */
@FeignClient(
        name = "${nk.file.feign.name:nk-file-provider}",
        fallback = FileFeignFallback.class
)
public interface FileFeignClientService extends FileRemoteService {
}

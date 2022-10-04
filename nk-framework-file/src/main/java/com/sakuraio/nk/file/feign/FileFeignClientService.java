package com.sakuraio.nk.file.feign;

import com.sakuraio.nk.file.service.FileRemoteService;
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

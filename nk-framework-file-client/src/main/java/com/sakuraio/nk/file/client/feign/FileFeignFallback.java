package com.sakuraio.nk.file.client.feign;

import com.sakuraio.nk.feign.AbstractFeignFallback;
import com.sakuraio.nk.file.api.vo.FileInfo;

/**
 * <p>FileFeignFallback</p>
 *
 * @author nekoimi 2022/10/04
 */
public class FileFeignFallback extends AbstractFeignFallback<FileInfo> {

    @Override
    protected FileInfo createFallback() {
        return null;
    }
}

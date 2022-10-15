package com.sakuraio.test.controller;

import com.sakuraio.nk.core.protocol.BaseResponse;
import com.sakuraio.nk.file.api.service.FileRemoteService;
import com.sakuraio.nk.file.api.vo.FileInfo;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>FileController</p>
 *
 * @author nekoimi 2022/10/04
 */
@RestController
public class FileController implements FileRemoteService {

    @Override
    public BaseResponse<FileInfo> save() {
        return BaseResponse.ok();
    }
}

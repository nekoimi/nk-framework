package com.sakuraio.nk.provider.file.controller;

import com.sakuraio.nk.core.protocol.BaseResponse;
import com.sakuraio.nk.error.Errors;
import com.sakuraio.nk.error.exception.BizException;
import com.sakuraio.nk.file.service.FileRemoteService;
import com.sakuraio.nk.file.vo.FileInfo;
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
        throw new BizException(Errors.SERVER_ERROR);
    }
}

package com.sakuraio.nk.file.api.service;

import com.sakuraio.nk.core.protocol.BaseResponse;
import com.sakuraio.nk.file.api.vo.FileInfo;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>FileRemoteService</p>
 *
 * @author nekoimi 2022/10/04
 */
public interface FileRemoteService {

    @RequestMapping("/nk-file/file/save")
    BaseResponse<FileInfo> save();
}

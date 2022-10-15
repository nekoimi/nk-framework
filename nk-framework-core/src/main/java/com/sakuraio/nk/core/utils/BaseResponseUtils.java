package com.sakuraio.nk.core.utils;

import com.sakuraio.nk.core.error.exception.BaseResponseException;
import com.sakuraio.nk.core.protocol.BaseResponse;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>BaseResponseUtils</p>
 *
 * @author nekoimi 2022/10/15
 */
public class BaseResponseUtils {

    private BaseResponseUtils() {
    }

    /**
     * <p>获取响应值</p>
     *
     * @param baseResponse
     * @param <T>
     * @return
     */
    public static <T extends Serializable> T resolveResponse(BaseResponse<T> baseResponse) {
        if (baseResponse == null) {
            return null;
        }
        if (!Objects.equals(BaseResponse.OK_CODE, baseResponse.getCode())) {
            throw new BaseResponseException(baseResponse.getCode(), baseResponse.getMessage(), baseResponse.getTrace());
        }
        return baseResponse.getData();
    }

    /**
     * <p>获取响应值，检查空值</p>
     *
     * @param baseResponse
     * @param <T>
     * @return
     */
    public static <T extends Serializable> T resolveResponseNonNull(BaseResponse<T> baseResponse) {
        T response = resolveResponse(baseResponse);
        if (response == null) {
            throw new BaseResponseException("baseResponse值为空");
        }
        return response;
    }
}

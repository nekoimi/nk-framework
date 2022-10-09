package com.sakuraio.nk.feign;

import com.sakuraio.nk.constants.Headers;
import feign.FeignException;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Collection;

/**
 * <p>AbstractFeignFallback</p>
 *
 * @author nekoimi 2022/10/04
 */
public abstract class AbstractFeignFallback<T> implements FallbackFactory<T> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractFeignFallback.class);

    protected AbstractFeignFallback() {
    }

    @Override
    public T create(Throwable cause) {
        if (cause instanceof FeignException) {
            FeignException fe = (FeignException) cause;
            StringBuilder errorLog = new StringBuilder("feign exception => ");
            if (fe.hasRequest()) {
                errorLog.append("method: ").append(fe.request().httpMethod().name()).append(";");
                errorLog.append("url: ").append(fe.request().url()).append(";");
                Collection<String> traceIdCollection = fe.request().headers().get(Headers.TRACE_ID);
                if (CollectionUtils.isNotEmpty(traceIdCollection)) {
                    errorLog.append("traceId: ").append(
                            traceIdCollection.stream().findFirst().orElse("")
                    ).append(";");
                }
            }
            errorLog.append("code: ").append(fe.status()).append(";");
            errorLog.append("message: ").append(fe.contentUTF8()).append(";");
            String errorLogString = errorLog.toString();
            logger.error(errorLogString);
        }

        return this.createFallback();
    }

    protected abstract T createFallback();
}

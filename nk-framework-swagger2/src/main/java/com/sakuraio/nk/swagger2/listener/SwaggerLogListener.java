package com.sakuraio.nk.swagger2.listener;

import com.sakuraio.nk.core.utils.SpringPropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>SwaggerLogListener</p>
 *
 * @author nekoimi 2022/10/07
 */
@Slf4j
@Component
public class SwaggerLogListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("[{}] swagger url http://{}:{}/doc.html",
                SpringPropertyUtils.applicationName(),
                SpringPropertyUtils.serverHost(),
                SpringPropertyUtils.serverPort()
        );
    }
}

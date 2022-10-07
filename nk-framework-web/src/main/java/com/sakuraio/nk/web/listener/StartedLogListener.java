package com.sakuraio.nk.web.listener;

import com.sakuraio.nk.core.utils.SpringPropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * <p>ApplicationStartedListener</p>
 *
 * @author nekoimi 2022/10/06
 */
@Slf4j
public class StartedLogListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("[{}] is running on http://{}:{}, {} seconds",
                SpringPropertyUtils.applicationName(),
                SpringPropertyUtils.serverHost(),
                SpringPropertyUtils.serverPort(),
                event.getTimeTaken().getSeconds()
        );
    }
}

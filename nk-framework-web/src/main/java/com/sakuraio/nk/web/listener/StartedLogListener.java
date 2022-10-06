package com.sakuraio.nk.web.listener;

import com.sakuraio.nk.web.utils.SpringPropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>ApplicationStartedListener</p>
 *
 * @author nekoimi 2022/10/06
 */
@Slf4j
@Component
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

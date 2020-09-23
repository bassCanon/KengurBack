package ba.com.kengur;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class KengurBackLauncher implements Daemon {

    private static final Logger LOGGER = LoggerFactory.getLogger(KengurBackLauncher.class);

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init(final DaemonContext context) throws Exception {
        try {
            LOGGER.info("Initializing application");
            applicationContext = SpringApplication.run(ApplicationConfiguration.class, new String[] {});
        } catch (final Exception e) {
            LOGGER.error("init => {}", e);
            if (context != null) {
                context.getController().shutdown();
            }
            return;
        }
    }

    @Override
    public void start() throws Exception {
        LOGGER.info("Application started successfully");
    }

    @Override
    public void stop() throws Exception {
        applicationContext.stop();
    }

    @Override
    public void destroy() {
        LOGGER.info("Destroying application");
    }

    public static void main(final String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, new String[] {});
    }

}
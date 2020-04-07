package com.codr.aws.config;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generally JDBI instances are thread-safe, it can be used as single instance throughout the context.
 * The object holds common configuration. It doesn't create any connection.
 * User withHandle/useHandle method to make DB calls it manages connections, we don't need to worry.
 */
public class JdbiDataSource {

    private static Jdbi jdbi;
    private static final Logger logger = LoggerFactory.getLogger(JdbiDataSource.class);
    private static final String ERROR_MSG = "Failed to create JDBI instance";


    static {
        synchronized (JdbiDataSource.class) {
            try {
                logger.debug("DataSource configuration has been loaded from properties Dynamo DB");
                jdbi = Jdbi.create(
                        DBCredentials.getConnUrl(),
                        DBCredentials.getUserName(),
                        DBCredentials.getPassword()
                );
            } catch (Exception e) {
                logger.error(ERROR_MSG, e);
            }
        }
    }
    public static Jdbi get() {
        return jdbi;
    }
}

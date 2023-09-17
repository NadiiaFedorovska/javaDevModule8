package org.example;

import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;

import static org.example.Config.*;

public class MigrationFW {
    private static final Logger logger = Logger.getLogger(MigrationFW.class);
     public void migrationFlyway() {
        logger.debug("Flyway migration execute");

        Flyway.configure()
                .dataSource(jdbcUrl, userName, password)
                .locations("classpath:flyway/scripts")
                .load()
                .migrate();
    }
}

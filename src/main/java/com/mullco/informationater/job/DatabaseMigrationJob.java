package com.mullco.informationater.job;

import com.mullco.informationater.jira.WorkItem;
import org.flywaydb.core.Flyway;

import java.util.List;

public class DatabaseMigrationJob implements Job {

    private final String url;
    private final String user;
    private final String password;

    public DatabaseMigrationJob(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getName() {
        return "database-migration";
    }

    public int execute(List<WorkItem> workItems) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, user, password);
        flyway.migrate();

        return 0;
    }
}

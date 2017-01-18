package com.mullco.informationater;

import com.mullco.informationater.email.EmailSender;
import com.mullco.informationater.email.EmailTemplateGenerator;
import com.mullco.informationater.job.*;
import com.mullco.informationater.ppt.PowerPointMaker;
import com.mullco.informationater.configuration.Configuration;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class JobMaker {

    public List<Job> getJobs(Configuration config) {
        List<Job> jobs = new ArrayList<>();
//        jobs.add(getDatabaseMigrationJob(config));
        jobs.add(getPowerPointJob());
//        jobs.add(getPersistWorkJob(config));
//        jobs.addAll(getEmailJobs(config));

        return jobs;
    }

    private List<Job> getEmailJobs(Configuration env) {
        EmailSender emailSender = new EmailSender(env.getEmailHost(), env.getEmailUid(), env.getEmailPwd());
        EmailTemplateGenerator templateGenerator = new EmailTemplateGenerator(env.getJiraUrl());

        EmailMFSJob mfsJob = new EmailMFSJob(emailSender, templateGenerator);
        EmailNotMFSJob notMFSJob = new EmailNotMFSJob(emailSender, templateGenerator);

        return asList(mfsJob);
    }

    private PowerPointJob getPowerPointJob() {
        PowerPointMaker powerPointMaker = new PowerPointMaker();
        return new PowerPointJob(powerPointMaker);
    }

    private DatabaseMigrationJob getDatabaseMigrationJob(Configuration config) {
        return new DatabaseMigrationJob(config.getDbUrl(), config.getDbUser(), config.getDbPwd());
    }

    private PersistWorkJob getPersistWorkJob(Configuration config) {
        Sql2o sql2o = new Sql2o(config.getDbUrl(), config.getDbUser(), config.getDbPwd());

        return new PersistWorkJob(sql2o);
    }

}

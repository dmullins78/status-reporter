package com.mullco.informationater;

import com.mullco.informationater.email.EmailSender;
import com.mullco.informationater.email.EmailTemplateGenerator;
import com.mullco.informationater.job.EmailMFSJob;
import com.mullco.informationater.job.EmailNotMFSJob;
import com.mullco.informationater.job.Job;
import com.mullco.informationater.job.PowerPointJob;
import com.mullco.informationater.ppt.PowerPointMaker;
import com.mullco.informationater.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class JobMaker {

    public List<Job> getJobs(Configuration config) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(getPowerPointJob());
//        jobs.addAll(getEmailJobs(config));

        return jobs;
    }

    private List<Job> getEmailJobs(Configuration env) {
        EmailSender emailSender = new EmailSender(env.getEmailHost(), env.getEmailUid(), env.getEmailPwd());
        EmailTemplateGenerator templateGenerator = new EmailTemplateGenerator();

        return asList(new EmailMFSJob(emailSender, templateGenerator), new EmailNotMFSJob(emailSender, templateGenerator));
    }

    private PowerPointJob getPowerPointJob() {
        PowerPointMaker powerPointMaker = new PowerPointMaker();
        return new PowerPointJob(powerPointMaker);
    }

}

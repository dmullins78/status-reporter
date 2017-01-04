package com.mullco.informationater;

import com.mullco.informationater.jira.JiraAdapter;
import com.mullco.informationater.jira.WorkItem;
import com.mullco.informationater.job.Job;
import com.mullco.informationater.configuration.Configuration;

import java.util.List;

import static com.mullco.informationater.configuration.ConfigurationLoader.configs;

public class Application {

    public static void main(String[] args) {
        Configuration configs = configs();

        JiraAdapter adapter = new JiraAdapter(configs.getJiraUrl(), configs.getJiraUid(), configs.getJiraPwd());
        List<WorkItem> workItems = adapter.getStuff();

        List<Job> jobs = new JobMaker().getJobs(configs);
        for (Job job : jobs) {
            System.out.println("Executing job " + job.getName());

            job.execute(workItems);
        }
    }

}

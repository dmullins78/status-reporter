package com.mullco.informationater;

import com.mullco.informationater.emails.EmailSender;
import com.mullco.informationater.emails.EmailTemplateGenerator;
import com.mullco.informationater.jira.JiraAdapter;
import com.mullco.informationater.jira.WorkItem;
import com.mullco.informationater.models.MonthlyStats;
import com.mullco.informationater.ppt.PowerPointMaker;
import com.mullco.informationater.utils.Environment;
import com.mullco.informationater.utils.EnvironmentLoader;
import com.mullco.informationater.work.WorkItemSorter;

import java.util.List;

public class Application {

    private final EmailSender emailSender;
    private final JiraAdapter adapter;
    private final WorkItemSorter workItemSorter;
    private final PowerPointMaker powerPointMaker;
    private final EmailTemplateGenerator templateGenerator;

    public Application() {
        Environment env = EnvironmentLoader.startMeUp();

        adapter = new JiraAdapter(env.getJiraUrl(), env.getJiraUid(), env.getJiraPwd());
        emailSender = new EmailSender(env.getEmailHost(), env.getEmailUid(), env.getEmailPwd());

        workItemSorter = new WorkItemSorter();
        powerPointMaker = new PowerPointMaker();
        templateGenerator = new EmailTemplateGenerator();
    }

    public void doIt() {
        List<WorkItem> stuff = adapter.getStuff();

        MonthlyStats monthlyStats = workItemSorter.sortWorkItems(stuff);

        powerPointMaker.makeStuff(monthlyStats, "hslf-table-2.ppt");

        String richTemplate = templateGenerator.getRichTemplate(monthlyStats);
        emailSender.sendIt(richTemplate);
    }
}

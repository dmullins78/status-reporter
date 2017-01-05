package com.mullco.informationater.job;

import com.mullco.informationater.email.EmailSender;
import com.mullco.informationater.email.EmailTemplateGenerator;
import com.mullco.informationater.jira.WorkItem;

import java.util.List;
import java.util.Map;

import static com.mullco.informationater.work.WorkItemFilter.getBacklog;
import static com.mullco.informationater.work.WorkItemFilter.getInProgressSignificantEfforts;
import static com.mullco.informationater.work.WorkItemFilter.groupWorkByProduct;

public class EmailMFSJob implements Job {

    private final EmailSender emailSender;
    private final EmailTemplateGenerator templateGenerator;

    public EmailMFSJob(EmailSender emailSender, EmailTemplateGenerator templateGenerator) {
        this.emailSender = emailSender;
        this.templateGenerator = templateGenerator;
    }

    public String getName() {
        return "emailing-MFS";
    }

    public int execute(List<WorkItem> workItems) {
        Map<String, List<WorkItem>> backlogByProduct = groupWorkByProduct("MFS", getBacklog(workItems));

        String message = templateGenerator.getEmailText(getInProgressSignificantEfforts(workItems), backlogByProduct);

        emailSender.sendIt(message);

        return 0;
    }
}

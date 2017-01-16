package com.mullco.informationater.job;

import com.mullco.informationater.email.EmailSender;
import com.mullco.informationater.email.EmailTemplateGenerator;
import com.mullco.informationater.jira.WorkItem;

import java.util.List;
import java.util.Map;

import static com.mullco.informationater.email.EmailDetails.getDigestEmailDefaults;
import static com.mullco.informationater.work.WorkItemFilter.*;

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
        List<WorkItem> ipItems = getInProgressItems(workItems);

        String message = templateGenerator.getEmailText(ipItems, backlogByProduct);

        Map<String, String> emailParams = getDigestEmailDefaults(message);
        emailSender.sendIt(emailParams);

        return 0;
    }

    private List<WorkItem> getInProgressItems(List<WorkItem> workItems) {
        List<WorkItem> ipSigEfforts = getInProgressSignificantEfforts(workItems);

        List<WorkItem> ipEnhancements = asSignificantEfforts(getInProgressEnhancements(workItems));
        ipSigEfforts.addAll(ipEnhancements);

        return ipSigEfforts;
    }

}

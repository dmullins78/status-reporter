package com.mullco.informationater.job;

import com.mullco.informationater.email.EmailSender;
import com.mullco.informationater.email.EmailTemplateGenerator;
import com.mullco.informationater.jira.WorkItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mullco.informationater.email.EmailDetails.getDigestEmailDefaults;
import static com.mullco.informationater.work.WorkItemFilter.*;

public class EmailNotMFSJob implements Job {
    private static final String[] NOT_MFS_MEMBER_AREAS = new String[]{"CID", "Marketing", "MPG", "Solutions"};
    private final EmailSender emailSender;
    private final EmailTemplateGenerator templateGenerator;

    public EmailNotMFSJob(EmailSender emailSender, EmailTemplateGenerator templateGenerator) {
        this.emailSender = emailSender;
        this.templateGenerator = templateGenerator;
    }

    public String getName() {
        return "emailing-not-MFS";
    }

    public int execute(List<WorkItem> workItems) {
        List<WorkItem> inProgress = getInProgressSignificantEfforts(workItems);
        Map<String, List<WorkItem>> backlogByArea = groupWorkByArea(getBacklog(workItems));

        for (String area : NOT_MFS_MEMBER_AREAS) {
            String message = templateGenerator.getEmailText(inProgress, asMap(backlogByArea, area));
            Map<String, String> emailParams = getDigestEmailDefaults(message);

            emailSender.sendIt(emailParams);
        }

        return 0;
    }

    private Map<String, List<WorkItem>> asMap(Map<String, List<WorkItem>> backlogByArea, String area) {
        Map<String, List<WorkItem>> backlogs = new HashMap<>();
        backlogs.put(area, backlogByArea.get(area));

        return backlogs;
    }
}

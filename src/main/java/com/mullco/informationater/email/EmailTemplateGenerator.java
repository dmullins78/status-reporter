package com.mullco.informationater.email;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.mullco.informationater.jira.WorkItem;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailTemplateGenerator {

    private final MustacheFactory mf;
    private String jiraUrl;

    public EmailTemplateGenerator(String jiraUrl) {
        this.jiraUrl = jiraUrl;
        mf = new DefaultMustacheFactory();
    }

    public String getEmailText(List<WorkItem> inProgress, Map<String, List<WorkItem>> backlog) {
        Mustache mustache = mf.compile("emails/weekly-update.mustache");

        return generate(inProgress, backlog, mustache);
    }

    private String generate(List<WorkItem> inProgress, Map<String, List<WorkItem>> backlog, Mustache mustache) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("jira", jiraUrl);
            params.put("inprogress", inProgress);
            params.put("backlog", backlog.entrySet());

            StringWriter stringWriter = new StringWriter();
            mustache.execute(stringWriter, params).flush();

            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

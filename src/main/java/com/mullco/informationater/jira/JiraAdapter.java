package com.mullco.informationater.jira;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Comment;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class JiraAdapter {

    private final String jiraUrl;
    private final String jiraUid;
    private final String jiraPwd;

    public JiraAdapter(String jiraUrl, String jiraUid, String jiraPwd) {
        this.jiraUrl = jiraUrl;
        this.jiraUid = jiraUid;
        this.jiraPwd = jiraPwd;
    }

    public List<WorkItem> getStuff() {
        BasicCredentials creds = new BasicCredentials(jiraUid, jiraPwd);
        JiraClient jira = new JiraClient(jiraUrl, creds);

        try {
            List<WorkItem> workItems = new ArrayList<>();

            Issue.SearchResult sr = jira.searchIssues("project=MEM", 100);
            for (Issue issue : sr.issues) {
                WorkItem workItem = makeWorkItem(issue);
                workItems.add(workItem);
            }

            return workItems;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private WorkItem makeWorkItem(Issue issue) {
        String description = null;
        String id = issue.getKey();
        String type = issue.getIssueType().getName();

        String summary = issue.getSummary();
        String people = null;
        if (type.equals("Epic")) {
            people = (String) issue.getField("customfield_10801");
            description = issue.getDescription();
        }

        String name = issue.getStatus().getName();
        boolean inProgress = name.equals("In Progress");
        boolean completed = name.equals("Done");

        Double priority = 99d;
        Object priorityO = issue.getField("customfield_15330");
        if (priorityO instanceof Double) {
            priority = (Double) priorityO;
        }

        String depValue = "";
        Object department = issue.getField("customfield_13602");
        if (department instanceof JSONObject) {
            depValue = "" + ((JSONObject)department).get("value");
        }

        String workScope = "";
        Object scope = issue.getField("customfield_15100");
        if (scope instanceof JSONObject) {
            workScope = "" + ((JSONObject)scope).get("value");
        }

        LocalDate completionDateValue = LocalDate.now().plusDays(1);
        Object completionDate = issue.getField("customfield_11300");
        if (completionDate instanceof String) {
            completionDateValue = parse((String)completionDate, ISO_LOCAL_DATE);
        }

        String productValue = "Other";

        Object productField = issue.getField("customfield_15101");
        if (productField instanceof JSONArray) {
            JSONArray products = (JSONArray) productField;
            productValue = products.getJSONObject(0).getString("value");
        }

        return new WorkItem(id, summary, depValue, productValue, completionDateValue, inProgress, type, description, people, priority, workScope, completed);
    }

}

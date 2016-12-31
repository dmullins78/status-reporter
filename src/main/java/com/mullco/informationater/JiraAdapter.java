package com.mullco.informationater;

import net.rcarz.jiraclient.BasicCredentials;
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

    public List<WorkItem> getStuff() {

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

        String description = issue.getDescription();
        String type = issue.getIssueType().getName();

        String summary = issue.getSummary();
        String people = null;
        if (type.equals("Epic")) {
            people = (String) issue.getField("customfield_10801");
        }

        String name = issue.getStatus().getName();
        boolean inProgress = name.equals("In Progress");

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

        return new WorkItem(issue.getId(), summary, depValue, productValue, completionDateValue, inProgress, type, description, people, priority);
    }

}

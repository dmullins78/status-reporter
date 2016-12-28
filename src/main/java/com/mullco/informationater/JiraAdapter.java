package com.mullco.informationater;

import net.rcarz.jiraclient.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JiraAdapter {

    public List<WorkItem> getStuff() {
        BasicCredentials creds = new BasicCredentials("", "");
        JiraClient jira = new JiraClient("", creds);

        try {
            ArrayList<WorkItem> workItems = new ArrayList<WorkItem>();

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
        String summary = (String) issue.getField("summary");

        JSONObject department = (JSONObject) issue.getField("customfield_13602");
        String depValue = department.getString("value");

        String productValue = "Other";

        Object productField = issue.getField("customfield_15101");
        if (productField instanceof JSONArray) {
            JSONArray products = (JSONArray) productField;
            productValue = products.getJSONObject(0).getString("value");
        }

        return new WorkItem(issue.getId(), summary, depValue, productValue);
    }

}

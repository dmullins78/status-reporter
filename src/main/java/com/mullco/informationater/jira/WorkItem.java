package com.mullco.informationater.jira;

import java.time.LocalDate;

public class WorkItem {
    private final String id;
    private final String summary;
    private final String depValue;
    private final String productValue;
    private final LocalDate completionDate;
    private boolean inProgress;
    private String type;
    private String description;
    private String people;
    private Double priority;

    public WorkItem(String id, String summary, String depValue, String productValue, LocalDate completionDate, boolean inProgress, String type, String description, String people, Double priority) {
        this.id = id;
        this.summary = summary;
        this.depValue = depValue;
        this.productValue = productValue;
        this.completionDate = completionDate;
        this.inProgress = inProgress;
        this.type = type;
        this.description = description;
        this.people = people;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public String getDepValue() {
        return depValue;
    }

    public String getProductValue() {
        return productValue;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public boolean isEpic() {
        return type.equals("Epic");
    }

    public String getDescription() {
        return description;
    }

    public String getPeople() {
        return people;
    }

    public Double getPriority() {
        return priority;
    }

    public String getUrl() {
        return "https://fhlbdm.atlassian.net/browse/" + id;
    }
}

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
    private String scope;
    private boolean done;

    public WorkItem(String id, String summary, String depValue, String productValue, LocalDate completionDate, boolean inProgress, String type, String description, String people, Double priority, String scope, boolean done) {
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
        this.scope = scope;
        this.done = done;
    }

    public WorkItem(String depValue, String summary) {
        this(null, summary, depValue, null, null, false, null, null, null, 0., null, true);
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

    public boolean isMaintenance() {
        return scope.equals("Maintenance");
    }

    public boolean isDone() {
        return done;
    }
}

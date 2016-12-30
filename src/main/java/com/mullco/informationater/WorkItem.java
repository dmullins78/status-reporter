package com.mullco.informationater;

import java.time.LocalDate;
import java.util.List;

public class WorkItem {
    private final String id;
    private final String summary;
    private final String depValue;
    private final String productValue;
    private final List<String> people;
    private final LocalDate completionDate;
    private boolean inProgress;
    private String type;

    public WorkItem(String id, String summary, String depValue, String productValue, List<String> people, LocalDate completionDate, boolean inProgress, String type) {
        this.id = id;
        this.summary = summary;
        this.depValue = depValue;
        this.productValue = productValue;
        this.people = people;
        this.completionDate = completionDate;
        this.inProgress = inProgress;
        this.type = type;
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

    public List<String> getPeople() {
        return people;
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
}

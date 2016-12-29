package com.mullco.informationater;

import java.util.List;

public class WorkItem {
    private final String id;
    private final String summary;
    private final String depValue;
    private final String productValue;
    private final List<String> people;

    public WorkItem(String id, String summary, String depValue, String productValue, List<String> people) {
        this.id = id;
        this.summary = summary;
        this.depValue = depValue;
        this.productValue = productValue;
        this.people = people;
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

    @Override
    public String toString() {
        return "WorkItem{" +
                "id='" + id + '\'' +
                ", summary='" + summary + '\'' +
                ", depValue='" + depValue + '\'' +
                ", productValue='" + productValue + '\'' +
                '}';
    }
}

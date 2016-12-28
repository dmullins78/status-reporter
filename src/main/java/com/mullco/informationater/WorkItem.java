package com.mullco.informationater;

public class WorkItem {
    private final String id;
    private final String summary;
    private final String depValue;
    private final String productValue;

    public WorkItem(String id, String summary, String depValue, String productValue) {
        this.id = id;
        this.summary = summary;
        this.depValue = depValue;
        this.productValue = productValue;
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

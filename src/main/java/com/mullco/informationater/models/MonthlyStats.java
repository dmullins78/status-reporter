package com.mullco.informationater.models;

import com.mullco.informationater.jira.WorkItem;

import java.util.List;

public class MonthlyStats {

    List<WorkItem> completed;
    List<WorkItem> inProgress;
    List<WorkItem> backlog;

    public MonthlyStats(List<WorkItem> completed, List<WorkItem> inProgress, List<WorkItem> backlog) {
        this.completed = completed;
        this.inProgress = inProgress;
        this.backlog = backlog;
    }

    public List<WorkItem> getCompleted() {
        return completed;
    }

    public List<WorkItem> getInProgress() {
        return inProgress;
    }

    public List<WorkItem> getBacklog() {
        return backlog;
    }
}

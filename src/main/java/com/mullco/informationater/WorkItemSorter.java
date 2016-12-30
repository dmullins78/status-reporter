package com.mullco.informationater;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;

public class WorkItemSorter {

    public MonthlyStats sortWorkItems(List<WorkItem> workItems) {

        List<WorkItem> completed = workItems.stream().filter(t -> t.getCompletionDate().isBefore(now())).collect(toList());
        List<WorkItem> backlog = workItems.stream().filter(t -> !t.isInProgress() && t.getCompletionDate().isAfter(now())).collect(toList());
        List<WorkItem> inProgress = workItems.stream().filter(WorkItem::isEpic).collect(toList());

        return new MonthlyStats(completed, inProgress, backlog);
    }

}

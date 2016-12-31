package com.mullco.informationater;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;

public class WorkItemSorter {

    public MonthlyStats sortWorkItems(List<WorkItem> workItems) {
        List<WorkItem> inProgress = workItems.stream().filter(WorkItem::isEpic).collect(toList());

        List<WorkItem> completed = workItems.stream()
                .filter(t -> t.getCompletionDate().isBefore(now()))
                .sorted((o1, o2) -> o1.getDepValue().compareTo(o2.getDepValue())).collect(toList());


        List<WorkItem> backlog = workItems.stream()
                .filter(t -> !t.isInProgress() && t.getCompletionDate().isAfter(now()))
                .collect(toList());

        return new MonthlyStats(completed, inProgress, backlog);
    }

}
